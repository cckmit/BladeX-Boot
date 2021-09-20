package org.springblade.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BidStatusEnum {
	CANCEL_WAIT("投标作废：等待审核",-199),
	CANCEL("投标作废:作废成功",-200),

	//1为投标审核流程,2为承接结果流程,3为保证金流程,4为中标流程,5为拨付保证金,6为录入承接
	//两个流程,全都做在对应的bid状态里面,所有的更新必须对bid状态进行更新,这里有两条流程
	//9.21  BidStatus显示为当前进度，Status显示为下一步可进行的进度
	//主要：投标流程 =》 保证金流程(可无) =》 中标流程 =》 拨付保证金(没中标需要进行保证金退回)/录入承接系统
	//承接：承接流程 =》 录入承接系统
	/**
	 * 投标枚举
	 */
	WAIT("投标:等待发起投标", 10),
	APPLY_BID("投标:发起投标", 11),
	APPLY_SUCCESS("投标:投标申请通过", 12),
	REJECT("投标:审核不通过", -1),
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
	BOND_WAIT("保证金:等待发起保证金",30),
	BOND_APPLY("保证金:等待审核",31),
	BOND_F_SUCCESS("保证金:分公司审核通过", 32),
	BOND_F_REJECT("保证金:分公司审核不通过", 33),
	BOND_Z_SUCCESS("保证金:专业公司审核通过", 34),
	BOND_Z_REJECT("保证金:专业公司审核不通过", 35),
	/**
	 * 中标枚举
	 */
	INPUT_OPEN("录入结果:等待录入开标信息", 40),
	WAIT_OPEN("录入结果:等待中标审核", 41),
	OPEN_SUCCESS("录入结果:中标审核通过", 42),
	OPEN_REJECT("录入结果:中标审核不通过", 43),


	IS_BOND_WAIT("等待发起保证金回收",50),
	IS_BOND_APPLY("发起保证金回收",51),
	IS_BOND_SUCCESS("退还保证金",52);




	final String name;
	final Integer value;
}
