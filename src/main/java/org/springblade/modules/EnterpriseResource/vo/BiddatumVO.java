
package org.springblade.modules.EnterpriseResource.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import org.springblade.modules.EnterpriseResource.entity.Biddatum;

import java.util.List;

/**
 * 投标资料表视图实体类
 *
 * @author BladeX
 * @since 2021-09-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "BiddatumVO对象", description = "投标资料表")
public class BiddatumVO extends Biddatum {
	private static final long serialVersionUID = 1L;
	private  Biddatum biddatum;
	private List<FileVO> list;

}
