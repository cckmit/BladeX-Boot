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
import org.springblade.modules.EnterpriseResource.vo.ProjectCaseVO;
import org.springblade.modules.EnterpriseResource.vo.SolveSchemeDemo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.EnterpriseResource.entity.SolveScheme;
import org.springblade.modules.EnterpriseResource.vo.SolveSchemeVO;
import org.springblade.modules.EnterpriseResource.wrapper.SolveSchemeWrapper;
import org.springblade.modules.EnterpriseResource.service.ISolveSchemeService;
import org.springblade.core.boot.ctrl.BladeController;

import java.util.List;

/**
 * 解决方案表 控制器
 *
 * @author BladeX
 * @since 2021-09-28
 */
@RestController
@AllArgsConstructor
@RequestMapping("blade-resource/solvescheme")
@Api(value = "解决方案表", tags = "解决方案表接口")
public class SolveSchemeController extends BladeController {

	private final ISolveSchemeService solveSchemeService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入solveScheme")
	public R<SolveSchemeVO> detail(SolveScheme solveScheme) {
		SolveScheme detail = solveSchemeService.getOne(Condition.getQueryWrapper(solveScheme));
		return R.data(SolveSchemeWrapper.build().entityVO(detail));
	}

	/**
	 * 分页 解决方案表
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入solveScheme")
	public R<IPage<SolveSchemeVO>> list(SolveScheme solveScheme, Query query) {
		IPage<SolveScheme> pages = solveSchemeService.page(Condition.getPage(query), Condition.getQueryWrapper(solveScheme));
		return R.data(SolveSchemeWrapper.build().pageVO(pages));
	}


	/**
	 * 自定义分页 解决方案表
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入solveScheme")
	public R<IPage<SolveSchemeVO>> page(SolveSchemeVO solveScheme, Query query) {
		IPage<SolveSchemeVO> pages = solveSchemeService.selectSolveSchemePage(Condition.getPage(query), solveScheme);
		return R.data(pages);
	}

	/**
	 * 新增 解决方案表
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入SolveSchemeDemo")
	public void save(@Valid @RequestBody SolveSchemeDemo demo) {
		R.status(solveSchemeService.saveFile(demo));
	}

	/**
	 * 修改 解决方案表
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入solveScheme")
	public R update(@Valid @RequestBody SolveScheme solveScheme) {
		return R.status(solveSchemeService.updateById(solveScheme));
	}

	/**
	 * 新增或修改 解决方案表
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入SolveSchemeDemo")
	public void submit(@Valid @RequestBody SolveSchemeDemo demo) {
		solveSchemeService.update(demo);
	}

	/**
	 * 删除 解决方案表
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(solveSchemeService.deleteLogic(Func.toLongList(ids)));
	}

	/**
	 *
	 * 根据父类查询对应附件
	 *
	 * @return
	 */
	@PostMapping("/selectListId")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "根据主键查询对应附件", notes = "传入aptitudeId")
	public List<SolveSchemeVO> selectListId(Long objectId) {
		return solveSchemeService.selectListId(objectId);
	}

}
