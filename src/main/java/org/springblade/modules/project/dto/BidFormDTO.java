package org.springblade.modules.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springblade.common.annotation.CompareProperty;
import org.springblade.modules.resource.entity.Upload;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * 数据传输对象实体类
 *
 * @author BladeX
 * @since 2021-08-20
 */
@Data
public class BidFormDTO {
	private static final long serialVersionUID = 1L;

//	private Bid bid;
//
//	private Business business;
//
//	private BidCancel bidCancel;

	//region Bid字段
	/**
	 * 投标主键
	 */
	@ApiModelProperty(value = "投标主键")
	private Long Id;
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
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@ApiModelProperty(value = "是否框架")
	private Integer isFrame;
	/**
	 * 项目经理主键
	 */
	@ApiModelProperty(value = "项目经理主键")
	private String managerId;
	/**
	 * 项目经理主键
	 */
	@ApiModelProperty(value = "项目经理名称")
	private String managerName;
	/**
	 * 标底金额
	 */
	@ApiModelProperty(value = "标底金额")
	private Double bidAmount;
	/**
	 * 开标日期
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@ApiModelProperty(value = "接收标书日期")
	private LocalDateTime receiveTime;
	/**
	 * 是否需要保证金
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@ApiModelProperty(value = "是否需要保证金")
	private Integer isNeedBond;
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
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@ApiModelProperty(value = "保证金应回收日期")
	private LocalDateTime bondRecoveryTime;

	/**
	 * 是否中标
	 */
	@ApiModelProperty(value = "是否中标")
	private Integer isWinBid;

	/**
	 * 是否废标
	 */
	@ApiModelProperty(value = "是否废标")
	private Boolean isFailBid;

	/**
	 * 中标日期
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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
	 * 是否需要垫付
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@ApiModelProperty(value = "是否需要垫付")
	private Integer isAdvancePay;
	/**
	 * 垫付理由
	 */
	@ApiModelProperty(value = "垫付理由")
	private String advancePayReason;


	/**
	 * 保证金状态
	 * 【-1：保证金审核不通过  0: 等待发起  1：发起保证金  2：保证金审核通过】
	 */
	@ApiModelProperty(value = "保证金状态")
	private Integer bondStatus;

	/**
	 * 投标状态
	 * 【-1：投标申请不通过  0: 等待投标   1：发起投标申请  2：投标申请通过   3：录入开标信息或承接结果     4：中标或承接审核通过】
	 */
	@ApiModelProperty(value = "投标状态")
	private Integer bidStatus;

	/**
	 * 流程定义主键
	 */
	@CompareProperty(isIgnore = true)
	@ApiModelProperty(value = "流程定义主键")
	private String processDefinitionId;
	/**
	 * 流程实例主键
	 */
	@CompareProperty(isIgnore = true)
	@ApiModelProperty(value = "流程实例主键")
	private String processInstanceId;
	/**
	 * 申请时间
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
	@ApiModelProperty(value = "申请时间")
	private Date applyTime;
	//endregion

	//region business字段
	/**
	 * 商机名称
	 */
	@ApiModelProperty(value = "商机名称")
	private String recordName;
	/**
	 * 商机编号
	 */
	@ApiModelProperty(value = "商机编号")
	private String recordCode;
	/**
	 * 商机分类
	 */
	@ApiModelProperty(value = "商机分类")
	private String projectCatrgory;
	/**
	 * 商机来源
	 */
	@ApiModelProperty(value = "招标方式")
	private String biddingType;
	/**
	 * 拓展模式
	 */
	@ApiModelProperty(value = "拓展模式")
	private String expandMode;
	/**
	 * 专业
	 */
	@ApiModelProperty(value = "专业")
	private String major;
	/**
	 * 行业
	 */
	@ApiModelProperty(value = "行业")
	private String industry;
	/**
	 * 客户名字
	 */
	@ApiModelProperty(value = "客户名字")
	private String clientName;
	/**
	 * 客户类型
	 */
	@ApiModelProperty(value = "客户类型")
	private String clientType;
	/**
	 * 客户类别
	 */
	@ApiModelProperty(value = "客户类别")
	private String clientCategory;
	/**
	 * 甲方联系人
	 */
	@ApiModelProperty(value = "甲方联系人")
	private String clientContact;
	/**
	 * 联系方式
	 */
	@ApiModelProperty(value = "联系方式")
	private String clientPhone;
	/**
	 * 客户关系层
	 */
	@ApiModelProperty(value = "客户关系层")
	private String clientRelationship;
	//endregion

	/**
	 * 文件
	 */
	@ApiModelProperty(value = "文件id")
	private List<Upload> upload;
}
