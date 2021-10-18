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
package org.springblade.modules.EnterpriseResource.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import org.springblade.core.mp.base.BaseEntity;
import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 发票表实体类
 *
 * @author BladeX
 * @since 2021-09-28
 */
@Data
@TableName("resource_invoice")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Invoice对象", description = "发票表")
public class Invoice extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@ApiModelProperty(value = "主键ID" ,hidden = true)
	private Long id;

	/**
	* 合同id
	*/
		@ApiModelProperty(value = "合同id")
		private Integer contractId;
	/**
	* 经办人
	*/
		@ApiModelProperty(value = "经办人")
		private String handlingPerson;
	/**
	* 经办日期
	*/
		@ApiModelProperty(value = "经办日期")
		private LocalDateTime handlingDate;
	/**
	* 开票申请单号
	*/
		@ApiModelProperty(value = "开票申请单号")
		private String billingNumber;
	/**
	* 经办单位
	*/
		@ApiModelProperty(value = "经办单位")
		private Integer handlingUnit;
	/**
	* 开票形式
	*/
		@ApiModelProperty(value = "开票形式")
		private Integer makeModality;
	/**
	* 开票类型
	*/
		@ApiModelProperty(value = "开票类型")
		private Integer makeType;
	/**
	* 开票单位抬头
	*/
		@ApiModelProperty(value = "开票单位抬头")
		private String makeRise;
	/**
	* 开票单位纳税人
	*/
		@ApiModelProperty(value = "开票单位纳税人")
		private String makeTaxpayer;
	/**
	* 开票单位地址识别号
	*/
		@ApiModelProperty(value = "开票单位地址识别号")
		private String makeAddressidentification;
	/**
	* 开票单位地址电话
	*/
		@ApiModelProperty(value = "开票单位地址电话")
		private String makeAddressphone;
	/**
	* Tax invoice
	*/
		@ApiModelProperty(value = "发票税率")
		private String taxInvoice;
	/**
	* 项目编号
	*/
		@ApiModelProperty(value = "项目编号")
		private String projectNumber;
	/**
	* 项目名称
	*/
		@ApiModelProperty(value = "项目名称")
		private String projectName;
	/**
	* 合同编号
	*/
		@ApiModelProperty(value = "合同编号")
		private String contractNumber;
	/**
	* 合同名称
	*/
		@ApiModelProperty(value = "合同名称")
		private String contractName;
	/**
	* 档案室文件编号
	*/
		@ApiModelProperty(value = "档案室文件编号")
		private String fileroomNumber;

	/**
	 * 项目实施单位
	 */
		@ApiModelProperty(value = "档案室文件编号")
		private String projectImplementationunit;
	/**
	* 发票备注
	*/
		@ApiModelProperty(value = "发票备注")
		private String invoiceNote;
	/**
	* 开票金额（含税）
	*/
		@ApiModelProperty(value = "开票金额（含税）")
		private String makeMoney;
	/**
	* 开票金额（不含税）
	*/
		@ApiModelProperty(value = "开票金额（不含税）")
		private String moneyTaxexclusive;
	/**
	* 发票号码
	*/
		@ApiModelProperty(value = "发票号码")
		private String makeNumber;
	/**
	* SAP凭证号
	*/
		@ApiModelProperty(value = "SAP凭证号")
		@TableField("SAPvoucher_number")
	private String sapvoucherNumber;
	/**
	* SAP过期账号
	*/
		@ApiModelProperty(value = "SAP过期账号")
		@TableField("SAPoverdue_account")
	private String sapoverdueAccount;
	/**
	* SAP过账日期
	*/
		@ApiModelProperty(value = "SAP过账日期")
		@TableField("SAPposting_date")
	private LocalDateTime sappostingDate;
	/**
	* 发票日期
	*/
		@ApiModelProperty(value = "发票日期")
		private LocalDateTime makeDate;










	/**
	 * 创建人
	 */
	@ApiModelProperty(value = "创建人",hidden = true)
	private Long createUser;
	/**
	 * 状态
	 */
	@ApiModelProperty(value = "状态",hidden = true)
	private Integer status;
	/**
	 * 修改人
	 */
	@ApiModelProperty(value = "修改人",hidden = true)
	private Long updateUser;
	/**
	 * 部门ID
	 */
	@ApiModelProperty(value = "部门ID",hidden = true)
	private Long createDept;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间",hidden = true)
	private Date createTime;
	/**
	 * 修改时间
	 */
	@ApiModelProperty(value = "修改时间",hidden = true)
	private Date updateTime;


}
