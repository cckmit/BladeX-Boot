package org.springblade.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BidStatusEnum {

	REJECT("审核不通过", -1),


	/**
	 * 等待投标
	 */
	WAIT("等待投标", 0),

	/**
	 * 发起投标
	 */
	APPLY_BID("发起投标", 1),

	/**
	 * 投标申请通过
	 */
	APPLY_SUCCESS("投标申请通过", 2),

	/**
	 * 录入开标信息或承接结果
	 */
	INPUT_OPEN_OR_CONTINUE("录入开标信息或承接结果", 3),

	/**
	 * 中标或承接审核通过
	 */
	OPEN_OR_CONTINUE_SUCCESS("中标或承接审核通过", 4);

	final String name;
	final Integer value;
}
