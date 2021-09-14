
package org.springblade.modules.EnterpriseResource.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.constant.RoleConstant;
import org.springblade.core.tool.utils.Func;
import org.springblade.modules.EnterpriseResource.entity.File;
import org.springblade.modules.EnterpriseResource.service.IFileService;
import org.springblade.modules.EnterpriseResource.vo.FileVO;
import org.springblade.modules.EnterpriseResource.wrapper.FileWrapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;

import org.springblade.core.boot.ctrl.BladeController;

/**
 * 企业资产附件表 控制器
 *
 * @author BladeX
 * @since 2021-09-02
 */
@RestController
@AllArgsConstructor
@RequestMapping("blade-resource/file")
@PreAuth(RoleConstant.HAS_ROLE_ADMIN)
@Api(value = "企业资产附件表", tags = "企业资产附件表接口")
public class FileController extends BladeController {

	private final IFileService fileService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入file")
	public R<FileVO> detail(File file) {
		File detail = fileService.getOne(Condition.getQueryWrapper(file));
		return R.data(FileWrapper.build().entityVO(detail));
	}

	/**
	 * 分页 企业资产附件表
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入file")
	public R<IPage<FileVO>> list(File file, Query query) {
		IPage<File> pages = fileService.page(Condition.getPage(query), Condition.getQueryWrapper(file));
		return R.data(FileWrapper.build().pageVO(pages));
	}


	/**
	 * 自定义分页 企业资产附件表
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入file")
	public R<IPage<FileVO>> page(FileVO file, Query query) {
		IPage<FileVO> pages = fileService.selectFilePage(Condition.getPage(query), file);
		return R.data(pages);
	}

	/**
	 * 新增 企业资产附件表
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入file")
	public R save(@Valid @RequestBody File file) {
		return R.status(fileService.save(file));
	}

	/**
	 * 修改 企业资产附件表
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入file")
	public R update(@Valid @RequestBody File file) {
		return R.status(fileService.updateById(file));
	}

	/**
	 * 新增或修改 企业资产附件表
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入file")
	public R submit(@Valid @RequestBody File file) {
		return R.status(fileService.saveOrUpdate(file));
	}


	/**
	 * 删除 企业资产附件表
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(fileService.deleteLogic(Func.toLongList(ids)));
	}


}
