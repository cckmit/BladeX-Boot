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

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.modules.system.entity.Manager;
import org.springblade.modules.system.entity.ManagerLog;
import org.springblade.modules.system.service.IManagerLogService;
import org.springblade.modules.system.service.IManagerService;
import org.springblade.modules.system.vo.ManagerLogVO;
import org.springblade.modules.system.vo.ManagerVO;
import org.springblade.modules.system.wrapper.BusinessManagerWrapper;
import org.springblade.modules.system.wrapper.ManagerLogWrapper;
import org.springblade.modules.system.wrapper.ManagerWrapper;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.*;

/**
 *  控制器
 *
 * @author BladeX
 * @since 2021-06-30
 */
@RestController
@AllArgsConstructor
@RequestMapping("blade-system/manager")
@Api(value = "项目经理模块", tags = "项目经理模块")
public class ManagerController extends BladeController {

	private final IManagerService managerService;

	private final IManagerLogService managerLogService;



	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "(list)分页", notes = "传入manager")
	public R<IPage<ManagerVO>> list(Manager manager, Query query) {
		IPage<Manager> pages = managerService.page(Condition.getPage(query), Condition.getQueryWrapper(manager));
		return R.data(ManagerWrapper.build().pageVO(pages));

	}

	/**
	 * 自定义分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入manager")
	public R<IPage<ManagerVO>> page(ManagerVO manager, Query query) {
		IPage<ManagerVO> pages = managerService.selectManagerPage(Condition.getPage(query), manager);
		return R.data(pages);
	}


	/**
	 * 新增
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入manager")
	public R save(@Valid @RequestBody Manager manager) {
		return R.status(managerService.save(manager));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入manager")
	public R update(@Valid @RequestBody Manager manager) {
		return R.status(managerService.updateById(manager));
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入manager")
	public R submit(@Valid @RequestBody Manager manager) {
		return R.status(managerService.saveOrUpdate(manager));
	}


	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(managerService.removeByIds(Func.toLongList(ids)));
	}

//	/**
//	 * 三合一
//	 * @return
//	 */
//	@GetMapping("/selectAllMap")
//	@ApiOperationSupport(order = 1)
//	@ApiOperation(value = "(w)三合一集合查询", notes = "id")
//	public Map selectAllMap(Long id, Query query) {
//		Map map = new HashMap();
//		//基础信息
//		Manager detail = managerService.selectManagerDetail(id);
//		ManagerVO VO = ManagerWrapper.build().entityVO(detail);
//		map.put("ManagerDetail",VO);
//		//关联的项目经理下的项目
//		IPage<ManagerVO> projectBusiness = managerService.selectProjectBusiness(Condition.getPage(query), id);
//		List<ManagerVO> projectBusiness1 = new LinkedList<ManagerVO>();
//
//		for (ManagerVO temp:projectBusiness.getRecords()) {
//			ManagerVO vo = BusinessManagerWrapper.build().entityVO(temp);
//			projectBusiness1.add(vo);
//		}
//
//		map.put("projectBusiness",projectBusiness);
//		//日志
//		IPage<ManagerLogVO> ManagerLogList = managerLogService.selectManagerList(Condition.getPage(query),id);
//		List<ManagerLogVO> ManagerLogList1 = new LinkedList<ManagerLogVO>();
//		for (ManagerLog temp:ManagerLogList.getRecords()) {
//			ManagerLogVO vo = ManagerLogWrapper.build().entityVO(temp);
//			ManagerLogList1.add(vo);
//		}
//		map.put("ManagerLogList",ManagerLogList1);
//		return map;
//	}

		/**
		 * 关联的项目经理下的项目
		 */
		@GetMapping("/projectBusiness")
		@ApiOperationSupport(order = 1)
		@ApiOperation(value = "关联的项目经理下的项目", notes = "传入id,current,size")
		public R<IPage<ManagerVO>> projectBusiness(Long id,Query query) {
			IPage<ManagerVO> projectBusiness = managerService.selectProjectBusiness(Condition.getPage(query), id);
			List<ManagerVO> projectBusiness1 = new LinkedList<ManagerVO>();
			for (ManagerVO temp:projectBusiness.getRecords()) {
				ManagerVO vo = BusinessManagerWrapper.build().entityVO(temp);
				projectBusiness1.add(vo);
			}


			return R.data(projectBusiness);
		}



	/**
	 * 	//日志
	 */
	@GetMapping("/ManagerLogList")
	@ApiOperationSupport(order = 11)
	@ApiOperation(value = "关联下日志信息", notes = "传入id,current,size")
	public R<IPage<ManagerLogVO>> ManagerLogList(Long id, Query query) {
		IPage<ManagerLogVO> ManagerLogList = managerLogService.selectManagerList(Condition.getPage(query),id);
		List<ManagerLogVO> ManagerLogList1 = new LinkedList<ManagerLogVO>();
		for (ManagerLog temp:ManagerLogList.getRecords()) {
			ManagerLogVO vo = ManagerLogWrapper.build().entityVO(temp);
			ManagerLogList1.add(vo);
		}
		return R.data(ManagerLogList);
	}

	/**
	 * （新）详情
	 */
	@GetMapping("/selectManagerDetail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "查询单个经理基础信息", notes = "传入id")
	public R<ManagerVO> selectManagerDetail(Long id, Query query) {
		Manager detail = managerService.selectManagerDetail(id);
		return R.data(ManagerWrapper.build().entityVO(detail));
	}


	/*
	 * 连表查询项目经理
	 * */
	@GetMapping("/getManagerList")
	@ApiOperationSupport(order =2)
	@ApiOperation(value = "(w)连表查询项目经理", notes = "传入manager")
	public R<IPage<ManagerVO>> getManagerList(ManagerVO manager, Query query){
		IPage<Manager> pages = managerService.selectManagerList(Condition.getPage(query),manager);
		return  R.data(ManagerWrapper.build().pageVO(pages));
	}


	/**
	 * 分页列表
	 */
	@GetMapping("/selectManagerList")
	@ApiOperationSupport(order = 9)
	@ApiOperation(value = "（w）分页列表", notes = "传入manager")
	public R<IPage<ManagerVO>> selectManagerList(ManagerVO manager, Query query) {
		IPage<Manager> pages = managerService.selectManagerList(Condition.getPage(query),manager);
		return R.data(ManagerWrapper.build().pageVO(pages));
	}

	/**
	 * 修改以及添加经理操作日志记录
	 */
	@PostMapping("/updateManagerOne")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "(w)修改以及添加经理操作日志表", notes = "传入manager")
	public R updateManagerOne(@Valid @RequestBody Manager manager) {
		boolean manager1=	managerService.updateById(manager);
		if (manager1==true){
			ManagerLog managerLog = new ManagerLog();
			managerLog.setUserId(manager.getUserId());
			managerLog.setManagerId(manager.getId());
			if(manager.getIsConstructor()==1){
				managerLog.setWhetherUnlock("未解锁");
			}
			if(manager.getIsConstructor()==2){
				managerLog.setWhetherUnlock("解锁");
			}
			managerLogService.save(managerLog);
		}
		return R.status(manager1);
	}

}
