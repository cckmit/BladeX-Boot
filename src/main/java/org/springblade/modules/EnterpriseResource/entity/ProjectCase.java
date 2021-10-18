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
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 项目案例表实体类
 *
 * @author BladeX
 * @since 2021-09-28
 */
@Data
@TableName("resource_project_case")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ProjectCase对象", description = "项目案例表")
public class ProjectCase extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@ApiModelProperty(value = "主键ID", hidden = true)
	private Long id;

	/**
	* 项目名称
	*/
		@ApiModelProperty(value = "项目名称")
		private String projectName;
	/**
	* 项目经理
	*/
		@ApiModelProperty(value = "项目经理")
		private String projectManager;
	/**
	* 项目归属公司
	*/
		@ApiModelProperty(value = "项目归属公司")
		private String projectOwner;
	/**
	* 行业
	*/
		@ApiModelProperty(value = "行业")
		private String industry;
	/**
	* 合同金额（万元）
	*/
		@ApiModelProperty(value = "合同金额（万元）")
		private String contractAmount;
	/**
	* 竣工日期
	*/
		@ApiModelProperty(value = "竣工日期")
		private LocalDate completionDate;
	/**
	* 建设单位或部门
	*/
		@ApiModelProperty(value = "建设单位或部门")
		private String constructionUnit;
	/**
	* 联系电话
	*/
		@ApiModelProperty(value = "联系电话")
		private String phone;
	/**
	* 专业
	*/
		@ApiModelProperty(value = "专业")
		private String specialty;
	/**
	* 启动日期
	*/
		@ApiModelProperty(value = "启动日期")
		private LocalDate activeDate;
	/**
	* 终验日期
	*/
		@ApiModelProperty(value = "终验日期")
		@TableField("FAC_date")
	private LocalDate facDate;
	/**
	* 项目背景
	*/
		@ApiModelProperty(value = "项目背景")
		private String projectProject;
	/**
	* 项目目标
	*/
		@ApiModelProperty(value = "项目目标")
		private String projectTarget;
	/**
	* 备注
	*/
		@ApiModelProperty(value = "备注")
		private String remark;


}
