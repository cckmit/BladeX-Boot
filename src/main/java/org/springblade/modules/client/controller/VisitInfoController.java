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
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.common.cache.SysCache;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.utils.SecureUtil;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.core.tool.utils.StringUtil;
import org.springblade.modules.client.entity.ClientContact;
import org.springblade.modules.client.entity.VisitInfo;
import org.springblade.modules.client.service.ClientContactService;
import org.springblade.modules.client.service.VisitInfoService;
import org.springblade.modules.client.vo.VisitInfoVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 控制器
 *
 * @author BladeX
 * @since 2021-06-26
 */
@RestController
@AllArgsConstructor
@RequestMapping("blade-client/visitinfo")
@Api(value = "客户拜访", tags = "客户拜访")
public class VisitInfoController extends BladeController {

	private final VisitInfoService visitInfoService;

	private final ClientContactService contactService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入visitInfo")
	public R<VisitInfo> detail(VisitInfo visitInfo) {
		VisitInfo detail = visitInfoService.getOne(Condition.getQueryWrapper(visitInfo));
		return R.data(detail);
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入visitInfo")
	public R<IPage<VisitInfo>> list(VisitInfo visitInfo, Query query) {
		//查询客户信息
		IPage<VisitInfo> pages = visitInfoService.page(Condition.getPage(query.setDescs("create_time")), Condition.getQueryWrapper(visitInfo));
		return R.data(pages);
	}

	/**
	 * 新增
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入visitInfo")
	public R save(@Valid @RequestBody VisitInfo visitInfo) {
		visitInfo.setDeptName(SysCache.getDeptName(Long.valueOf(SecureUtil.getDeptId())));
		//通过联系人ID获取组织中的客户ID
		visitInfo.setClientId(contactService.getClientByContact(visitInfo.getContactId()));
		boolean save = visitInfoService.save(visitInfo);
		return R.status(save);
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入visitInfo")
	public R update(@Valid @RequestBody VisitInfo visitInfo) {
		boolean flag = visitInfoService.updateById(visitInfo);
		return R.status(flag);
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入visitInfo")
	public R submit(@Valid @RequestBody VisitInfo visitInfo) {
		if (visitInfo.getId() == null) {
			//设置部门名称
			visitInfo.setDeptName(SysCache.getDeptName(Long.valueOf(SecureUtil.getDeptId())));
			//通过联系人ID获取组织中的客户ID
			visitInfo.setClientId(contactService.getClientByContact(visitInfo.getContactId()));
		}
		boolean flag = visitInfoService.saveOrUpdate(visitInfo);
		return R.status(flag);
	}


	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(visitInfoService.removeByIds(Func.toLongList(ids)));
	}

	/**
	 * 通过客户id获取拜访人员列表
	 */
	@GetMapping("/qryContactList")
	@ApiOperationSupport(order = 9)
	@ApiOperation(value = "获取拜访人员", notes = "")
	public R<IPage<ClientContact>> qryContactList(Query query) {
		ClientContact clientContact = new ClientContact();
		clientContact.setCreateUser(SecureUtil.getUser().getUserId());
		IPage<ClientContact> page = contactService.page(Condition.getPage(query), Condition.getQueryWrapper(clientContact));
		return R.data(page);
	}

	/**
	 * 联系人拜访列表
	 */
	@PostMapping("/listOfContact")
	@ApiOperationSupport(order = 10)
	@ApiOperation(value = "列表", notes = "")
	public R<List<VisitInfo>> listOfContact(@RequestBody VisitInfoVO condition) {
		QueryWrapper<VisitInfo> queryWrapper = Condition.getQueryWrapper(condition);
		queryWrapper.ge(condition.getStartDate() != null, "visit_date", condition.getStartDate());
		queryWrapper.le(condition.getEndDate() != null, "visit_date", condition.getEndDate());
		String searchContent = condition.getContactName();
		condition.setContactName(null);
		// WHERE (contact_name LIKE '' OR visit_region LIKE '' OR visit_event LIKE '')
		queryWrapper.and(StringUtil.isNotBlank(searchContent),
			q -> q.like("contact_name", searchContent)
			.or(q1 -> q1.like("visit_region", searchContent))
			.or(q2 -> q2.like("visit_event", searchContent)));
		// 拜访时间倒序
		queryWrapper.orderByDesc("visit_date");
		return R.data(visitInfoService.list(queryWrapper));
	}
}
