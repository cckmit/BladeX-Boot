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
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 实体类
 *
 * @author BladeX
 * @since 2021-06-30
 */
@Data
@TableName("blade_manager")
@ApiModel(value = "Manager对象", description = "Manager对象")
public class Manager implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* 主键
	*/
		@ApiModelProperty(value = "主键")
		private Long id;

	/**
	 * 用户表外键
	 */
	@ApiModelProperty(value = "用户表外键")
	private Long userId;

	/**
	* 是否锁定
	*/
		@ApiModelProperty(value = "是否锁定")
		private Integer isLock;
	/**
	* 是否一级建造师
	*/
		@ApiModelProperty(value = "是否一级建造师")
		private Integer isConstructor;
	/**
	* 备注
	*/
		@ApiModelProperty(value = "备注")
		private String remark;


	/**
	 * 用户真名
	 */
	@ApiModelProperty(value = "用户真名",hidden = true)
	@TableField(exist = false) //表示该属性不为数据库表字段
	private String realName;

	/**
	 * 组织
	 */
	@ApiModelProperty(value = "组织",hidden = true)
	@TableField(exist = false) //表示该属性不为数据库表字段
	private String deptName;
}
