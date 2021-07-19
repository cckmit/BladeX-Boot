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
import org.springblade.modules.project.entity.Change;
import org.springblade.modules.project.vo.ChangeVO;
import org.springblade.modules.project.wrapper.ChangeWrapper;
import org.springblade.modules.project.service.IChangeService;
import org.springblade.core.boot.ctrl.BladeController;

/**
 *  控制器
 *
 * @author BladeX
 * @since 2021-07-14
 */
@RestController
@AllArgsConstructor
@RequestMapping("blade-project/change")
@Api(value = "", tags = "接口")
public class ChangeController extends BladeController {

	private final IChangeService changeService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入change")
	public R<ChangeVO> detail(Change change) {
		Change detail = changeService.getOne(Condition.getQueryWrapper(change));
		return R.data(ChangeWrapper.build().entityVO(detail));
	}

	/**
	 * 分页 
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入change")
	public R<IPage<ChangeVO>> list(Change change, Query query) {
		IPage<Change> pages = changeService.page(Condition.getPage(query), Condition.getQueryWrapper(change));
		return R.data(ChangeWrapper.build().pageVO(pages));
	}


	/**
	 * 自定义分页 
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入change")
	public R<IPage<ChangeVO>> page(ChangeVO change, Query query) {
		IPage<ChangeVO> pages = changeService.selectChangePage(Condition.getPage(query), change);
		return R.data(pages);
	}

	/**
	 * 新增 
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入change")
	public R save(@Valid @RequestBody Change change) {
		return R.status(changeService.save(change));
	}

	/**
	 * 修改 
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入change")
	public R update(@Valid @RequestBody Change change) {
		return R.status(changeService.updateById(change));
	}

	/**
	 * 新增或修改 
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入change")
	public R submit(@Valid @RequestBody Change change) {
		return R.status(changeService.saveOrUpdate(change));
	}

	
	/**
	 * 删除 
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(changeService.removeByIds(Func.toLongList(ids)));
	}

	
}
