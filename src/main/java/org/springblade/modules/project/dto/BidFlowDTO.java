package org.springblade.modules.project.dto;


import lombok.Data;

/**
 * @author MyPC
 */
@Data
public class BidFlowDTO {
	public String bidprocessInstanceId;
	public String businessprocessInstanceId;
	public String bidbondprocessInstanceId;
	public String bidundertakeprocessInstanceId;
	public String bidresultprocessInstanceId;
	public String bidcancelprocessInstanceId;
}
