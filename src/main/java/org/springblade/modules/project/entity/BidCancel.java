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

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.common.annotation.CompareProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类
 *
 * @author BladeX
 * @since 2021-08-13
 */
@Data
@TableName("project_bidcancel")
@EqualsAndHashCode()
@CompareProperty
@ApiModel(value = "ProjectBidcancel对象", description = "ProjectBidcancel对象")
public class BidCancel implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 创建人
	 */
	@CompareProperty(isIgnore = true)
	@ApiModelProperty(value = "创建人")
	private String createUser;
	/**
	 * 创建部门
	 */
	@CompareProperty(isIgnore = true)
	@ApiModelProperty(value = "创建部门")
	private String createDept;
	/**
	 * 状态
	 */
	@ApiModelProperty(value = "报废状态")
	private Integer cancelStatus;
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
	 * 报废理由
	 */
	@CompareProperty(isIgnore = true)
	@ApiModelProperty(value = "报废理由")
	private String cancelReason;

}
