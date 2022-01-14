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
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 记录表实体类
 *
 * @author BladeX
 * @since 2022-01-13
 */
@Data
@TableName("resource_derive_record")
@ApiModel(value = "DeriveRecord对象", description = "记录表")
public class DeriveRecord implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* 主键id
	*/
		@ApiModelProperty(value = "主键id")
		private Long id;
	/**
	* 所属模块里面的内容主键id
	*/
		@ApiModelProperty(value = "所属模块里面的内容主键id")
		private Long masterId;
	/**
	* 所属模块ID
	*/
		@ApiModelProperty(value = "所属模块ID")
		private Long moduleId;
	/**
	* 日志表关联id
	*/
		@ApiModelProperty(value = "日志表关联id")
		private Long enterpriseId;


}
