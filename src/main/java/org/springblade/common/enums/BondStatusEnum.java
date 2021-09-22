package org.springblade.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BondStatusEnum {

	/**
	 * 保证金审核不通过
	 */
	REJECT("保证金审核不通过", -1),

	/**
	 * 等待发起
	 */
	WAIT("等待发起", 0),

	/**
	 * 发起保证金
	 */
	APPLY("发起保证金", 1),

	/**
	 * 保证金审核通过
	 */
	SUCCESS("保证金审核通过", 2),

	/**
	 * 分公司保证金审核通过
	 */
	COM_SUCCESS("分公司保证金审核通过", 3);



	final String name;
	final Integer value;
}
