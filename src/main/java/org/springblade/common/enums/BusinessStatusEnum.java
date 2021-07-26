package org.springblade.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BusinessStatusEnum {

	/**
	 * 备案成功
	 */
	SUCCESS("备案成功", 2),

	/**
	 * 等待审核
	 */
	WAIT_REVIEW("等待审核", 0),

	/**
	 * 备案冲突
	 */
	CLASH("备案冲突", 1),

	/**
	 * 备案失效
	 */
	INVALID("备案失效", -1);

	final String name;
	final Integer value;
}
