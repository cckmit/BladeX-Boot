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
package org.springblade.modules.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 操作日志实体类
 *
 * @author Chill
 */
@Data
@EqualsAndHashCode()
@ApiModel(value = "OperationLogVO对象", description = "OperationLogVO对象")
public class OperationLogVO implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 数据主键ID
	 */
	@ApiModelProperty(value = "数据主键ID")
//	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;

	/**
	 * 日志业务ID
	 */
	@ApiModelProperty(value = "日志业务ID")
	private String logId;

	/**
	 * 操作人
	 */
	@ApiModelProperty(value = "操作人")
	private String createBy;

	/**
	 * 操作时间
	 */
	@ApiModelProperty(value = "操作时间")
	private Date createTime;

	/**
	 * 操作类型
	 */
	@ApiModelProperty(value = "操作类型")
	private String methodName;
}
