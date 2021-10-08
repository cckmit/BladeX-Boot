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
import java.util.Date;

/**
 * 实体类
 *
 * @author BladeX
 * @since 2021-06-26
 */
@Data
@TableName("client_event_info")
@ApiModel(value = "EventInfo对象", description = "EventInfo对象")
public class EventInfo extends TenantEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 客户ID
	 */
	@ApiModelProperty(value = "客户ID")
	private Long clientId;
	/**
	 * 事件标题
	 */
	@ApiModelProperty(value = "事件标题")
	private String eventTitle;
	/**
	 * 事件日期
	 */
	@ApiModelProperty(value = "事件日期")
	private Date eventDate;
	/**
	 * 事件来源
	 */
	@ApiModelProperty(value = "事件来源")
	private String eventSource;
	/**
	 * 事件类型
	 */
	@ApiModelProperty(value = "事件类型")
	private String eventType;
	/**
	 * 事件关注点
	 */
	@ApiModelProperty(value = "事件关注点")
	private String eventFocus;
	/**
	 * 事件内容
	 */
	@ApiModelProperty(value = "事件内容")
	private String eventContent;
	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注")
	private String remark;
	/**
	 * 附件
	 */
	@ApiModelProperty(value = "附件")
	private String fileUrl;
	/**
	 * 网站链接
	 */
	@ApiModelProperty(value = "网站链接")
	private String webLink;

}
