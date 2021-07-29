package org.springblade.common.enums;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author
 */

@Getter
@AllArgsConstructor
public enum BusinessFlowStatusEnum {

	/**
	 * 无冲突本部审核
	 */
	N_WAIT_REVIEW("无冲突本部审核", 0),

	/**
	 * 分公司审核
	 */
	F_WAIT_REVIEW("分公司审核", 1),

	/**
	 * 全公司冲突本部审核
	 */
	S_WAIT_REVIEW("全公司冲突本部审核", 2),

	/**
	 * 分公司冲突本部审核
	 */
	E_WAIT_REVIEW("分公司冲突本部审核", 3),

	/**
	 * 分公司审核不通过
	 */
	F_CLASH_Fail("分公司审核不通过", 4),

	/**
	 * 本部审核不通过
	 */
	E_CLASH_Fail("本部审核不通过", 5),

	/**
	 * 审核通过
	 */
	E_SUCCESS("本部审核通过", 6);

	final String name;
	final Integer value;
}
