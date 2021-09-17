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
package org.springblade.modules.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.modules.project.dto.*;
import org.springblade.modules.project.entity.Bid;
import org.springblade.modules.project.entity.Bidcom;
import org.springblade.modules.project.service.IBidService;
import org.springblade.modules.project.service.IBusinessService;
import org.springblade.modules.project.vo.BidVO;
import org.springblade.modules.project.vo.BidcomVO;
import org.springblade.modules.project.wrapper.BidWrapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 *  控制器
 *
 * @author BladeX
 * @since 2021-07-18
 */
@RestController
@AllArgsConstructor
@RequestMapping("blade-project/bid")
@Api(value = "", tags = "接口")
public class BidController extends BladeController {

	private final IBidService bidService;
	private final IBusinessService businessService;

	//region 基础接口
	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入bid")
	public R<BidFormDTO> detail(String bidId) {
		BidFormDTO detail = bidService.getDetail(bidId);
		return R.data(detail);
	}

	/**
	 * 分页
	 */
	@GetMapping("/lists")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入bid")
	public R<IPage<BidVO>> list(Bid bid, Query query) {
		IPage<Bid> pages = bidService.page(Condition.getPage(query), Condition.getQueryWrapper(bid));
		return R.data(BidWrapper.build().pageVO(pages));
	}


	/**
	 * 自定义分页
	 */
//	@GetMapping("/page")
//	@ApiOperationSupport(order = 3)
//	@ApiOperation(value = "分页", notes = "传入bid")
//	public R<IPage<BidVO>> page(BidVO bid, Query query) {
//		IPage<BidVO> pages = bidService.selectBidPage(Condition.getPage(query), bid);
//		return R.data(pages);
//	}

	/**
	 * 新增
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入bid")
	public R save(@Valid @RequestBody Bid bid) {
		return R.status(bidService.save(bid));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入bid")
	public R update(@Valid @RequestBody Bid bid) {
		return R.status(bidService.updateById(bid));
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入bid")
	public R submit(@Valid @RequestBody BidFormDTO bidFormDTO) {
		bidService.startBidProcess(bidFormDTO);
		return R.status(true);
	}


	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(bidService.removeByIds(Func.toLongList(ids)));
	}


//	/**
//	 * 终止投标
//	 * @return
//	 */
//	@PostMapping("/stop")
//	public R stopBid(@Valid @RequestBody BidToVoidDTO cancelDTO)
//	{
//		return R.status(bidService.stopBid(cancelDTO));
//	}
	//endregion

	//region 流程接口
	/**
	 * 列表分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入bid")
	public R<IPage<BidFormDTO>> page(BidVO bid, Query query) {
		IPage<BidFormDTO> pages = bidService.selectBidList(Condition.getPage(query), bid);
		return R.data(pages);
	}
	/**
	 * 推送到投标
	 * @return
	 */
	@PostMapping("/pushToBid")
	public R pushToBid(long businessId)
	{
		return R.status(bidService.pushToBid(businessId));
	}

	@PostMapping("/start-bidprocess")
	@ApiOperation(value = "开启流程", notes = "传入流程信息")
	public R startbidProcess(@RequestBody BidFormDTO bidFormDTO) {
		return R.status(bidService.startBidProcess(bidFormDTO));
	}
	/**
	 * 流程详情
	 */
	@GetMapping("/flow-biddetail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入bid")
	public R<BidDTO> biddetail(String bidId) {
		BidDTO detail = bidService.getBidDetail(bidId);
		return R.data(detail);
	}
	/**
	 * 投标流程审核环节
	 *
	 * @param
	 */
	@PostMapping("/complete-bidtask")
	@ApiOperation(value = "审核流程", notes = "传入流程信息")
	public R bidHandle(@RequestBody BidDTO bidDTO){
		return R.status(bidService.completeBidTask(bidDTO));
	}

	/**
	 * 开启投标作废流程
	 *
	 * @param bidId,reason
	 */
	@PostMapping("/start-cancelprocess")
	@ApiOperation(value = "开启流程", notes = "传入流程信息")
	public R startcancelProcess(Long bidId,String reason) {
		return R.status(bidService.startBidcancelProcess(bidId,reason));
	}

	/**
	 * 投标作废流程审核环节
	 *
	 * @param
	 */
	@PostMapping("/complete-canceltask")
	@ApiOperation(value = "审核流程", notes = "传入流程信息")
	public R cancelHandle(@RequestBody BidCancelDTO bidCancelDTO){
		return R.status(bidService.completeCancelTask(bidCancelDTO));
	}

	/**
	 * 开启保证金流程
	 *
	 * @param BidbondDTO
	 */
	@PostMapping("/start-bondprocess")
	@ApiOperation(value = "开启流程", notes = "传入流程信息")
	public R startbondProcess(@RequestBody BidbondDTO BidbondDTO) {
		return R.status(bidService.startbondProcess(BidbondDTO));
	}
	/**
	 * 保证金流程审核环节
	 *
	 * @param
	 */
	@PostMapping("/complete-bondtask")
	@ApiOperation(value = "审核流程", notes = "传入流程信息")
	public R bondHandle(@RequestBody BidbondDTO bidbondDTO){
		return R.status(bidService.completeBondTask(bidbondDTO));
	}

	/**
	 * 开启承接流程
	 *
	 * @param bidundertakeFormDTO
	 */
	@PostMapping("/start-undertakeprocess")
	@ApiOperation(value = "开启流程", notes = "传入流程信息")
	public R startundertakeProcess(@RequestBody BidundertakeFormDTO bidundertakeFormDTO) {
		return R.status(bidService.startundertakeProcess(bidundertakeFormDTO));
	}
	/**
	 * 承接流程审核环节
	 *
	 * @param bidundertakeDTO
	 */
	@PostMapping("/complete-undertaketask")
	@ApiOperation(value = "审核流程", notes = "传入流程信息")
	public R undertakeHandle(@RequestBody BidundertakeDTO bidundertakeDTO){
		return R.status(bidService.conpleteUndertakeTask(bidundertakeDTO));
	}
	/**
	 * 承接流程审核环节
	 *
	 * @param bidId
	 */
	@PostMapping("/flow-undertakedetail")
	@ApiOperation(value = "审核流程", notes = "传入流程信息")
	public R<BidundertakeFormDTO> undertakeDetail(String bidId){
		return R.data(bidService.undertakeDetail(bidId));
	}
	/**
	 * 承接流程审核环节
	 *
	 * @param bidId
	 */
	@GetMapping("/BidComList")
	@ApiOperation(value = "参标单位列表", notes = "传入bidid")
	public R<List<BidcomVO>> bidcomList(String bidId){
		return R.data(bidService.bidcomList(bidId));
	}

	@PostMapping("/addBidCom")
	@ApiOperation(value = "参标单位列表", notes = "传入bidid")
	public R addBidCom(@RequestBody Bidcom bidcom){
		return R.data(bidService.addcom(bidcom));
	}

	@PostMapping("/detBidCom")
	@ApiOperation(value = "参标单位列表", notes = "传入bidcomid")
	public R detBidCom(String bidcomid){
		return R.data(bidService.detBidcom(bidcomid));
	}
	//endregion
}
