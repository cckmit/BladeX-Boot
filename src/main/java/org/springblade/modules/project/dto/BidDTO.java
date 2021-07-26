
package org.springblade.modules.project.dto;

import org.springblade.modules.project.entity.Bid;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 数据传输对象实体类
 *
 * @author BladeX
 * @since 2021-07-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BidDTO extends Bid {
	private static final long serialVersionUID = 1L;

}
