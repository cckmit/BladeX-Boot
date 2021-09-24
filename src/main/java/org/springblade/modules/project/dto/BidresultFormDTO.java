package org.springblade.modules.project.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springblade.flow.core.entity.BladeFlow;
import org.springblade.modules.resource.entity.Upload;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BidresultFormDTO {
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	@ApiModelProperty(value = "id")
	private Long id;
	/**
	 * 是否废标
	 */
	@ApiModelProperty(value = "是否废标")
	private Integer isFail;
	/**
	 * 是否中标
	 */
	@ApiModelProperty(value = "是否中标")
	private Integer isWin;
	/**
	 * 中标金额
	 */
	@ApiModelProperty(value = "中标金额")
	private Double winAmount;
	/**
	 * 中标单位
	 */
	@ApiModelProperty(value = "中标单位")
	private String bidCom;
	/**
	 * 中标日期
	 */
	@ApiModelProperty(value = "中标日期")
	private LocalDateTime winbidTime;
	/**
	 * 报价方式
	 */
	@ApiModelProperty(value = "报价方式")
	private String quotationMethod;
	/**
	 * 报价
	 */
	@ApiModelProperty(value = "报价")
	private String offer;
	/**
	 * 折扣率
	 */
	@ApiModelProperty(value = "折扣率")
	private String discount;
	/**
	 * 降点
	 */
	@ApiModelProperty(value = "降点")
	private String dropPoint;
	/**
	 * 承接项目部
	 */
	@ApiModelProperty(value = "承接项目部")
	private String continueDept;
	/**
	 * 预计毛利率
	 */
	@ApiModelProperty(value = "预计毛利率")
	private String grossRate;
	/**
	 * 服务周期
	 */
	@ApiModelProperty(value = "服务周期")
	private String serviceCycle;
	/**
	 * 上传文件
	 */
	private List<Upload> upload;

	private BladeFlow flow;

}
