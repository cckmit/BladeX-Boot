package org.springblade.modules.project.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BidApplyDTO {

	/**
	 * 投标主键
	 */
	private long bidId;
	/**
	 * 商机主键
	 */
	private long businessId;
	/**
	 * 商机名称
	 */
	private String recordName;
	/**
	 * 商机编码
	 */
	private String recordCode;

	/**
	 * 商机分类
	 */
	private String projectCategory;

	/**
	 * 招标方式
	 */
	private String biddingType;

	/**
	 * 拓展模式
	 */
	private String expandMode;

	/**
	 *招标编号
	 */
	private String bidCode;

	/**
	 * 投标项目名称
	 */
	private String projectName;

	/**
	 * 专业
	 */
	private String major;

	/**
	 * 行业
	 */
	private String industry;

	/**
	 * 项目经理主键
	 */
	private long managerId;

	/**
	 * 项目经理名称
	 */
	private String managerName;

	/**
	 * 标底金额
	 */
	private Double bidAmount;

	/**
	 * 预计开标日期
	 */
	private LocalDateTime bidOpenTime;

	/**
	 * 接收标书日期
	 */
	private LocalDateTime receiveTime;

	/**
	 * 是否需要保证金
	 */
	private Integer isNeedBound;

	/**
	 * 是否框架
	 */
	private Integer isFrame;

	/**
	 * 是否需要垫付
	 */
	private Integer isAdvancePay;

	/**
	 * 垫付理由
	 */
	private String advancePayReason;

	/**
	 * 客户主键
	 */
	private long clientId;

	/**
	 * 客户名称
	 */
	private String clientName;

	/**
	 * 客户类别
	 */
	private String clientType;

	/**
	 * 客户类型
	 */
	private String clientCategory;


	/**
	 * 客户关系层
	 */
	private String clientRelationship;

	/**
	 * 联系人
	 */
	private String clientContact;

	/**
	 * 联系方式
	 */
	private String clientPhone;
}
