
package org.springblade.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 企业资质枚举类
 *
 * @author Chill
 */
@Getter
@AllArgsConstructor
public enum RescoreEnum {

	RESCORE_APTITUDE("企业资质",1),
	RESCORE_ATTACHMENTPROVE("社保证明",2),
	RESCORE_BIDDATUM("投标资料",3),
	RESCORE_CONTRACT("合同",4),
	RESCORE_INVOICE("发票",5),
	RESCORE_PERSONNELQUALIFICATION("人员资质",6),
	RESCORE_PROIECTCASE("项目案例",7),
	RESCORE_SOLVECHEM("解决方案",8),
	;

	final String name;
	final Integer value;
}
