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
import org.springblade.modules.EnterpriseResource.entity.AptitudeCertificate;
import org.springblade.modules.EnterpriseResource.vo.AptitudeCertificateVO;
import org.springblade.modules.EnterpriseResource.wrapper.AptitudeCertificateWrapper;
import org.springblade.modules.EnterpriseResource.service.IAptitudeCertificateService;
import org.springblade.core.boot.ctrl.BladeController;

/**
 * 企业资质证书类别表 控制器
 *
 * @author BladeX
 * @since 2022-01-17
 */
@RestController
@AllArgsConstructor
@RequestMapping("blade-resource/aptitudecertificate")
@Api(value = "企业资质证书类别表", tags = "企业资质证书类别表接口")
public class AptitudeCertificateController extends BladeController {

	private final IAptitudeCertificateService aptitudeCertificateService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入aptitudeCertificate")
	public R<AptitudeCertificateVO> detail(AptitudeCertificate aptitudeCertificate) {
		AptitudeCertificate detail = aptitudeCertificateService.getOne(Condition.getQueryWrapper(aptitudeCertificate));
		return R.data(AptitudeCertificateWrapper.build().entityVO(detail));
	}

	/**
	 * 分页 企业资质证书类别表
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入aptitudeCertificate")
	public R<IPage<AptitudeCertificateVO>> list(AptitudeCertificate aptitudeCertificate, Query query) {
		IPage<AptitudeCertificate> pages = aptitudeCertificateService.page(Condition.getPage(query), Condition.getQueryWrapper(aptitudeCertificate));
		return R.data(AptitudeCertificateWrapper.build().pageVO(pages));
	}


	/**
	 * 自定义分页 企业资质证书类别表
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入aptitudeCertificate")
	public R<IPage<AptitudeCertificateVO>> page(AptitudeCertificateVO aptitudeCertificate, Query query) {
		IPage<AptitudeCertificateVO> pages = aptitudeCertificateService.selectAptitudeCertificatePage(Condition.getPage(query), aptitudeCertificate);
		return R.data(pages);
	}

	/**
	 * 新增 企业资质证书类别表
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入aptitudeCertificate")
	public R save(@Valid @RequestBody AptitudeCertificate aptitudeCertificate) {
		return R.status(aptitudeCertificateService.save(aptitudeCertificate));
	}

	/**
	 * 修改 企业资质证书类别表
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入aptitudeCertificate")
	public R update(@Valid @RequestBody AptitudeCertificate aptitudeCertificate) {
		return R.status(aptitudeCertificateService.updateById(aptitudeCertificate));
	}

	/**
	 * 新增或修改 企业资质证书类别表
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入aptitudeCertificate")
	public R submit(@Valid @RequestBody AptitudeCertificate aptitudeCertificate) {
		return R.status(aptitudeCertificateService.saveOrUpdate(aptitudeCertificate));
	}

	
	/**
	 * 删除 企业资质证书类别表
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(aptitudeCertificateService.removeByIds(Func.toLongList(ids)));
	}

	
}
