
package org.springblade.modules.EnterpriseResource.controller;

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
import org.springblade.modules.EnterpriseResource.vo.InvoiceDemo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.EnterpriseResource.entity.Invoice;
import org.springblade.modules.EnterpriseResource.vo.InvoiceVO;
import org.springblade.modules.EnterpriseResource.wrapper.InvoiceWrapper;
import org.springblade.modules.EnterpriseResource.service.IInvoiceService;
import org.springblade.core.boot.ctrl.BladeController;

import java.util.List;

/**
 * 发票表 控制器
 *
 * @author BladeX
 * @since 2021-09-28
 */
@RestController
@AllArgsConstructor
@RequestMapping("blade-resource/invoice")
@Api(value = "发票表", tags = "发票表接口")
public class InvoiceController extends BladeController {

	private final IInvoiceService invoiceService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入invoice")
	public R<InvoiceVO> detail(Invoice invoice) {
		Invoice detail = invoiceService.getOne(Condition.getQueryWrapper(invoice));
		return R.data(InvoiceWrapper.build().entityVO(detail));
	}

	/**
	 * 分页 发票表
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入invoice")
	public R<IPage<InvoiceVO>> list(Invoice invoice, Query query) {
		IPage<Invoice> pages = invoiceService.page(Condition.getPage(query), Condition.getQueryWrapper(invoice));
		return R.data(InvoiceWrapper.build().pageVO(pages));
	}


	/**
	 * 自定义分页 发票表
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入invoice")
	public R<IPage<InvoiceVO>> page(InvoiceVO invoice, Query query) {
		IPage<InvoiceVO> pages = invoiceService.selectInvoicePage(Condition.getPage(query), invoice);
		return R.data(pages);
	}

	/**
	 * 新增 发票表
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入invoice")
	public void save(@Valid @RequestBody InvoiceDemo demo) {
		R.status(invoiceService.saveFile(demo));
	}

	/**
	 * 修改 发票表
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入invoice")
	public R update(@Valid @RequestBody Invoice invoice) {
		return R.status(invoiceService.updateById(invoice));
	}

	/**
	 * 新增或修改 发票表
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入invoice")
	public void submit(@Valid @RequestBody InvoiceDemo demo) {
		invoiceService.update(demo);
	}


	/**
	 * 删除 发票表
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(invoiceService.deleteLogic(Func.toLongList(ids)));
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
	public List<InvoiceVO> selectListId(Long objectId) {
		return invoiceService.selectListId(objectId);
	}

}
