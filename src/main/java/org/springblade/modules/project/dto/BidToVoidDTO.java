package org.springblade.modules.project.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BidToVoidDTO {

	/**
	 * 投标管理主键
	 */
	@NotNull
	private long bidId;


	/**
	 * 终止理由
	 */
	@NotNull(message = "终止理由不能为空")
	private String reasons;

}
