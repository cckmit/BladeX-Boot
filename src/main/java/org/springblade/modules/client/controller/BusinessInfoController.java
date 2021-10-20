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
package org.springblade.modules.client.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.log.annotation.ApiLog;
import org.springblade.core.log.logger.BladeLogger;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.jackson.JsonUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.modules.client.entity.BusinessInfo;
import org.springblade.modules.client.service.BusinessInfoService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 *  控制器
 *
 * @author BladeX
 * @since 2021-06-26
 */
@RestController
@AllArgsConstructor
@RequestMapping("blade-client/businessinfo")
@Api(value = "客户商机", tags = "客户商机")
public class BusinessInfoController extends BladeController {

	private final BusinessInfoService businessInfoService;

	private BladeLogger logger;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入businessInfo")
	@ApiLog("客户商机详情")
	public R<BusinessInfo> detail(BusinessInfo businessInfo) {
		BusinessInfo detail = businessInfoService.getOne(Condition.getQueryWrapper(businessInfo));
		logger.info(detail.getId().toString(), JsonUtil.toJson(detail));
		return R.data(detail);
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入businessInfo")
	@ApiLog("客户商机列表")
	public R<IPage<BusinessInfo>> list(BusinessInfo businessInfo, Query query) {
		//查询客户信息
		IPage<BusinessInfo> pages = businessInfoService.page(Condition.getPage(query.setDescs("create_time")), Condition.getQueryWrapper(businessInfo));
		//日志记录
		pages.getRecords().forEach(item -> {
			logger.info(item.getId().toString(), JsonUtil.toJson(item));
		});
		return R.data(pages);
	}

	/**
	 * 新增
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入businessInfo")
	public R save(@Valid @RequestBody BusinessInfo businessInfo) {
		boolean save = businessInfoService.save(businessInfo);
		if (save) {
			logger.info(businessInfo.getId().toString(), JsonUtil.toJson(businessInfo));
		}
		return R.status(save);
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入businessInfo")
	@ApiLog("客户商机编辑")
	public R update(@Valid @RequestBody BusinessInfo businessInfo) {
		boolean flag = businessInfoService.updateById(businessInfo);
		if (flag) {
			logger.info(businessInfo.getId().toString(), JsonUtil.toJson(businessInfo));
		}
		return R.status(flag);
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入businessInfo")
	public R submit(@Valid @RequestBody BusinessInfo businessInfo) {
		boolean flag = businessInfoService.saveOrUpdate(businessInfo);
		if (flag) {
			logger.info(businessInfo.getId().toString(), JsonUtil.toJson(businessInfo));
		}
		return R.status(flag);
	}


	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(businessInfoService.removeByIds(Func.toLongList(ids)));
	}

}
