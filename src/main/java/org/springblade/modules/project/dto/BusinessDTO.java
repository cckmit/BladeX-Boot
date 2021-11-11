
package org.springblade.modules.project.dto;

import lombok.Data;
import org.springblade.flow.core.entity.BladeFlow;
import org.springblade.modules.project.entity.Business;
import org.springblade.modules.project.entity.Change;

import java.util.List;

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

	private List<Change> Change;

	private List<ClashDTO> obusiness;
}


