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
package org.springblade.modules.EnterpriseResource.vo;

import io.swagger.annotations.ApiModelProperty;
import org.springblade.modules.EnterpriseResource.entity.SolveScheme;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;

/**
 * 解决方案表视图实体类
 *
 * @author BladeX
 * @since 2021-09-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SolveSchemeVO对象", description = "解决方案表")
public class SolveSchemeVO extends SolveScheme {
	private static final long serialVersionUID = 1L;

	/**
	 * 方案名称
	 */
	@ApiModelProperty(value = "方案名称")
	private String schemeName;
	/**
	 * 方案类别
	 */
	@ApiModelProperty(value = "方案类别")
	private String schemeType;
	private String fileAddress;
	/**
	 * 方案简介
	 */
	@ApiModelProperty(value = "方案简介")
	private String schemeSynopsis;





	//附件表的字段



	/**
	 * 文件名
	 */
	@ApiModelProperty(value = "文件名")
	private String fileName;
	/**
	 * 文件大小
	 */
	@ApiModelProperty(value = "文件大小")
	private String fileSize;
	/**
	 * 文件类型()
	 */
	@ApiModelProperty(value = "文件类型()")
	private Integer fileType;
	/**
	 * 文件状态
	 */
	@ApiModelProperty(value = "文件状态")
	private Integer fileStatus;
	/**
	 * 文件地址
	 */
	@ApiModelProperty(value = "文件地址")
	private String fileAddess;


}
