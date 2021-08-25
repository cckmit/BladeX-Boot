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
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.flowable.engine.TaskService;
import org.springblade.common.enums.BidCancelStatusEnum;
import org.springblade.common.enums.BidStatusEnum;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.support.Kv;
import org.springblade.core.tool.utils.DateUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.flow.business.service.IFlowService;
import org.springblade.flow.core.constant.ProcessConstant;
import org.springblade.flow.core.entity.BladeFlow;
import org.springblade.flow.core.utils.FlowUtil;
import org.springblade.flow.engine.service.FlowEngineService;
import org.springblade.modules.project.dto.*;
import org.springblade.modules.project.entity.Bid;
import org.springblade.modules.project.entity.BidCancel;
import org.springblade.modules.project.entity.Business;
import org.springblade.modules.project.mapper.BidMapper;
import org.springblade.modules.project.mapper.BusinessMapper;
import org.springblade.modules.project.service.IBidService;
import org.springblade.modules.project.service.IBidcancelService;
import org.springblade.modules.project.service.IBusinessService;
import org.springblade.modules.project.vo.BidVO;
import org.springblade.modules.resource.entity.Attach;
import org.springblade.modules.resource.entity.Upload;
import org.springblade.modules.resource.service.IAttachService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
	/**
	 * 附件表服务
	 */
	private final IAttachService attachService;

	@Override
	public IPage<BidVO> selectBidPage(IPage<BidVO> page, BidVO bid) {
		return page.setRecords(baseMapper.selectBidPage(page, bid));
	}
	@Override
	public IPage<BidFormDTO> selectBidList(IPage<BidFormDTO> page, BidVO bid) {
		return page.setRecords(baseMapper.selectBidList(page, bid));
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
//		Bid bid = baseMapper.selectById(cancelDTO.getBidId());

//		if (bid != null && !bid.getIsCancel() && bid.getCancelStatus().equals(BidCancelStatusEnum.WAIT.getValue())) {
////			bid.setApplyCancelTime(LocalDateTime.now());
////			bid.setApplyCancelUser(AuthUtil.getUser().getUserId());
////			bid.setCancelReason(cancelDTO.getReasons());
////			bid.setCancelStatus(BidCancelStatusEnum.APPLY.getValue());
//			baseMapper.updateById(bid);
//			return true;
//		}

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
		if (record.getStatus() != 1) {
			throw new ServiceException("该项目未备案成功，请审核成功后重试！");
		}
		if (record != null && Func.isNotEmpty(record.getId())) {
			Bid bid = getBidByBusinessId(record.getId());

			if (bid == null) {
				Bid newBid = new Bid();
				newBid.setBusinessId(record.getId());
				newBid.setProjectName(record.getRecordName());
				return save(newBid);
			}
			throw new ServiceException("该项目已推送到投标模块！");
		}
		throw new ServiceException("未找到该项目！");
	}
	private final FlowEngineService flowEngineService;
	private final IBidcancelService bidcancelService;
	//启动投标报废流程
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean startBidcancelProcess(Long bidId,String reason) {
		String businessTable = FlowUtil.getBusinessTable(ProcessConstant.BIDCANCEL_KEY);
		Bid bid;
		BidCancel bidCancel = new BidCancel();
		bid = getById(bidId);
		System.out.println("校验系统是否有表：" + businessTable);

		//投标报废表
		bidCancel.setId(bidId);
		bidCancel.setCancelStatus(BidCancelStatusEnum.APPLY.getValue());
		bidCancel.setApplyTime(DateUtil.now());
		bidCancel.setCreateUser(AuthUtil.getUser().getUserId().toString());
		bidCancel.setCreateDept(AuthUtil.getUser().getDeptId());
		bidCancel.setCancelReason(reason);
		if (Func.isEmpty(bid.getId())) {
			throw new ServiceException("当前项目不存在！");
		}
		//加入对应的参数，即在
		Kv variables = Kv.create().set(ProcessConstant.TASK_VARIABLE_CREATE_USER, AuthUtil.getUserName());

		String processDefinitionId = flowEngineService.selectProcessPage(Condition.getPage(new Query()), "flow_6", 1).getRecords().get(0).getId();

		System.out.println("variables：" + variables.toString());

		// 启动流程
		BladeFlow flow = flowService.startProcessInstanceById(processDefinitionId, FlowUtil.getBusinessKey(businessTable, String.valueOf(bidId)), variables);


		if (Func.isNotEmpty(flow)) {
			log.debug("流程已启动,流程ID:" + flow.getProcessInstanceId());
			// 返回流程id写入business
			bidCancel.setProcessInstanceId(flow.getProcessInstanceId());
			bidCancel.setProcessDefinitionId(processDefinitionId);

			System.out.println("bidcancel：" + bidCancel.toString());
			bidcancelService.saveOrUpdate(bidCancel);
		} else {
			throw new ServiceException("开启流程失败");
		}

		return true;
	}
	private final TaskService taskService;
	//审核投标报废流程
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean completeCancelTask(BidCancelDTO bidCancelDTO){
		BladeFlow flow = bidCancelDTO.getFlow();
		BidCancel bidCancel = bidCancelDTO.getBidCancel();
		Integer cancelStatus = bidCancel.getCancelStatus();
		String taskId = flow.getTaskId();
		String processInstanceId = flow.getProcessInstanceId();
		String comment = Func.toStr(flow.getComment(), ProcessConstant.PASS_COMMENT);
		Map<String, Object> variables = flow.getVariables();
		if (variables == null) {
			variables = Kv.create();
		}
		String IsOk = flow.getFlag();
		if(cancelStatus==1){
			if ("ok".equals(IsOk)) {
				variables.put("pass", true);
				bidCancel.setCancelStatus(BidCancelStatusEnum.SUCCESS.getValue());
			}else{
				variables.put("pass", false);
				bidCancel.setCancelStatus(BidCancelStatusEnum.REJECT.getValue());
			}
		}
		if (org.springblade.core.tool.utils.StringUtil.isNoneBlank(processInstanceId, comment)) {
			taskService.addComment(taskId, processInstanceId, comment);
		}
		variables.put(ProcessConstant.PASS_KEY, flow.isPass());
		bidcancelService.saveOrUpdate(bidCancel);
		return true;
	}

	//region  投标流程
	/**
	 * 投标流程开启
	 *
	 * @param bidFormDTO
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean startBidProcess(BidFormDTO bidFormDTO){
		String businessTable = FlowUtil.getBusinessTable(ProcessConstant.BID_KEY);

		System.out.println("校验系统是否有表：" + businessTable);
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Bid bid = this.getById(bidFormDTO.getId());
		//region 表单处理
		// 设置发起时间以及保存信息
		bid.setApplyTime(DateUtil.now());
		bid.setBidStatus(BidStatusEnum.APPLY_BID.getValue());
		//bid表单处理
		bid.setBusinessId(bidFormDTO.getBusinessId());
		bid.setBidCode(bidFormDTO.getBidCode());
		bid.setProjectName(bidFormDTO.getProjectName());
		bid.setIsFrame(bidFormDTO.getIsFrame());
		bid.setManagerId(bidFormDTO.getManagerId());
		bid.setBidAmount(bidFormDTO.getBidAmount());
		bid.setBidOpenTime(bidFormDTO.getBidOpenTime());
		bid.setBidAgentName(bidFormDTO.getBidAgentName());
		bid.setBidAgentCode(bidFormDTO.getBidAgentCode());
		bid.setAgentContact(bidFormDTO.getAgentContact());
		bid.setTenderNo(bidFormDTO.getTenderNo());
		bid.setPublicWebSite(bidFormDTO.getPublicWebSite());
		bid.setReceiveTime(bidFormDTO.getReceiveTime());
		bid.setIsNeedBond(bidFormDTO.getIsNeedBond());
		bid.setBondAmount(bidFormDTO.getBondAmount());
		bid.setBondPayMethod(bidFormDTO.getBondPayMethod());
		bid.setBondRecoveryTime(bidFormDTO.getBondRecoveryTime());
		bid.setIsWinBid(bidFormDTO.getIsWinBid());
		bid.setIsFailBid(bidFormDTO.getIsFailBid());
		bid.setWinBidTime(bidFormDTO.getWinBidTime());
		bid.setQuotationMethod(bidFormDTO.getQuotationMethod());
		bid.setOffer(bidFormDTO.getOffer());
		bid.setDiscount(bidFormDTO.getDiscount());
		bid.setDropPoint(bidFormDTO.getDropPoint());
		bid.setContinueDept(bidFormDTO.getContinueDept());
		bid.setGrossRate(bidFormDTO.getGrossRate());
		bid.setServiceCycle(bidFormDTO.getServiceCycle());
		bid.setIsDelete(bidFormDTO.getIsDelete());
		bid.setIsCancel(bidFormDTO.getIsCancel());
		bid.setIsAdvancePay(bidFormDTO.getIsAdvancePay());
		bid.setAdvancePayReason(bidFormDTO.getAdvancePayReason());
		bid.setBondStatus(bidFormDTO.getBondStatus());
		bid.setProcessInstanceId(bidFormDTO.getProcessInstanceId());
		bid.setProcessDefinitionId(bidFormDTO.getProcessDefinitionId());
		bid.setApplyTime(bidFormDTO.getApplyTime());
		//business表处理
		Business business = businessService.getById(bid.getBusinessId());
		business.setRecordName(bidFormDTO.getRecordName());
		business.setRecordCode(bidFormDTO.getRecordCode());
		business.setProjectCatrgory(bidFormDTO.getProjectCatrgory());
		business.setBiddingType(bidFormDTO.getBiddingType());
		business.setExpandMode(bidFormDTO.getExpandMode());
		business.setMajor(bidFormDTO.getMajor());
		business.setIndustry(bidFormDTO.getIndustry());
		business.setClientName(bidFormDTO.getClientName());
		business.setClientType(bidFormDTO.getClientType());
		business.setClientCategory(bidFormDTO.getClientCategory());
		business.setClientContact(bidFormDTO.getClientContact());
		business.setClientPhone(bidFormDTO.getClientPhone());
		business.setClientRelationship(bidFormDTO.getClientRelationship());
		businessService.saveOrUpdate(business);
		String fl ="";
		//附件表
		List<Upload> upload = bidFormDTO.getUpload();
		for(Upload m:upload) {
			if(Objects.equals(m.getUploadTip(), "操作成功")) {
				Attach attach = attachService.getById(m.getAttachId());
				attach.setBidType(m.getFileType());
				attachService.saveOrUpdate(attach);
				fl = fl + attach.getId()+ ",";
			}
		}
		bid.setFileAttachId(fl);
		//endregion

		//加入对应的参数，即在
		Kv variables = Kv.create().set(ProcessConstant.TASK_VARIABLE_CREATE_USER, AuthUtil.getUserName());
		//获取最新的流程Id
		String processDefinitionId = flowEngineService.selectProcessPage(Condition.getPage(new Query()), "flow_7", 1).getRecords().get(0).getId();
		// 启动流程
		BladeFlow flow = flowService.startProcessInstanceById(processDefinitionId, FlowUtil.getBusinessKey(businessTable, String.valueOf(bid.getId())), variables);

		if (Func.isNotEmpty(flow)) {
			log.debug("流程已启动,流程ID:" + flow.getProcessInstanceId());
			// 返回流程id写入business
			bid.setProcessDefinitionId(processDefinitionId);
			bid.setProcessInstanceId(flow.getProcessInstanceId());

			System.out.println("bid：" + bid.toString());
			//先保存
			this.saveOrUpdate(bid);
		} else {
			throw new ServiceException("开启流程失败");
		}
		return true;
	}
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean completeBidTask(BidDTO bidDTO){
		BladeFlow flow = bidDTO.getFlow();
		Bid bid = bidDTO.getBid();
		Integer bidStatus = bid.getBidStatus();
		String taskId = flow.getTaskId();
		String processInstanceId = flow.getProcessInstanceId();
		String comment = Func.toStr(flow.getComment(), ProcessConstant.PASS_COMMENT);
		Map<String, Object> variables = flow.getVariables();
		if (variables == null) {
			variables = Kv.create();
		}
		String IsOk = flow.getFlag();
		if(bidStatus==1){
			if ("ok".equals(IsOk)) {
				variables.put("pass", true);
				bid.setBidStatus(BidStatusEnum.APPLY_SUCCESS.getValue());
			}else{
				variables.put("pass", false);
				bid.setBidStatus(BidStatusEnum.REJECT.getValue());
			}
		}
		if (org.springblade.core.tool.utils.StringUtil.isNoneBlank(processInstanceId, comment)) {
			taskService.addComment(taskId, processInstanceId, comment);
		}
		variables.put(ProcessConstant.PASS_KEY, flow.isPass());
		this.saveOrUpdate(bid);
		businessService.saveOrUpdate(bidDTO.getBusiness());
		return true;
	}
	@Override
	@Transactional(rollbackFor = Exception.class)
	public BidFormDTO getDetail(String bidId){
		Bid bid = this.getById(bidId);
		Business business = businessService.getById(bid.getBusinessId());
		BidFormDTO bidFormDTO = new BidFormDTO();
		//投标字段
		bidFormDTO.setId(bid.getId());
		bidFormDTO.setBusinessId(bid.getBusinessId());
		bidFormDTO.setBidCode(bid.getBidCode());
		bidFormDTO.setProjectName(bid.getProjectName());
		bidFormDTO.setIsFrame(bid.getIsFrame());
		bidFormDTO.setManagerId(bid.getManagerId());
		bidFormDTO.setBidAmount(bid.getBidAmount());
		bidFormDTO.setBidOpenTime(bid.getBidOpenTime());
		bidFormDTO.setBidAgentName(bid.getBidAgentName());
		bidFormDTO.setBidAgentCode(bid.getBidAgentCode());
		bidFormDTO.setAgentContact(bid.getAgentContact());
		bidFormDTO.setTenderNo(bid.getTenderNo());
		bidFormDTO.setPublicWebSite(bid.getPublicWebSite());
		bidFormDTO.setReceiveTime(bid.getReceiveTime());
		bidFormDTO.setIsNeedBond(bid.getIsNeedBond());
		bidFormDTO.setBondAmount(bid.getBondAmount());
		bidFormDTO.setBondPayMethod(bid.getBondPayMethod());
		bidFormDTO.setBondRecoveryTime(bid.getBondRecoveryTime());
		bidFormDTO.setIsWinBid(bid.getIsWinBid());
		bidFormDTO.setIsFailBid(bid.getIsFailBid());
		bidFormDTO.setWinBidTime(bid.getWinBidTime());
		bidFormDTO.setQuotationMethod(bid.getQuotationMethod());
		bidFormDTO.setOffer(bid.getOffer());
		bidFormDTO.setDiscount(bid.getDiscount());
		bidFormDTO.setDropPoint(bid.getDropPoint());
		bidFormDTO.setContinueDept(bid.getContinueDept());
		bidFormDTO.setGrossRate(bid.getGrossRate());
		bidFormDTO.setServiceCycle(bid.getServiceCycle());
		bidFormDTO.setIsDelete(bid.getIsDelete());
		bidFormDTO.setIsCancel(bid.getIsCancel());
		bidFormDTO.setIsAdvancePay(bid.getIsAdvancePay());
		bidFormDTO.setAdvancePayReason(bid.getAdvancePayReason());
		bidFormDTO.setBondStatus(bid.getBondStatus());
		bidFormDTO.setProcessInstanceId(bid.getProcessInstanceId());
		bidFormDTO.setProcessDefinitionId(bid.getProcessDefinitionId());
		bidFormDTO.setApplyTime(bid.getApplyTime());
		if(bid.getFileAttachId()!=null) {
			String[] fls = bid.getFileAttachId().split(" ，");
			List<Upload> flist = new ArrayList<>();
			for (String fl : fls
			) {
				Attach attach = attachService.getById(fl);
				Upload upload = new Upload();
				upload.setAttachId(attach.getId().toString());
				upload.setDomain(attach.getDomain());
				upload.setName(attach.getName());
				upload.setOriginalName(attach.getOriginalName());
				upload.setFileSuffix(attach.getExtension());
				upload.setFileSize(attach.getAttachSize().toString());
				upload.setFileType(attach.getBidType());
				upload.setUploadTip("操作成功");
				flist.add(upload);
			}
			bidFormDTO.setUpload(flist);
		}
		//商机报备字段
		bidFormDTO.setRecordName(business.getRecordName());
		bidFormDTO.setRecordCode(business.getRecordCode());
		bidFormDTO.setProjectCatrgory(business.getProjectCatrgory());
		bidFormDTO.setBiddingType(business.getBiddingType());
		bidFormDTO.setExpandMode(business.getExpandMode());
		bidFormDTO.setMajor(business.getMajor());
		bidFormDTO.setIndustry(business.getIndustry());
		bidFormDTO.setClientName(business.getClientName());
		bidFormDTO.setClientType(business.getClientType());
		bidFormDTO.setClientCategory(business.getClientCategory());
		bidFormDTO.setClientContact(business.getClientContact());
		bidFormDTO.setClientPhone(business.getClientPhone());
		bidFormDTO.setClientRelationship(business.getClientRelationship());
		return bidFormDTO;
	}

	//endregion

}
