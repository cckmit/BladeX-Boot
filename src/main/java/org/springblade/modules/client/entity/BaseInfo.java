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

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springblade.core.tenant.mp.TenantEntity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 实体类
 *
 * @author BladeX
 * @since 2021-06-26
 */
@Data
@TableName("client_base_info")
@ApiModel(value = "BaseInfo对象", description = "BaseInfo对象")
public class BaseInfo extends TenantEntity implements Serializable {

	private static final long serialVersionUID = 1L;

/**
 * 公私海类型：1公海 2私海
 */

	@ApiModelProperty(value = "公私海类型：1公海 2私海")
	private Integer mode;

	/**
	 * 客户编码
	 */
	@ApiModelProperty(value = "客户编码")
	private String clientcode;
	/**
	 * 账户组编码
	 */
	@ApiModelProperty(value = "账户组编码")
	private String clientgroup;
	/**
	 * 客户全称
	 */
	@ApiModelProperty(value = "客户全称")
	private String fullname;
	/**
	 * 简称
	 */
	@ApiModelProperty(value = "简称")
	private String shortname;
	/**
	 * 街道地址
	 */
	@ApiModelProperty(value = "街道地址")
	private String address;
	/**
	 * 工商登记证号
	 */
	@ApiModelProperty(value = "工商登记证号")
	private String crnum;
	/**
	 * 组织机构代码
	 */
	@ApiModelProperty(value = "组织机构代码")
	private String orgcode;
	/**
	 * 单位性质
	 */
	@ApiModelProperty(value = "单位性质")
	private String orgcategory;
	/**
	 * 是否供应商
	 */
	@ApiModelProperty(value = "是否供应商")
	private Boolean issupplier;
	/**
	 * 是否客户
	 */
	@ApiModelProperty(value = "是否客户")
	private Boolean iscustomer;
	/**
	 * 联系人
	 */
	@ApiModelProperty(value = "联系人")
	private String contact;
	/**
	 * 电话号码
	 */
	@ApiModelProperty(value = "电话号码")
	private String phone;
	/**
	 * 行业
	 */
	@ApiModelProperty(value = "行业")
	private String industry;
	/**
	 * 所属地区
	 */
	@ApiModelProperty(value = "所属地区")
	private String region;
	/**
	 * 客户来源'
	 */
	@ApiModelProperty(value = "客户来源")
	private String clientSources;
	/**
	 * 业务范围
	 */
	@ApiModelProperty(value = "业务范围")
	private String businessScope;
	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注")
	private String remark;
	/**
	 * 职务
	 */
	@ApiModelProperty(value = "职务")
	private String post;
	/**
	 * 角色
	 */
	@ApiModelProperty(value = "角色")
	private String usrRole;
	/**
	 * 所在名单
	 */
	@ApiModelProperty(value = "所在名单")
	private String roster;
	/**
	 * 附件地址
	 */
	@ApiModelProperty(value = "附件地址")
	private String fileUrl;
	/**
	 * 关注状态
	 */
	@ApiModelProperty(value = "关注状态")
	@TableField(exist = false)
	private String focusStatus;
	/**
	 * 关注表id
	 */
	@ApiModelProperty(value = "关注表id")
	@TableField(exist = false)
	private Long focusId;

}
