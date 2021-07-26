
package org.springblade.modules.project.dto;

import org.springblade.modules.project.entity.Business;
import lombok.Data;
import org.springblade.flow.core.entity.BladeFlow;
/**
 * 数据传输对象实体类
 *
 * @author BladeX
 * @since 2021-07-03
 */
@Data
//@EqualsAndHashCode(callSuper = true)
public class BusinessDTO {
	private static final long serialVersionUID = 1L;

	private BladeFlow flow;

	private Business business;
}
