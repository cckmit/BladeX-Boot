
package org.springblade.modules.project.dto;

import lombok.Data;
import org.springblade.flow.core.entity.BladeFlow;
import org.springblade.modules.project.entity.Bid;
import org.springblade.modules.project.entity.Business;

/**
 * 数据传输对象实体类
 *
 * @author BladeX
 * @since 2021-07-18
 */
@Data
//@EqualsAndHashCode(callSuper = true)
public class BidDTO {
	private static final long serialVersionUID = 1L;

	private Bid bid;

	private  Business business;

	private BladeFlow flow;
}
