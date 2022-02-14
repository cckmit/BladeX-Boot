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
import org.springblade.modules.EnterpriseResource.entity.AptitudeGrade;
import org.springblade.modules.EnterpriseResource.vo.AptitudeGradeVO;
import org.springblade.modules.EnterpriseResource.wrapper.AptitudeGradeWrapper;
import org.springblade.modules.EnterpriseResource.service.IAptitudeGradeService;
import org.springblade.core.boot.ctrl.BladeController;

/**
 * 企业资质等级表 控制器
 *
 * @author BladeX
 * @since 2022-01-17
 */
@RestController
@AllArgsConstructor
@RequestMapping("blade-resource/aptitudegrade")
@Api(value = "企业资质等级表", tags = "企业资质等级表接口")
public class AptitudeGradeController extends BladeController {

	private final IAptitudeGradeService aptitudeGradeService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入aptitudeGrade")
	public R<AptitudeGradeVO> detail(AptitudeGrade aptitudeGrade) {
		AptitudeGrade detail = aptitudeGradeService.getOne(Condition.getQueryWrapper(aptitudeGrade));
		return R.data(AptitudeGradeWrapper.build().entityVO(detail));
	}

	/**
	 * 分页 企业资质等级表
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入aptitudeGrade")
	public R<IPage<AptitudeGradeVO>> list(AptitudeGrade aptitudeGrade, Query query) {
		IPage<AptitudeGrade> pages = aptitudeGradeService.page(Condition.getPage(query), Condition.getQueryWrapper(aptitudeGrade));
		return R.data(AptitudeGradeWrapper.build().pageVO(pages));
	}


	/**
	 * 自定义分页 企业资质等级表
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入aptitudeGrade")
	public R<IPage<AptitudeGradeVO>> page(AptitudeGradeVO aptitudeGrade, Query query) {
		IPage<AptitudeGradeVO> pages = aptitudeGradeService.selectAptitudeGradePage(Condition.getPage(query), aptitudeGrade);
		return R.data(pages);
	}

	/**
	 * 新增 企业资质等级表
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入aptitudeGrade")
	public R save(@Valid @RequestBody AptitudeGrade aptitudeGrade) {
		return R.status(aptitudeGradeService.save(aptitudeGrade));
	}

	/**
	 * 修改 企业资质等级表
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入aptitudeGrade")
	public R update(@Valid @RequestBody AptitudeGrade aptitudeGrade) {
		return R.status(aptitudeGradeService.updateById(aptitudeGrade));
	}

	/**
	 * 新增或修改 企业资质等级表
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入aptitudeGrade")
	public R submit(@Valid @RequestBody AptitudeGrade aptitudeGrade) {
		return R.status(aptitudeGradeService.saveOrUpdate(aptitudeGrade));
	}

	
	/**
	 * 删除 企业资质等级表
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(aptitudeGradeService.removeByIds(Func.toLongList(ids)));
	}

	
}
