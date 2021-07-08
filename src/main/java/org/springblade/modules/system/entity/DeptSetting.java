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

import java.math.BigDecimal;
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
 * @since 2021-07-06
 */
@Data
@TableName("blade_dept_setting")
@ApiModel(value = "DeptSetting对象", description = "DeptSetting对象")
public class DeptSetting implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* 主键
	*/
		@ApiModelProperty(value = "主键")
		private Long id;
	/**
	* 组织架构主键
	*/
		@ApiModelProperty(value = "组织架构主键")
		private Long deptId;
	/**
	* 项目名称冲突率
	*/
		@ApiModelProperty(value = "项目名称冲突率")
		private BigDecimal conflictProjectnameRate;
	/**
	* 其他冲突率
	*/
		@ApiModelProperty(value = "其他冲突率")
		private BigDecimal conflictOtherRate;
	/**
	* 冲突替换字符
	*/
		@ApiModelProperty(value = "冲突替换字符")
		private String conflictNeedReplace;


}
