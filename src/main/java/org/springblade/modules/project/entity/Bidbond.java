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
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 实体类
 *
 * @author BladeX
 * @since 2021-09-01
 */
@Data
@TableName("project_bidbond")
//@ApiModel(value = "Bidbond对象", description = "Bidbond对象")
public class Bidbond{

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@ApiModelProperty(value = "id")
	private Long id;
	/**
	 * bidid
	 */
	@ApiModelProperty(value = "bidid")
	private Long bidId;
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
	 * 投标保证金金额
	 */
	@ApiModelProperty(value = "投标保证金金额")
	private Double bondAmount;
	/**
	 * 保证金支付方式
	 */
	@ApiModelProperty(value = "保证金支付方式")
	private String bondPayMethod;
	/**
	 * 保证金应回收日期
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@ApiModelProperty(value = "保证金应回收日期")
	private LocalDateTime bondRecoveryTime;
	/**
	 * 保证金状态
	 */
	@ApiModelProperty(value = "保证金状态")
	private Integer bondStatus;
	/**
	 * 申请时间
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
	 * 文件列表
	 */
	@ApiModelProperty(value = "文件列表")
	private String fileAttachId;
//	/**
//	 * 文件
//	 */
//	@ApiModelProperty(value = "文件id")
//	private List<Upload> upload;
}
