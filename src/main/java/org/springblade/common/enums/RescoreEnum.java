
package org.springblade.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 系统字典枚举类
 *
 * @author Chill
 */
@Getter
@AllArgsConstructor
public enum RescoreEnum {

	RESCORE_APTITUDE("企业资质",1),
	;

	final String name;
	final Integer value;
}
