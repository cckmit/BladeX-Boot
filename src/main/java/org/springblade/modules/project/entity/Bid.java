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
package org.springblade.modules.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 实体类
 *
 * @author BladeX
 * @since 2021-07-18
 */
@Data
@TableName("project_bid")
@ApiModel(value = "Bid对象", description = "Bid对象")
public class Bid implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* 主键
	*/
		@ApiModelProperty(value = "主键")
		private Long id;
	/**
	* 商机主键
	*/
		@ApiModelProperty(value = "商机主键")
		private Long businessId;
	/**
	* 招标编号
	*/
		@ApiModelProperty(value = "招标编号")
		private String bidCode;
	/**
	* 投标项目名称
	*/
		@ApiModelProperty(value = "投标项目名称")
		private String projectName;
	/**
	* 是否框架
	*/
		@ApiModelProperty(value = "是否框架")
		private Boolean isFrame;
	/**
	* 项目经理主键
	*/
		@ApiModelProperty(value = "项目经理主键")
		private Long managerId;
	/**
	* 标底金额
	*/
		@ApiModelProperty(value = "标底金额")
		private Double bidAmount;
	/**
	* 开标日期
	*/
		@ApiModelProperty(value = "开标日期")
		private LocalDateTime bidOpenTime;
	/**
	* 招标代理机构
	*/
		@ApiModelProperty(value = "招标代理机构")
		private String bidAgentName;
	/**
	* 招标代理编号
	*/
		@ApiModelProperty(value = "招标代理编号")
		private String bidAgentCode;
	/**
	* 招标代理联系方式
	*/
		@ApiModelProperty(value = "招标代理联系方式")
		private String agentContact;
	/**
	* 招标编号
	*/
		@ApiModelProperty(value = "招标编号")
		private String tenderNo;
	/**
	* 公示网站
	*/
		@ApiModelProperty(value = "公示网站")
		private String publicWebSite;
	/**
	* 接收标书日期
	*/
		@ApiModelProperty(value = "接收标书日期")
		private LocalDateTime receiveTime;
	/**
	* 是否需要保证金
	*/
		@ApiModelProperty(value = "是否需要保证金")
		private Boolean isNeedBond;
	/**
	* 投标保证金金额
	*/
		@ApiModelProperty(value = "投标保证金金额")
		private Double bondAmount;
	/**
	* 保证金支付方式
	*/
		@ApiModelProperty(value = "保证金支付方式")
		private String bondPayMethod;
	/**
	* 保证金应回收日期
	*/
		@ApiModelProperty(value = "保证金应回收日期")
		private LocalDateTime bondRecoveryTime;
	/**
	* 保证金状态
	*/
		@ApiModelProperty(value = "保证金状态")
		private Integer bondStatus;
	/**
	* 是否中标
	*/
		@ApiModelProperty(value = "是否中标")
		private Boolean isWinBid;
	/**
	* 中标日期
	*/
		@ApiModelProperty(value = "中标日期")
		private LocalDateTime winBidTime;
	/**
	* 报价方式
	*/
		@ApiModelProperty(value = "报价方式")
		private String quotationMethod;
	/**
	* 报价
	*/
		@ApiModelProperty(value = "报价")
		private Double offer;
	/**
	* 折扣率
	*/
		@ApiModelProperty(value = "折扣率")
		private Double discount;
	/**
	* 降点
	*/
		@ApiModelProperty(value = "降点")
		private Double dropPoint;
	/**
	* 承接项目部
	*/
		@ApiModelProperty(value = "承接项目部")
		private Long continueDept;
	/**
	* 预计毛利率
	*/
		@ApiModelProperty(value = "预计毛利率")
		private Double grossRate;
	/**
	* 服务周期
	*/
		@ApiModelProperty(value = "服务周期")
		private Integer serviceCycle;
	private Integer bidStatus;
	/**
	* 是否删除
	*/
		@ApiModelProperty(value = "是否删除")
		private Boolean isDelete;
	/**
	* 是否作废
	*/
		@ApiModelProperty(value = "是否作废")
		private Boolean isCancel;
	/**
	* 申请作废用户
	*/
		@ApiModelProperty(value = "申请作废用户")
		private String applyCancelUser;
	/**
	* 申请作废时间
	*/
		@ApiModelProperty(value = "申请作废时间")
		private LocalDateTime applyCancelTime;
	/**
	* 作废理由
	*/
		@ApiModelProperty(value = "作废理由")
		private String cancelReason;
	/**
	* 是否需要垫付
	*/
		@ApiModelProperty(value = "是否需要垫付")
		private Boolean isAdvancePay;
	/**
	* 垫付理由
	*/
		@ApiModelProperty(value = "垫付理由")
		private String advancePayReason;


}
