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
import org.springblade.common.cache.UserCache;
import org.springblade.common.enums.BidStatusEnum;
import org.springblade.common.enums.BondStatusEnum;
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
import org.springblade.modules.project.entity.*;
import org.springblade.modules.project.mapper.BidMapper;
import org.springblade.modules.project.mapper.BidcomMapper;
import org.springblade.modules.project.mapper.BusinessMapper;
import org.springblade.modules.project.service.*;
import org.springblade.modules.project.vo.BidVO;
import org.springblade.modules.project.vo.BidcomVO;
import org.springblade.modules.resource.entity.Attach;
import org.springblade.modules.resource.entity.Upload;
import org.springblade.modules.resource.service.IAttachService;
import org.springblade.modules.system.service.IDictService;
import org.springblade.modules.system.service.IMajorService;
import org.springblade.modules.system.service.IManagerService;
import org.springblade.modules.system.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 服务实现类
 *
 * @author BladeX
 * @since 2021-07-18
 */
@Service
@AllArgsConstructor
public class BidServiceImpl extends ServiceImpl<BidMapper, Bid> implements IBidService {

	//region 引用
	private final IFlowService flowService;
	private final TaskService taskService;
	private final IAttachService attachService;
	private final IDictService idictService;
	private final FlowEngineService flowEngineService;
	private final IUserService userService;

	private final IBusinessService businessService;
	private final BusinessMapper businessMapper;
	private final BidcomMapper bidcomMapper;
	private final IMajorService imajorService;
	private final IBidcancelService bidcancelService;
	private final IBidbondService bidbondService;
	private final IBidundertakeService bidundertakeService;
	private final IBidcomService bidcomService;
	private final IBidresultService bidresultService;
	private final IManagerService managerService;
	//endregion

	//region 其他
	@Override
	public IPage<BidVO> selectBidPage(IPage<BidVO> page, BidVO bid) {
		return page.setRecords(baseMapper.selectBidPage(page, bid));
	}

	@Override
	public IPage<BidListDTO> selectBidList(IPage<BidListDTO> page, BidListDTO bid) {
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

//	/**
//	 * 终止投标
//	 *
//	 * @param cancelDTO 终止实体
//	 * @return
//	 */
//	@Override
//	public Boolean stopBid(BidToVoidDTO cancelDTO) {
////		Bid bid = baseMapper.selectById(cancelDTO.getBidId());
//
////		if (bid != null && !bid.getIsCancel() && bid.getCancelStatus().equals(BidCancelStatusEnum.WAIT.getValue())) {
//////			bid.setApplyCancelTime(LocalDateTime.now());
//////			bid.setApplyCancelUser(AuthUtil.getUser().getUserId());
//////			bid.setCancelReason(cancelDTO.getReasons());
//////			bid.setCancelStatus(BidCancelStatusEnum.APPLY.getValue());
////			baseMapper.updateById(bid);
////			return true;
////		}
//
//		return false;
//	}

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
		bid.setBidStatus(BidStatusEnum.BID_LAUNCH.getValue());


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
		if (record.getRecordStatus() != 2) {
			throw new ServiceException("该项目未备案成功，请审核成功后重试！");
		}
		if (record != null && Func.isNotEmpty(record.getId())) {
			Bid bid = getBidByBusinessId(record.getId());

			if (bid == null) {
				Bid newBid = new Bid();
				newBid.setBusinessId(record.getId());
				newBid.setProjectName(record.getRecordName());
				newBid.setBidStatus(10);
				newBid.setCreateUser(AuthUtil.getUserId());
				newBid.setCreateDept(Long.valueOf(AuthUtil.getDeptId()));
				newBid.setCreateTime(DateUtil.now());
				newBid.setStatus(10);
				newBid.setIsDeleted(0);
				return save(newBid);
			}
			throw new ServiceException("该项目已推送到投标模块！");
		}
		throw new ServiceException("未找到该项目！");
	}

	//endregion

	//region 投标报废流程
	//启动投标报废流程
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean startBidcancelProcess(Long bidId, String reason) {
		String businessTable = FlowUtil.getBusinessTable(ProcessConstant.BIDCANCEL_KEY);
		BidCancel bidCancel = new BidCancel();
		Bid bid = getById(bidId);
		System.out.println("校验系统是否有表：" + businessTable);

		//投标报废表
		bidCancel.setId(bidId);
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
			bidCancel.setCancelStatus(BidStatusEnum.CANCEL_LAUNCH.getValue());
			System.out.println("bidcancel：" + bidCancel.toString());
			bid.setBidStatus(BidStatusEnum.CANCEL_LAUNCH.getValue());
			bid.setStatus(BidStatusEnum.CANCEL_WAIT.getValue());
			this.saveOrUpdate(bid);
			bidcancelService.saveOrUpdate(bidCancel);
		} else {
			throw new ServiceException("开启流程失败");
		}

		return true;
	}

	//审核投标报废流程
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean completeCancelTask(BidCancelDTO bidCancelDTO) {
		BladeFlow flow = bidCancelDTO.getFlow();
		Bid bid = bidCancelDTO.getBid();
		BidCancel bidCancel = bidcancelService.getById(bid.getId());
		Bid newbid = this.getById(bid.getId());
		String taskId = flow.getTaskId();
		String processInstanceId = flow.getProcessInstanceId();
		String comment = Func.toStr(flow.getComment(), ProcessConstant.PASS_COMMENT);
		Map<String, Object> variables = flow.getVariables();
		if (variables == null) {
			variables = Kv.create();
		}
		String IsOk = flow.getFlag();
		if ("ok".equals(IsOk)) {
			bidCancel.setCancelStatus(BidStatusEnum.CANCEL.getValue());
			newbid.setBidStatus(BidStatusEnum.CANCEL.getValue());
			newbid.setStatus(BidStatusEnum.CANCEL.getValue());
			comment += "(作废审核通过)";
		} else {
			bidCancel.setCancelStatus(BidStatusEnum.CANCEL_FAIL.getValue());
			newbid.setBidStatus(BidStatusEnum.CANCEL_FAIL.getValue());
			newbid.setStatus(BidStatusEnum.CANCEL.getValue());
			comment += "(作废审核不通过)";
		}
		if (org.springblade.core.tool.utils.StringUtil.isNoneBlank(processInstanceId, comment)) {
			taskService.addComment(taskId, processInstanceId, comment);
		}
		variables.put(ProcessConstant.PASS_KEY, flow.isPass());
		this.saveOrUpdate(newbid);
		bidcancelService.saveOrUpdate(bidCancel);
		// 完成任务
		taskService.complete(taskId, variables);
		return true;
	}
	//endregion

	//region  投标流程

	/**
	 * 投标流程开启
	 *
	 * @param bidFormDTO
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean startBidProcess(BidFormDTO bidFormDTO) {
		String businessTable = FlowUtil.getBusinessTable(ProcessConstant.BID_KEY);

		System.out.println("校验系统是否有表：" + businessTable);
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Bid bid = this.getById(bidFormDTO.getId());
		//region 表单处理
		// 设置发起时间以及保存信息
		bid.setApplyTime(DateUtil.now());
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
		//business表处理
		Business business = businessService.getById(bid.getBusinessId());
		business.setRecordName(bidFormDTO.getRecordName());
		business.setRecordCode(bidFormDTO.getRecordCode());
		business.setProjectCatrgory(bidFormDTO.getProjectCatrgory());
		business.setBiddingType(bidFormDTO.getBiddingType());
		business.setExpandMode(bidFormDTO.getExpandMode());
		business.setMajor(bidFormDTO.getMajor());
		business.setIndustry(bidFormDTO.getIndustry());
		business.setClientId(Long.valueOf(bidFormDTO.getClientId()));
		business.setClientName(bidFormDTO.getClientName());
		business.setClientType(bidFormDTO.getClientType());
		business.setClientCategory(bidFormDTO.getClientCategory());
		business.setClientContact(bidFormDTO.getClientContact());
		business.setClientPhone(bidFormDTO.getClientPhone());
		business.setClientRelationship(bidFormDTO.getClientRelationship());
		businessService.saveOrUpdate(business);
		String fl = "";
		//附件表
		List<Upload> upload = bidFormDTO.getUpload();
		for (Upload m : upload) {
			if (Objects.equals(m.getUploadTip(), "操作成功")) {
				Attach attach = attachService.getById(m.getAttachId());
				attach.setBidType(m.getFileType());
				attachService.saveOrUpdate(attach);
				fl = fl + attach.getId() + ",";
			}
		}
		bid.setFileAttachId(fl);
		//endregion
		if ("WT".equals(bidFormDTO.getBiddingType())) {
			bid.setBidStatus(BidStatusEnum.CONTINUE_LAUNCH.getValue());
			bid.setStatus(BidStatusEnum.CONTINUE_LAUNCH.getValue());
			this.saveOrUpdate(bid);
			return true;
		}
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
			bid.setBidStatus(BidStatusEnum.BID_WAIT.getValue());
			bid.setStatus(BidStatusEnum.BID_WAIT.getValue());
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
	public BidFlowDTO bidallflow(String bidId) {
		Bid bid = this.getById(bidId);
		Business business = businessService.getById(bid.getBusinessId());
		Bidbond bidbond = bidbondService.getById(bidId);
		Bidundertake bidundertake = bidundertakeService.getById(bidId);
		Bidresult bidresult = bidresultService.getById(bidId);
		BidCancel bidcancel = bidcancelService.getById(bidId);
		BidFlowDTO bidFlowDTO = new BidFlowDTO();
		if (!Func.isEmpty(bid)) {
			bidFlowDTO.setBidprocessInstanceId(bid.getProcessInstanceId());
		}
		if (!Func.isEmpty(business)) {
			bidFlowDTO.setBusinessprocessInstanceId(business.getProcessInstanceId());
		}
		if (!Func.isEmpty(bidbond)) {
			bidFlowDTO.setBidbondprocessInstanceId(bidbond.getProcessInstanceId());
		}
		if (!Func.isEmpty(bidundertake)) {
			bidFlowDTO.setBidundertakeprocessInstanceId(bidundertake.getProcessInstanceId());
		}
		if (!Func.isEmpty(bidresult)) {
			bidFlowDTO.setBidresultprocessInstanceId(bidresult.getProcessInstanceId());
		}
		if (!Func.isEmpty(bidcancel)) {
			bidFlowDTO.setBidcancelprocessInstanceId(bidcancel.getProcessInstanceId());
		}
		return bidFlowDTO;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public BidDTO getBidDetail(String bidId) {
		BidDTO bidDTO = new BidDTO();
		//2021.8.26
		Bid bid = this.getById(bidId);
		bid.setManagerId(userService.getById(managerService.getById(bid.getManagerId()).getUserId()).getName());
		List<Upload> flist = new ArrayList<>();

		//文件列表
		if (!Func.isEmpty(bid.getFileAttachId())) {
			String[] fls = bid.getFileAttachId().split(",");
			for (String fl : fls
			) {
				Attach attach = attachService.getById(fl);
				Upload upload = new Upload();
				upload.setAttachId(attach.getId().toString());
				upload.setDomain(attach.getDomain());
				upload.setName(attach.getName());
				upload.setFileName(attach.getOriginalName());
				upload.setFileSuffix(attach.getExtension());
				upload.setFileSize(Integer.parseInt(attach.getAttachSize().toString()) / 1024 + "kb");
				upload.setFileType(attach.getBidType());
				upload.setUploadTip("操作成功");
				flist.add(upload);
			}
		}
		Bidbond bidbond = bidbondService.getById(bidId);
		if (!Func.isEmpty(bidbond)) {
			bidbond.setBondPayMethod(idictService.getValue("project_Bond_Pay_Method", bidbond.getBondPayMethod()));
			if (!Func.isEmpty(bidbond.getFileAttachId())) {
				String[] fls = bidbond.getFileAttachId().split(",");
				for (String fl : fls
				) {
					Attach attach = attachService.getById(fl);
					Upload upload = new Upload();
					upload.setAttachId(attach.getId().toString());
					upload.setDomain(attach.getDomain());
					upload.setName(attach.getName());
					upload.setFileName(attach.getOriginalName());
					upload.setFileSuffix(attach.getExtension());
					upload.setFileSize(Integer.parseInt(attach.getAttachSize().toString()) / 1024 + "kb");
					upload.setFileType(attach.getBidType());
					upload.setUploadTip("操作成功");
					flist.add(upload);
				}
			}
			bidDTO.setBidbond(bidbond);
		}
		Business business = businessService.getById(bid.getBusinessId());
		if ("WT".equals(business.getBiddingType())) {
			Bidundertake bidundertake = bidundertakeService.getById(bidId);
			if(!Func.isEmpty(bidundertake.getQualityType())) {
				bidundertake.setQualityType(idictService.getValue("quality_type", bidundertake.getQualityType()));
			}
			if (!Func.isEmpty(bidundertake.getFileAttachId())) {
				String[] fls = bidundertake.getFileAttachId().split(",");
				for (String fl : fls
				) {
					Attach attach = attachService.getById(fl);
					Upload upload = new Upload();
					upload.setAttachId(attach.getId().toString());
					upload.setDomain(attach.getDomain());
					upload.setName(attach.getName());
					upload.setFileName(attach.getOriginalName());
					upload.setFileSuffix(attach.getExtension());
					upload.setFileSize(Integer.parseInt(attach.getAttachSize().toString()) / 1024 + "kb");
					upload.setFileType(attach.getBidType());
					upload.setUploadTip("操作成功");
					flist.add(upload);
				}
			}
			bidDTO.setBidundertake(bidundertake);
		}
		Bidresult bidresult = bidresultService.getById(bidId);
		if (!Func.isEmpty(bidresult)) {
			bidresult.setQuotationMethod(idictService.getValue("project_Quotation_Method", bidresult.getQuotationMethod()));
			if (!Func.isEmpty(bidresult.getBidCom())) {
				bidresult.setBidCom(bidcomService.getById(bidresult.getBidCom()).getCompanyName());
			}
			if (!Func.isEmpty(bidresult.getFileAttachId())) {
				String[] fls = bidresult.getFileAttachId().split(",");
				for (String fl : fls
				) {
					Attach attach = attachService.getById(fl);
					Upload upload = new Upload();
					upload.setAttachId(attach.getId().toString());
					upload.setDomain(attach.getDomain());
					upload.setName(attach.getName());
					upload.setFileName(attach.getOriginalName());
					upload.setFileSuffix(attach.getExtension());
					upload.setFileSize(Integer.parseInt(attach.getAttachSize().toString()) / 1024 + "kb");
					upload.setFileType(attach.getBidType());
					upload.setUploadTip("操作成功");
					flist.add(upload);
				}
			}
			bidDTO.setBidresult(bidresult);
		}
		//加入申请人信息
		business.getFlow().setAssigneeName(UserCache.getUser(bid.getCreateUser()).getName());
		//处理business数据
		business.setProjectCatrgory(idictService.getValue("project_Catrgory", business.getProjectCatrgory()));
		business.setBiddingType(idictService.getValue("project_BiddingType", business.getBiddingType()));
		business.setExpandMode(idictService.getValue("project_ExpandMode", business.getExpandMode()));
		business.setIndustry(idictService.getValue("project_Industry", business.getIndustry()));
		String a = business.getTrack().substring(1, business.getTrack().length() - 1).replace("\"", "");
		String[] arr = a.split(",");
		List<String> list = new ArrayList<String>();
		for (String t : arr) {
			list.add(idictService.getValue("project_track", t));
		}
		business.setTrack(list.toString());
		business.setMajor(imajorService.getName(business.getMajor()));
		business.setClientType(idictService.getValue("client_type", business.getClientType()));
		business.setClientCategory(idictService.getValue("client_category", business.getClientCategory()));
		business.setClientRelationship(idictService.getValue("client_relationship", business.getClientRelationship()));

		BidCancel bidCancel = bidcancelService.getById(bid.getId());
		if(Func.isNotEmpty(bidCancel)){
			bidDTO.setBidCancel(bidCancel);
		}
		bidDTO.setBid(bid);
		bidDTO.setUpload(flist);
		bidDTO.setBusiness(business);
		bidDTO.setFlow(business.getFlow());
		return bidDTO;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean completeBidTask(BidDTO bidDTO) {
		BladeFlow flow = bidDTO.getFlow();
		Bid bid = this.getById(bidDTO.getBid().getId());
		Integer bidStatus = bid.getBidStatus();
		String taskId = flow.getTaskId();
		String processInstanceId = flow.getProcessInstanceId();
		String comment = Func.toStr(flow.getComment(), ProcessConstant.PASS_COMMENT);
		Map<String, Object> variables = flow.getVariables();
		if (variables == null) {
			variables = Kv.create();
		}
		String IsOk = flow.getFlag();
		if ("ok".equals(IsOk)) {
			variables.put("pass", true);
			bid.setBidStatus(BidStatusEnum.BID_SUCCESS.getValue());
			if (bid.getIsNeedBond() == 1) {
				bid.setStatus(BidStatusEnum.BOND_LAUNCH.getValue());
			} else {
				bid.setStatus(BidStatusEnum.OPEN_LAUNCH.getValue());
			}
			comment += "(投标审核通过)";
		} else {
			variables.put("pass", false);
			bid.setBidStatus(BidStatusEnum.BID_REJECT.getValue());
			bid.setStatus(BidStatusEnum.BID_LAUNCH.getValue());
			comment += "(投标审核不通过)";
		}
		if (org.springblade.core.tool.utils.StringUtil.isNoneBlank(processInstanceId, comment)) {
			taskService.addComment(taskId, processInstanceId, comment);
		}
		variables.put(ProcessConstant.PASS_KEY, flow.isPass());
		this.saveOrUpdate(bid);
		Business business = businessService.getById(bid.getBusinessId());
		businessService.saveOrUpdate(business);
		// 完成任务
		taskService.complete(taskId, variables);
		return true;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public BidFormDTO getDetail(String bidId) {
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
		if (Func.isNotEmpty(bid.getManagerId())) {
			if(Func.isNotEmpty(userService.getById(managerService.getById(bid.getManagerId()).getUserId()))) {
				bidFormDTO.setManagerName(userService.getById(managerService.getById(bid.getManagerId()).getUserId()).getName());
			}
		}
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
		if (Func.isNotEmpty(bid.getFileAttachId())) {
			String[] fls = bid.getFileAttachId().split(",");
			List<Upload> flist = new ArrayList<>();
			for (String fl : fls
			) {
				Attach attach = attachService.getById(fl);
				Upload upload = new Upload();
				upload.setAttachId(attach.getId().toString());
				upload.setDomain(attach.getDomain());
				upload.setName(attach.getName());
				upload.setFileName(attach.getOriginalName());
				upload.setFileSuffix(attach.getExtension());
				upload.setFileSize(Integer.parseInt(attach.getAttachSize().toString()) / 1024 + "kb");
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
		bidFormDTO.setClientId(business.getClientId().toString());
		bidFormDTO.setClientName(business.getClientName());
		bidFormDTO.setClientType(business.getClientType());
		bidFormDTO.setClientCategory(business.getClientCategory());
		bidFormDTO.setClientContact(business.getClientContact());
		bidFormDTO.setClientPhone(business.getClientPhone());
		bidFormDTO.setClientRelationship(business.getClientRelationship());
		return bidFormDTO;
	}

	//endregion

	//region 保证金流程
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean startbondProcess(BidbondDTO BidbondDTO) {
		//校验是否存在该表
		String businessTable = FlowUtil.getBusinessTable(ProcessConstant.BIDBOND_KEY);
		System.out.println("校验系统是否有表：" + businessTable);
		Bidbond bidbond = BidbondDTO.getBidbond();
		Bid bid = getById(bidbond.getId());
		if (Func.isEmpty(bid.getId())) {
			throw new ServiceException("当前项目不存在！");
		}
		bidbond.setCreateUser(Long.valueOf(AuthUtil.getUserId()));
		bidbond.setCreateDept(Long.valueOf(AuthUtil.getDeptId()));
		bidbond.setApplyTime(DateUtil.now());
		bidbond.setBondStatus(BondStatusEnum.APPLY.getValue());
		String fl = "";
		//附件表
		List<Upload> upload = BidbondDTO.getUpload();
		for (Upload m : upload) {
			if (Objects.equals(m.getUploadTip(), "操作成功")) {
				Attach attach = attachService.getById(m.getAttachId());
				attach.setBidType(m.getFileType());
				attachService.saveOrUpdate(attach);
				fl = fl + attach.getId() + ",";
			}
		}
		bidbond.setFileAttachId(fl);
		//加入对应的参数，即在
		Kv variables = Kv.create().set(ProcessConstant.TASK_VARIABLE_CREATE_USER, AuthUtil.getUserName());

		String processDefinitionId = flowEngineService.selectProcessPage(Condition.getPage(new Query()), "flow_8", 1).getRecords().get(0).getId();

		System.out.println("variables：" + variables.toString());
		bidbondService.saveOrUpdate(bidbond);
		// 启动流程
		BladeFlow flow = flowService.startProcessInstanceById(processDefinitionId, FlowUtil.getBusinessKey(businessTable, String.valueOf(bidbond.getId())), variables);


		if (Func.isNotEmpty(flow)) {
			log.debug("流程已启动,流程ID:" + flow.getProcessInstanceId());
			// 返回流程id写入business
			bidbond.setProcessInstanceId(flow.getProcessInstanceId());
			bidbond.setProcessDefinitionId(processDefinitionId);
			bidbond.setBondStatus(BidStatusEnum.BOND_F_WAIT.getValue());
			bid.setBidStatus(BidStatusEnum.BOND_F_WAIT.getValue());
			bid.setStatus(BidStatusEnum.BOND_F_WAIT.getValue());
			System.out.println("bidbond：" + bidbond.toString());
			this.saveOrUpdate(bid);
			bidbondService.saveOrUpdate(bidbond);
		} else {
			throw new ServiceException("开启流程失败");
		}

		return true;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean completeBondTask(BidbondDTO bidbondDTO) {
		//10.11下午
		BladeFlow flow = bidbondDTO.getFlow();
		Long bidid = bidbondDTO.getBidbond().getId();
		Bidbond bidbond = bidbondService.getById(bidid);
		Bid bid = this.getById(bidid);
		Integer bondStatus = bidbond.getBondStatus();
		String taskId = flow.getTaskId();
		String processInstanceId = flow.getProcessInstanceId();
		String comment = Func.toStr(flow.getComment(), ProcessConstant.PASS_COMMENT);
		Map<String, Object> variables = flow.getVariables();
		if (variables == null) {
			variables = Kv.create();
		}
		String IsOk = flow.getFlag();
		variables.put(ProcessConstant.PASS_KEY, flow.isPass());
		if (bondStatus.equals(BidStatusEnum.BOND_F_WAIT.getValue())) {
			if ("ok".equals(IsOk)) {
				variables.put("compass", "1");
				bidbond.setBondStatus(BidStatusEnum.BOND_F_SUCCESS.getValue());
				bid.setBidStatus(BidStatusEnum.BOND_F_SUCCESS.getValue());
				bid.setStatus(BidStatusEnum.BOND_Z_WAIT.getValue());
				comment += "(分公司保证金审核通过)";
			} else {
				variables.put("compass", "0");
				bidbond.setBondStatus(BondStatusEnum.REJECT.getValue());
				bid.setBidStatus(BidStatusEnum.BOND_F_REJECT.getValue());
				bid.setStatus(BidStatusEnum.BOND_LAUNCH.getValue());
				comment += "(分公司保证金审核不通过)";
			}
		} else {
			if ("ok".equals(IsOk)) {
				bidbond.setBondStatus(BidStatusEnum.BOND_Z_SUCCESS.getValue());
				bid.setBidStatus(BidStatusEnum.BOND_Z_SUCCESS.getValue());
				bid.setStatus(BidStatusEnum.OPEN_LAUNCH.getValue());
				comment += "(专业公司保证金审核通过)";
			} else {
				bidbond.setBondStatus(BondStatusEnum.REJECT.getValue());
				bid.setBidStatus(BidStatusEnum.BOND_Z_REJECT.getValue());
				bid.setStatus(BidStatusEnum.BOND_LAUNCH.getValue());
				comment += "(专业公司保证金审核不通过)";
			}
		}
		if (org.springblade.core.tool.utils.StringUtil.isNoneBlank(processInstanceId, comment)) {
			taskService.addComment(taskId, processInstanceId, comment);
		}
		this.saveOrUpdate(bid);
		bidbondService.saveOrUpdate(bidbond);
		// 完成任务
		taskService.complete(taskId, variables);
		return true;
	}

	//endregion

	//region 直接委托流程
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean startundertakeProcess(BidundertakeFormDTO bidundertakeFormDTO) {
		//校验是否存在该表
		Bid bid = this.getById(bidundertakeFormDTO.getId());
		if (Func.isEmpty(bid.getId())) {
			throw new ServiceException("当前项目不存在！");
		}
		Business business = businessService.getById(bid.getBusinessId());
		if (!"WT".equals(business.getBiddingType())) {
			throw new ServiceException("该投标项目招标方式不是直接委托，不能进行委托");
		}
		String businessTable = FlowUtil.getBusinessTable(ProcessConstant.BIDUNDERTAKE_KEY);
		System.out.println("校验系统是否有表：" + businessTable);
		Bidundertake bidundertake = new Bidundertake();
		business.setClientName(bidundertakeFormDTO.getClientName());
		business.setRegion(business.getRegion());
		businessService.saveOrUpdate(business);

		bidundertake.setId(bid.getId());
		bidundertake.setQualityType(bidundertakeFormDTO.getQualityType());
		bidundertake.setMajor(bidundertakeFormDTO.getMajor());
		bidundertake.setGrossRate(bidundertakeFormDTO.getGrossRate());
//		bidundertake.setManagerId(bidundertakeFormDTO.getManagerId());
		bid.setManagerId(bidundertakeFormDTO.getManagerId());
		bidundertake.setStartTime(bidundertakeFormDTO.getStartTime());
		bidundertake.setEndTime(bidundertakeFormDTO.getEndTime());
		bidundertake.setSchedulesTime(bidundertakeFormDTO.getSchedulesTime());
		bidundertake.setCreateUser(Long.valueOf(AuthUtil.getUserId()));
		bidundertake.setCreateDept(Long.valueOf(AuthUtil.getDeptId()));
		bidundertake.setApplyTime(DateUtil.now());
		bidundertake.setStatus(BondStatusEnum.APPLY.getValue());
		String fl = "";
		//附件表
		List<Upload> upload = bidundertakeFormDTO.getUpload();
		for (Upload m : upload) {
			if (Objects.equals(m.getUploadTip(), "操作成功")) {
				Attach attach = attachService.getById(m.getAttachId());
				attach.setBidType(m.getFileType());
				attachService.saveOrUpdate(attach);
				fl = fl + attach.getId() + ",";
			}
		}
		bidundertake.setFileAttachId(fl);
		//加入对应的参数，即在
		Kv variables = Kv.create().set(ProcessConstant.TASK_VARIABLE_CREATE_USER, AuthUtil.getUserName());

		String processDefinitionId = flowEngineService.selectProcessPage(Condition.getPage(new Query()), "flow_9", 1).getRecords().get(0).getId();

		System.out.println("variables：" + variables.toString());
		// 启动流程
		BladeFlow flow = flowService.startProcessInstanceById(processDefinitionId, FlowUtil.getBusinessKey(businessTable, String.valueOf(bid.getId())), variables);


		if (Func.isNotEmpty(flow)) {
			log.debug("流程已启动,流程ID:" + flow.getProcessInstanceId());
			// 返回流程id写入business
			bidundertake.setProcessInstanceId(flow.getProcessInstanceId());
			bidundertake.setProcessDefinitionId(processDefinitionId);
			bidundertake.setStatus(BidStatusEnum.CONTINUE_WAIT.getValue());
			bid.setBidStatus(BidStatusEnum.CONTINUE_WAIT.getValue());
			bid.setStatus(BidStatusEnum.CONTINUE_WAIT.getValue());
			System.out.println("bidundertake：" + bidundertake.toString());
			this.saveOrUpdate(bid);
			bidundertakeService.saveOrUpdate(bidundertake);
		} else {
			throw new ServiceException("开启流程失败");
		}

		return true;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean completeUndertakeTask(BidundertakeDTO bidundertakeDTO) {
		BladeFlow flow = bidundertakeDTO.getFlow();
		Bid bid = this.getById(bidundertakeDTO.getBid().getId());
		Bidundertake bidundertake = bidundertakeService.getById(bid.getId());
		String taskId = flow.getTaskId();
		String processInstanceId = flow.getProcessInstanceId();
		String comment = Func.toStr(flow.getComment(), ProcessConstant.PASS_COMMENT);
		Map<String, Object> variables = flow.getVariables();
		if (variables == null) {
			variables = Kv.create();
		}
		String IsOk = flow.getFlag();

		variables.put(ProcessConstant.PASS_KEY, flow.isPass());
		if ("ok".equals(IsOk)) {
			bidundertake.setStatus(BidStatusEnum.BID_SUCCESS.getValue());
			bid.setBidStatus(BidStatusEnum.BID_SUCCESS.getValue());
			bid.setStatus(BidStatusEnum.UNDERTAKE_SUCCESS.getValue());
			comment += "(委托审核通过)";
		} else {
			bidundertake.setStatus(BidStatusEnum.BID_REJECT.getValue());
			bid.setBidStatus(BidStatusEnum.BID_REJECT.getValue());
			bid.setStatus(BidStatusEnum.CONTINUE_LAUNCH.getValue());
			comment += "(委托审核不通过)";
		}

		if (org.springblade.core.tool.utils.StringUtil.isNoneBlank(processInstanceId, comment)) {
			taskService.addComment(taskId, processInstanceId, comment);
		}
		this.saveOrUpdate(bid);
		bidundertakeService.saveOrUpdate(bidundertake);
		// 完成任务
		taskService.complete(taskId, variables);
		return true;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public BidundertakeFormDTO undertakeDetail(String id) {
		Bid bid = this.getById(id);
		Business business = businessService.getById(bid.getBusinessId());
		BidundertakeFormDTO undertake = new BidundertakeFormDTO();
		undertake.setId(Long.valueOf(id));
		undertake.setClientName(business.getClientName());
		undertake.setClientId(business.getClientId());
		undertake.setRegion(business.getRegion());
		undertake.setRecordName(business.getRecordName());
		undertake.setMajor(business.getMajor());
		undertake.setRecordCode(business.getRecordCode());
		undertake.setManagerId(bid.getManagerId());
		if(Func.isNotEmpty(bid.getManagerId())) {
			undertake.setManagerName(userService.getById(managerService.getById(bid.getManagerId()).getUserId()).getName());
		}
		if (!Func.isEmpty(bidundertakeService.getById(id))) {
			Bidundertake bidundertake = bidundertakeService.getById(id);
			undertake.setQualityType(bidundertake.getQualityType());
			undertake.setGrossRate(bidundertake.getGrossRate());
			undertake.setStartTime(bidundertake.getStartTime());
			undertake.setEndTime(bidundertake.getEndTime());
			undertake.setSchedulesTime(bidundertake.getSchedulesTime());
			List<Upload> flist = new ArrayList<>();
			if (!Func.isEmpty(bidundertake.getFileAttachId())) {
				String[] fls = bidundertake.getFileAttachId().split(",");
				for (String fl : fls
				) {
					Attach attach = attachService.getById(fl);
					Upload upload = new Upload();
					upload.setAttachId(attach.getId().toString());
					upload.setDomain(attach.getDomain());
					upload.setName(attach.getName());
					upload.setFileName(attach.getOriginalName());
					upload.setFileSuffix(attach.getExtension());
					upload.setFileSize(Integer.parseInt(attach.getAttachSize().toString()) / 1024 + "kb");
					upload.setFileType(attach.getBidType());
					upload.setUploadTip("操作成功");
					flist.add(upload);
				}
				undertake.setUpload(flist);
			}
		}
		return undertake;
	}
	//endregion

	//region 参标单位
	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<BidcomVO> bidcomList(String bidId) {
		return bidcomMapper.selectBidcomList(Long.valueOf(bidId));
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean addcom(Bidcom bidcom) {
		bidcomService.save(bidcom);
		return true;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean detBidcom(String bidcomid) {
		bidcomService.removeById(bidcomid);
		return true;
	}
	//endregion

	//region 录入结果
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean startResultProcess(BidresultFormDTO bidresultFormDTO) {
		//校验是否存在该表
		Bid bid = this.getById(bidresultFormDTO.getId());
		if (Func.isEmpty(bid)) {
			throw new ServiceException("当前项目不存在！");
		}
		if ("30".equals(bid.getBidStatus())) {
			throw new ServiceException("当前项目未完成投标审核，无法进行录入结果！");
		}
		String businessTable = FlowUtil.getBusinessTable(ProcessConstant.BIDRESULT_KEY);
		System.out.println("校验系统是否有表：" + businessTable);
		Bidresult result = new Bidresult();
		result.setId(bidresultFormDTO.getId());
		result.setApplyTime(DateUtil.now());
		result.setCreateUser(Long.valueOf(AuthUtil.getUserId()));
		result.setCreateDept(Long.valueOf(AuthUtil.getDeptId()));
		result.setIsFail(bidresultFormDTO.getIsFail());
		result.setIsWin(bidresultFormDTO.getIsWin());
		if (bidresultFormDTO.getIsWin() == 0) {
			result.setBidCom(bidresultFormDTO.getBidCom());
			result.setStatus(BidStatusEnum.OPEN_OTHER.getValue());
			bid.setBidStatus(BidStatusEnum.OPEN_OTHER.getValue());
			if (bid.getIsNeedBond() == 1) {
				bid.setStatus(BidStatusEnum.IS_BOND_LAUNCH.getValue());
				Bidbond bidbond = bidbondService.getById(bid.getId());
				bidbond.setBondStatus(BidStatusEnum.IS_BOND_LAUNCH.getValue());
				bidbondService.saveOrUpdate(bidbond);
			} else {
				bid.setStatus(BidStatusEnum.BID_END.getValue());
			}
			this.saveOrUpdate(bid);
			bidresultService.saveOrUpdate(result);
			return true;
		}
		result.setWinAmount(bidresultFormDTO.getWinAmount());
		result.setWinbidTime(bidresultFormDTO.getWinbidTime());
		result.setQuotationMethod(bidresultFormDTO.getQuotationMethod());
		result.setOffer(bidresultFormDTO.getOffer());
		result.setDiscount(bidresultFormDTO.getDiscount());
		result.setDropPoint(bidresultFormDTO.getDropPoint());
		result.setContinueDept(bidresultFormDTO.getContinueDept());
		result.setGrossRate(bidresultFormDTO.getGrossRate());
		result.setServiceCycle(bidresultFormDTO.getServiceCycle());
		String fl = "";
		//附件表
		List<Upload> upload = bidresultFormDTO.getUpload();
		for (Upload m : upload) {
			if (Objects.equals(m.getUploadTip(), "操作成功")) {
				Attach attach = attachService.getById(m.getAttachId());
				attach.setBidType(m.getFileType());
				attachService.saveOrUpdate(attach);
				fl = fl + attach.getId() + ",";
			}
		}
		result.setFileAttachId(fl);
		//加入对应的参数，即在
		Kv variables = Kv.create().set(ProcessConstant.TASK_VARIABLE_CREATE_USER, AuthUtil.getUserName());

		String processDefinitionId = flowEngineService.selectProcessPage(Condition.getPage(new Query()), "flow_10", 1).getRecords().get(0).getId();

		System.out.println("variables：" + variables.toString());
		// 启动流程
		BladeFlow flow = flowService.startProcessInstanceById(processDefinitionId, FlowUtil.getBusinessKey(businessTable, String.valueOf(bid.getId())), variables);


		if (Func.isNotEmpty(flow)) {
			log.debug("流程已启动,流程ID:" + flow.getProcessInstanceId());
			// 返回流程id写入business
			result.setProcessInstanceId(flow.getProcessInstanceId());
			result.setProcessDefinitionId(processDefinitionId);
			result.setStatus(BidStatusEnum.OPEN_WAIT.getValue());
			bid.setBidStatus(BidStatusEnum.OPEN_WAIT.getValue());
			bid.setStatus(BidStatusEnum.OPEN_WAIT.getValue());
			System.out.println("result：" + result.toString());
			this.saveOrUpdate(bid);
			bidresultService.saveOrUpdate(result);
		} else {
			throw new ServiceException("开启流程失败");
		}

		return true;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean completeResultTask(BidresultDTO bidresultDTO) {
		BladeFlow flow = bidresultDTO.getFlow();
		Bid bid = this.getById(bidresultDTO.getBidresult().getId());
		Bidresult bidresult = bidresultService.getById(bid.getId());
		String taskId = flow.getTaskId();
		String processInstanceId = flow.getProcessInstanceId();
		String comment = Func.toStr(flow.getComment(), ProcessConstant.PASS_COMMENT);
		Map<String, Object> variables = flow.getVariables();
		if (variables == null) {
			variables = Kv.create();
		}
		String IsOk = flow.getFlag();

		variables.put(ProcessConstant.PASS_KEY, flow.isPass());
		if ("ok".equals(IsOk)) {
			bidresult.setStatus(BidStatusEnum.OPEN_SUCCESS.getValue());
			bid.setBidStatus(BidStatusEnum.OPEN_SUCCESS.getValue());
			bid.setStatus(BidStatusEnum.UNDERTAKE_SUCCESS.getValue());
			comment += "(中标审核通过)";

			//录入新承接列表
			Bidundertake bidundertake = new Bidundertake();
			bidundertake.setId(bid.getId());
			bidundertake.setApplyTime(DateUtil.now());
			bidundertakeService.saveOrUpdate(bidundertake);
		} else {
			bidresult.setStatus(BidStatusEnum.OPEN_REJECT.getValue());
			bid.setBidStatus(BidStatusEnum.OPEN_REJECT.getValue());
			bid.setStatus(BidStatusEnum.OPEN_LAUNCH.getValue());
			comment += "(中标审核不通过)";
		}

		if (org.springblade.core.tool.utils.StringUtil.isNoneBlank(processInstanceId, comment)) {
			taskService.addComment(taskId, processInstanceId, comment);
		}
		this.saveOrUpdate(bid);
		bidresultService.saveOrUpdate(bidresult);
		// 完成任务
		taskService.complete(taskId, variables);
		return true;
	}
	//endregion

	//保证金退还
	@Override
	public boolean BondCovery(String id){
		Bidbond bidbond = bidbondService.getById(id);
		if(bidbond.getBondStatus()!=50){
			throw new ServiceException("当前状态不可发起保证金回收流程");
		}
		bidbond.setBondStatus(52);
		Bid bid = this.getById(id);
		bid.setStatus(52);
		this.saveOrUpdate(bid);
		bidbondService.saveOrUpdate(bidbond);
		return true;
	}

	@Override
	public IPage<BidVO> selectBidListVO(IPage page, BidVO bid) {
		List<BidVO> BidVOList	=  baseMapper.selectBidListVO(page,bid);
		for (BidVO temp:BidVOList){
			temp.setMajorName(imajorService.getName(temp.getMajor()));
		}
		return page.setRecords(BidVOList);
	}

}
