package org.springblade.modules.project.dto;

import lombok.Data;

@Data
public class BidListDTO {

	private Long Id;
	private Long businessId;
	private String projectName;
	private Integer bidStatus;
	private Integer Status;
	private String recordName;
	private String recordCode;
}
