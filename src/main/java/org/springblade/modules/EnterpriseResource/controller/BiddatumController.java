
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
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.modules.EnterpriseResource.entity.Biddatum;
import org.springblade.modules.EnterpriseResource.service.IBiddatumService;
import org.springblade.modules.EnterpriseResource.vo.BiddatumVO;
import org.springblade.modules.EnterpriseResource.wrapper.BiddatumWrapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springblade.core.boot.ctrl.BladeController;

import java.util.List;

/**
 * 投标资料表 控制器
 *
 * @author BladeX
 * @since 2021-09-23
 */
@RestController
@AllArgsConstructor
@RequestMapping("blade-resource/biddatum")
@Api(value = "投标资料表", tags = "投标资料表接口")
public class BiddatumController extends BladeController {

	private final IBiddatumService biddatumService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入biddatum")
	public R<BiddatumVO> detail(Biddatum biddatum) {
		Biddatum detail = biddatumService.getOne(Condition.getQueryWrapper(biddatum));
		return R.data(BiddatumWrapper.build().entityVO(detail));
	}

	/**
	 * 分页 投标资料表
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入biddatum")
	public R<IPage<BiddatumVO>> list(Biddatum biddatum, Query query) {
		IPage<Biddatum> pages = biddatumService.page(Condition.getPage(query), Condition.getQueryWrapper(biddatum));
		return R.data(BiddatumWrapper.build().pageVO(pages));
	}


	/**
	 * 自定义分页 投标资料表
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入biddatum")
	public R<IPage<BiddatumVO>> page(BiddatumVO biddatum, Query query) {
		IPage<BiddatumVO> pages = biddatumService.selectBiddatumPage(Condition.getPage(query), biddatum);
		return R.data(pages);
	}

	/**
	 * 新增 投标资料表
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入biddatum")
	public R save(@Valid @RequestBody Biddatum biddatum) {
		return R.status(biddatumService.save(biddatum));
	}

	/**
	 * 修改 投标资料表
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入biddatum")
	public R update(@Valid @RequestBody Biddatum biddatum) {
		return R.status(biddatumService.updateById(biddatum));
	}

	/**
	 * 新增或修改 投标资料表
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入biddatum")
	public R submit(@Valid @RequestBody Biddatum biddatum) {
		return R.status(biddatumService.saveOrUpdate(biddatum));
	}


	/**
	 * 删除 投标资料表
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(biddatumService.deleteLogic(Func.toLongList(ids)));
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
	public List<BiddatumVO> selectListId(Long objectId) {
		return biddatumService.selectListId(objectId);
	}

}
