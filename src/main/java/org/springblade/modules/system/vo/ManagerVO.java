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

import io.swagger.annotations.ApiModelProperty;
import org.springblade.modules.system.entity.Manager;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;

import java.time.LocalDateTime;

/**
 * 视图实体类
 *
 * @author BladeX
 * @since 2021-06-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ManagerVO对象", description = "ManagerVO对象")
public class ManagerVO extends Manager {
	private static final long serialVersionUID = 1L;


	/*
		主键
		 */
	private Long id;

	/*

	/*
	外键用户Id
	 */
	private Long userId;

	/*
	用户名
	 */
	private String userName;

	/*
	是否锁定
	 */
	private Integer isLock;

	/*
	是否一级建造师
	 */
	private Integer isConstructor;
	/*
	备注
	 */
	private String remark;

/**************************************************************/
		/*
	投标名称
	 */
private String projectName;

	/*
	商机名称
	 */
	private String recordName;

	/*
	商机代码
	 */
	private String recordCode;

	/*
	下一级状态
 	*/
	private String status;

	/**
	 * 商机主键
	 */
	@ApiModelProperty(value = "商机主键")
	private Long businessId;

	private Integer bidStatus;

	private String biddingType;
	/**
	 * 招标方式名称
	 */
	@ApiModelProperty(value = "招标方式名称")
	private String biddingTypeName;


	private String projectCatrgory;
	/**
	 * 商机分类
	 */
	@ApiModelProperty(value = "商机分类名称")
	private String projectCatrgoryName;


	private String industry;
	/**
	 * 商机主键
	 */
	@ApiModelProperty(value = "行业名称")
	private String industryName;



	private String region;
	/**
	 * 行政区域名称
	 */
	@ApiModelProperty(value = "行政区域名称")
	private String regionName;


	private LocalDateTime tenderDate;
	private String isRelationship;
	private String contractAmount;

	private String clientType;
	/**
	 * 客户类别
	 */
	@ApiModelProperty(value = "客户类别")
	private String clientTypeName;

	/**
	 * 客户名字
	 */
	@ApiModelProperty(value = "客户名字")
	private String clientName;

	private String isWin;
	private LocalDateTime winBidTime;
	private String major;

	/*
是否锁定
 */
	private String isLockName;

	/*
	是否一级建造师
	 */
	private String isConstructorName;


}
