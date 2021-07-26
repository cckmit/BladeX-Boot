package org.springblade.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BidCancelStatusEnum {

	/**
	 * 不通过
	 */
	REJECT("不通过", -1),

	/**
	 * 等待申请
	 */
	WAIT("等待申请", 0),

	/**
	 * 发起作废
	 */
	APPLY("发起作废", 1),

	/**
	 * 作废申请通过
	 */
	SUCCESS("作废申请通过", 2);



	final String name;
	final Integer value;
}
