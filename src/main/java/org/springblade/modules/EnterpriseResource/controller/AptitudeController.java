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

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.constant.RoleConstant;
import org.springblade.core.tool.utils.Func;
import org.springblade.modules.EnterpriseResource.entity.Aptitude;
import org.springblade.modules.EnterpriseResource.service.IAptitudeService;
import org.springblade.modules.EnterpriseResource.service.IFileService;
import org.springblade.modules.EnterpriseResource.vo.AptitudeVO;
import org.springblade.modules.EnterpriseResource.vo.demo;
import org.springblade.modules.EnterpriseResource.wrapper.AptitudeWrapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springblade.core.boot.ctrl.BladeController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 企业资质表 控制器
 *
 * @author BladeX
 * @since 2021-09-02
 */
@RestController
@AllArgsConstructor
@RequestMapping("blade-resource/aptitude")
@Api(value = "企业资质表", tags = "企业资质表接口")
@PreAuth(RoleConstant.HAS_ROLE_ADMIN)
public class AptitudeController extends BladeController {

	private final IAptitudeService aptitudeService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入aptitude")
	public R<AptitudeVO> detail(Aptitude aptitude) {
		Aptitude detail = aptitudeService.getOne(Condition.getQueryWrapper(aptitude));
		return R.data(AptitudeWrapper.build().entityVO(detail));
	}

	/**
	 * 分页 企业资质表
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入aptitude")
	public R<IPage<AptitudeVO>> list(Aptitude aptitude, Query query) {
		IPage<Aptitude> pages = aptitudeService.page(Condition.getPage(query), Condition.getQueryWrapper(aptitude));
		return R.data(AptitudeWrapper.build().pageVO(pages));
	}


	/**
	 * 自定义分页 企业资质表
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入aptitude")
	public R<IPage<AptitudeVO>> page(AptitudeVO aptitude, Query query) {
		IPage<AptitudeVO> pages = aptitudeService.selectAptitudePage(Condition.getPage(query), aptitude);
		return R.data(pages);
	}


	/**
	 *
	 * 自定义分页+模糊查询（证书名称）
	 *
	 */
	@GetMapping("/selectAptitudeDim")
	@ApiOperationSupport(order = 9)
	@ApiOperation(value = "分页", notes = "传入aptitude")
	public R<IPage<AptitudeVO>> selectAptitudeDim(AptitudeVO aptitude, Query query) {
		IPage<AptitudeVO> pages = aptitudeService.selectAptitudeDim(Condition.getPage(query), aptitude);
		return R.data(pages);
	}

	/**
	 * 新增 企业资质表
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入demo")
	public void save( @RequestBody demo demo) {

		aptitudeService.saveFile(demo);

	}
	/**
	 * 修改 企业资质表
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入aptitude")
	public R update(@Valid @RequestBody Aptitude aptitude) {
		return R.status(aptitudeService.updateById(aptitude));
	}

	/**
	 * 新增或修改 企业资质表
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入demo")
	public void submit(@Valid @RequestBody demo demo) {
		aptitudeService.update(demo);
	}


	/**
	 * 删除 企业资质表
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(aptitudeService.deleteLogic(Func.toLongList(ids)));
	}


/**
 *
 * 根据主键查询对应附件
 *
 * @return
 */
	@PostMapping("/selectListId")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "根据主键查询对应附件", notes = "传入aptitudeId")
	public List<AptitudeVO> selectListId(Long aptitudeId) {
		return aptitudeService.selectListId(aptitudeId);
	}

}
