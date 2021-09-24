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
 * @since 2021-09-16
 */
@Data
@TableName("project_bidresult")
@ApiModel(value = "Bidresult对象", description = "Bidresult对象")
public class Bidresult implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* id
	*/
		@ApiModelProperty(value = "id")
		private Long id;
	/**
	* 是否废标
	*/
		@ApiModelProperty(value = "是否废标")
		private Integer isFail;
	/**
	* 是否中标
	*/
		@ApiModelProperty(value = "是否中标")
		private Integer isWin;
	/**
	* 中标金额
	*/
		@ApiModelProperty(value = "中标金额")
		private Double winAmount;
	/**
	* 中标单位
	*/
		@ApiModelProperty(value = "中标单位")
		private String bidCom;
	/**
	* 中标日期
	*/
		@ApiModelProperty(value = "中标日期")
		private LocalDateTime winbidTime;
	/**
	* 报价方式
	*/
		@ApiModelProperty(value = "报价方式")
		private String quotationMethod;
	/**
	* 报价
	*/
		@ApiModelProperty(value = "报价")
		private String offer;
	/**
	* 折扣率
	*/
		@ApiModelProperty(value = "折扣率")
		private String discount;
	/**
	* 降点
	*/
		@ApiModelProperty(value = "降点")
		private String dropPoint;
	/**
	* 承接项目部
	*/
		@ApiModelProperty(value = "承接项目部")
		private String continueDept;
	/**
	* 预计毛利率
	*/
		@ApiModelProperty(value = "预计毛利率")
		private String grossRate;
	/**
	* 服务周期
	*/
		@ApiModelProperty(value = "服务周期")
		private String serviceCycle;
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
	* 录入结果状态
	*/
		@ApiModelProperty(value = "录入结果状态")
		private Integer status;
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


}
