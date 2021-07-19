/*
 *      Copyright (c) 2018-2028, Chill Zhuang All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the dreamlu.net developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: Chill 庄骞 (smallchill@163.com)
 */
package org.springblade.modules.project.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import org.springblade.common.cache.CacheNames;
import org.springblade.common.constant.CommonConstant;
import org.springblade.common.utils.CompareUtil;
import org.springblade.common.utils.StringCompare.IStringSimilarityService;
import org.springblade.common.utils.StringCompare.StringSimilarityFactory;
import org.springblade.common.utils.StringUtil;
import org.springblade.core.redis.cache.BladeRedis;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.support.Kv;
import org.springblade.modules.project.entity.Business;
import org.springblade.modules.project.entity.ChangeDetail;
import org.springblade.modules.project.entity.Clash;
import org.springblade.modules.project.service.IChangeService;
import org.springblade.modules.project.service.IClashService;
import org.springblade.modules.project.vo.BusinessVO;
import org.springblade.modules.project.mapper.BusinessMapper;
import org.springblade.modules.project.service.IBusinessService;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.modules.system.entity.DeptSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 服务实现类
 *
 * @author BladeX
 * @since 2021-07-03
 */
@Service
@AllArgsConstructor
public class BusinessServiceImpl extends BaseServiceImpl<BusinessMapper, Business> implements IBusinessService {

	private final BladeRedis bladeRedis;

	private final IClashService clashService;

	private final IChangeService changeService;


	@Autowired
	private StringSimilarityFactory stringCompareFactory;

	@Override
	public IPage<BusinessVO> selectBusinessPage(IPage<BusinessVO> page, BusinessVO business) {
		return page.setRecords(baseMapper.selectBusinessPage(page, business));
	}

	//region 冲突判断

	/**
	 * 判断冲突项目
	 *
	 * @param project
	 * @return
	 */
	private List<Clash> checkConflictProject(Business project) {
		BladeUser currUser = AuthUtil.getUser();

		//获取需要进行匹对判断冲突的列表
		LambdaQueryWrapper<Business> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(Business::getProCompany, currUser.getDetail().getStr(CommonConstant.PROF_COM_ID));
		queryWrapper.eq(Business::getProjectCatrgory, project.getProjectCatrgory());//同分类的商机

		if (!project.getId().equals("")) {
			queryWrapper.ne(Business::getId, project.getId());
		}

		if (project.getBiddingType().equals("直接委托")) {
			//已报备成功的直接委托项目
			queryWrapper.eq(Business::getBiddingType, project.getBiddingType());
			queryWrapper.eq(Business::getStatus, 1); //备案成功
		} else {
			//来源是公开招标和内部邀标，且备案成功、备案冲突 状态的未投标的已报备项目
			queryWrapper.ne(Business::getBiddingType, project.getBiddingType());
			queryWrapper.in(Business::getStatus, 1, -2);//备案成功或备案冲突
			//queryWrapper.inSql(Business::getId,"select ");//在投标表中，未发起任何流程的记录
		}

		List<Business> list = baseMapper.selectList(queryWrapper);
		List<Clash> sameList = new ArrayList<Clash>();

		//先筛选出项目名称100%相同的项目信息【项目名称全部转为半角进行判断】
		String sourceName = StringUtil.removeSpecialCharacter(StringUtil.ToDBC(project.getRecordName()));
		for (Business item : list) {
			String oldName = StringUtil.removeSpecialCharacter(StringUtil.ToDBC(item.getRecordName()));
			if (oldName.equals(sourceName)) {
				Clash c = new Clash();
				c.setClashBusinessId(item.getId());
				c.setProjectNameRate(100.00);
				c.setClientNameRate(0.0);
				c.setClashType(item.getBranchCompany() != project.getBranchCompany() ? 2 : 1);
				c.setCreateTime(LocalDateTime.now());
				c.setIsHandle(false);
				sameList.add(c);
			}
		}

		if (sameList.stream().count() > 0) {
			return sameList;
		} else { //不存项目名称100% 相同的项目时并且非直接委托的，进行另外一种判断方式

			List<Clash> cshList = new ArrayList<Clash>();

			String proComId = currUser.getDetail().getStr(CommonConstant.PROF_COM_ID);
			DeptSetting setting = bladeRedis.get(CacheNames.DEPTSETTING_KEY + proComId);

			Double rate = setting.getConflictOtherRate();
			Double pRate = setting.getConflictProjectnameRate();
			String needReplaceStr = setting.getConflictNeedReplace();
			String method = setting.getConflictMethod();

			if (rate == null || rate == 0.0) rate = 0.6;
			if (pRate == null || pRate == 0.0) pRate = 0.9;
			if (needReplaceStr == null) needReplaceStr = "";
			if (method == null || method.equals("")) method = "CXL";

			for (Business item : list) {
				//全部先将字符串转为半角后再进行对比，计算出来的结果四舍五入，只保留两位小数
				//先判断项目名称的冲突率，大于90%或以上
				Double checkRate = conflictJudgement(project.getRecordName(), item.getRecordName(), method);

				BigDecimal pn_decimal = new BigDecimal(checkRate);
				double final_pn_Rate = pn_decimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

				if (final_pn_Rate >= pRate) {
					Clash c1 = new Clash();
					c1.setClashBusinessId(item.getId());
					c1.setProjectNameRate(final_pn_Rate * 100);
					c1.setClientNameRate(0.0);
					c1.setClashType(item.getBranchCompany() != project.getBranchCompany() ? 2 : 1);
					c1.setCreateTime(LocalDateTime.now());
					c1.setIsHandle(false);
					cshList.add(c1);
				} else {  //当冲突列表中没有信息时,再去进行检测报备项目名称与所有被检查项目名称，最大相似度60%及以上，并且业主名称最大相似度60%及以上
					Double clientRate = conflictJudgement(StringUtil.replaceString(project.getClientName(), needReplaceStr), StringUtil.replaceString(item.getClientName(), needReplaceStr), method);

					BigDecimal cl_decimal = new BigDecimal(clientRate);
					double final_cl_Rate = cl_decimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

					if (final_pn_Rate >= rate && final_cl_Rate >= rate) {
						Clash c2 = new Clash();
						c2.setClashBusinessId(item.getId());
						c2.setProjectNameRate(final_pn_Rate * 100);
						c2.setClientNameRate(final_cl_Rate * 100);
						c2.setClashType(item.getBranchCompany() != project.getBranchCompany() ? 2 : 1);
						c2.setCreateTime(LocalDateTime.now());
						c2.setIsHandle(false);
						cshList.add(c2);
					}

				}
			}


			return cshList;
		}
	}

	/**
	 * 对比两个字符
	 *
	 * @param str1
	 * @param str2
	 * @param conflictType
	 * @return
	 */
	private Double conflictJudgement(String str1, String str2, String conflictType) {

		//构建渠道类型对应的服务类
		IStringSimilarityService compareService = stringCompareFactory.buildService(conflictType);
		//对比字符
		return compareService.stringCompare(str1, str2);

	}

	//endregion

	//region 对比实体的修改值

	/**
	 * 对比两个实体
	 *
	 * @param newEntity
	 * @return
	 */
	private List<ChangeDetail> differenceComparison(Business newEntity) {
		List<ChangeDetail> result = new ArrayList<>();

		if (newEntity.getId().equals(""))
			return result;


		Business oldEntity = baseMapper.selectById(newEntity.getId());

		List<Kv> diff = CompareUtil.compareEntityFields(oldEntity, newEntity);

		if (diff.stream().count() > 0) {
			result = JSON.parseObject(JSON.toJSONString(diff), List.class);
		}

		return result;
	}

	//endregion
}
