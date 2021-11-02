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
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.TaskService;
import org.springblade.common.cache.CacheNames;
import org.springblade.common.cache.UserCache;
import org.springblade.common.constant.CommonConstant;
import org.springblade.common.enums.BusinessFlowStatusEnum;
import org.springblade.common.enums.BusinessStatusEnum;
import org.springblade.common.utils.CompareUtil;
import org.springblade.common.utils.StringCompare.IStringSimilarityService;
import org.springblade.common.utils.StringCompare.StringSimilarityFactory;
import org.springblade.common.utils.StringUtil;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.redis.cache.BladeRedis;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.support.Kv;
import org.springblade.core.tool.utils.DateUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.flow.business.service.IFlowService;
import org.springblade.flow.core.constant.ProcessConstant;
import org.springblade.flow.core.entity.BladeFlow;
import org.springblade.flow.core.utils.FlowUtil;
import org.springblade.flow.engine.service.FlowEngineService;
import org.springblade.modules.project.dto.BusinessDTO;
import org.springblade.modules.project.entity.Business;
import org.springblade.modules.project.entity.Change;
import org.springblade.modules.project.entity.ChangeDetail;
import org.springblade.modules.project.entity.Clash;
import org.springblade.modules.project.mapper.BusinessMapper;
import org.springblade.modules.project.mapper.ChangeDetailMapper;
import org.springblade.modules.project.mapper.ChangeMapper;
import org.springblade.modules.project.service.IBusinessService;
import org.springblade.modules.project.service.IChangeDetailService;
import org.springblade.modules.project.service.IChangeService;
import org.springblade.modules.project.service.IClashService;
import org.springblade.modules.project.vo.BusinessVO;
import org.springblade.modules.project.vo.ChangeDetailVO;
import org.springblade.modules.system.entity.DeptSetting;
import org.springblade.modules.system.service.IDictService;
import org.springblade.modules.system.service.IMajorService;
import org.springblade.modules.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import org.springblade.modules.project.service.IBidService;
//流程引擎相关import

/**
 * 服务实现类
 *
 * @author BladeX
 * @since 2021-07-03
 */
@Slf4j
@Service
@AllArgsConstructor
public class BusinessServiceImpl extends BaseServiceImpl<BusinessMapper, Business> implements IBusinessService {

	private final BladeRedis bladeRedis;
	private final IFlowService flowService;
	private final IChangeService changeService;
	private final ChangeMapper changeMapper;
	private final IChangeDetailService changedetailService;
	private final ChangeDetailMapper changeDetailMapper;
	private final IClashService clashService;
	private final TaskService taskService;
	private final IDictService idictService;
	private final IMajorService imajorService;
	private final FlowEngineService flowEngineService;
	private final IUserService userService;

	@Autowired
	private StringSimilarityFactory stringCompareFactory;


	@Override
	public IPage<BusinessVO> selectBusinessPage(IPage<BusinessVO> page, BusinessVO business) {
		return page.setRecords(baseMapper.selectBusinessPage(page, business));
	}

	//region 商机报备流程
	/**
	 * 保存备案信息，且启动相关流程
	 *
	 * @param business
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean startProcess(Business business) {
		String businessTable = FlowUtil.getBusinessTable(ProcessConstant.BUSINESS_KEY);

		System.out.println("校验系统是否有表：" + businessTable);

		// 设置发起时间以及保存信息
		business.setApplyTime(DateUtil.now());
		business.setTenantId(Long.parseLong(AuthUtil.getTenantId()));
		business.setBranchCompany(AuthUtil.getUser().getDetail().getLong(CommonConstant.BRANCH_COM_ID));
		business.setProCompany(AuthUtil.getUser().getDetail().getLong(CommonConstant.PROF_COM_ID));

		int ischange = 0;

		//判断修改了哪些字段
		List<ChangeDetail> diffList = new ArrayList<>();
		if (!Func.isEmpty(business.getId())) {
			diffList = differenceComparison(business);
			changeService.saveChange(business.getId(), diffList);
			ischange = 1;
		}


		//先保存
		saveOrUpdate(business);


		//判断修改的字段是否包含一下信息，是则重新走流程，否则不走
		if (diffList.stream().anyMatch(d -> d.getColIndex() == "recordName" || d.getColIndex() == "clientName") || ischange == 0) {

			//加入对应的参数，即在
			Kv variables = Kv.create().set(ProcessConstant.TASK_VARIABLE_CREATE_USER, AuthUtil.getUserName());
			//发起流程设置路线，0为不冲突，1为分公司接口人，2为本部接口人
			List<Clash> clashList = checkConflictProject(business);
			//排他网关
			if (clashList.size() == 0) {
				//直接通过
				variables.set("judge", BusinessFlowStatusEnum.N_WAIT_REVIEW.getValue().toString());
				//无冲突本部接口人审批环节中
				business.setStatus(BusinessFlowStatusEnum.N_WAIT_REVIEW.getValue());
				business.setRecordStatus(BusinessStatusEnum.WAIT_REVIEW.getValue());
			} else {
				business.setRecordStatus(BusinessStatusEnum.CLASH.getValue());
				if (clashList.stream().anyMatch(n -> n.getClashType() == 2)) {
					//走本部接口人分支
					business.setStatus(BusinessFlowStatusEnum.S_WAIT_REVIEW.getValue());
					variables.set(CommonConstant.BUSINESS_FLOW, BusinessFlowStatusEnum.S_WAIT_REVIEW.getValue().toString());
				} else {
					//走分公司接口人分支
					business.setStatus(BusinessFlowStatusEnum.F_WAIT_REVIEW.getValue());
					variables.set(CommonConstant.BUSINESS_FLOW, BusinessFlowStatusEnum.F_WAIT_REVIEW.getValue().toString());
				}

				for (Clash clash : clashList) {
					clash.setNewBusinessId(business.getId());
				}

				//保存冲突记录
				clashService.saveBatch(clashList);
				business.setRecordStatus(BusinessStatusEnum.CLASH.getValue());
			}

			String processDefinitionId = flowEngineService.selectProcessPage(Condition.getPage(new Query()), "flow_5", 1).getRecords().get(0).getId();
			// 启动流程
			BladeFlow flow = flowService.startProcessInstanceById(processDefinitionId, FlowUtil.getBusinessKey(businessTable, String.valueOf(business.getId())), variables);


			if (Func.isNotEmpty(flow)) {
				log.debug("流程已启动,流程ID:" + flow.getProcessInstanceId());
				// 返回流程id写入business
				business.setProcessDefinitionId(processDefinitionId);
				business.setProcessInstanceId(flow.getProcessInstanceId());

				System.out.println("business：" + business.toString());
				updateById(business);
			} else {
				throw new ServiceException("开启流程失败");
			}
		}
		return true;
	}

	/**
	 * 流程审批操作
	 *
	 * @param businessdto
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean com(BusinessDTO businessdto) {
		Business business =this.getById( businessdto.getBusiness().getId());
		BladeFlow flow = businessdto.getFlow();
		int a = business.getStatus();
		String taskId = flow.getTaskId();
		String processInstanceId = flow.getProcessInstanceId();
		String comment = Func.toStr(flow.getComment(), ProcessConstant.PASS_COMMENT);
		// 创建变量
		Map<String, Object> variables = flow.getVariables();
		if (variables == null) {
			variables = Kv.create();
		}
		String c = flow.getFlag();
		if ("ok".equals(c)) {
			if (a == 0 || a == 2 || a == 3) {
				//备案成功
				business.setStatus(BusinessFlowStatusEnum.E_SUCCESS.getValue());
				business.setRecordStatus(BusinessStatusEnum.SUCCESS.getValue());
				comment += "(专业公司审核通过)";
			}
			if (a == 4) {
				//分公司备案通过，下一步移交本部审核
				variables.put(CommonConstant.BUSINESS_FLOW, BusinessFlowStatusEnum.E_WAIT_REVIEW.getValue());
				business.setStatus(BusinessFlowStatusEnum.E_WAIT_REVIEW.getValue());
				business.setRecordStatus(BusinessStatusEnum.WAIT_REVIEW.getValue());
				comment += "(分公司审核通过)";
			}
		} else {
			business.setRecordStatus(BusinessStatusEnum.INVALID.getValue());
			//备案失败
			if (a == 1) {
				variables.put(CommonConstant.BUSINESS_FLOW, BusinessFlowStatusEnum.F_CLASH_Fail.getValue());
				business.setStatus(BusinessFlowStatusEnum.F_CLASH_Fail.getValue());
				comment += "(分公司审核不通过)";
			} else {
				business.setStatus(BusinessFlowStatusEnum.E_CLASH_Fail.getValue());
				comment += "(专业公司审核补通过)";
			}
		}
		this.saveOrUpdate(business);
		if (org.springblade.core.tool.utils.StringUtil.isNoneBlank(processInstanceId, comment)) {
			taskService.addComment(taskId, processInstanceId, comment);
		}
		variables.put(ProcessConstant.PASS_KEY, flow.isPass());
		// 完成任务
		taskService.complete(taskId, variables);
		return (true);
	}
	//endregion

	//region 冲突判断

	/**
	 * /* 判断冲突项目
	 *
	 * @param project
	 * @return
	 */
	private List<Clash> checkConflictProject(Business project) {
		BladeUser currUser = AuthUtil.getUser();

		//获取需要进行匹对判断冲突的列表
		LambdaQueryWrapper<Business> queryWrapper = new LambdaQueryWrapper<>();

		queryWrapper.eq(Business::getProCompany, currUser.getDetail().getStr(CommonConstant.PROF_COM_ID));


		if (Func.isNotEmpty(project.getId()) && project.getId().equals("")) {
			queryWrapper.ne(Business::getId, project.getId());
		}

		if (project.getBiddingType().equals("直接委托")) {
			//已报备成功的直接委托项目
			queryWrapper.eq(Business::getBiddingType, project.getBiddingType());
			queryWrapper.eq(Business::getRecordStatus, BusinessStatusEnum.SUCCESS.getValue()); //备案成功
		} else {
			//来源是公开招标和内部邀标，且备案成功、备案冲突 状态的未投标的已报备项目
			queryWrapper.ne(Business::getBiddingType, project.getBiddingType());
			queryWrapper.in(Business::getRecordStatus, BusinessStatusEnum.SUCCESS.getValue(), BusinessStatusEnum.CLASH.getValue());//备案成功或备案冲突
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
		return compareService.stringCompare(str1, str2);

	}


	@Override
	public BusinessDTO flowDetail(Business business){
		Business detail = this.getById(business.getId());
		//加入申请人信息
		detail.getFlow().setAssigneeName(UserCache.getUser(detail.getCreateUser()).getName());
		//处理business数据
		detail.setProjectCatrgory(idictService.getValue("project_Catrgory", detail.getProjectCatrgory()));
		detail.setBiddingType(idictService.getValue("project_BiddingType", detail.getBiddingType()));
		detail.setExpandMode(idictService.getValue("project_ExpandMode", detail.getExpandMode()));
		detail.setIndustry(idictService.getValue("project_Industry", detail.getIndustry()));
		String a = detail.getTrack().substring(1, detail.getTrack().length()-1).replace("\"", "");
		String[] arr = a.split(",");
		List<String> list=new ArrayList<String>();
		for (String t:arr) {
			list.add(idictService.getValue("project_track", t));
		}
		detail.setTrack(list.toString());
		detail.setMajor(imajorService.getName(detail.getMajor()));
		detail.setClientType(idictService.getValue("client_type", detail.getClientType()));
		detail.setClientCategory(idictService.getValue("client_category", detail.getClientCategory()));
		detail.setClientRelationship(idictService.getValue("client_relationship",detail.getClientRelationship()));

//		long test = Long.parseLong("1415219664417677314");
		//处理change数据
		List<Change> change = changeMapper.getChangeList(detail.getId());
		for (Change l:change) {
			l.setChangeUser(userService.getById(l.getChangeUser()).getName());
			List<ChangeDetailVO> changeDetail = changeDetailMapper.selectChangeDetialList(l.getId());
			l.setChangeDetailList(changeDetail);
		}
		BusinessDTO businessDTO = new BusinessDTO();
		businessDTO.setBusiness(detail);
		businessDTO.setChange(change);
		return (businessDTO);
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

		if (Func.isEmpty(newEntity.getId())){
			return result;
		}


		Business oldEntity = baseMapper.selectById(newEntity.getId());

		List<Kv> diff = CompareUtil.compareEntityFields(oldEntity, newEntity);

		if (diff.size() > 0) {
			result = JSON.parseArray(JSON.toJSONString(diff), ChangeDetail.class);
		}

		return result;
	}

	//endregion


}
