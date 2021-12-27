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
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 实体类
 *
 * @author BladeX
 * @since 2021-12-11
 */
@Data
@TableName("mdm_xy_user_info")
@ApiModel(value = "XyUserInfo对象", description = "XyUserInfo对象")
public class XyUserInfo implements Serializable {

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
	 * 员工供应商编码
	 */
	@ApiModelProperty(value = "员工供应商编码")
	private String bpCode;
	/**
	 * 姓名
	 */
	@ApiModelProperty(value = "姓名")
	private String name;
	/**
	 * AD帐号
	 */
	@ApiModelProperty(value = "AD帐号")
	private String adId;
	/**
	 * 性别
	 */
	@ApiModelProperty(value = "性别")
	private String genda;
	/**
	 * 地区代码
	 */
	@ApiModelProperty(value = "地区代码")
	private String region;
	/**
	 * SAP国家代码
	 */
	@ApiModelProperty(value = "SAP国家代码")
	private String sapCounty;
	/**
	 * SAP地区代码
	 */
	@ApiModelProperty(value = "SAP地区代码")
	private String sapRegion;
	/**
	 * 详细地址
	 */
	@ApiModelProperty(value = "详细地址")
	private String addr;
	/**
	 * 身份证号
	 */
	@ApiModelProperty(value = "身份证号")
	private String personalId;
	/**
	 * 所属部门编码
	 */
	@ApiModelProperty(value = "所属部门编码")
	private String dept;
	/**
	 * SAP公司代码
	 */
	@ApiModelProperty(value = "SAP公司代码")
	private String sapCompany;
	/**
	 * SAP工厂代码
	 */
	@ApiModelProperty(value = "SAP工厂代码")
	private String sapPlant;
	/**
	 * 账户组
	 */
	@ApiModelProperty(value = "账户组")
	private String accountGroup;
	/**
	 * 手机号
	 */
	@ApiModelProperty(value = "手机号")
	private String mobile;
	/**
	 * 出生日期
	 */
	@ApiModelProperty(value = "出生日期")
	private String birthDate;
	/**
	 * 电子邮箱
	 */
	@ApiModelProperty(value = "电子邮箱")
	private String email;
	/**
	 * 员工类别
	 */
	@ApiModelProperty(value = "员工类别")
	private String employType;
	/**
	 * 直属领导
	 */
	@ApiModelProperty(value = "直属领导")
	private String reportingTo;
	/**
	 * 供应商标识
	 */
	@ApiModelProperty(value = "供应商标识")
	private String vendorInd;
	/**
	 * 客户表识
	 */
	@ApiModelProperty(value = "客户表识")
	private String customerInd;
	/**
	 * 供应商分类代码
	 */
	@ApiModelProperty(value = "供应商分类代码")
	private String vendorCateCode;
	/**
	 * 供应商分类名称
	 */
	@ApiModelProperty(value = "供应商分类名称")
	private String vendorCateName;
	/**
	 * 入职状态
	 */
	@ApiModelProperty(value = "入职状态")
	private String employmentStatus;
	/**
	 * SAP采购组织
	 */
	@ApiModelProperty(value = "SAP采购组织")
	private String sapPurchOrg;
	/**
	 * 邮编
	 */
	@ApiModelProperty(value = "邮编")
	private String postCode;
	/**
	 * 状态
	 */
	@ApiModelProperty(value = "状态")
	private String status;
	/**
	 * 职务
	 */
	@ApiModelProperty(value = "职务")
	private String post;

	/**
	 * 创建时间
	 */
	@DateTimeFormat(
		pattern = "yyyy-MM-dd HH:mm:ss"
	)
	@JsonFormat(
		pattern = "yyyy-MM-dd HH:mm:ss"
	)
	@ApiModelProperty("创建时间")
	private Date createTime;
}
