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
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 日志表实体类
 *
 * @author BladeX
 * @since 2022-01-06
 */
@Data
@TableName("resource_enterprise_log")
@ApiModel(value = "EnterpriseLog对象", description = "日志表")
public class EnterpriseLog implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* 主键
	*/
		@ApiModelProperty(value = "主键")
		private Long id;
	/**
	* 资质表id
	*/
		@ApiModelProperty(value = "资质表id")
		private String aptitudeId;
	/**
	* 用户id
	*/
		@ApiModelProperty(value = "用户id")
		private Long userId;
	/**
	* （0：下载中 1：下载完毕）
	*/
		@ApiModelProperty(value = "（0：下载中 1：下载完毕）")
		private Integer status;
	/**
	* 下载的地址
	*/
		@ApiModelProperty(value = "下载的地址")
		private String address;
	/**
	* 创建时间
	*/
		@ApiModelProperty(value = "创建时间")
		private Date createTime;
	/**
	* 主表条件
	*/
		@ApiModelProperty(value = "主表条件")
		private String mainCondition;


}
