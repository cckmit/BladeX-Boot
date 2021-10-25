package org.springblade.modules.client.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springblade.modules.client.entity.ClientAssign;

import java.util.List;

/**
 * @author zhuyilong
 */
@Data
@ApiModel(value="客户分配信息VO")
public class ClientAssignVO extends ClientAssign {

	private static final long serialVersionUID = 1459702174540199824L;

	/**
	 * 客户分配信息列表
	 */
	private List<ClientAssignVO> list;

	/**
	 * 人员或部门名称
	 */
	private String userOrDeptName;

	/**
	 * 所属部门名称
	 */
	private String parentDeptName;
}
