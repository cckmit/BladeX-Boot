
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
import org.springblade.modules.EnterpriseResource.vo.ContractDemo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.EnterpriseResource.entity.Contract;
import org.springblade.modules.EnterpriseResource.vo.ContractVO;
import org.springblade.modules.EnterpriseResource.wrapper.ContractWrapper;
import org.springblade.modules.EnterpriseResource.service.IContractService;
import org.springblade.core.boot.ctrl.BladeController;

import java.util.List;

/**
 * 合同表 控制器
 *
 * @author BladeX
 * @since 2021-09-28
 */
@RestController
@AllArgsConstructor
@RequestMapping("blade-resource/contract")
@Api(value = "合同表", tags = "合同表接口")
public class ContractController extends BladeController {

	private final IContractService contractService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入contract")
	public R<ContractVO> detail(Contract contract) {
		Contract detail = contractService.getOne(Condition.getQueryWrapper(contract));
		return R.data(ContractWrapper.build().entityVO(detail));
	}

	/**
	 * 分页 合同表
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入contract")
	public R<IPage<ContractVO>> list(Contract contract, Query query) {
		IPage<Contract> pages = contractService.page(Condition.getPage(query), Condition.getQueryWrapper(contract));
		return R.data(ContractWrapper.build().pageVO(pages));
	}


	/**
	 * 自定义分页 合同表
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入contract")
	public R<IPage<ContractVO>> page(ContractVO contract, Query query) {
		IPage<ContractVO> pages = contractService.selectContractPage(Condition.getPage(query), contract);
		return R.data(pages);
	}

	/**
	 * 新增 合同表
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入contract")
	public void save(@Valid @RequestBody ContractDemo demo) {
		R.status(contractService.saveFile(demo));

	}

	/**
	 * 修改 合同表
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入contract")
	public R update(@Valid @RequestBody Contract contract) {
		return R.status(contractService.updateById(contract));
	}

	/**
	 * 新增或修改 合同表
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入contract")
	public void submit(@Valid @RequestBody ContractDemo demo) {
		contractService.update(demo);
	}


	/**
	 * 删除 合同表
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(contractService.deleteLogic(Func.toLongList(ids)));
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
	public List<ContractVO> selectListId(Long objectId) {
		return contractService.selectListId(objectId);
	}

}
