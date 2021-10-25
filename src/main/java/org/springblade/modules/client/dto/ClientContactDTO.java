package org.springblade.modules.client.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springblade.modules.client.entity.ClientContact;

@Data
@ApiModel(value="客户联系人DTO")
public class ClientContactDTO extends ClientContact {

	private static final long serialVersionUID = -4082766565295093750L;

}
