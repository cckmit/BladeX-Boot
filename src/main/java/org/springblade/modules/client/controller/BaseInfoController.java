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

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.springblade.common.aspect.annotation.DataOperateAuth;
import org.springblade.common.aspect.inteceptor.DataOperateAuthController;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.log.logger.BladeLogger;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.utils.SecureUtil;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.jackson.JsonUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.core.tool.utils.StringUtil;
import org.springblade.modules.client.entity.BaseInfo;
import org.springblade.modules.client.service.IBaseInfoService;
import org.springblade.modules.client.service.UserFocusService;
import org.springblade.modules.client.vo.BaseInfoVO;
import org.springblade.modules.system.entity.Role;
import org.springblade.modules.system.service.IRoleService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 控制器
 *
 * @author BladeX
 * @since 2021-06-26
 */
@RestController
@AllArgsConstructor
@RequestMapping("blade-client/baseinfo")
@Api(value = "", tags = "客户信息")
public class BaseInfoController extends BladeController implements DataOperateAuthController {

	private final IBaseInfoService baseInfoService;

	private final UserFocusService userFocusService;

	private BladeLogger logger;

	private final IRoleService roleService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入baseInfo")
	public R<BaseInfo> detail(BaseInfo baseInfo) {
		BaseInfo detail = baseInfoService.getOne(Condition.getQueryWrapper(baseInfo));
		logger.info(detail.getId().toString(), JsonUtil.toJson(detail));
		return R.data(detail);
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入baseInfo")
	public R<IPage<BaseInfo>> list(BaseInfoVO baseInfo, Query query) {
		//查询客户信息
		IPage<BaseInfo> pages = baseInfoService.pageClientInfoPub(Condition.getPage(query.setDescs("create_time")), baseInfo);
		//日志记录
		pages.getRecords().forEach(item -> {
			logger.info(item.getId().toString(), JsonUtil.toJson(item));
		});
		return R.data(pages);
	}

	@Override
	public boolean isPermit(String ids, String methodName, JoinPoint point) {
		BaseInfoVO info = new BaseInfoVO();
		List<Long> idList = Func.toLongList(ids);
		info.setIds(idList);
		IPage<BaseInfoVO> page = Condition.getPage(new Query());
		// 私海客户权限
		info.setMode(2);
		if (baseInfoService.pageClientInfo(page, info).getTotal() >= idList.size()) {
			return true;
		}
		// 公海客户权限
		info.setMode(1);
		if (baseInfoService.pageClientInfoPub(Condition.getPage(new Query()), info).getTotal() >= idList.size()) {
			return true;
		}
		return false;
	}

	/**
	 * 分页
	 */
	@GetMapping("/listWithAuth")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入baseInfo")
	public R<IPage<BaseInfoVO>> listWithAuth(BaseInfoVO baseInfo, Query query) {
		//查询客户信息
		IPage<BaseInfoVO> pages = baseInfoService.pageClientInfo(Condition.getPage(query.setDescs("create_time")), baseInfo);
		//日志记录
		pages.getRecords().forEach(item -> {
			logger.info(item.getId().toString(), JsonUtil.toJson(item));
		});
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
		boolean save = baseInfoService.save(baseInfo);
		if (save) {
			logger.info(baseInfo.getId().toString(), JsonUtil.toJson(baseInfo));
		}
		return R.status(save);
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入baseInfo")
	public R update(@Valid @RequestBody BaseInfo baseInfo) {
		boolean flag = baseInfoService.updateById(baseInfo);
		if (flag) {
			logger.info(baseInfo.getId().toString(), JsonUtil.toJson(baseInfo));
		}
		return R.status(flag);
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入baseInfo")
	public R submit(@Valid @RequestBody BaseInfo baseInfo) {
		this.setClientcode(baseInfo);
		boolean flag = baseInfoService.saveOrUpdate(baseInfo);
		if (flag) {
			logger.info(baseInfo.getId().toString(), JsonUtil.toJson(baseInfo));
		}
		return R.status(flag);
	}


	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "删除", notes = "传入ids")
	@DataOperateAuth
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		List<Long> longs = Func.toLongList(ids);
		BaseInfo baseInfoSelf = new BaseInfo();
		baseInfoSelf.setId(longs.get(0));
		BaseInfo detailSelf = baseInfoService.getOne(Condition.getQueryWrapper(baseInfoSelf));
		if (2 == detailSelf.getMode()) {
			return R.status(baseInfoService.removeByIds(Func.toLongList(ids)));
		}

		BladeUser user = SecureUtil.getUser();

		//获取登陆人角色信息
		Role role = new Role();
		role.setId(Long.parseLong(user.getRoleId()));
		Role roleDetail = roleService.getOne(Condition.getQueryWrapper(role));
		if (roleDetail.getParentId().toString().equals("0")) {
			return R.status(baseInfoService.removeByIds(Func.toLongList(ids)));
		}

		Long userId = user.getUserId();
		AtomicReference<Boolean> flag = new AtomicReference<>(false);

		longs.forEach(id -> {
			BaseInfo baseInfo = new BaseInfo();
			baseInfo.setId(id);
			BaseInfo detail = baseInfoService.getOne(Condition.getQueryWrapper(baseInfo));
			if (!userId.equals(detail.getCreateUser())) {
				flag.set(true);
			}
		});
		if (flag.get()) {
			return R.fail("数据越权");
		}
		return R.status(baseInfoService.removeByIds(longs));
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

}
