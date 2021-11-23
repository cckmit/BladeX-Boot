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
package org.springblade.modules.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.modules.project.dto.*;
import org.springblade.modules.project.entity.Bid;
import org.springblade.modules.project.entity.Bidcom;
import org.springblade.modules.project.vo.BidVO;
import org.springblade.modules.project.vo.BidcomVO;

import java.util.List;

/**
 *  服务类
 *
 * @author BladeX
 * @since 2021-07-18
 */
public interface IBidService extends IService<Bid> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param bid
	 * @return
	 */
	IPage<BidVO> selectBidPage(IPage<BidVO> page, BidVO bid);
	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param bid
	 * @return
	 */
	IPage<BidListDTO> selectBidList(IPage<BidListDTO> page, BidListDTO bid);




	/**
	 * 根据商机主键获取投标信息
	 * @param businessId
	 * @return
	 */
	Bid getBidByBusinessId(long businessId);

//	/**
//	 * 终止投标流程
//	 * @param cancelDTO 终止实体
//	 * @return
//	 */
//	Boolean stopBid(BidToVoidDTO cancelDTO);

	/**
	 * 发起投标申请
	 * @param applyDTO
	 * @return
	 */
	Boolean saveBidApply(BidApplyDTO applyDTO);

	/**
	 * 推送至投标
	 *
	 * @param businessId
	 * @return boolean
	 */
	boolean pushToBid(long businessId);

	/**
	 * 开启投标报废流程
	 *
	 * @param bidId,reason
	 * @return boolean
	 */
	boolean startBidcancelProcess(Long bidId,String reason);
	/**
	 * 审核投标报废流程
	 *
	 * @param bidCancelDTO
	 * @return boolean
	 */
	boolean completeCancelTask(BidCancelDTO bidCancelDTO);
	/**
	 * 开启投标流程
	 *
	 * @param bidFormDTO
	 * @return boolean
	 */
	boolean startBidProcess(BidFormDTO bidFormDTO);
	/**
	 * 投标详细流程
	 *
	 * @param bidId
	 * @return BidDTO
	 */
	BidDTO getBidDetail(String bidId);
	/**
	 * 开启投标流程
	 *
	 * @param bidDTO
	 * @return boolean
	 */
	boolean completeBidTask(BidDTO bidDTO);

	/**
	 * 相关流程详细信息
	 *
	 * @param bidId
	 * @return BidFormDTO
	 */
	BidFormDTO getDetail(String bidId);
	/**
	 * 开启保证金流程
	 *
	 * @param BidbondDTO
	 * @return boolean
	 */
	boolean startbondProcess(BidbondDTO BidbondDTO);
	/**
	 * 保证金审核流程
	 *
	 * @parambidbondDTO
	 * @return boolean
	 */
	boolean completeBondTask(BidbondDTO bidbondDTO);
	/**
	 * 开启委托流程
	 *
	 * @param bidundertakeFormDTO
	 * @return boolean
	 */
	boolean startundertakeProcess(BidundertakeFormDTO bidundertakeFormDTO);
	/**
	 * 委托流程审核流程
	 *
	 * @param bidundertakeDTO
	 * @return boolean
	 */
	boolean completeUndertakeTask(BidundertakeDTO bidundertakeDTO);

	BidundertakeFormDTO undertakeDetail(String id);

	List<BidcomVO> bidcomList(String bidId);

	boolean addcom(Bidcom bidcom);

	boolean detBidcom(String bidcomid);

	boolean startResultProcess(BidresultFormDTO bidresultFormDTO);

	boolean completeResultTask(BidresultDTO bidresultDTO);

	BidFlowDTO bidallflow(String bidId);

	boolean BondCovery(String id);

	/***********************************************手机端接口*********************************************************************/

	/**
	 *
	 *手机端列表+各种高级筛选查询
	 *
	 * @param page
	 * @param bid
	 * @return
	 */
	IPage<BidVO> selectBidListVO (IPage page ,BidVO bid);

}
