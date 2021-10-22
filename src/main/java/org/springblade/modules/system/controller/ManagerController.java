
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
import org.springblade.modules.system.entity.ManagerLog;
import org.springblade.modules.system.service.IManagerLogService;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.system.entity.Manager;
import org.springblade.modules.system.vo.ManagerVO;
import org.springblade.modules.system.wrapper.ManagerWrapper;
import org.springblade.modules.system.service.IManagerService;
import org.springblade.core.boot.ctrl.BladeController;

/**
 *  控制器
 *
 * @author BladeX
 * @since 2021-06-30
 */
@RestController
@AllArgsConstructor
@RequestMapping("blade-system/manager")
@Api(value = "", tags = "接口")
public class ManagerController extends BladeController {

	private final IManagerService managerService;

	//项目经理日志--Service
	private final IManagerLogService managerLogService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入manager")
	public R<ManagerVO> detail(Manager manager) {
		Manager detail = managerService.getOne(Condition.getQueryWrapper(manager));
		return R.data(ManagerWrapper.build().entityVO(detail));
	}



	/**
	 * 详情
	 */
	@GetMapping("/selectManagerDetail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入manager")
	public R<ManagerVO> selectManagerDetail(Long id) {
		Manager detail = managerService.selectManagerDetail(id);
		return R.data(ManagerWrapper.build().entityVO(detail));
	}


	/*
	 * 多组连表查询项目经理
	 * */
	@GetMapping("/getManagerList")
	@ApiOperationSupport(order =2)
	@ApiOperation(value = "分页", notes = "传入manager")
	public R<IPage<ManagerVO>> getManagerList(ManagerVO manager, Query query){
		IPage<Manager> pages = managerService.selectManagerList(Condition.getPage(query),manager);
		return  R.data(ManagerWrapper.build().pageVO(pages));
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入manager")
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
	@ApiOperation(value = "分页", notes = "传入manager")
	public R<IPage<ManagerVO>> selectManagerList(ManagerVO manager, Query query) {
		IPage<Manager> pages = managerService.selectManagerList(Condition.getPage(query),manager);
		return R.data(ManagerWrapper.build().pageVO(pages));
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


}
