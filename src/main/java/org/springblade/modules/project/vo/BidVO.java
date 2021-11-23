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
package org.springblade.modules.project.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.springblade.modules.project.entity.Bid;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 视图实体类
 *
 * @author BladeX
 * @since 2021-07-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "BidVO对象", description = "BidVO对象")
public class BidVO extends Bid {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键")
	private Long Id;

	/**
	 * 商机主键
	 */
	@ApiModelProperty(value = "商机主键")
	private Long businessId;

	/**
	 * 投标项目名称
	 */
	@ApiModelProperty(value = "投标项目名称")
	private String projectName;

	/**
	 * 投标状态
	 * 【-1：投标申请不通过  0: 等待投标   1：发起投标申请  2：投标申请通过   3：录入开标信息或承接结果     4：中标或承接审核通过】
	 */
	@ApiModelProperty(value = "投标状态")
	private Integer bidStatus;
	@ApiModelProperty(value = "投标状态名称")
	private String bidStatusName;

	@ApiModelProperty(value = "投标下一步状态")
	private Integer Status;
	@ApiModelProperty(value = "投标下一步状态名称")
	private String StatusName;
	/**
	 * 商机名称
	 */
	@ApiModelProperty(value = "商机名称")
	private String recordName;

	/**
	 *
	 * 招标方式
	 *
	 */
	@ApiModelProperty(value = "招标方式")
	private String biddingType;
	@ApiModelProperty(value = "招标方式名称")
	private String projectBiddingTypeName;


	/**
	 * 商机编号
	 */
	@ApiModelProperty(value = "商机编号")
	private String recordCode;

	/**
	 *
	 * 商机分类
	 *
	 */
	@ApiModelProperty(value = "商机分类")
	private String projectCatrgory;
	@ApiModelProperty(value = "商机分类名称")
	private String projectCatrgoryName;


	/**
	 *
	 * 拓展模式
	 *
	 */
	@ApiModelProperty(value = "拓展模式")
	private String expandMode;
	@ApiModelProperty(value = "拓展模式名称")
	private String expandModeName;

	/**
	 *
	 * 行业
	 *
	 */
	@ApiModelProperty(value = "行业")
	private String industry;
	@ApiModelProperty(value = "行业名称")
	private String projectIndustryName;


	/**
	 * 专业
	 */
	@ApiModelProperty(value = "专业")
	private String major;
	@ApiModelProperty(value = "专业名称")
	private String majorName;

	/**
	 * 项目实施区域
	 */
	@ApiModelProperty(value = "项目实施区域")
	private String region;

	/**
	 * 预计投标日期
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@ApiModelProperty(value = "预计投标日期")
	private Date tenderDate;

	/**
	 *
	 * 客户关系层
	 *
	 */
	@ApiModelProperty(value = "客户关系层")
	private String isRelationship;
	@ApiModelProperty(value = "客户关系名称")
	private String isRelationshipName;

	/**
	 * 预计合同金额
	 */
	@ApiModelProperty(value = "预计合同金额")
	private String contractAmount;

	/**
	 * 客户名字
	 */
	@ApiModelProperty(value = "客户名字")
	private String clientName;


	/**
	 *
	 * 客户类型
	 *
	 */
	@ApiModelProperty(value = "客户类型")
	private String clientType;
	@ApiModelProperty(value = "客户类型名称")
	private String clientTypeName;


	private String isWin;

	private LocalDateTime winBidTime;

}
