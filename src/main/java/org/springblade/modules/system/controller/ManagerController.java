
package org.springblade.modules.system.controller;

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
import org.springblade.modules.project.vo.BusinessVO;
import org.springblade.modules.project.wrapper.BusinessWrapper;
import org.springblade.modules.system.entity.Manager01;
import org.springblade.modules.system.entity.ManagerLog;
import org.springblade.modules.system.service.IManagerLogService;
import org.springblade.modules.system.vo.ManagerLogVO;
import org.springblade.modules.system.vo.ManagerVO1;
import org.springblade.modules.system.wrapper.BusinessManagerWrapper;
import org.springblade.modules.system.wrapper.ManagerLogWrapper;
import org.springblade.modules.system.wrapper.ManagerWrapper01;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.system.entity.Manager;
import org.springblade.modules.system.vo.ManagerVO;
import org.springblade.modules.system.wrapper.ManagerWrapper;
import org.springblade.modules.system.service.IManagerService;
import org.springblade.core.boot.ctrl.BladeController;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *  控制器
 *
 * @author BladeX
 * @since 2021-06-30
 */
@RestController
@AllArgsConstructor
@RequestMapping("blade-system/manager")
@Api(value = "项目经理", tags = "项目经理")
public class ManagerController extends BladeController {

	private final IManagerService managerService;

	//项目经理日志--Service
	private final IManagerLogService managerLogService;


//	/**
//	 * 详情
//	 */
//	@GetMapping("/detail")
//	@ApiOperationSupport(order = 1)
//	@ApiOperation(value = "详情", notes = "传入manager")
//	public R<ManagerVO> detail(Manager manager) {
//		Manager detail = managerService.getOne(Condition.getQueryWrapper(manager));
//		return R.data(ManagerWrapper.build().entityVO(detail));
//	}



	/**
	 * （）详情
	 */
	@GetMapping("/selectManagerDetail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "（改-新）详情", notes = "传入manager")
	public R<ManagerVO1> selectManagerDetail(Long id) {
		Manager01 detail = managerService.selectManagerDetail(id);
		return R.data(ManagerWrapper01.build().entityVO(detail));
	}


	/**
	 *
	 * 根据项目经理ID查询商机+投标表
	 */
	@GetMapping("/selectProjectBusiness")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "根据项目经理ID查询商机+投标表", notes = "id")
	public R<List<ManagerVO1>>selectProjectBusiness(Long id) {
		List<ManagerVO1> detail = managerService.selectProjectBusiness(id);
		return R.data(detail);
	}


	/*
	 * 多组连表查询项目经理
	 * */
	@GetMapping("/getManagerList")
	@ApiOperationSupport(order =2)
	@ApiOperation(value = "(久)分页", notes = "传入manager")
	public R<IPage<ManagerVO1>> getManagerList(ManagerVO manager, Query query){
		IPage<Manager01> pages = managerService.selectManagerList(Condition.getPage(query),manager);
		return  R.data(ManagerWrapper01.build().pageVO(pages));
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "(久)分页", notes = "传入manager")
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
	 * 分页
	 */
	@GetMapping("/selectManagerList")
	@ApiOperationSupport(order = 9)
	@ApiOperation(value = "（改-新）分页列表", notes = "传入manager")
	public R<IPage<ManagerVO1>> selectManagerList(ManagerVO manager, Query query) {
		IPage<Manager01> pages = managerService.selectManagerList(Condition.getPage(query),manager);
		return R.data(ManagerWrapper01.build().pageVO(pages));
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
	 * 修改以及添加经理操作日志记录
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改以及添加经理操作日志表", notes = "传入manager")
	public R update(@Valid @RequestBody Manager manager) {
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

	/**
	 * 正常修改
	 */
	@PostMapping("/normalUpdate")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "正常修改", notes = "传入manager")
	public R normalUpdate(@Valid @RequestBody Manager manager) {
		return R.status(managerService.updateById(manager));
	}

	/**
	 *
	 * 新增或修改
	 *
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

	/**
	 * 三合一
	 * @return
	 */
	@GetMapping("/selectAllMap")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "三合一集合查询", notes = "id")
	public Map selectAllMap(Long id) {
		Map map = new HashMap();
		//基础信息
		Manager01 detail = managerService.selectManagerDetail(id);
		ManagerVO1 VO = ManagerWrapper01.build().entityVO(detail);
		map.put("ManagerDetail",VO);
		//关联的项目经理下的项目
		List<ManagerVO1> projectBusiness1 = new LinkedList<ManagerVO1>();
		List<ManagerVO1> projectBusiness = managerService.selectProjectBusiness(id);
		for (ManagerVO1 temp:projectBusiness) {
			ManagerVO1 vo = BusinessManagerWrapper.build().entityVO(temp);
			projectBusiness1.add(vo);
		}
		map.put("projectBusiness",projectBusiness);
		//日志
		List<ManagerLog> ManagerLogList = managerLogService.selectManagerList(id);
		List<ManagerLogVO> ManagerLogList1 = new LinkedList<ManagerLogVO>();
		for (ManagerLog temp:ManagerLogList) {
			ManagerLogVO vo = ManagerLogWrapper.build().entityVO(temp);
			ManagerLogList1.add(vo);
		}
		map.put("ManagerLogList",ManagerLogList1);
		return map;
	}


}
