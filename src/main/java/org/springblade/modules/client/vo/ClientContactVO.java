package org.springblade.modules.client.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springblade.modules.client.entity.ClientContact;

import java.util.List;

/**
 * @author zhuyilong
 */
@Data
@ApiModel(value="客户联系人VO")
public class ClientContactVO extends ClientContact {

	private static final long serialVersionUID = 1459702174540199824L;

	/**
	 * 创建人名称
	 */
	private String createUserName;

	private List<Long> ids;

	/**
	 * 所属组织层级ID
	 */
	private String pids;

	/**
	 * 客户ID，用于筛选客户下的联系人
	 */
	private Long clientId;
}
