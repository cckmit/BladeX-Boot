package org.springblade.modules.project.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BidListDTO {

	private Long Id;
	private Long businessId;
	private String projectName;
	private Integer bidStatus;
	private Integer Status;
	private String clientName;
	private String recordCode;
}
