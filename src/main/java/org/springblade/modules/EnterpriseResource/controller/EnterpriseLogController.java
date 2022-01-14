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
package org.springblade.modules.EnterpriseResource.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.EnterpriseResource.entity.EnterpriseLog;
import org.springblade.modules.EnterpriseResource.vo.EnterpriseLogVO;
import org.springblade.modules.EnterpriseResource.wrapper.EnterpriseLogWrapper;
import org.springblade.modules.EnterpriseResource.service.IEnterpriseLogService;
import org.springblade.core.boot.ctrl.BladeController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 日志表 控制器
 *
 * @author BladeX
 * @since 2022-01-06
 */
@RestController
@AllArgsConstructor
@RequestMapping("blade-resource/enterpriselog")
@Api(value = "日志表", tags = "日志表接口")
public class EnterpriseLogController extends BladeController {

	private final IEnterpriseLogService enterpriseLogService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入enterpriseLog")
	public R<EnterpriseLogVO> detail(EnterpriseLog enterpriseLog) {
		EnterpriseLog detail = enterpriseLogService.getOne(Condition.getQueryWrapper(enterpriseLog));
		return R.data(EnterpriseLogWrapper.build().entityVO(detail));
	}

	/**
	 * 分页 日志表
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入enterpriseLog")
	public R<IPage<EnterpriseLogVO>> list(EnterpriseLog enterpriseLog, Query query) {
		IPage<EnterpriseLog> pages = enterpriseLogService.page(Condition.getPage(query), Condition.getQueryWrapper(enterpriseLog));
		return R.data(EnterpriseLogWrapper.build().pageVO(pages));
	}


	/**
	 * 自定义分页 日志表
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入enterpriseLog")
	public R<IPage<EnterpriseLogVO>> page(EnterpriseLogVO enterpriseLog, Query query) {
		IPage<EnterpriseLogVO> pages = enterpriseLogService.selectEnterpriseLogPage(Condition.getPage(query), enterpriseLog);
		return R.data(pages);
	}

	/**
	 * 新增 日志表
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入enterpriseLog")
	public R save(@Valid @RequestBody EnterpriseLog enterpriseLog) {
		return R.status(enterpriseLogService.save(enterpriseLog));
	}

	/**
	 * 修改 日志表
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入enterpriseLog")
	public R update(@Valid @RequestBody EnterpriseLog enterpriseLog) {
		return R.status(enterpriseLogService.updateById(enterpriseLog));
	}

	/**
	 * 新增或修改 日志表
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入enterpriseLog")
	public R submit(@Valid @RequestBody EnterpriseLog enterpriseLog) {
		return R.status(enterpriseLogService.saveOrUpdate(enterpriseLog));
	}


	/**
	 * 删除 日志表
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(enterpriseLogService.removeByIds(Func.toLongList(ids)));
	}

	/**
	 * 查询不同状态下的集合
	 */
	@GetMapping("/status")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "查询不同状态下的集合", notes = "")
	public R status() {
	List<EnterpriseLogVO>	list0 = enterpriseLogService.selectStatus0();
	List<EnterpriseLogVO>	list1=	enterpriseLogService.selectStatus1();
	Map map = new HashMap();
		map.put("list0",list0);
		map.put("list1",list1);

		return	R.data(map);
	}
}
