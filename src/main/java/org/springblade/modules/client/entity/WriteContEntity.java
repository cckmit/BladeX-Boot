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

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 实体类
 *
 * @author BladeX
 * @since 2021-06-26
 */
@Data
@TableName("client_write_content")
@ApiModel(value = "InteractiveContEntity对象", description = "InteractiveContEntity对象")
public class WriteContEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@ApiModelProperty("主键id")
	@TableId(
		value = "id",
		type = IdType.ASSIGN_ID
	)
	private Long id;
	/**
	 * 互动内容ID
	 */
	@ApiModelProperty(value = "互动内容ID")
	private Long interactiveId;
	/**
	 * 标签标题
	 */
	@ApiModelProperty(value = "标签标题")
	private String label;
	/**
	 * 标签类型
	 */
	@ApiModelProperty(value = "标签类型")
	private String type;
	/**
	 * 标签内容
	 */
	@ApiModelProperty(value = "标签内容")
	private String value;
	/**
	 * 选择内容
	 */
	@ApiModelProperty(value = "选择内容")
	private String contentList;
	/**
	 * 排序编号
	 */
	@ApiModelProperty(value = "排序编号")
	private String sort;

}
