
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.EnterpriseResource.entity.BiddatumCatalogue;
import org.springblade.modules.EnterpriseResource.vo.BiddatumCatalogueVO;
import org.springblade.modules.EnterpriseResource.wrapper.BiddatumCatalogueWrapper;
import org.springblade.modules.EnterpriseResource.service.IBiddatumCatalogueService;
import org.springblade.core.boot.ctrl.BladeController;

import java.util.Iterator;
import java.util.List;

/**
 * 投标资料目录表 控制器
 *
 * @author BladeX
 * @since 2021-10-18
 */
@RestController
@AllArgsConstructor
@RequestMapping("blade-resource/biddatumcatalogue")
@Api(value = "投标资料目录表", tags = "投标资料目录表接口")
public class BiddatumCatalogueController extends BladeController {

	private final IBiddatumCatalogueService biddatumCatalogueService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入biddatumCatalogue")
	public R<BiddatumCatalogueVO> detail(BiddatumCatalogue biddatumCatalogue) {
		BiddatumCatalogue detail = biddatumCatalogueService.getOne(Condition.getQueryWrapper(biddatumCatalogue));
		return R.data(BiddatumCatalogueWrapper.build().entityVO(detail));
	}

	/**
	 * 分页 投标资料目录表
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入biddatumCatalogue")
	public R<IPage<BiddatumCatalogueVO>> list(BiddatumCatalogue biddatumCatalogue, Query query) {
		IPage<BiddatumCatalogue> pages = biddatumCatalogueService.page(Condition.getPage(query), Condition.getQueryWrapper(biddatumCatalogue));
		return R.data(BiddatumCatalogueWrapper.build().pageVO(pages));
	}


	/**
	 * 自定义分页 投标资料目录表
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入biddatumCatalogue")
	public R<IPage<BiddatumCatalogueVO>> page(BiddatumCatalogueVO biddatumCatalogue, Query query) {
		IPage<BiddatumCatalogueVO> pages = biddatumCatalogueService.selectBiddatumCataloguePage(Condition.getPage(query), biddatumCatalogue);
		return R.data(pages);
	}

	/**
	 * 新增 投标资料目录表
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入biddatumCatalogue")
	public R save(@Valid @RequestBody BiddatumCatalogue biddatumCatalogue) {
		return R.status(biddatumCatalogueService.save(biddatumCatalogue));
	}

	/**
	 * 修改 投标资料目录表
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入biddatumCatalogue")
	public R update(@Valid @RequestBody BiddatumCatalogue biddatumCatalogue) {
		return R.status(biddatumCatalogueService.updateById(biddatumCatalogue));
	}

	/**
	 * 新增或修改 投标资料目录表
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入biddatumCatalogue")
	public R submit(@Valid @RequestBody BiddatumCatalogue biddatumCatalogue) {
		return R.status(biddatumCatalogueService.saveOrUpdate(biddatumCatalogue));
	}


	/**
	 * 删除 投标资料目录表
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(biddatumCatalogueService.deleteLogic(Func.toLongList(ids)));
	}

	/**
	 * 投标资料一级目录合集
	 */
	@GetMapping("/selectCatalog")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "分页")
	public R<List<BiddatumCatalogueVO>> selectCatalog() {
		return R.data(biddatumCatalogueService.selectCatalog());
	}

}

