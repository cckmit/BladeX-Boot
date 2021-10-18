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
import org.springblade.core.mp.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 解决方案表实体类
 *
 * @author BladeX
 * @since 2021-09-28
 */
@Data
@TableName("resource_solve_scheme")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SolveScheme对象", description = "解决方案表")
public class SolveScheme extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@ApiModelProperty(value = "主键ID", hidden = true)
	private Long id;


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









	/**
	 * 创建人
	 */
	@ApiModelProperty(value = "创建人",hidden = true)
	private Long createUser;
	/**
	 * 状态
	 */
	@ApiModelProperty(value = "状态",hidden = true)
	private Integer status;
	/**
	 * 修改人
	 */
	@ApiModelProperty(value = "修改人",hidden = true)
	private Long updateUser;
	/**
	 * 部门ID
	 */
	@ApiModelProperty(value = "部门ID",hidden = true)
	private Long createDept;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间",hidden = true)
	private Date createTime;
	/**
	 * 修改时间
	 */
	@ApiModelProperty(value = "修改时间",hidden = true)
	private Date updateTime;

}
