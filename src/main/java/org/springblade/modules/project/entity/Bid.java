
package org.springblade.modules.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springblade.common.annotation.CompareProperty;
import org.springblade.flow.core.entity.FlowEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 实体类
 *
 * @author BladeX
 * @since 2021-07-18
 */
@Data
@TableName("project_bid")
@ApiModel(value = "Bid对象", description = "Bid对象")
public class Bid extends FlowEntity {

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
	private Integer isFrame;
	/**
	 * 项目经理主键
	 */
	@ApiModelProperty(value = "项目经理主键")
	private String ManagerId;
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

	@ApiModelProperty(value = "投标下一步状态")
	private Integer status;

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
	@ApiModelProperty(value = "申请时间")
	private Date applyTime;
	/**
	 * 文件列表
	 */
	@ApiModelProperty(value = "文件列表")
	private String fileAttachId;

}

