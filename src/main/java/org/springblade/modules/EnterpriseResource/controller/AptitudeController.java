
package org.springblade.modules.EnterpriseResource.controller;

import com.alibaba.fastjson.JSON;
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

import org.springblade.core.excel.util.ExcelUtil;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tenant.annotation.NonDS;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.constant.BladeConstant;
import org.springblade.core.tool.constant.RoleConstant;
import org.springblade.core.tool.utils.DateUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.modules.EnterpriseResource.dto.AptitudeDTO;
import org.springblade.modules.EnterpriseResource.entity.Aptitude;
import org.springblade.modules.EnterpriseResource.entity.AptitudeCatalogue;
import org.springblade.modules.EnterpriseResource.entity.EnterpriseLog;
import org.springblade.modules.EnterpriseResource.excel.AptitudeExcel;
import org.springblade.modules.EnterpriseResource.excel.AptitudeImporter;
import org.springblade.modules.EnterpriseResource.service.IAptitudeCatalogueService;
import org.springblade.modules.EnterpriseResource.service.IAptitudeService;
import org.springblade.modules.EnterpriseResource.service.IEnterpriseLogService;
import org.springblade.modules.EnterpriseResource.vo.AptitudeVO;
import org.springblade.modules.EnterpriseResource.vo.demo;
import org.springblade.modules.EnterpriseResource.wrapper.AptitudeWrapper;
import org.springblade.modules.EnterpriseResource.wrapper.AptitudeWrapperDTO;
import org.springblade.modules.EnterpriseResource.wrapper.AptitudeWrapperVO;
import org.springblade.modules.system.entity.Tenant;
import org.springblade.modules.system.entity.User;
import org.springblade.modules.system.excel.UserExcel;
import org.springblade.modules.system.excel.UserImporter;
import org.springblade.modules.system.service.ITenantService;
import org.springblade.modules.system.service.IUserService;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springblade.core.boot.ctrl.BladeController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 企业资质表 控制器
 *
 * @author BladeX
 * @since 2021-09-02
 */
@NonDS
@RestController
@AllArgsConstructor
@RequestMapping("blade-resource/aptitude")
@Api(value = "企业资质模块", tags = "企业资质模块")
public class AptitudeController extends BladeController {

	private final IAptitudeService aptitudeService;

	private final ITenantService tenantService;

	private final IEnterpriseLogService enterpriseLogService;

	@Autowired
	private RabbitTemplate rabbitTemplate;


	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入aptitude")
	public R<AptitudeDTO> detail(Aptitude aptitude) {
		AptitudeDTO detail = aptitudeService.selectFileLsit(aptitude.getId());
		return R.data(detail);
	}



	/**
	 * 分页 企业资质表
	 */
//	@GetMapping("/list")
//	@ApiOperationSupport(order = 2)
//	@ApiOperation(value = "系统生成（分页）", notes = "传入aptitude")
//	public R<IPage<AptitudeVO>> list(Aptitude aptitude, Query query) {
//		IPage<Aptitude> pages = aptitudeService.page(Condition.getPage(query), Condition.getQueryWrapper(aptitude));
//		return R.data(AptitudeWrapper.build().pageVO(pages));
//	}

	/**
	 *
	 *  根据TenantID查询父子级数据
	 *
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order =22)
	@ApiOperation(value = "根据TenantID查询父子级数据", notes = "传入id")
	public R<IPage<AptitudeVO>> selectTenantLsit(Query query) {
		String a = AuthUtil.getTenantId();
		Tenant tenantOne = tenantService.selectId(a);
		IPage<Aptitude> pages = aptitudeService.selectTenantLsit(Condition.getPage(query), tenantOne.getId());
		return R.data(AptitudeWrapper.build().pageVO(pages));
	}



	/**
	 * 根据主键查询父子级数据
	 */
	@GetMapping("/selectcatalogueLsit")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "根据catalogueID主键查询父子级数据", notes = "传入id")
	public R<IPage<AptitudeVO>> selectcatalogueLsit(Long id, Query query) {
		IPage<Aptitude> pages = aptitudeService.selectcatalogueLsit(Condition.getPage(query), id);
		return R.data(AptitudeWrapper.build().pageVO(pages));
	}






	/**
	 * 自定义分页(拿)
	 */
	@GetMapping("/selectAptitudePage")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "企业列表分页（带名字）", notes = "传入aptitude")
	public R<IPage<AptitudeVO>> selectAptitudePage(AptitudeVO aptitude, Query query) {
		String a = AuthUtil.getTenantId();
		Tenant tenantOne = tenantService.selectId(a);
		aptitude.setTenementId(tenantOne.getId());
		IPage<AptitudeVO> pages = aptitudeService.selectAptitudePage(Condition.getPage(query), aptitude);
		return R.data(AptitudeWrapperVO.build().pageVO(pages));
	}


	/**
	 *
	 * 自定义分页+模糊查询（证书名称）
	 *
	 */
	@GetMapping("/selectAptitudeDim")
	@ApiOperationSupport(order = 9)
	@ApiOperation(value = "分页", notes = "传入aptitude")
	public R<IPage<AptitudeVO>> selectAptitudeDim(AptitudeVO aptitude, Query query) {
		IPage<AptitudeVO> pages = aptitudeService.selectAptitudeDim(Condition.getPage(query), aptitude);
		return R.data(pages);
	}

	/**
	 * 新增企业资质表
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入demo")
	public void save(@RequestBody demo demo) {
		R.status(aptitudeService.saveFile(demo));
	}
	/**
	 * 修改 企业资质表
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入aptitude")
	public R update(@Valid @RequestBody Aptitude aptitude) {

		return R.status(aptitudeService.updateById(aptitude));
	}

	/**
	 * 新增或修改 企业资质表
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入demo")
	public void submit(@Valid @RequestBody demo demo) {
		aptitudeService.update(demo);
	}


	/**
	 * 删除 企业资质表
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(aptitudeService.deleteLogic(Func.toLongList(ids)));
	}



	/**
	 *
	 * 查询对应类型的类型下所有的信息
	 *
	 * @return
	 */
	@PostMapping("/aptitudeTypeId")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "查询对应类型的类型下所有的信息", notes = "传入aptitudeType")
	public List<AptitudeVO> aptitudeTypeId(Long aptitudeType) {
		return aptitudeService.aptitudeTypeId(aptitudeType);
	}



	/**
	 * 导出企业资质（一次性导出全部数据）
	 */
	@GetMapping("export-aptitude")
	@ApiOperationSupport(order = 13)
	@ApiOperation(value = "导出企业资质（一次性导出全部数据）", notes = "传入aptitude(有特定条件就传 没有就不传)")
	public void exportAptitude(@ApiIgnore @RequestParam Map<String, Object> aptitude, BladeUser bladeUser, HttpServletResponse response) {
		QueryWrapper<Aptitude> queryWrapper = Condition.getQueryWrapper(aptitude, Aptitude.class);
		//QueryWrapper<Aptitude> queryWrapper =new QueryWrapper<>();
		if (!AuthUtil.isAdmin()) {
			queryWrapper.lambda().eq(Aptitude::getTenantId, bladeUser.getTenantId());
		}
		//queryWrapper.lambda().eq(Aptitude::getIsDeleted, BladeConstant.DB_NOT_DELETED);
		List<AptitudeExcel> list = aptitudeService.exportAptitude(queryWrapper);
		ExcelUtil.export(response, "企业资质" + DateUtil.time(), "企业资质表", list, AptitudeExcel.class);
	}

	/**
	 * 导出企业资质(带条件导出)
	 */
	@PostMapping("exportAptitude")
	@ApiOperationSupport(order = 13)
	@ApiOperation(value = "导出企业资质(带条件导出)", notes = "传入aptitude(有特定条件就传 没有就不传)")
	public void exportAxptitude1(@ApiIgnore Aptitude aptitude, String ids) {
		if (ids==null){
		String a = AuthUtil.getTenantId();
		Tenant tenantOne = tenantService.selectId(a);
		aptitude.setTenementId(tenantOne.getId());
		EnterpriseLog entity = new EnterpriseLog ();
		String  gather = aptitude.getProvincialCompanyId()+","+aptitude.getAptitudeId()+","+aptitude.getTerritoryId()+","+aptitude.getPropertyId()+","+aptitude.getCategoryId();
		entity.setMainCondition(gather);
		Long userId = AuthUtil.getUserId();
		entity.setUserId(userId);
			Date d = new Date();
			entity.setCreateTime(d);
			entity.setStatus(0);
			enterpriseLogService.save(entity);
			JSONObject json=new JSONObject();
			json.put("aptitude",aptitude);
			json.put("ids01",entity.getId());
			rabbitTemplate.convertAndSend("rabbitmq_queue_object", json.toJSONString());
		}
		if(ids!=null){
			EnterpriseLog entity = new EnterpriseLog ();
			Long userId = AuthUtil.getUserId();
			entity.setUserId(userId);
			Date d = new Date();
			entity.setCreateTime(d);
			entity.setStatus(0);
			entity.setAptitudeId(ids);
			enterpriseLogService.save(entity);
			List<Long> idss= Func.toLongList(ids);
			JSONObject json=new JSONObject();
			json.put("idss",idss);
			json.put("ids01",entity.getId());
			rabbitTemplate.convertAndSend("rabbitmq_queue_object",json.toJSONString() );
		}

	}






	/**
	 * 导出模板
	 */
	@GetMapping("export-template")
	@ApiOperationSupport(order = 14)
	@ApiOperation(value = "企业资质导出模板")
	public void exportUser(HttpServletResponse response) {
		List<AptitudeExcel> list = new ArrayList<>();
		ExcelUtil.export(response, "企业资质", "企业资质表", list, AptitudeExcel.class);
	}



	/**
	 * 导入企业资质
	 */
	@PostMapping("import-aptitude")
	@ApiOperationSupport(order = 16)
	@ApiOperation(value = "导入企业资质", notes = "传入excel")
	public R importAptitude(MultipartFile file, Integer isCovered) {
			AptitudeImporter aptitudeImporter = new AptitudeImporter(aptitudeService, isCovered == 1, "");
			ExcelUtil.save(file, aptitudeImporter, AptitudeExcel.class);
		return R.success("操作成功");
	}



}
