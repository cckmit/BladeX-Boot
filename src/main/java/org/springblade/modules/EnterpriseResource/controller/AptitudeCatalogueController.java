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
import org.springblade.modules.EnterpriseResource.entity.AptitudeCatalogue;
import org.springblade.modules.EnterpriseResource.vo.AptitudeCatalogueVO;
import org.springblade.modules.EnterpriseResource.wrapper.AptitudeCatalogueWrapper;
import org.springblade.modules.EnterpriseResource.service.IAptitudeCatalogueService;
import org.springblade.core.boot.ctrl.BladeController;

/**
 *  控制器
 *
 * @author BladeX
 * @since 2021-11-26
 */
@RestController
@AllArgsConstructor
@RequestMapping("blade-resource/aptitudecatalogue")
@Api(value = "企业资质联级模块", tags = "企业资质联级模块")
public class AptitudeCatalogueController extends BladeController {

	private final IAptitudeCatalogueService aptitudeCatalogueService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入aptitudeCatalogue")
	public R<AptitudeCatalogueVO> detail(AptitudeCatalogue aptitudeCatalogue) {
		AptitudeCatalogue detail = aptitudeCatalogueService.getOne(Condition.getQueryWrapper(aptitudeCatalogue));
		return R.data(AptitudeCatalogueWrapper.build().entityVO(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入aptitudeCatalogue")
	public R<IPage<AptitudeCatalogueVO>> list(AptitudeCatalogue aptitudeCatalogue, Query query) {
		IPage<AptitudeCatalogue> pages = aptitudeCatalogueService.page(Condition.getPage(query), Condition.getQueryWrapper(aptitudeCatalogue));
		return R.data(AptitudeCatalogueWrapper.build().pageVO(pages));
	}


	/**
	 * 自定义分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入aptitudeCatalogue")
	public R<IPage<AptitudeCatalogueVO>> page(AptitudeCatalogueVO aptitudeCatalogue, Query query) {
		IPage<AptitudeCatalogueVO> pages = aptitudeCatalogueService.selectAptitudeCataloguePage(Condition.getPage(query), aptitudeCatalogue);
		return R.data(pages);
	}

	/**
	 * 新增
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入aptitudeCatalogue")
	public R save(@Valid @RequestBody AptitudeCatalogue aptitudeCatalogue) {
		return R.status(aptitudeCatalogueService.save(aptitudeCatalogue));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入aptitudeCatalogue")
	public R update(@Valid @RequestBody AptitudeCatalogue aptitudeCatalogue) {
		return R.status(aptitudeCatalogueService.updateById(aptitudeCatalogue));
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入aptitudeCatalogue")
	public R submit(@Valid @RequestBody AptitudeCatalogue aptitudeCatalogue) {
		return R.status(aptitudeCatalogueService.saveOrUpdate(aptitudeCatalogue));
	}


	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(aptitudeCatalogueService.removeByIds(Func.toLongList(ids)));
	}
	/**
	 * 根据id返回名字
	 */
	@GetMapping("/detail1")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入aptitudeCatalogue")
	public AptitudeCatalogue detail1(Long id) {
		AptitudeCatalogue detail = aptitudeCatalogueService.getById(id);
		return detail;
	}
}
