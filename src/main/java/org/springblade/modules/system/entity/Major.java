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

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 实体类
 *
 * @author BladeX
 * @since 2021-07-08
 */
@Data
@TableName("blade_major")
@ApiModel(value = "Major对象", description = "Major对象")
public class Major implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* 专业编码
	*/
		@ApiModelProperty(value = "专业编码")
		private String code;
	/**
	* 上级专业编码
	*/
		@ApiModelProperty(value = "上级专业编码")
		private String parentCode;
	/**
	* 专业名称
	*/
		@ApiModelProperty(value = "专业名称")
		private String majorName;
	/**
	* 专业层级
	*/
		@ApiModelProperty(value = "专业层级")
		private Integer majorLevel;
	/**
	* 专业全路径
	*/
		@ApiModelProperty(value = "专业全路径")
		private String fullPath;
	/**
	* 是否最后一级
	*/
		@ApiModelProperty(value = "是否最后一级")
		@TableField("is_Last")
	private Boolean isLast;
	/**
	* 专业状态
	*/
		@ApiModelProperty(value = "专业状态")
		private Integer status;


}
