package org.springblade.modules.project.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springblade.common.annotation.CompareProperty;
import org.springblade.flow.core.entity.BladeFlow;
import org.springblade.modules.resource.entity.Upload;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BidundertakeFormDTO {
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键")
	private Long id;
	//==================商机表====================
	/**
	 * 商机名称
	 */
	@ApiModelProperty(value = "商机名称")
	private String recordName;
	/**
	 * 客户主键
	 */
	@ApiModelProperty(value = "客户主键")
	@CompareProperty(isIgnore = true )
	private Long clientId;
	 /**
	 * 客户名字
	 */
	@ApiModelProperty(value = "客户名字")
	private String clientName;
	/**
	 * 项目实施区域
	 */
	@ApiModelProperty(value = "项目实施区域")
	private String region;
	/**
	 * 商机编号
	 */
	@ApiModelProperty(value = "商机编号")
	private String recordCode;
	/**
	 * 专业
	 */
	@ApiModelProperty(value = "专业")
	private String major;
	@ApiModelProperty(value = "专业名称")
	@TableField(exist = false) //表示该属性不为数据库表字段
	private String majorName;
	//==================承接表====================
	/**
	 * 质量类型
	 */
	@ApiModelProperty(value = "质量类型")
	private String qualityType;
	/**
	 * 预计毛利率
	 */
	@ApiModelProperty(value = "预计毛利率")
	private Double grossRate;
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
	 * 项目开始时间
	 */
	@ApiModelProperty(value = "项目开始时间")
	private LocalDateTime startTime;
	/**
	 * 项目完工时间
	 */
	@ApiModelProperty(value = "项目完工时间")
	private LocalDateTime endTime;
	/**
	 * 项目工期
	 */
	@ApiModelProperty(value = "项目工期")
	private String schedulesTime;

	private List<Upload> upload;

	private BladeFlow flow;
}
