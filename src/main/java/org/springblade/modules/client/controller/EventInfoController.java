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

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import org.springblade.core.tool.utils.StringUtil;
import org.springblade.modules.client.entity.EventInfo;
import org.springblade.modules.client.service.EventInfoService;
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
@RequestMapping("blade-client/eventinfo")
@Api(value = "客户事件", tags = "客户事件")
public class EventInfoController extends BladeController {

	private final EventInfoService eventInfoService;

	private BladeLogger logger;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入eventInfo")
	@ApiLog("客户事件详情")
	public R<EventInfo> detail(EventInfo eventInfo) {
		EventInfo detail = eventInfoService.getOne(Condition.getQueryWrapper(eventInfo));
		logger.info(detail.getId().toString(), JsonUtil.toJson(detail));
		return R.data(detail);
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入eventInfo")
	@ApiLog("客户事件列表")
	public R<IPage<EventInfo>> list(EventInfo eventInfo, Query query) {
		String eventTitle = eventInfo.getEventTitle();
		String clientName = eventInfo.getClientName();
		QueryWrapper<EventInfo> queryWrapper = Condition.getQueryWrapper(eventInfo);
		if (StringUtil.isNotBlank(eventTitle)) {
			queryWrapper.like("event_title", eventTitle);
			eventInfo.setEventTitle(null);
		}
		if (StringUtil.isNotBlank(clientName)) {
			queryWrapper.like("client_name", clientName);
			eventInfo.setClientName(null);
		}
		//查询客户信息
		IPage<EventInfo> pages = eventInfoService.page(Condition.getPage(query.setDescs("create_time")), queryWrapper);
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
	@ApiOperation(value = "新增", notes = "传入eventInfo")
	public R save(@Valid @RequestBody EventInfo eventInfo) {
		boolean save = eventInfoService.save(eventInfo);
		if (save) {
			logger.info(eventInfo.getId().toString(), JsonUtil.toJson(eventInfo));
		}
		return R.status(save);
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入eventInfo")
	@ApiLog("客户事件编辑")
	public R update(@Valid @RequestBody EventInfo eventInfo) {
		boolean flag = eventInfoService.updateById(eventInfo);
		if (flag) {
			logger.info(eventInfo.getId().toString(), JsonUtil.toJson(eventInfo));
		}
		return R.status(flag);
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入eventInfo")
	public R submit(@Valid @RequestBody EventInfo eventInfo) {
		boolean flag = eventInfoService.saveOrUpdate(eventInfo);
		if (flag) {
			logger.info(eventInfo.getId().toString(), JsonUtil.toJson(eventInfo));
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
		return R.status(eventInfoService.removeByIds(Func.toLongList(ids)));
	}

}
