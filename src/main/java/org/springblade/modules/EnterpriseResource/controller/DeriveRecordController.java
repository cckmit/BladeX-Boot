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
import org.springblade.modules.EnterpriseResource.entity.DeriveRecord;
import org.springblade.modules.EnterpriseResource.vo.DeriveRecordVO;
import org.springblade.modules.EnterpriseResource.wrapper.DeriveRecordWrapper;
import org.springblade.modules.EnterpriseResource.service.IDeriveRecordService;
import org.springblade.core.boot.ctrl.BladeController;

/**
 * 记录表 控制器
 *
 * @author BladeX
 * @since 2022-01-13
 */
@RestController
@AllArgsConstructor
@RequestMapping("blade-resource/deriverecord")
@Api(value = "记录表", tags = "记录表接口")
public class DeriveRecordController extends BladeController {

	private final IDeriveRecordService deriveRecordService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入deriveRecord")
	public R<DeriveRecordVO> detail(DeriveRecord deriveRecord) {
		DeriveRecord detail = deriveRecordService.getOne(Condition.getQueryWrapper(deriveRecord));
		return R.data(DeriveRecordWrapper.build().entityVO(detail));
	}

	/**
	 * 分页 记录表
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入deriveRecord")
	public R<IPage<DeriveRecordVO>> list(DeriveRecord deriveRecord, Query query) {
		IPage<DeriveRecord> pages = deriveRecordService.page(Condition.getPage(query), Condition.getQueryWrapper(deriveRecord));
		return R.data(DeriveRecordWrapper.build().pageVO(pages));
	}


	/**
	 * 自定义分页 记录表
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入deriveRecord")
	public R<IPage<DeriveRecordVO>> page(DeriveRecordVO deriveRecord, Query query) {
		IPage<DeriveRecordVO> pages = deriveRecordService.selectDeriveRecordPage(Condition.getPage(query), deriveRecord);
		return R.data(pages);
	}

	/**
	 * 新增 记录表
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入deriveRecord")
	public R save(@Valid @RequestBody DeriveRecord deriveRecord) {
		return R.status(deriveRecordService.save(deriveRecord));
	}

	/**
	 * 修改 记录表
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入deriveRecord")
	public R update(@Valid @RequestBody DeriveRecord deriveRecord) {
		return R.status(deriveRecordService.updateById(deriveRecord));
	}

	/**
	 * 新增或修改 记录表
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入deriveRecord")
	public R submit(@Valid @RequestBody DeriveRecord deriveRecord) {
		return R.status(deriveRecordService.saveOrUpdate(deriveRecord));
	}

	
	/**
	 * 删除 记录表
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(deriveRecordService.removeByIds(Func.toLongList(ids)));
	}

	
}
