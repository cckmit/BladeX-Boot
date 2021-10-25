package org.springblade.modules.client.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springblade.modules.client.entity.ClientContactOrg;

import java.util.List;

/**
 * @author zhuyilong
 */
@Data
@ApiModel(value="客户联系人VO")
public class ClientContactOrgVO extends ClientContactOrg {

	private static final long serialVersionUID = 1459702174540199825L;

	private List<Long> ids;

	private List<ClientContactOrgVO> children;

}
