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
package org.springblade.modules.project.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springblade.core.mp.base.BaseEntity;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 实体类
 *
 * @author BladeX
 * @since 2021-07-03
 */
@Data
@TableName("project_business")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Business对象", description = "Business对象")
public class Business extends BaseEntity {

	private static final long serialVersionUID = 1L;

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
	private String biddingType;
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
	private String clientPhont;
	/**
	 * 客户关系层
	 */
	@ApiModelProperty(value = "客户关系层")
	private String clientRelationship;
	/**
	 * 是否主实业协同
	 */
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
	@ApiModelProperty(value = "修改部门")
	private Long updateDept;
	/**
	 * 是否已删除
	 */
	@ApiModelProperty(value = "是否已删除")
	private Integer isDeleted;


	/**
	 * 专业公司主键
	 */
	@ApiModelProperty(value = "专业公司主键")
	private Long proCompany;

	/**
	 * 分公司主键
	 */
	@ApiModelProperty(value = "分公司主键")
	private Long branchCompany;
}
