package org.springblade.modules.client.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.log.annotation.ApiLog;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.modules.client.entity.ClientContactOrg;
import org.springblade.modules.client.service.ClientContactOrgService;
import org.springblade.modules.client.vo.ClientContactOrgVO;
import org.springblade.modules.client.wrapper.ClientContactOrgWrapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhuyilong
 */
@RestController
@AllArgsConstructor
@RequestMapping("blade-client/contact-org")
@Api(value = "", tags = "客户联系人组织")
public class ClientContactOrgController extends BladeController {

	private final ClientContactOrgService clientContactOrgService;

	/**
	 * 分页
	 */
	@PostMapping("/list")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "分页", notes = "传入baseInfo")
	@ApiLog("客户联系人组织列表")
	public R<IPage<ClientContactOrgVO>> list(@RequestBody Query query, @RequestBody ClientContactOrgVO condition) {
		IPage<ClientContactOrg> page = clientContactOrgService.page(Condition.getPage(query), Condition.getQueryWrapper(condition));
		return R.data(ClientContactOrgWrapper.build().pageVO(page));
	}

	/**
	 * 分页
	 */
	@PostMapping("/tree")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "组织树", notes = "传入baseInfo")
	@ApiLog("客户联系人组织列表")
	public R<List<ClientContactOrgVO>> tree(@RequestBody ClientContactOrgVO condition) {
		return R.data(clientContactOrgService.getOrgTreeList(condition));
	}


	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "组织详情", notes = "传入记录 ID")
	@ApiLog("客户联系人组织详情")
	public R<ClientContactOrg> detail(ClientContactOrgVO condition) {
		return R.data(clientContactOrgService.getById(condition.getId()));
	}

	/**
	 * 详情（带父层级名称）
	 */
	@GetMapping("/getFullContactOrg")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "组织详情（带父层级名称）", notes = "传入记录 ID")
	@ApiLog("客户联系人组织详情（带父层级名称）")
	public R<ClientContactOrg> getFullContactOrg(ClientContactOrgVO condition) {
		return R.data(clientContactOrgService.getFullContactOrg(condition));
	}

	/**
	 * 新增
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "联系人组织信息")
	@ApiLog("客户联系人组织新增")
	public R save(@RequestBody ClientContactOrgVO entity) {
		return R.data(clientContactOrgService.saveOrUpdateOrg(entity));
	}

	/**
	 * 更新
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "联系人组织信息")
	@ApiLog("客户联系人组织新增")
	public R update(@RequestBody ClientContactOrgVO entity) {
		return R.data(clientContactOrgService.saveOrUpdateOrg(entity));
	}

	/**
	 * 删除
	 */
	@PostMapping("/del")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "删除", notes = "联系人组织信息")
	@ApiLog("客户联系人组织删除")
	public R del(@RequestBody ClientContactOrgVO condition) {
		return R.data(clientContactOrgService.removeByIds(condition.getIds()));
	}

	/**
	 * 组织联系人列表
	 */
	@PostMapping("/orgClientList")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "组织联系人列表", notes = "")
	@ApiLog("组织联系人列表")
	public R<IPage<ClientContactOrgVO>> orgClientList(@RequestBody Query query, @RequestBody ClientContactOrgVO condition) {
		return R.data(clientContactOrgService.pageOrgClient(Condition.getPage(query), condition));
	}

	/**
	 * 联系人组织懒加载
	 */
	@PostMapping("/lazyTree")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "联系人组织懒加载", notes = "")
	@ApiLog("联系人组织懒加载")
	public R<List<ClientContactOrg>> lazyTree(@RequestBody ClientContactOrgVO condition) {
		QueryWrapper<ClientContactOrg> query = Condition.getQueryWrapper(condition);
		query.orderByAsc("sort");
		return R.data(clientContactOrgService.list(query));
	}

}
