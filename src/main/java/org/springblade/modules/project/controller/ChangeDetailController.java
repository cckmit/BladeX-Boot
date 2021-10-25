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
import org.springblade.modules.project.entity.ChangeDetail;
import org.springblade.modules.project.service.IChangeDetailService;
import org.springblade.modules.project.vo.ChangeDetailVO;
import org.springblade.modules.project.wrapper.ChangeDetailWrapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 *
 *
 * @author BladeX
 * @since 2021-07-14
 */
@RestController
@AllArgsConstructor
@RequestMapping("blade-project/changedetail")
@Api(value = "", tags = "接口")
public class ChangeDetailController extends BladeController {

	private final IChangeDetailService changeDetailService = null;
	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 401)
	@ApiOperation(value = "商机报备修改情况详情", notes = "传入changeDetail")
	public R<ChangeDetailVO> detail(ChangeDetail changeDetail) {
		ChangeDetail detail = changeDetailService.getOne(Condition.getQueryWrapper(changeDetail));
		return R.data(ChangeDetailWrapper.build().entityVO(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 402)
	@ApiOperation(value = "商机报备修改情况分页", notes = "传入changeDetail")
	public R<IPage<ChangeDetailVO>> list(ChangeDetail changeDetail, Query query) {
		IPage<ChangeDetail> pages = changeDetailService.page(Condition.getPage(query), Condition.getQueryWrapper(changeDetail));
		return R.data(ChangeDetailWrapper.build().pageVO(pages));
	}


	/**
	 * 自定义分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 403)
	@ApiOperation(value = "商机报备修改情况分页", notes = "传入changeDetail")
	public R<IPage<ChangeDetailVO>> page(ChangeDetailVO changeDetail, Query query) {
		IPage<ChangeDetailVO> pages = changeDetailService.selectChangeDetailPage(Condition.getPage(query), changeDetail);
		return R.data(pages);
	}

	/**
	 * 新增
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 404)
	@ApiOperation(value = "商机报备修改情况新增", notes = "传入changeDetail")
	public R save(@Valid @RequestBody ChangeDetail changeDetail) {
		return R.status(changeDetailService.save(changeDetail));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 405)
	@ApiOperation(value = "商机报备修改情况修改", notes = "传入changeDetail")
	public R update(@Valid @RequestBody ChangeDetail changeDetail) {
		return R.status(changeDetailService.updateById(changeDetail));
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 406)
	@ApiOperation(value = "商机报备修改情况新增或修改", notes = "传入changeDetail")
	public R submit(@Valid @RequestBody ChangeDetail changeDetail) {
		return R.status(changeDetailService.saveOrUpdate(changeDetail));
	}


	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 407)
	@ApiOperation(value = "商机报备修改情况删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(changeDetailService.removeByIds(Func.toLongList(ids)));
	}


}
