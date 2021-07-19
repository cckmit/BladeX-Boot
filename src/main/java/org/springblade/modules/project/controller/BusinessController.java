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
package org.springblade.modules.project.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.project.entity.Business;
import org.springblade.modules.project.vo.BusinessVO;
import org.springblade.modules.project.dto.BusinessDTO;
import org.springblade.modules.project.wrapper.BusinessWrapper;
import org.springblade.modules.project.service.IBusinessService;
import org.springblade.core.boot.ctrl.BladeController;

//流程引擎相关import
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import org.springblade.flow.business.service.FlowBusinessService;
import org.springblade.flow.core.entity.BladeFlow;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.springblade.common.cache.UserCache;
import org.springblade.core.launch.constant.AppConstant;
import org.springblade.core.tenant.annotation.NonDS;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.*;
/**
 *  控制器
 *
 * @author BladeX
 * @since 2021-07-03
 */
@NonDS
@RestController
@AllArgsConstructor
@RequestMapping("blade-project/business")
@Api(value = "", tags = "接口")
public class BusinessController extends BladeController {

	private final IBusinessService businessService;
	private final FlowBusinessService flowBusinessService;
	private final RuntimeService runtimeService;
	private final TaskService taskService;
	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入business")
	public R<BusinessVO> detail(Business business) {
		Business detail = businessService.getOne(Condition.getQueryWrapper(business));
		//加入申请人信息
		detail.getFlow().setAssigneeName(UserCache.getUser(detail.getCreateUser()).getName());
		return R.data(BusinessWrapper.build().entityVO(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入business")
	public R<IPage<BusinessVO>> list(Business business, Query query) {
		IPage<Business> pages = businessService.page(Condition.getPage(query), Condition.getQueryWrapper(business));
		return R.data(BusinessWrapper.build().pageVO(pages));
	}


	/**
	 * 自定义分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入business")
	public R<IPage<BusinessVO>> page(BusinessVO business, Query query) {
		IPage<BusinessVO> pages = businessService.selectBusinessPage(Condition.getPage(query), business);
		return R.data(pages);
	}

	/**
	 * 新增
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入business")
	public R save(@Valid @RequestBody Business business) {
		return R.status(businessService.save(business));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入business")
	public R update(@Valid @RequestBody Business business) {
		return R.status(businessService.updateById(business));
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入business")
	public R submit(@Valid @RequestBody Business business) {
		return R.status(businessService.saveOrUpdate(business));
	}


	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(businessService.deleteLogic(Func.toLongList(ids)));
	}
	//流程引擎
	/**
	 * 新增或修改
	 *
	 * @param business 集客报备信息
	 */
	@PostMapping("start-process")
	@ApiOperation(value = "开启流程", notes = "传入流程信息")
	public R startProcess(@RequestBody Business business) {
		System.out.println(business.toString());
		return R.status(businessService.startProcess(business));
	}
	/**
	 * 完成任务
	 *
	 * @param
	 */
	@PostMapping("complete-task")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "完成任务", notes = "传入流程信息")
	public R completeTask(@ApiParam("任务信息") @RequestBody BusinessDTO businessdto) {
		businessService.saveOrUpdate(businessdto.getBusiness());
		return R.status(flowBusinessService.completeTask(businessdto.getFlow()));
	}

	/**
	 * 驳回任务
	 *
	 * @param flow 绩效流程信息
	 */
	@PostMapping("reject-task")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "驳回任务", notes = "传入流程信息")
	public R rejectTask(@ApiParam("任务信息") @RequestBody BladeFlow flow,@RequestBody Business business) {
		//根据流程id获取act_ru_task表的数据
		List<Task> list = taskService.createTaskQuery()
			.processInstanceId(flow.getProcessInstanceId())
			.list();

		//获取当前步骤的用户key，即画流程图时设置的id
		List<String> curTaskKeys = new ArrayList<>();
		for (Task task:list) {
			//String curTaskKey =  task.getTaskDefinitionKey();
			curTaskKeys.add( task.getTaskDefinitionKey());
		}
		String targetKey = flow.getTaskDefinitionKey();
		rollback(flow.getProcessInstanceId(),curTaskKeys,targetKey);
		businessService.saveOrUpdate(business);
		return R.status(true);
	}
	/**
	 *@Author
	 *@Description  驳回
	 * proInstanceId  需要驳回的流程实例id(当前发起节点的流程实例id)
	 * currTaskKeys   驳回发起的当前节点key 为  act_ru_task 中TASK_DEF_KEY_ 字段的值
	 * targetKey  目标节点的key  为act_hi_taskinst 中 TASK_DEF_KEY_
	 **/
	public void rollback(String proInstanceId, List<String> currTaskKeys, String targetKey) {
		runtimeService.createChangeActivityStateBuilder()
			.processInstanceId(proInstanceId)
			.moveActivityIdsToSingleActivityId(currTaskKeys, targetKey)
			.changeState();
	}

}
