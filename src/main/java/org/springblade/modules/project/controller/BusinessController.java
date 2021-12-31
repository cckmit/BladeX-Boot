/*
 *      Copyright (c) 2018-2028, Chill Zhuang All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the dreamlu.net developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: Chill 庄骞 (smallchill@163.com)
 */
package org.springblade.modules.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.joda.time.DateTime;
import org.springblade.common.cache.UserCache;
import org.springblade.common.constant.CommonConstant;
import org.springblade.common.utils.SnowflakeIdUtil;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.excel.util.ExcelUtil;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tenant.annotation.NonDS;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.flow.business.service.FlowBusinessService;
import org.springblade.flow.core.entity.BladeFlow;
import org.springblade.modules.project.dto.BusinessDTO;
import org.springblade.modules.project.entity.Business;
import org.springblade.modules.project.excel.DeptExcel;
import org.springblade.modules.project.excel.GuangxinBusinessExcel;
import org.springblade.modules.project.service.IBusinessService;
import org.springblade.modules.project.vo.BusinessVO;
import org.springblade.modules.project.wrapper.BusinessWrapper;
import org.springblade.modules.system.entity.Dept;
import org.springblade.modules.system.entity.Dict;
import org.springblade.modules.system.service.IDeptService;
import org.springblade.modules.system.service.IDictService;
import org.springblade.modules.system.service.IMajorService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//流程引擎相关import
/**
 * 控制器
 *
 * @author BladeX
 * @since 2021-07-03
 */
@NonDS
@RestController
@AllArgsConstructor
@RequestMapping("blade-project/business")
@Api(value = "商机报备流程以及手机端模块", tags = "商机报备流程以及手机端模块")
public class BusinessController extends BladeController {

	private final IBusinessService businessService ;
	private final FlowBusinessService flowBusinessService ;
	private final RuntimeService runtimeService ;
	private final TaskService taskService ;
	private final IDeptService deptService ;


	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 201)
	@ApiOperation(value = "商机报备详情", notes = "传入business")
	public R<BusinessVO> detail(Business business) {
		Business detail = businessService.getById(business.getId());
		//加入申请人信息
		detail.getFlow().setAssigneeName(UserCache.getUser(detail.getCreateUser()).getName());
		return R.data(BusinessWrapper.build().entityVO(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 202)
	@ApiOperation(value = "商机报备分页", notes = "传入business")
	public R<IPage<BusinessVO>> list(Business business, Query query) {
		IPage<Business> pages = businessService.page(Condition.getPage(query), Condition.getQueryWrapper(business));
		return R.data(BusinessWrapper.build().pageVO(pages));
	}


	/**
	 * 自定义分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 203)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "recordName", value = "商机名称", paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "biddingType", value = "招标方式", paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "projectCatrgory", value = "商机分类", paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "expandMode", value = "拓展模式", paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "industry", value = "行业", paramType = "query", dataType = "String"),
	})
	@ApiOperation(value = "商机报备分页", notes = "传入business")
	public R<IPage<BusinessVO>> page(BusinessVO business, Query query) {
		IPage<BusinessVO> pages = businessService.selectBusinessPage(Condition.getPage(query), business);
		return R.data(pages);
	}

	//region 废弃
	/**
	 * 新增
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 209)
	@ApiOperation(value = "商机报备信息保存", notes = "传入business")
	public R save(@Valid @RequestBody Business business) {
		return R.status(businessService.save(business));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 210)
	@ApiOperation(value = "商机报备信息更新", notes = "传入business")
	public R update(@Valid @RequestBody Business business) {
		return R.status(businessService.updateById(business));
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 211)
	@ApiOperation(value = "商机报备信息保存", notes = "传入business")
	public R submit(@Valid @RequestBody Business business) {
		return R.status(businessService.saveOrUpdate(business));
	}


	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 212)
	@ApiOperation(value = "商机报备信息删除", notes = "传入business")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(businessService.deleteLogic(Func.toLongList(ids)));
	}
	//endregion
	//流程引擎

	/**
	 * 新增或修改
	 *
	 * @param business 集客报备信息
	 */
	@PostMapping("start-process")
	@ApiOperationSupport(order = 204)
	@ApiOperation(value = "开启商机流程", notes = "传入流程信息")
	public R startProcess(@RequestBody Business business) {
		System.out.println(business.toString());
		return R.status(businessService.startProcess(business));
	}

	/**
	 * 完成任务
	 *
	 * @param
	 */
	@PostMapping("complete-task")
	@ApiOperationSupport(order = 205)
	@ApiOperation(value = "完成商机流程任务", notes = "传入流程信息")
	public R completeTask(@ApiParam("任务信息") @RequestBody BusinessDTO businessdto) {
		return R.status(businessService.com(businessdto));
	}

	/**
	 * 驳回任务
	 *
	 * @param flow 商机报备流程信息
	 */
	@PostMapping("reject-task")
	@ApiOperationSupport(order = 206)
	@ApiOperation(value = "驳回商机流程任务", notes = "传入流程信息")
	public R rejectTask(@ApiParam("任务信息") @RequestBody BladeFlow flow, @RequestBody Business business) {
		//根据流程id获取act_ru_task表的数据
		List<Task> list = taskService.createTaskQuery()
			.processInstanceId(flow.getProcessInstanceId())
			.list();

		//获取当前步骤的用户key，即画流程图时设置的id
		List<String> curTaskKeys = new ArrayList<>();
		for (Task task : list) {
			//String curTaskKey =  task.getTaskDefinitionKey();
			curTaskKeys.add(task.getTaskDefinitionKey());
		}
		String targetKey = flow.getTaskDefinitionKey();
		rollback(flow.getProcessInstanceId(), curTaskKeys, targetKey);
		businessService.saveOrUpdate(business);
		return R.status(true);
	}

	/**
	 * @Author
	 * @Description 驳回
	 * proInstanceId  需要驳回的流程实例id(当前发起节点的流程实例id)
	 * currTaskKeys   驳回发起的当前节点key 为  act_ru_task 中TASK_DEF_KEY_ 字段的值
	 * targetKey  目标节点的key  为act_hi_taskinst 中 TASK_DEF_KEY_
	 **/
	public void rollback(String proInstanceId, List<String> currTaskKeys, String targetKey) {
		runtimeService.createChangeActivityStateBuilder()
			.processInstanceId(proInstanceId)
			.moveActivityIdsToSingleActivityId(currTaskKeys, targetKey)
			.changeState();

	}

	/**
	 * 流程详情
	 */
	@GetMapping("/flowdetail")
	@ApiOperationSupport(order = 207)
	@ApiOperation(value = "商机报备流程详情", notes = "传入business")
	public R<BusinessDTO> Flowdetail(Business business) {
		BusinessDTO businessDTO = businessService.flowDetail(business);
		return R.data(businessDTO);
	}


	/**
	 * 生成商机编号【专业公司编码 + SJ + 两位年 +两位月 + 8位随机数】
	 *
	 * @return
	 */
	@GetMapping("/genCode")
	@ApiOperationSupport(order = 208)
	@ApiOperation(value = "生成商机编号", notes = "")
	public synchronized R<String> genBusinessCode() {
		String year = String.valueOf(DateTime.now().getYear());
		String month = String.format("%02d", DateTime.now().getMonthOfYear());

		String randNum = RandomStringUtils.randomNumeric(8);

		String resultCode = "SJ" + year.substring(year.length() - 2) + month + randNum;

		String proComId = AuthUtil.getUser().getDetail().getStr(CommonConstant.PROF_COM_ID);

		Dept dept = deptService.getById(proComId);

		if (dept != null && Func.isNotEmpty(dept.getId()) && Func.isNotEmpty(dept.getDeptCode())) {
			resultCode = dept.getDeptCode() + resultCode;
		}
		return R.data(resultCode);
	}


	@GetMapping("/getSnowflakeId")
	public void getSnowflakeId(){
		for(int i=0;i<=100;i++) {
			System.out.println(SnowflakeIdUtil.getSnowflakeId());
		}
	}


	@PostMapping("read-dept")
	public R<List<Dept>> readNotice(MultipartFile file) {
		List<DeptExcel> list = ExcelUtil.read(file, DeptExcel.class);
		List<Dept> depts = new ArrayList<>();
		for (DeptExcel i : list) {
			Dept dept  = new Dept();
			dept.setParentId(Long.parseLong(deptService.getDeptId(i.getParentId()).get(0)));
			dept.setDeptName(i.getShortName());
			dept.setFullName(i.getFullName());
			dept.setDeptCategory(Integer.parseInt(i.getLevel()));
			dept.setSort(Integer.parseInt(i.getSort()));
			dept.setDeptCode(i.getCode());
			deptService.submit(dept);
			depts.add(dept);
		}
		return R.data(depts);
	}
	private final IDictService idictService;
	private final IMajorService imajorService;
	@PostMapping("read-business")
	public R<List<Business>> readBusiness(MultipartFile file) {
		List<GuangxinBusinessExcel> list = ExcelUtil.read(file, GuangxinBusinessExcel.class);
		List<Business> buss = new ArrayList<>();
		for (GuangxinBusinessExcel i : list) {
			Business bus  = new Business();

			//编号
			String year = String.valueOf(DateTime.now().getYear());
			String month = String.format("%02d", DateTime.now().getMonthOfYear());
			String randNum = RandomStringUtils.randomNumeric(8);
			String resultCode = "4401SJ" + year.substring(year.length() - 2) + month + randNum;
			bus.setRecordCode(resultCode);
			//项目来源
			bus.setBiddingType(i.getBiddingType());
			//项目名称和商机分类
			String cat = i.getRecordName();
			bus.setProjectCatrgory(idictService.getKey("project_Catrgory",cat.substring(1, 2)));
			if(cat.substring(1, 2).equals("集客")){
				bus.setRecordName(cat.substring(4, cat.length()-1));
			}else{bus.setRecordName(cat.substring(5, cat.length()-1));}
			//投资规模
			bus.setInvestmentAmount(i.getInvestmentAmount().multiply(BigDecimal.valueOf(10000)));
			//专业
			bus.setMajor(imajorService.getCode(i.getMajor1()+"-"+i.getMajor2()));

			//行业
			String dog = i.getIndustry();
			if(dog.substring(0, 2).equals("运营商")){
				bus.setIndustry(dog.substring(4, dog.length()-1));
			}else{
				bus.setIndustry(idictService.getKey("project_Industry", "集团客户-"+dog));
			}
			//项目实施区域
			bus.setRegion(i.getRegion1()+i.getRegion2());
			//预计投标时间
			bus.setTenderDate(i.getTenderDate());
			//项目建设内容
			bus.setProjectContent(i.getProjectContent());
			//扩展模式
			bus.setExpandMode(idictService.getKey("project_ExpandMode", i.getExpandMode()));
			bus.setClientName(i.getClientName());
			if(i.getClientType().equals("是")){
				bus.setClientType("YXXKH");
			}else{
				bus.setClientType("XTZKH");
			}
			bus.setClientContact(i.getClientContact());
			bus.setClientPhone(i.getClientPhone());
			bus.setApplyTime(i.getApplyTime());
			deptService.getDeptId(i.getBranchCompany());
			//保存
			buss.add(bus);
		}
		return R.data(buss);
	}




/***************************************************手机端接口**************************************************************/


	/**
	 *
	 * 列表+模糊查询（手机端）
	 *
	 *
	 */
	@GetMapping("/selectBusinessLsit")
	@ApiOperationSupport(order = 15)
	@ApiOperation(value = "列表+模糊查询", notes = "传入business")
	public R<IPage<BusinessVO>> selectBusinessLsit(BusinessVO business, Query query) {
		if (business.getTypeId()!=null){

			if(business.getTypeId()==1){
				//时间升序
				IPage<Business> pages = businessService.selectBusinessLsit(Condition.getPage(query), business);
				return R.data(BusinessWrapper.build().pageVO(pages));
			}else if (business.getTypeId()==2){
				//时间降序
				IPage<Business> pages = businessService.selectDescendingOrderTime(Condition.getPage(query), business);
				return R.data(	BusinessWrapper.build().pageVO(pages));
			}else if (business.getTypeId()==3){
				//金额升序
				IPage<Business> pages = businessService.selectAscendingOrderMoney(Condition.getPage(query), business);
				return R.data(	BusinessWrapper.build().pageVO(pages));
			}else if (business.getTypeId()==4){
				//金额降序
				IPage<Business> pages = businessService.selectDescendingOrderMoney(Condition.getPage(query), business);
				return R.data(	BusinessWrapper.build().pageVO(pages));
			}else {
				IPage<Business> pages = businessService.selectBusinessLsit(Condition.getPage(query), business);
				return R.data(BusinessWrapper.build().pageVO(pages));
			}
		}else {
			IPage<Business> pages = businessService.selectBusinessLsit(Condition.getPage(query), business);
			return R.data(BusinessWrapper.build().pageVO(pages));
		}

	}


	/**
	 * 商机报备详情（手机端）
	 */
	@GetMapping("/mobiledetail")
	@ApiOperationSupport(order = 202)
	@ApiOperation(value = "商机报备详情", notes = "传入business")
	public R<BusinessVO> mobiledetail(Business business) {
		Business detail = businessService.getById(business.getId());
		//加入申请人信息
		detail.getFlow().setAssigneeName(UserCache.getUser(detail.getCreateUser()).getName());
		return R.data(BusinessWrapper.build().entityVO(detail));
	}

//	/**
//	 *
//	 * 商机报备时间升降序切换
//	 *
//	 */
//	@GetMapping("/selectSwitchoverTime")
//	@ApiOperationSupport(order = 16)
//	@ApiOperation(value = "商机报备时间升降序切换", notes = "传入business")
//	public R<IPage<BusinessVO>> selectSwitchoverTime(BusinessVO business, Query query) {
//		if (business.getTypeId()==1){
//			IPage<Business> pages = businessService.selectBusinessLsit(Condition.getPage(query), business);
//			return R.data(	BusinessWrapper.build().pageVO(pages));
//		}else {
//			IPage<Business> pages = businessService.selectDescendingOrderTime(Condition.getPage(query), business);
//			return R.data(	BusinessWrapper.build().pageVO(pages));
//		}
//	}
//
//	/**
//	 *
//	 * 商机报备金额升降序切换
//	 *
//	 */
//	@GetMapping("/selectSwitchoverMoney")
//	@ApiOperationSupport(order = 16)
//	@ApiOperation(value = "商机报备金额升降序切换", notes = "传入business")
//	public R<IPage<BusinessVO>> selectSwitchoverMoney(BusinessVO business, Query query) {
//		if (business.getTypeId()==1){
//			IPage<Business> pages = businessService.selectAscendingOrderMoney(Condition.getPage(query), business);
//			return R.data(	BusinessWrapper.build().pageVO(pages));
//		}else {
//			IPage<Business> pages = businessService.selectDescendingOrderMoney(Condition.getPage(query), business);
//			return R.data(	BusinessWrapper.build().pageVO(pages));
//		}
//	}

}
