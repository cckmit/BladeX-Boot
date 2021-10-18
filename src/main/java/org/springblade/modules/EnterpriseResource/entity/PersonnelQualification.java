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
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 人员资质表实体类
 *
 * @author BladeX
 * @since 2021-09-23
 */
@Data
@TableName("resourece_personnel_qualification")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PersonnelQualification对象", description = "人员资质表")
public class PersonnelQualification extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	@ApiModelProperty(value = "主键ID" ,hidden = true)
	private Long id;

	/**
	* 人员名字
	*/
		@ApiModelProperty(value = "人员名字")
		private String personnelName;
	/**
	* 身份证证号
	*/
		@ApiModelProperty(value = "身份证证号")
		private String idNumber;
	/**
	* 用工性质
	*/
		@ApiModelProperty(value = "用工性质")
		@TableField("Nature_employment")
	private String natureEmployment;
	/**
	* 证书名称
	*/
		@ApiModelProperty(value = "证书名称")
		private String certificateName;
	/**
	* 证书编号
	*/
		@ApiModelProperty(value = "证书编号")
		private String certificateNumber;
	/**
	* 证书类型
	*/
		@ApiModelProperty(value = "证书类型")
		private Integer certificateType;
	/**
	* 证书等级
	*/
		@ApiModelProperty(value = "证书等级")
		private Integer certificateGrade;
	/**
	* 发证单位
	*/
		@ApiModelProperty(value = "发证单位")
		private String issueUnit;
	/**
	* 证书使用情况
	*/
		@ApiModelProperty(value = "证书使用情况")
		private Integer certificateUsage;
	/**
	* 是否长期有效
	*/
		@ApiModelProperty(value = "是否长期有效")
		private Integer protecteds;
	/**
	* 有效期
	*/
		@ApiModelProperty(value = "有效期")
		private LocalDate periodValidity;
	/**
	* 附件地址
	*/
		@ApiModelProperty(value = "附件地址")
		private String fileAddress;












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
