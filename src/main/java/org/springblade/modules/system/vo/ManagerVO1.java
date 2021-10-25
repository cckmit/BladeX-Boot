
package org.springblade.modules.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.modules.system.entity.Manager;
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
public class ManagerVO1 extends Manager {
	private static final long serialVersionUID = 1L;

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

}
