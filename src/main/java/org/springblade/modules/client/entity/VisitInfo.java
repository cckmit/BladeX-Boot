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

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类
 *
 * @author BladeX
 * @since 2021-06-26
 */
@Data
@TableName("client_visit_info")
@ApiModel(value = "VisitInfo对象", description = "VisitInfo对象")
public class VisitInfo extends TenantEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 客户ID
	 */
	@ApiModelProperty(value = "客户ID")
	private Long clientId;
	/**
	 * 拜访时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	@ApiModelProperty(value = "拜访时间")
	private Date visitDate;
	/**
	 * 联系人ID
	 */
	@ApiModelProperty(value = "联系人ID")
	private Long contactId;
	/**
	 * 联系人姓名
	 */
	@ApiModelProperty(value = "联系人姓名")
	private String contactName;
	/**
	 * 商机ID
	 */
	@ApiModelProperty(value = "商机ID")
	private Long businessId;
	/**
	 * 商机名称
	 */
	@ApiModelProperty(value = "商机名称")
	private String businessName;
	/**
	 * 拜访地点
	 */
	@ApiModelProperty(value = "拜访地点")
	private String visitRegion;
	/**
	 * 拜访阶段
	 */
	@ApiModelProperty(value = "拜访阶段")
	private String visitStage;
	/**
	 * 互动内容
	 */
	@ApiModelProperty(value = "互动内容")
	private String visitInteraction;
	/**
	 * 填报内容
	 */
	@ApiModelProperty(value = "填报内容")
	private String visitContent;
	/**
	 * 拜访事务
	 */
	@ApiModelProperty(value = "拜访事务")
	private String visitEvent;
	/**
	 * 拜访纪要
	 */
	@ApiModelProperty(value = "拜访纪要")
	private String visitSummary;
	/**
	 * 记录用户名
	 */
	@ApiModelProperty(value = "记录用户名")
	private String createName;
	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注")
	private String remark;
	/**
	 * 记录部门
	 */
	@ApiModelProperty(value = "记录部门")
	private String recordDeptName;

}
