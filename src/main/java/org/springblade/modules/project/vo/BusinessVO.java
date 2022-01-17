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
package org.springblade.modules.project.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.springblade.common.annotation.CompareProperty;
import org.springblade.modules.project.entity.Business;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 视图实体类
 *
 * @author BladeX
 * @since 2021-07-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "BusinessVO对象", description = "BusinessVO对象")
public class BusinessVO extends Business {
	private static final long serialVersionUID = 1L;
	/**
	 * 分公司名称
	 */
	@ApiModelProperty(value = "分公司名称")
	private String deptName;



	/**
	 * 商机名称
	 */
	@ApiModelProperty(value = "商机名称")
	private String recordName;
	/**
	 * 商机编号
	 */
	@ApiModelProperty(value = "商机编号")
	private String recordCode;
	/**
	 * 商机分类
	 */
	@ApiModelProperty(value = "商机分类")
	private String projectCatrgory;
	/**
	 * 商机来源
	 */
	@ApiModelProperty(value = "招标方式")
	private String obioa;


	/**
	 * 招标方式名称
	 */
	@ApiModelProperty(value = "招标方式名称")
	private String projectBiddingTypeName;
	/**
	 * 项目投资金额
	 */
	@ApiModelProperty(value = "项目投资金额")
	private BigDecimal investmentAmount;
	/**
	 * 预计合同金额
	 */
	@ApiModelProperty(value = "预计合同金额")
	private BigDecimal contractAmount;
	/**
	 * 专业
	 */
	@ApiModelProperty(value = "专业")
	private String major;
	/**
	 * 行业
	 */
	@ApiModelProperty(value = "行业")
	private String industry;
	/**
	 * 项目实施区域
	 */
	@ApiModelProperty(value = "项目实施区域")
	private String region;
	/**
	 * 预计投标日期
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@ApiModelProperty(value = "预计投标日期")
	private Date tenderDate;
	/**
	 * 项目建设内容
	 */
	@ApiModelProperty(value = "项目建设内容")
	private String projectContent;
	/**
	 * 拓展模式
	 */
	@ApiModelProperty(value = "拓展模式")
	private String expandMode;
	/**
	 * 客户主键
	 */
	@ApiModelProperty(value = "客户主键")
	@CompareProperty(isIgnore = true )
	private Long clientId;
	/**
	 * 客户名字
	 */
	@ApiModelProperty(value = "客户名字")
	private String clientName;

	/**
	 * 客户类型
	 */
	@ApiModelProperty(value = "客户类型")
	private String clientType;
	/**
	 * 客户类别
	 */
	@ApiModelProperty(value = "客户类别")
	private String clientCategory;
	/**
	 * 甲方联系人
	 */
	@ApiModelProperty(value = "甲方联系人")
	private String clientContact;
	/**
	 * 联系方式
	 */
	@ApiModelProperty(value = "联系方式")
	private String clientPhone;
	/**
	 * 客户关系层
	 */
	@ApiModelProperty(value = "客户关系层")
	private String clientRelationship;
	/**
	 * 是否主实业协同
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@ApiModelProperty(value = "是否主实业协同")
	private Integer isRelationship;
	/**
	 * 所属赛道
	 */
	@ApiModelProperty(value = "所属赛道")
	private String track;
	/**
	 * 修改部门
	 */
	@CompareProperty(isIgnore = true)
	@ApiModelProperty(value = "修改部门")
	private Long updateDept;
	/**
	 * 是否已删除
	 */
	@CompareProperty(isIgnore = true)
	@ApiModelProperty(value = "是否已删除")
	private Integer isDeleted;
	/**
	 * 流程定义主键
	 */
	@CompareProperty(isIgnore = true)
	@ApiModelProperty(value = "流程定义主键")
	private String processDefinitionId;
	/**
	 * 流程实例主键
	 */
	@CompareProperty(isIgnore = true)
	@ApiModelProperty(value = "流程实例主键")
	private String processInstanceId;
	/**
	 * 申请时间
	 */
	@ApiModelProperty(value = "申请时间")
	private Date applyTime;

	/**
	 * 专业公司主键
	 */
	@CompareProperty(isIgnore = true)
	@ApiModelProperty(value = "专业公司主键")
	private Long proCompany;
	/**
	 * 专业公司主键
	 */
	@CompareProperty(isIgnore = true)
	@ApiModelProperty(value = "专业公司主键")
	private String proCom;
	/**
	 * 分公司主键
	 */
	@CompareProperty(isIgnore = true)
	@ApiModelProperty(value = "分公司主键")
	private Long branchCompany;
	/**
	 * 分公司主键
	 */
	@CompareProperty(isIgnore = true)
	@ApiModelProperty(value = "分公司主键")
	private String branchCom;
	/**
	 * 商机信息状态
	 * -1：备案失效  0：等待审核  1:备案冲突   2：备案成功
	 */
	@CompareProperty(isIgnore = true)
	@ApiModelProperty(value = "商机信息状态")
	private Integer recordStatus;

	/**
	 * 商机状态名称
	 */
	@ApiModelProperty(value = "商机状态名称")
	private String businessStatusName;


	/**
	 * 租户主键
	 */
	@CompareProperty(isIgnore = true)
	@ApiModelProperty(value = "租户主键")
	private long tenantId;


	/**
	 * 标识类别ID（1为升序，2为降序）
	 *
	 */
	@TableField(exist = false)
	private Long typeId;

}
