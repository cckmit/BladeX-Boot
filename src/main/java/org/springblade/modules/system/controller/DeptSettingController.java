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
package org.springblade.modules.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import lombok.AllArgsConstructor;

import javax.validation.Valid;

import org.springblade.common.cache.CacheNames;
import org.springblade.core.cache.utils.CacheUtil;
import org.springblade.core.launch.constant.AppConstant;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.redis.cache.BladeRedis;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.modules.system.entity.Dept;
import org.springblade.modules.system.entity.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.system.entity.DeptSetting;
import org.springblade.modules.system.vo.DeptSettingVO;
import org.springblade.modules.system.service.IDeptSettingService;
import org.springblade.core.boot.ctrl.BladeController;

/**
 * 控制器
 *
 * @author BladeX
 * @since 2021-07-06
 */
@RestController
@AllArgsConstructor
@RequestMapping(AppConstant.APPLICATION_SYSTEM_NAME + "/deptsetting")
@Api(value = "组织架构配置", tags = "接口")
public class DeptSettingController extends BladeController {

	private final IDeptSettingService deptSettingService;

	private final BladeRedis bladeRedis;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入deptSetting")
	public R<DeptSetting> detail(long deptId) {
		DeptSetting detail = bladeRedis.get(CacheNames.DEPTSETTING_KEY + deptId);
		if (detail == null) {
			detail = deptSettingService.lambdaQuery().eq(DeptSetting::getDeptId, deptId).one();
		}

		return R.data(detail);
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入deptSetting")
	public R submit(@Valid @RequestBody DeptSetting deptSetting) {
		boolean resule = deptSettingService.saveOrUpdate(deptSetting);

		bladeRedis.set(CacheNames.DEPTSETTING_KEY + deptSetting.getDeptId(), deptSetting);

		return R.status(resule);
	}
}
