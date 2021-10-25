
package org.springblade.modules.project.dto;

import lombok.Data;
import org.springblade.flow.core.entity.BladeFlow;
import org.springblade.modules.project.entity.*;
import org.springblade.modules.resource.entity.Upload;

import java.util.List;

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

	private Business business;

	private Bidbond bidbond;

	private Bidresult bidresult;

	private BidCancel bidCancel;

	private BladeFlow flow;

	private Bidundertake bidundertake;

	private List<Upload> upload;
}
