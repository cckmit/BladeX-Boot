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

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 实体类
 *
 * @author BladeX
 * @since 2021-09-09
 */
@Data
@TableName("project_bidundertake")
@ApiModel(value = "Bidundertake对象", description = "Bidundertake对象")
public class Bidundertake implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键")
	private Long id;
	/**
	 * 质量类型
	 */
	@ApiModelProperty(value = "质量类型")
	private String qualityType;
	/**
	 * 专业
	 */
	@ApiModelProperty(value = "专业")
	private String major;
	/**
	 * 预计毛利率
	 */
	@ApiModelProperty(value = "预计毛利率")
	private Double grossRate;
	/**
	 * 项目经理主键
	 */
	@ApiModelProperty(value = "项目经理主键")
	private Long managerId;
	/**
	 * 项目开始时间
	 */
	@ApiModelProperty(value = "项目开始时间")
	private LocalDateTime startTime;
	/**
	 * 项目完工时间
	 */
	@ApiModelProperty(value = "项目完工时间")
	private LocalDateTime endTime;
	/**
	 * 项目工期
	 */
	@ApiModelProperty(value = "项目工期")
	private String schedulesTime;
	/**
	 * 委托状态
	 *
	 * 【-1：委托审核不通过  1: 发起委托  2：委托通过  】
	 */
	@ApiModelProperty(value = "委托状态")
	private Integer status;
	/**
	 * 创建用户
	 */
	@ApiModelProperty(value = "创建用户")
	private Long createUser;
	/**
	 * 创建部门
	 */
	@ApiModelProperty(value = "创建部门")
	private Long createDept;
	/**
	 * 创建部门
	 */
	@ApiModelProperty(value = "申请时间")
	private Date applyTime;
	/**
	 * 流程定义主键
	 */
	@ApiModelProperty(value = "流程定义主键")
	private String processDefinitionId;
	/**
	 * 流程实例主键
	 */
	@ApiModelProperty(value = "流程实例主键")
	private String processInstanceId;
	/**
	 * 中标通知书附件
	 */
	@ApiModelProperty(value = "中标通知书附件")
	private String fileAttachId;


}
