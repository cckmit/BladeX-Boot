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
package org.springblade.modules.client.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import org.springblade.core.tool.utils.StringUtil;
import org.springblade.modules.client.entity.UserFocusEntity;
import org.springblade.modules.client.service.UserFocusService;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.client.entity.BaseInfo;
import org.springblade.modules.client.vo.BaseInfoVO;
import org.springblade.modules.client.service.IBaseInfoService;
import org.springblade.core.boot.ctrl.BladeController;

import java.util.List;
import java.util.stream.Collectors;

/**
 *  控制器
 *
 * @author BladeX
 * @since 2021-06-26
 */
@RestController
@AllArgsConstructor
@RequestMapping("blade-client/baseinfo")
@Api(value = "", tags = "客户信息")
public class BaseInfoController extends BladeController {

	private final IBaseInfoService baseInfoService;

	private final UserFocusService userFocusService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入baseInfo")
	public R<BaseInfo> detail(BaseInfo baseInfo) {
		BaseInfo detail = baseInfoService.getOne(Condition.getQueryWrapper(baseInfo));
		return R.data(detail);
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入baseInfo")
	public R<IPage<BaseInfo>> list(BaseInfo baseInfo, Query query) {
		//查询客户信息
		IPage<BaseInfo> pages = baseInfoService.page(Condition.getPage(query), Condition.getQueryWrapper(baseInfo));
		//设置关注状态
		setUserFocusStaus(pages.getRecords());
		return R.data(pages);
	}

	/**
	 * 自定义分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入baseInfo")
	public R<IPage<BaseInfoVO>> page(BaseInfoVO baseInfo, Query query) {
		IPage<BaseInfoVO> pages = baseInfoService.selectBaseInfoPage(Condition.getPage(query), baseInfo);
		return R.data(pages);
	}

	/**
	 * 新增
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入baseInfo")
	public R save(@Valid @RequestBody BaseInfo baseInfo) {
		this.setClientcode(baseInfo);
		return R.status(baseInfoService.save(baseInfo));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入baseInfo")
	public R update(@Valid @RequestBody BaseInfo baseInfo) {
		return R.status(baseInfoService.updateById(baseInfo));
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入baseInfo")
	public R submit(@Valid @RequestBody BaseInfo baseInfo) {
		this.setClientcode(baseInfo);
		return R.status(baseInfoService.saveOrUpdate(baseInfo));
	}


	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(baseInfoService.removeByIds(Func.toLongList(ids)));
	}

	/**
	 * 新增时设置客户编码
	 */
	private void setClientcode(BaseInfo baseInfo) {
		if (StringUtil.isBlank(baseInfo.getClientcode())) {
			int random = (int) ((Math.random() * 9 + 1) * 100000);
			baseInfo.setClientcode(System.currentTimeMillis() + String.valueOf(random));
		}
	}

	/**
	 * 设置关注状态
	 */
	private void setUserFocusStaus(List<BaseInfo> records) {
		//取客户id组装成list
		List<Long> ids = records.stream().map(BaseInfo::getId).collect(Collectors.toList());
		QueryWrapper<UserFocusEntity> wrapper = new QueryWrapper<>();
		wrapper.in("client_id", ids);
		List<UserFocusEntity> userFocusList = userFocusService.getBaseMapper().selectList(wrapper);
		//先全部设置为未关注状态
		records.forEach(item -> {
			item.setFocusStatus("0");
		});
		//设置关注字段
		if (userFocusList != null && userFocusList.size() > 0) {
			records.forEach(item -> {
				userFocusList.forEach(focus -> {
					//能匹配上说明已关注
					if (StringUtil.equals(item.getId().toString(), focus.getClientId().toString())) {
						item.setFocusStatus("1");
						item.setFocusId(focus.getId());
					}
				});
			});
		}
	}

}
