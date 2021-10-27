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

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;

/**
 * 实体类
 *
 * @author BladeX
 * @since 2021-10-21
 */
@Data
@TableName("blade_manager_log")
@ApiModel(value = "ManagerLog对象", description = "ManagerLog对象")
public class ManagerLog implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* 主键ID
	*/
		@ApiModelProperty(value = "主键ID")
	private Long id;
	/**
	* 当前经理人ID
	*/
		@ApiModelProperty(value = "当前经理人ID")
		private Long managerId;
	/**
	* 当前操作用户ID
	*/
		@ApiModelProperty(value = "当前操作用户ID")
		private Long userId;
	/**
	* 操作类
	*/
		@ApiModelProperty(value = "操作类")
		private String whetherUnlock;
	/**
	 * 创建时间
	 */
		@ApiModelProperty(value = "创建时间")
		private Date createTime;




	/**
	 * 用户名
	 */
		@ApiModelProperty(value = "用户名")
		@TableField(exist = false) //表示该属性不为数据库表字段
		private String userName;

}
