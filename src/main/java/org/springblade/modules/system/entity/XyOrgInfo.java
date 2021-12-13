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
package org.springblade.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 实体类
 *
 * @author BladeX
 * @since 2021-12-11
 */
@Data
@TableName("mdm_xy_org_info")
@EqualsAndHashCode()
@ApiModel(value = "XyOrgInfo对象", description = "XyOrgInfo对象")
public class XyOrgInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键")
	private Long id;
	/**
	 * SAP消息号
	 */
	@ApiModelProperty(value = "SAP消息号")
	private String sapxxh;
	/**
	 * 组织结构编码
	 */
	@ApiModelProperty(value = "组织结构编码")
	private String orgNumber;
	/**
	 * 组织结构简称
	 */
	@ApiModelProperty(value = "组织结构简称")
	private String orgNameAbbr;
	/**
	 * 组织结构全称
	 */
	@ApiModelProperty(value = "组织结构全称")
	private String orgNameFull;
	/**
	 * 负责人代码
	 */
	@ApiModelProperty(value = "负责人代码")
	private String respPerson;
	/**
	 * 所属地区编码
	 */
	@ApiModelProperty(value = "所属地区编码")
	private String region;
	/**
	 * 上级组织结构编码
	 */
	@ApiModelProperty(value = "上级组织结构编码")
	private String upperOrg;
	/**
	 * 有效期开始
	 */
	@ApiModelProperty(value = "有效期开始")
	private String effectStart;
	/**
	 * 有效期结束
	 */
	@ApiModelProperty(value = "有效期结束")
	private String effectEnd;
	/**
	 * 层级代码
	 */
	@ApiModelProperty(value = "层级代码")
	private String level;
	/**
	 * 组织结构分类
	 */
	@ApiModelProperty(value = "组织结构分类")
	private String orgType;
	/**
	 * 详细地址
	 */
	@ApiModelProperty(value = "详细地址")
	private String addr;
	/**
	 * 邮政编码
	 */
	@ApiModelProperty(value = "邮政编码")
	private String postCode;
	/**
	 * 本位币代码
	 */
	@ApiModelProperty(value = "本位币代码")
	private String currency;
	/**
	 * SAP公司代码
	 */
	@ApiModelProperty(value = "SAP公司代码")
	private String sapComp;
	/**
	 * SAP采购组织
	 */
	@ApiModelProperty(value = "SAP采购组织")
	private String sapPurchOrg;
	/**
	 * SAP工厂代码
	 */
	@ApiModelProperty(value = "SAP工厂代码")
	private String sapPlant;
	/**
	 * SAP销售组织
	 */
	@ApiModelProperty(value = "SAP销售组织")
	private String sapSalesOrg;
	/**
	 * 是否暂估销项税
	 */
	@ApiModelProperty(value = "是否暂估销项税")
	private String taxInd;
	/**
	 * 利润中心编码
	 */
	@ApiModelProperty(value = "利润中心编码")
	private String profitCenter;
	/**
	 * 成本中心编码
	 */
	@ApiModelProperty(value = "成本中心编码")
	private String costCenter;
	/**
	 * 是否核算对象
	 */
	@ApiModelProperty(value = "是否核算对象")
	private String acctObjInd;
	/**
	 * 部门属性
	 */
	@ApiModelProperty(value = "部门属性")
	private String deptType;
	/**
	 * 完整路径
	 */
	@ApiModelProperty(value = "完整路径")
	private String orgPath;
	/**
	 * 完整路径名称
	 */
	@ApiModelProperty(value = "完整路径名称")
	private String orgPathName;
	/**
	 * 所属分公司
	 */
	@ApiModelProperty(value = "所属分公司")
	private String compCode;
	/**
	 * 状态
	 */
	@ApiModelProperty(value = "状态")
	private String status;
	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序")
	private String sort;
	/**
	 * 是否末级
	 */
	@ApiModelProperty(value = "是否末级")
	private String leafNodeInd;
	/**
	 * 是否法人
	 */
	@ApiModelProperty(value = "是否法人")
	private String legalEntity;


}
