package org.springblade.modules.client.controller;

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
import org.springblade.modules.client.service.ClientContactService;
import org.springblade.modules.client.vo.ClientContactVO;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhuyilong
 */
@RestController
@AllArgsConstructor
@RequestMapping("blade-client/contact")
@Api(value = "", tags = "客户联系人")
public class ClientContactController extends BladeController {

	private final ClientContactService clientContactService;

	/**
	 * 分页
	 */
	@PostMapping("/list")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "分页", notes = "传入baseInfo")
	@ApiLog("客户联系人列表")
	public R<IPage<ClientContactVO>> list(@RequestBody Query query, @RequestBody ClientContactVO condition) {
		IPage<ClientContactVO> page = clientContactService.pageContact(Condition.getPage(query), condition);
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

}
