package org.springblade.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BidStatusEnum {

	CANCEL("投标作废",-200),

	REJECT("审核不通过", -1),

	//1为投标审核流程,2为承接结果流程,3为保证金流程,4为中标流程,5为拨付保证金,6为录入承接
	/**
	 * 投标枚举
	 */
	WAIT("等待投标", 10),
	APPLY_BID("发起投标", 11),
	APPLY_SUCCESS("投标申请通过", 12),
	/**
	 * 承接枚举
	 */
	INPUT_CONTINUE("承接:等待录入承接结果", 20),
	APPLY_CONTINUE("承接:发起录入承接结果", 21),
	CONTINUE_SUCCESS("承接:承接审核通过", 22),
	CONTINUE_REJECT("承接:承接审核不通过", 23),
	/**
	 * 保证金枚举
	 */

	BOND_F_SUCCESS("保证金:分公司审核通过", 30),
	BOND_F_REJECT("保证金:分公司审核不通过", 31),
	BOND_Z_SUCCESS("保证金:专业公司审核通过", 32),
	BOND_Z_REJECT("保证金:专业公司审核不通过", 33),
	/**
	 * 中标枚举
	 */
	INPUT_OPEN("录入结果:等待录入开标信息", 40),
	WAIT_OPEN("录入结果:等待中标审核", 41),
	OPEN_SUCCESS("录入结果:中标审核通过", 42),
	OPEN_REJECT("录入结果:中标审核不通过", 43);






	final String name;
	final Integer value;
}
