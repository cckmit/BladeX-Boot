
package org.springblade.modules.EnterpriseResource.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.common.utils.EncryptedIDUtil;
import org.springblade.common.utils.EncryptionDecryption;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.modules.EnterpriseResource.entity.PersonnelQualification;
import org.springblade.modules.EnterpriseResource.service.IPersonnelQualificationService;
import org.springblade.modules.EnterpriseResource.vo.AttachmentProveVO;
import org.springblade.modules.EnterpriseResource.vo.PersonnelQualificationDemo;
import org.springblade.modules.EnterpriseResource.vo.PersonnelQualificationVO;
import org.springblade.modules.EnterpriseResource.vo.demo;
import org.springblade.modules.EnterpriseResource.wrapper.PersonnelQualificationWrapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springblade.core.boot.ctrl.BladeController;

import java.util.List;

/**
 * 人员资质表 控制器
 *
 * @author BladeX
 * @since 2021-09-23
 */
@RestController
@AllArgsConstructor
@RequestMapping("blade-resource/personnelqualification")
@Api(value = "人员资质表", tags = "人员资质表接口")
public class PersonnelQualificationController extends BladeController {

	private final IPersonnelQualificationService personnelQualificationService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入personnelQualification")
	public R<PersonnelQualificationVO> detail(PersonnelQualification personnelQualification) {
		PersonnelQualification detail = personnelQualificationService.getOne(Condition.getQueryWrapper(personnelQualification));
		return R.data(PersonnelQualificationWrapper.build().entityVO(detail));
	}

	/**
	 * 分页 人员资质表
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入personnelQualification")
	public R<IPage<PersonnelQualificationVO>> list(PersonnelQualification personnelQualification, Query query) {
		IPage<PersonnelQualification> pages = personnelQualificationService.page(Condition.getPage(query), Condition.getQueryWrapper(personnelQualification));
		IPage<PersonnelQualificationVO> s = PersonnelQualificationWrapper.build().pageVO(pages);
		for (PersonnelQualification temp : s.getRecords()) {
			String a = EncryptionDecryption.decryptPhone(temp.getIdNumber());
			temp.setIdNumber(EncryptedIDUtil.idCardMask(a));
		}
		return R.data(s);
	}

	/**
	 * 自定义分页 人员资质表
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入personnelQualification")
	public R<IPage<PersonnelQualificationVO>> page(PersonnelQualificationVO personnelQualification, Query query) {
		IPage<PersonnelQualificationVO> pages = personnelQualificationService.selectPersonnelQualificationPage(Condition.getPage(query), personnelQualification);
		return R.data(pages);
	}

	/**
	 * 新增 人员资质表
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入personnelQualification")
	public void save(@Valid @RequestBody PersonnelQualificationDemo demo) {
		R.status(personnelQualificationService.saveFile(demo));
	}

	/**
	 * 修改 人员资质表
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入personnelQualification")
	public R update(@Valid @RequestBody PersonnelQualification personnelQualification) {
		return R.status(personnelQualificationService.updateById(personnelQualification));
	}

	/**
	 * 新增或修改 人员资质表
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入personnelQualification")
	public void submit(@Valid @RequestBody PersonnelQualificationDemo demo) {
		personnelQualificationService.update(demo);
	}


	/**
	 * 删除 人员资质表
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(personnelQualificationService.deleteLogic(Func.toLongList(ids)));
	}


	/**
	 *
	 * 根据主键查询对应附件
	 *
	 * @return
	 */
	@PostMapping("/selectListId")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "根据主键查询对应附件", notes = "objectId")
	public List<PersonnelQualificationVO> selectListId(Long objectId) {
		return personnelQualificationService.selectListId(objectId);
	}
}
