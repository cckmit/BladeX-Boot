
package org.springblade.modules.EnterpriseResource.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import lombok.AllArgsConstructor;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.redisson.api.RMap;
import org.springblade.core.excel.util.ExcelUtil;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tenant.annotation.NonDS;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.DateUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.modules.EnterpriseResource.dto.AptitudeDTO;
import org.springblade.modules.EnterpriseResource.entity.Aptitude;
import org.springblade.modules.EnterpriseResource.entity.EnterpriseLog;
import org.springblade.modules.EnterpriseResource.excel.AptitudeExcel;
import org.springblade.modules.EnterpriseResource.excel.AptitudeImporter;
import org.springblade.modules.EnterpriseResource.service.IAptitudeService;
import org.springblade.modules.EnterpriseResource.service.IEnterpriseLogService;
import org.springblade.modules.EnterpriseResource.vo.AptitudeVO;
import org.springblade.modules.EnterpriseResource.vo.EnterpriseLogVO;
import org.springblade.modules.EnterpriseResource.vo.demo;
import org.springblade.modules.EnterpriseResource.wrapper.AptitudeWrapper;
import org.springblade.modules.EnterpriseResource.wrapper.AptitudeWrapperVO;
import org.springblade.modules.system.entity.Tenant;
import org.springblade.modules.system.service.ITenantService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springblade.core.boot.ctrl.BladeController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;
import java.util.*;

/**
 * ??????????????? ?????????
 *
 * @author BladeX
 * @since 2021-09-02
 */
@NonDS
@RestController
@AllArgsConstructor
@RequestMapping("blade-resource/aptitude")
@Api(value = "??????????????????", tags = "??????????????????")
public class AptitudeController extends BladeController {

	private final IAptitudeService aptitudeService;

	private final ITenantService tenantService;

	private final IEnterpriseLogService enterpriseLogService;


	@Autowired
	private RabbitTemplate rabbitTemplate;


	/**
	 * ??????
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "??????", notes = "??????aptitude")
	public R<AptitudeDTO> detail(Aptitude aptitude) {
		AptitudeDTO detail = aptitudeService.selectFileLsit(aptitude.getId());
		return R.data(detail);
	}


	/**
	 * ??????TenantID?????????????????????
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 22)
	@ApiOperation(value = "??????TenantID?????????????????????", notes = "??????id")
	public R<IPage<AptitudeVO>> selectTenantLsit(Query query) {
		String a = AuthUtil.getTenantId();
		Tenant tenantOne = tenantService.selectId(a);
		IPage<Aptitude> pages = aptitudeService.selectTenantLsit(Condition.getPage(query), tenantOne.getId());
		return R.data(AptitudeWrapper.build().pageVO(pages));
	}


	/**
	 * ?????????????????????????????????
	 */
	@GetMapping("/selectcatalogueLsit")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "??????catalogueID???????????????????????????", notes = "??????id")
	public R<IPage<AptitudeVO>> selectcatalogueLsit(Long id, Query query) {
		IPage<Aptitude> pages = aptitudeService.selectcatalogueLsit(Condition.getPage(query), id);
		return R.data(AptitudeWrapper.build().pageVO(pages));
	}


	/**
	 * ???????????????(?????????)
	 */
	@GetMapping("/selectAptitudePage")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "?????????????????????????????????", notes = "??????aptitude")
	public R<IPage<AptitudeVO>> selectAptitudePage(AptitudeVO aptitude, Query query) {
		String a = AuthUtil.getTenantId();
		Tenant tenantOne = tenantService.selectId(a);
		aptitude.setTenementId(tenantOne.getId());
		IPage<AptitudeVO> pages = aptitudeService.selectAptitudePage(Condition.getPage(query), aptitude);
		return R.data(AptitudeWrapperVO.build().pageVO(pages));
	}


	/**
	 * ??????????????????
	 */
	@GetMapping("/selectLongTerm")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "??????????????????", notes = "??????aptitude")
	public R<IPage<AptitudeVO>> selectLongTerm(AptitudeVO aptitude, Query query) {
		String a = AuthUtil.getTenantId();
		Tenant tenantOne = tenantService.selectId(a);
		aptitude.setTenementId(tenantOne.getId());
		IPage<AptitudeVO> pages = aptitudeService.selectLongTerm(Condition.getPage(query), aptitude);
		return R.data(AptitudeWrapperVO.build().pageVO(pages));
	}

	/**
	 * ???????????????+??????????????????????????????
	 */
	@GetMapping("/selectAptitudeDim")
	@ApiOperationSupport(order = 9)
	@ApiOperation(value = "??????", notes = "??????aptitude")
	public R<IPage<AptitudeVO>> selectAptitudeDim(AptitudeVO aptitude, Query query) {
		IPage<AptitudeVO> pages = aptitudeService.selectAptitudeDim(Condition.getPage(query), aptitude);
		return R.data(pages);
	}

	/**
	 * ?????????????????????
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "??????", notes = "??????demo")
	public void save(@RequestBody demo demo) {
		R.status(aptitudeService.saveFile(demo));
	}

	/**
	 * ?????? ???????????????
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "??????", notes = "??????aptitude")
	public R update(@Valid @RequestBody Aptitude aptitude) {

		return R.status(aptitudeService.updateById(aptitude));
	}

	/**
	 * ??????????????? ???????????????
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "???????????????", notes = "??????demo")
	public void submit(@Valid @RequestBody demo demo) {
		aptitudeService.update(demo);
	}


	/**
	 * ?????? ???????????????
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "????????????", notes = "??????ids")
	public R remove(@ApiParam(value = "????????????", required = true) @RequestParam String ids) {
		return R.status(aptitudeService.deleteLogic(Func.toLongList(ids)));
	}


	/**
	 * ?????????????????????????????????????????????
	 *
	 * @return
	 */
	@PostMapping("/aptitudeTypeId")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "?????????????????????????????????????????????", notes = "??????aptitudeType")
	public List<AptitudeVO> aptitudeTypeId(Long aptitudeType) {
		return aptitudeService.aptitudeTypeId(aptitudeType);
	}


	/**
	 * ???????????????????????????????????????????????????
	 */
	@GetMapping("export-aptitude")
	@ApiOperationSupport(order = 13)
	@ApiOperation(value = "???????????????????????????????????????????????????", notes = "??????aptitude(????????????????????? ???????????????)")
	public void exportAptitude(@ApiIgnore @RequestParam Map<String, Object> aptitude, BladeUser bladeUser, HttpServletResponse response) {
		QueryWrapper<Aptitude> queryWrapper = Condition.getQueryWrapper(aptitude, Aptitude.class);
		//QueryWrapper<Aptitude> queryWrapper =new QueryWrapper<>();
		if (!AuthUtil.isAdmin()) {
			queryWrapper.lambda().eq(Aptitude::getTenantId, bladeUser.getTenantId());
		}
		//queryWrapper.lambda().eq(Aptitude::getIsDeleted, BladeConstant.DB_NOT_DELETED);
		List<AptitudeExcel> list = aptitudeService.exportAptitude(queryWrapper);
		ExcelUtil.export(response, "????????????" + DateUtil.time(), "???????????????", list, AptitudeExcel.class);
	}

	/**
	 * ??????????????????(???????????????)U
	 */
	@PostMapping("exportAptitude")
	@ApiOperationSupport(order = 13)
	@ApiOperation(value = "??????????????????(???????????????)", notes = "??????aptitude(????????????????????? ???????????????)")
	public void exportAxptitude1(@ApiIgnore Aptitude aptitude, String ids) {
		if (ids == null) {
			String a = AuthUtil.getTenantId();
			Tenant tenantOne = tenantService.selectId(a);
			aptitude.setTenementId(tenantOne.getId());
			EnterpriseLog entity = new EnterpriseLog();
			String gather = aptitude.getProvincialCompanyId() + "," + aptitude.getAptitudeId() + "," + aptitude.getTerritoryId() + "," + aptitude.getPropertyId() + "," + aptitude.getCategoryId();
			entity.setMainCondition(gather);
			Long userId = AuthUtil.getUserId();
			entity.setUserId(userId);
			Date d = new Date();
			entity.setCreateTime(d);
			entity.setStatus(0);
			enterpriseLogService.save(entity);
			JSONObject json = new JSONObject();
			json.put("aptitude", aptitude);
			json.put("ids01", entity.getId());
			rabbitTemplate.convertAndSend("rabbitmq_queue_object", json.toJSONString());
		}
		if (ids != null) {
			EnterpriseLog entity = new EnterpriseLog();
			Long userId = AuthUtil.getUserId();
			entity.setUserId(userId);
			Date d = new Date();
			entity.setCreateTime(d);
			entity.setStatus(0);
			entity.setAptitudeId(ids);
			enterpriseLogService.save(entity);
			List<Long> idss = Func.toLongList(ids);
			JSONObject json = new JSONObject();
			json.put("idss", idss);
			json.put("ids01", entity.getId());
			rabbitTemplate.convertAndSend("rabbitmq_queue_object", json.toJSONString());
		}

	}


	/**
	 * ??????????????????
	 *
	 * @return
	 */
	@GetMapping("ownloadDirectory")
	@ApiOperationSupport(order = 14)
	@ApiOperation(value = "??????????????????")
	public R<Map> ownloadDirectory() {
		List<EnterpriseLogVO> enterpriseLog0 = enterpriseLogService.selectStatus0();
		List<EnterpriseLogVO> enterpriseLog1 = enterpriseLogService.selectStatus1();
		Map map = new HashMap();
		List<EnterpriseLogVO> List = new ArrayList<EnterpriseLogVO>();
		map.put("finish", enterpriseLog0);
		map.put("beDownloading", enterpriseLog1);
		return R.data(map);
	}

	/**
	 * ????????????
	 */
	@GetMapping("export-template")
	@ApiOperationSupport(order = 14)
	@ApiOperation(value = "????????????????????????")
	public void exportUser(HttpServletResponse response) {
		List<AptitudeExcel> list = new ArrayList<>();
		ExcelUtil.export(response, "????????????", "???????????????", list, AptitudeExcel.class);
	}


	/**
	 * ??????????????????
	 */
	@PostMapping("import-aptitude")
	@ApiOperationSupport(order = 16)
	@ApiOperation(value = "??????????????????", notes = "??????excel")
	public R importAptitude(MultipartFile file, Integer isCovered) {
		AptitudeImporter aptitudeImporter = new AptitudeImporter(aptitudeService, isCovered == 1, "");
		ExcelUtil.save(file, aptitudeImporter, AptitudeExcel.class);
		return R.success("????????????");
	}


}
