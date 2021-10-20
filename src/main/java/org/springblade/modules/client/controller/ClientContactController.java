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
import org.springblade.modules.client.entity.ClientContact;
import org.springblade.modules.client.entity.ClientContactOrg;
import org.springblade.modules.client.service.ClientContactOrgService;
import org.springblade.modules.client.service.ClientContactService;
import org.springblade.modules.client.vo.ClientContactVO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhuyilong
 */
@RestController
@AllArgsConstructor
@RequestMapping("blade-client/contact")
@Api(value = "", tags = "客户联系人")
public class ClientContactController extends BladeController {

	private final ClientContactService clientContactService;

	private final ClientContactOrgService clientContactOrgService;

	/**
	 * 分页
	 */
	@PostMapping("/list")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "分页", notes = "传入baseInfo")
	@ApiLog("客户联系人列表")
	public R<IPage<ClientContactVO>> list(@RequestBody Query query, @RequestBody ClientContactVO condition) {
		IPage<ClientContactVO> page = clientContactService.pageContact(Condition.getPage(query.setDescs("create_time")), condition);
		return R.data(page);
	}


	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "详情", notes = "传入记录 ID")
	@ApiLog("客户联系人详情")
	public R<ClientContactVO> detail(ClientContactVO condition) {
		return R.data(clientContactService.getDetail(condition));
	}

	/**
	 * 新增
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "联系人信息")
	@ApiLog("客户联系人新增")
	public R save(@RequestBody ClientContact entity) {
		return R.data(clientContactService.saveContact(entity));
	}

	/**
	 * 更新
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "联系人信息")
	@ApiLog("客户联系人新增")
	public R update(@RequestBody ClientContact entity) {
		return R.data(clientContactService.updateContact(entity));
	}

	/**
	 * 删除
	 */
	@PostMapping("/del")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "删除", notes = "联系人信息")
	@ApiLog("客户联系人删除")
	public R del(@RequestBody ClientContactVO condition) {
		return R.data(clientContactService.batchDelContact(condition.getIds()));
	}

	/**
	 * 通过客户id获取拜访人员列表
	 */
	@GetMapping("/qryContactList")
	@ApiOperationSupport(order = 9)
	@ApiOperation(value = "获取拜访人员", notes = "")
	public R<IPage<ClientContact>> qryContactList(Long clientId, Query query) {
		if (null == clientId) {
			return R.fail("客户id不能为空");
		}
		// 获取该客户下的联系人组织架构
		ClientContactOrg org = new ClientContactOrg();
		org.setClientId(clientId);
		List<ClientContactOrg> list = clientContactOrgService.list(Condition.getQueryWrapper(org));
		if (list == null || list.size() == 0) {
			return R.data(Condition.getPage(query));
		}

		List<Long> idList = list.stream().map(ClientContactOrg::getId).collect(Collectors.toList());
		QueryWrapper<ClientContact> queryWrapper = new QueryWrapper<>();
		queryWrapper.in("contact_org_id", idList);
		queryWrapper.orderByDesc("create_time");
		// 分页获取联系人列表
		IPage<ClientContact> page = clientContactService.page(Condition.getPage(query), queryWrapper);

		return R.data(page);
	}

}
