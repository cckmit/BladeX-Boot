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

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.tool.api.R;
import org.springblade.modules.system.entity.Major;
import org.springblade.modules.system.service.IMajorService;
import org.springblade.modules.system.vo.MajorVO;
import org.springblade.modules.system.wrapper.MajorWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 *  控制器
 *
 * @author BladeX
 * @since 2021-07-08
 */
@RestController
@AllArgsConstructor
@RequestMapping("blade-system/major")
@Api(value = "", tags = "接口")
public class MajorController extends BladeController {

	private final IMajorService majorService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入major")
	public R<Major> detail(Major major) {
		Major detail = majorService.getOne(Condition.getQueryWrapper(major));
		return R.data(detail);
	}




	/**
	 * 懒加载列表
	 */
	@GetMapping("/lazy-tree")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "code", value = "专业编号", paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "name", value = "专业名称", paramType = "query", dataType = "string")
	})
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "懒加载列表", notes = "传入menu")
	public R<List<MajorVO>> lazyTree(String parentCode, @ApiIgnore @RequestParam Map<String, Object> menu) {
		List<MajorVO> list = majorService.lazyTree(parentCode, menu);
		return R.data(MajorWrapper.build().listNodeLazyVO(list));
	}

}
