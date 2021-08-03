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

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import org.springblade.common.constant.CommonConstant;
import org.springblade.common.enums.BidCancelStatusEnum;
import org.springblade.common.enums.BusinessFlowStatusEnum;
import org.springblade.common.enums.BusinessStatusEnum;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.common.enums.BidStatusEnum;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.support.Kv;
import org.springblade.core.tool.utils.DateUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.flow.business.service.IFlowService;
import org.springblade.flow.core.constant.ProcessConstant;
import org.springblade.flow.core.entity.BladeFlow;
import org.springblade.flow.core.utils.FlowUtil;
import org.springblade.modules.project.dto.BidApplyDTO;
import org.springblade.modules.project.dto.BidToVoidDTO;
import org.springblade.modules.project.entity.Bid;
import org.springblade.modules.project.entity.Business;
import org.springblade.modules.project.entity.Clash;
import org.springblade.modules.project.service.IBusinessService;
import org.springblade.modules.project.vo.BidVO;
import org.springblade.modules.project.mapper.BidMapper;
import org.springblade.modules.project.mapper.BusinessMapper;
import org.springblade.modules.project.service.IBidService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 服务实现类
 *
 * @author BladeX
 * @since 2021-07-18
 */
@Service
@AllArgsConstructor
public class BidServiceImpl extends ServiceImpl<BidMapper, Bid> implements IBidService {


	private final IBusinessService businessService;
	private final BusinessMapper businessMapper;
	private final IFlowService flowService;

	@Override
	public IPage<BidVO> selectBidPage(IPage<BidVO> page, BidVO bid) {
		return page.setRecords(baseMapper.selectBidPage(page, bid));
	}

	/**
	 * 根据商机主键获取投标信息
	 *
	 * @param businessId 商机主键
	 * @return
	 */
	@Override
	public Bid getBidByBusinessId(long businessId) {

		LambdaQueryWrapper<Bid> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(Bid::getBusinessId, businessId);

		Bid bid = baseMapper.selectOne(queryWrapper);

		return bid;
	}

	/**
	 * 终止投标
	 *
	 * @param cancelDTO 终止实体
	 * @return
	 */
	@Override
	public Boolean stopBid(BidToVoidDTO cancelDTO) {
		Bid bid = baseMapper.selectById(cancelDTO.getBidId());

		if (bid != null && !bid.getIsCancel() && bid.getCancelStatus() == BidCancelStatusEnum.WAIT.getValue()) {
			bid.setApplyCancelTime(LocalDateTime.now());
			bid.setApplyCancelUser(AuthUtil.getUser().getUserId());
			bid.setCancelReason(cancelDTO.getReasons());
			bid.setCancelStatus(BidCancelStatusEnum.APPLY.getValue());
			baseMapper.updateById(bid);
			return true;
		}

		return false;
	}

	/**
	 * 发起投标申请
	 *
	 * @param applyDTO
	 * @return
	 */
	@Override
	@Transactional
	public Boolean saveBidApply(BidApplyDTO applyDTO) {


		Bid bid = baseMapper.selectById(applyDTO.getBidId());
		Business business = businessService.getById(bid.getBusinessId());

		business.setMajor(applyDTO.getMajor());
		business.setIndustry(applyDTO.getIndustry());
		business.setExpandMode(applyDTO.getExpandMode());
		business.setClientId(applyDTO.getClientId());
		business.setClientName(applyDTO.getClientName());
		business.setClientCategory(applyDTO.getClientCategory());
		business.setClientType(applyDTO.getClientType());
		business.setClientRelationship(applyDTO.getClientRelationship());
		business.setClientContact(applyDTO.getClientContact());
		business.setClientPhone(applyDTO.getClientPhone());


		bid.setBidCode(applyDTO.getBidCode());
		bid.setProjectName(applyDTO.getProjectName());
		bid.setManagerId(applyDTO.getManagerId());
		bid.setBidAmount(applyDTO.getBidAmount());
		bid.setBidOpenTime(applyDTO.getBidOpenTime());
		bid.setReceiveTime(applyDTO.getReceiveTime());
		bid.setIsNeedBond(applyDTO.getIsNeedBound());
		bid.setIsFrame(applyDTO.getIsFrame());
		bid.setIsAdvancePay(applyDTO.getIsAdvancePay());
		bid.setAdvancePayReason(applyDTO.getAdvancePayReason());
		bid.setBidStatus(BidStatusEnum.APPLY_BID.getValue());


		//附件信息保存


		return businessService.saveOrUpdate(business) && this.saveOrUpdate(bid);
	}

	/**
	 * 推送至投标管理
	 *
	 * @param businessId
	 * @return
	 */
	@Override
	public boolean pushToBid(long businessId) {

		if (Func.isEmpty(businessId)) {
			throw new ServiceException("参数错误！");
		}

		Business record = businessMapper.selectById(businessId);
		if (record.getStatus() != 1) throw new ServiceException("该项目未备案成功，请审核成功后重试！");
		if (record != null && Func.isNotEmpty(record.getId())) {
			Bid bid = getBidByBusinessId(record.getId());

			if (bid == null) {
				Bid newBid = new Bid();
				newBid.setBusinessId(record.getId());
				return save(newBid);
			}
			throw new ServiceException("该项目已推送到投标模块！");
		}
		throw new ServiceException("未找到该项目！");
	}

	//启动流程
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean startProcess(Bid bid) {
		String businessTable = FlowUtil.getBusinessTable(ProcessConstant.BID_KEY);

		System.out.println("校验系统是否有表：" + businessTable);

		// 设置发起时间以及保存信息
		bid.setApplyCancelTime(LocalDateTime.now());
		bid.setApplyCancelUser(AuthUtil.getUser().getUserId());
		bid.setCancelReason("111111测试");
		bid.setCancelStatus(BidCancelStatusEnum.APPLY.getValue());
		if (Func.isEmpty(bid.getId())) {
			throw new ServiceException("当前项目不存在！");
		} else {
			updateById(bid);
		}
		//加入对应的参数，即在
		Kv variables = Kv.create().set(ProcessConstant.TASK_VARIABLE_CREATE_USER, AuthUtil.getUserName());
		//排他网关
		variables.set(CommonConstant.BUSINESS_FLOW, BusinessFlowStatusEnum.F_WAIT_REVIEW.getValue().toString());


		updateById(bid);
		System.out.println("variables：" + variables.toString());

		// 启动流程
		BladeFlow flow = flowService.startProcessInstanceById(bid.getProcessDefinitionId(), FlowUtil.getBusinessKey(businessTable, String.valueOf(bid.getId())), variables);


		if (Func.isNotEmpty(flow)) {
			log.debug("流程已启动,流程ID:" + flow.getProcessInstanceId());
			// 返回流程id写入business
			bid.setProcessInstanceId(flow.getProcessInstanceId());


			System.out.println("business：" + bid.toString());
			updateById(bid);
		} else {
			throw new ServiceException("开启流程失败");
		}

		return true;
	}
}
