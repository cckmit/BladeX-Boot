package org.springblade.modules.project.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BidListDTO {

	private Long Id;
	private Long businessId;
	private String projectName;
	private Integer bidStatus;
	private Integer Status;
	private String recordName;
	private String recordCode;
	private String biddingType;
	private String projectCatrgory;
	private String industry;
	private String major;
	private String region;
	private LocalDateTime tenderDate;
	private String isRelationship;
	private String contractAmount;
	private String clientName;
	private String clientType;
	private String isWin;
	private LocalDateTime winBidTime;
}
