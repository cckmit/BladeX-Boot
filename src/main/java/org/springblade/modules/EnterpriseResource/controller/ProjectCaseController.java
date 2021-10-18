
package org.springblade.modules.EnterpriseResource.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.common.enums.RescoreEnum;
import org.springblade.common.utils.EncryptedIDUtil;
import org.springblade.common.utils.EncryptionDecryption;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.modules.EnterpriseResource.vo.ProjectCaseDemo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.EnterpriseResource.entity.ProjectCase;
import org.springblade.modules.EnterpriseResource.vo.ProjectCaseVO;
import org.springblade.modules.EnterpriseResource.wrapper.ProjectCaseWrapper;
import org.springblade.modules.EnterpriseResource.service.IProjectCaseService;
import org.springblade.core.boot.ctrl.BladeController;

import java.util.List;

/**
 * 项目案例表 控制器
 *
 * @author BladeX
 * @since 2021-09-28
 */
@RestController
@AllArgsConstructor
@RequestMapping("blade-resource/projectcase")
@Api(value = "项目案例表", tags = "项目案例表接口")
public class ProjectCaseController extends BladeController {

	private final IProjectCaseService projectCaseService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入projectCase")
	public R<ProjectCaseVO> detail(ProjectCase projectCase) {
		ProjectCase detail = projectCaseService.getOne(Condition.getQueryWrapper(projectCase));
		return R.data(ProjectCaseWrapper.build().entityVO(detail));
	}

	/**
	 * 分页 项目案例表
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入projectCase")
	public R<IPage<ProjectCaseVO>> list(ProjectCase projectCase, Query query) {
		IPage<ProjectCase> pages = projectCaseService.page(Condition.getPage(query), Condition.getQueryWrapper(projectCase));
		IPage<ProjectCaseVO> s = ProjectCaseWrapper.build().pageVO(pages);
		for (ProjectCase temp: s.getRecords()){
		String a =	EncryptionDecryption.decryptPhone(temp.getPhone());
		String b =	EncryptedIDUtil.phoneMask(a);
		temp.setPhone(b);
		}
		return R.data(s);
	}

	/**
	 * 自定义分页 项目案例表
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入projectCase")
	public R<IPage<ProjectCaseVO>> page(ProjectCaseVO projectCase, Query query) {
		IPage<ProjectCaseVO> pages = projectCaseService.selectProjectCasePage(Condition.getPage(query), projectCase);
		return R.data(pages);
	}

	/**
	 * 新增 项目案例表
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入projectCase")
	public void save(@Valid @RequestBody ProjectCaseDemo demo) {
		 R.status(projectCaseService.saveFile(demo));

	}

	/**
	 * 修改 项目案例表
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入projectCase")
	public R update(@Valid @RequestBody ProjectCase projectCase) {
		return R.status(projectCaseService.updateById(projectCase));
	}

	/**
	 * 新增或修改 项目案例表
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入projectCase")
	public void submit(@Valid @RequestBody ProjectCaseDemo demo) {
		projectCaseService.update(demo);
	}


	/**
	 * 删除 项目案例表
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(projectCaseService.deleteLogic(Func.toLongList(ids)));
	}

	/**
	 *
	 * 根据父类查询对应附件
	 *
	 * @return
	 */
	@PostMapping("/selectListId")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "根据主键查询对应附件", notes = "传入aptitudeId")
	public List<ProjectCaseVO> selectListId(Long objectId) {
		return projectCaseService.selectListId(objectId);
	}


}
