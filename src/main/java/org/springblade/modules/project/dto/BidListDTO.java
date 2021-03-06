package org.springblade.modules.project.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BidListDTO {

	private Long Id;
	private Long businessId;
	private String projectName;
	private Integer bidStatus;
	private Integer Status;
	private String recordName;
	private String biddingType;
	private String recordCode;
	private String projectCatrgory;
	private String expandMode;
	private String industry;
	private String major;
	private String region;
	private Date tenderDate;
	private String isRelationship;
	private String contractAmount;
	private String clientName;
	private String clientType;
	private String isWin;
	private Date winBidTime;
	private String proCom;
	private String branchCom;
}
