package org.springblade.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BidStatusEnum {

	CANCEL("投标作废",-200),

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
	INPUT_OPEN("录入结果:等待录入开标信息", 30),
	INPUT_CONTINUE("承接:等待录入承接结果", 31),
	/**
	 * 发起开标信息或承接结果
	 */
	APPLY_OPEN("录入结果:发起录入开标信息", 32),
	APPLY_CONTINUE("承接:发起录入承接结果", 33),
	/**
	 * 分公司中标或承接审核通过
	 */
	OPEN_F_SUCCESS("录入结果:分公司审核通过", 40),
	CONTINUE_F_SUCCESS("承接:分公司审核通过", 41),
	/**
	 * 分公司中标或承接审核通过
	 */
	OPEN_Z_SUCCESS("录入结果:专业公司中标审核通过", 42),
	CONTINUE_Z_SUCCESS("承接:专业公司承接审核通过", 43),
	/**
	 * 中标或承接审核不通过
	 */
	OPEN_F_REJECT("录入结果:分公司中标审核不通过", 50),
	CONTINUE_F_REJECT("承接:分公司承接审核不通过", 51),
	OPEN_Z_REJECT("录入结果:专业公司中标审核不通过", 52),
	CONTINUE_Z_REJECT("承接:专业公司承接审核不通过", 53);

	final String name;
	final Integer value;
}
