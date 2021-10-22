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
package org.springblade.modules.client.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springblade.core.tenant.mp.TenantEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.DecimalMin;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 实体类
 *
 * @author BladeX
 * @since 2021-06-26
 */
@Data
@TableName("client_business_info")
@ApiModel(value = "BusinessInfo对象", description = "BusinessInfo对象")
public class BusinessInfo extends TenantEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 客户ID
	 */
	@ApiModelProperty(value = "客户ID")
	private Long clientId;
	/**
	 * 客户名称
	 */
	@ApiModelProperty(value = "客户名称")
	private String clientName;
	/**
	 * 商机名称
	 */
	@ApiModelProperty(value = "商机名称")
	private String businessName;
	/**
	 * 商机阶段
	 */
	@ApiModelProperty(value = "商机阶段")
	private String businessStage;
	/**
	 * 商机类型
	 */
	@ApiModelProperty(value = "商机类型")
	private String businessType;
	/**
	 * 商机等级
	 */
	@ApiModelProperty(value = "商机等级")
	private String businessLevel;
	/**
	 * 商机状态
	 */
	@ApiModelProperty(value = "商机状态")
	private String businessStatus;
	/**
	 * 商机来源
	 */
	@ApiModelProperty(value = "商机来源")
	private String businessSource;
	/**
	 * 结单日期
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "结单日期")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date resultDate;
	/**
	 * 所属地区
	 */
	@ApiModelProperty(value = "所属地区")
	private String region;
	/**
	 * 商机金额
	 */
	@ApiModelProperty(value = "商机金额")
	@DecimalMin(value = "0.00")
	private BigDecimal businessAmount;
	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注")
	private String remark;
	/**
	 * 附件
	 */
	@ApiModelProperty(value = "附件")
	private String fileUrl;
	/**
	 * 记录用户名称
	 */
	@ApiModelProperty(value = "记录用户名称")
	private String createName;

}
