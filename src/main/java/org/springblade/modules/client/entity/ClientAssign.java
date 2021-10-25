package org.springblade.modules.client.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springblade.core.tenant.mp.TenantEntity;

import java.io.Serializable;

/**
 * 客户分配关系
 *
 * @author zhuyilong
 */
@ApiModel(value = "客户分配关系")
@Data
@TableName(value = "client_assign")
public class ClientAssign extends TenantEntity implements Serializable {

	private static final long serialVersionUID = 6344956163305913865L;

	/**
	 * 客户ID
	 */
	@TableField(value = "client_id")
	@ApiModelProperty(value = "客户ID")
	private Long clientId;

	/**
	 * 分配类型 1分公司 2项目部 3个人
	 */
	@TableField(value = "assign_type")
	@ApiModelProperty(value = "分配类型 1分公司 2项目部 3个人")
	@JsonSerialize(using = ToStringSerializer.class)
	private Integer assignType;

	/**
	 * 所属分公司/项目部
	 */
	@TableField(value = "dept_id")
	@ApiModelProperty(value = "所属分公司/项目部")
	private String deptId;

	/**
	 * 所属用户
	 */
	@TableField(value = "user_id")
	@ApiModelProperty(value = "所属用户")
	private Long userId;

	/**
	 * 所属部门ID
	 */
	@TableField(value = "parent_dept_id")
	@ApiModelProperty(value = "所属部门ID")
	private String parentDeptId;

}
