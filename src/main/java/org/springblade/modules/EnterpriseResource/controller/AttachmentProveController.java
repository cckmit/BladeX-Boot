
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
import org.springblade.modules.EnterpriseResource.entity.AttachmentProve;
import org.springblade.modules.EnterpriseResource.service.IAttachmentProveService;
import org.springblade.modules.EnterpriseResource.vo.AttachmentProveDemo;
import org.springblade.modules.EnterpriseResource.vo.AttachmentProveVO;
import org.springblade.modules.EnterpriseResource.wrapper.AttachmentProveWrapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springblade.core.boot.ctrl.BladeController;
import java.util.List;

/**
 * 社保证明表 控制器
 *
 * @author BladeX
 * @since 2021-09-23
 */
@RestController
@AllArgsConstructor
@RequestMapping("blade-resource/attachmentprove")
@Api(value = "社保证明表", tags = "社保证明表接口")
public class AttachmentProveController extends BladeController {

	private final IAttachmentProveService attachmentProveService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入attachmentProve")
	public R<AttachmentProveVO> detail(AttachmentProve attachmentProve) {
		AttachmentProve detail = attachmentProveService.getOne(Condition.getQueryWrapper(attachmentProve));
		return R.data(AttachmentProveWrapper.build().entityVO(detail));
	}

	/**
	 * 分页 社保证明表
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入attachmentProve")
	public R<IPage<AttachmentProveVO>> list(AttachmentProve attachmentProve, Query query) {
		IPage<AttachmentProve> pages = attachmentProveService.page(Condition.getPage(query), Condition.getQueryWrapper(attachmentProve));
		IPage<AttachmentProveVO> s =  AttachmentProveWrapper.build().pageVO(pages);
		for (AttachmentProve temp:s.getRecords()){
			temp.setIdNumber(EncryptedIDUtil.idCardMask(EncryptionDecryption.decryptPhone(temp.getIdNumber())));
		}
		return R.data(s);
	}


	/**
	 * 自定义分页 社保证明表
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入attachmentProve")
	public R<IPage<AttachmentProveVO>> page(AttachmentProveVO attachmentProve, Query query) {
		IPage<AttachmentProveVO> pages = attachmentProveService.selectAttachmentProvePage(Condition.getPage(query), attachmentProve);
		return R.data(pages);
	}

	/**
	 * 新增 社保证明表
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入attachmentProve")
	public R save(@Valid @RequestBody AttachmentProveDemo demo) {
	return R.status(attachmentProveService.saveFile(demo));
	}

	/**
	 * 修改 社保证明表
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入attachmentProve")
	public R update(@Valid @RequestBody AttachmentProve attachmentProve) {
		return R.status(attachmentProveService.updateById(attachmentProve));
	}

	/**
	 * 新增或修改 社保证明表
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入attachmentProve")
	public void submit(@Valid @RequestBody  AttachmentProveDemo demo) {
	 attachmentProveService.update(demo);
	}


	/**
	 * 删除 社保证明表
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(attachmentProveService.deleteLogic(Func.toLongList(ids)));
	}

	/**
	 *
	 * 根据主键查询对应附件
	 *
	 * @return
	 */
	@PostMapping("/selectListId")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "根据主键查询对应附件", notes = "传入objectId")
	public List<AttachmentProveVO> selectListId(Long objectId) {
		return attachmentProveService.selectListId(objectId);
	}


}
