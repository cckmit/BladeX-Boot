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
import org.springblade.modules.project.entity.Enclosure;
import org.springblade.modules.project.vo.EnclosureVO;
import org.springblade.modules.project.wrapper.EnclosureWrapper;
import org.springblade.modules.project.service.IEnclosureService;
import org.springblade.core.boot.ctrl.BladeController;

/**
 *  控制器
 *
 * @author BladeX
 * @since 2021-08-07
 */
@RestController
@AllArgsConstructor
@RequestMapping("blade-project/enclosure")
@Api(value = "", tags = "接口")
public class EnclosureController extends BladeController {

	private final IEnclosureService enclosureService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入enclosure")
	public R<EnclosureVO> detail(Enclosure enclosure) {
		Enclosure detail = enclosureService.getOne(Condition.getQueryWrapper(enclosure));
		return R.data(EnclosureWrapper.build().entityVO(detail));
	}

	/**
	 * 分页 
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入enclosure")
	public R<IPage<EnclosureVO>> list(Enclosure enclosure, Query query) {
		IPage<Enclosure> pages = enclosureService.page(Condition.getPage(query), Condition.getQueryWrapper(enclosure));
		return R.data(EnclosureWrapper.build().pageVO(pages));
	}


	/**
	 * 自定义分页 
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入enclosure")
	public R<IPage<EnclosureVO>> page(EnclosureVO enclosure, Query query) {
		IPage<EnclosureVO> pages = enclosureService.selectEnclosurePage(Condition.getPage(query), enclosure);
		return R.data(pages);
	}

	/**
	 * 新增 
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入enclosure")
	public R save(@Valid @RequestBody Enclosure enclosure) {
		return R.status(enclosureService.save(enclosure));
	}

	/**
	 * 修改 
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入enclosure")
	public R update(@Valid @RequestBody Enclosure enclosure) {
		return R.status(enclosureService.updateById(enclosure));
	}

	/**
	 * 新增或修改 
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入enclosure")
	public R submit(@Valid @RequestBody Enclosure enclosure) {
		return R.status(enclosureService.saveOrUpdate(enclosure));
	}

	
	/**
	 * 删除 
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(enclosureService.removeByIds(Func.toLongList(ids)));
	}

	
}
