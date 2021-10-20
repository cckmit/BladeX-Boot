package org.springblade.modules.client.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.log.annotation.ApiLog;
import org.springblade.core.tool.api.R;
import org.springblade.modules.client.service.ClientAssignService;
import org.springblade.modules.client.vo.ClientAssignVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhuyilong
 */
@RestController
@AllArgsConstructor
@RequestMapping("blade-client/assign")
@Api(value = "", tags = "客户分配")
public class ClientAssignController extends BladeController {

	private final ClientAssignService clientAssignService;

	@PostMapping("/list")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "列表", notes = "传入baseInfo")
	@ApiLog("客户分配信息列表")
	public R<List<ClientAssignVO>> list(@RequestBody ClientAssignVO condition) {
		return R.data(clientAssignService.listClientAssign(condition));
	}


	@PostMapping("/save")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "保存", notes = "")
	@ApiLog("客户分配信息保存")
	public R save(@RequestBody ClientAssignVO info) {
		clientAssignService.saveClientAssign(info);
		return R.status(true);
	}

}
