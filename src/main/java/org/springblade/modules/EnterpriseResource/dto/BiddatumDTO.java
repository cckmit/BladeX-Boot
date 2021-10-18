
package org.springblade.modules.EnterpriseResource.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.modules.EnterpriseResource.entity.Biddatum;

/**
 * 投标资料表数据传输对象实体类
 *
 * @author BladeX
 * @since 2021-09-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BiddatumDTO extends Biddatum {
	private static final long serialVersionUID = 1L;

}
