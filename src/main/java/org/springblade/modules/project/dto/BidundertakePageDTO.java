package org.springblade.modules.project.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springblade.common.annotation.CompareProperty;

@Data
public class BidundertakePageDTO {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键")
	private Long id;

	@ApiModelProperty(value = "商机名称")
	private String recordName;

	@ApiModelProperty(value = "商机编号")
	private String recordCode;

	@CompareProperty(isIgnore = true )
	private Long clientId;

	@ApiModelProperty(value = "客户名字")
	private String clientName;

	@ApiModelProperty(value = "项目经理主键")
	private String managerId;

	@ApiModelProperty(value = "项目经理名称")
	private String managerName;

	@ApiModelProperty(value = "投标状态")
	private String status;
}
