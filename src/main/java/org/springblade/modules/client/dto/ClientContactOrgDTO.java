package org.springblade.modules.client.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springblade.modules.client.entity.ClientContactOrg;

/**
 * @author zhuyilong
 */
@Data
@ApiModel(value="客户联系人DTO")
public class ClientContactOrgDTO extends ClientContactOrg {

	private static final long serialVersionUID = -4082766565295093750L;

}
