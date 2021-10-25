package org.springblade.modules.client.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springblade.core.tenant.mp.TenantEntity;

import java.io.Serializable;

/**
 * 客户联系人主表
 *
 * @author zhuyilong
 */
@Data
@ApiModel(value = "客户联系人主表")
@TableName(value = "client_contact")
public class ClientContact extends TenantEntity implements Serializable {

	private static final long serialVersionUID = -8383456467377998378L;

	/**
	 * 所属组织部门
	 */
	@TableField(value = "contact_org_id")
	@ApiModelProperty(value = "所属组织部门")
	private Long contactOrgId;

	/**
	 * 联系人名称
	 */
	@TableField(value = "contact_name")
	@ApiModelProperty(value = "联系人名称")
	private String contactName;

	/**
	 * 尊称 字典client_contact_honorific
	 */
	@TableField(value = "honorific")
	@ApiModelProperty(value = "尊称 字典client_contact_honorific")
	private String honorific;

	/**
	 * 职务
	 */
	@TableField(value = "job_title")
	@ApiModelProperty(value = "职务")
	private String jobTitle;

	/**
	 * 角色 字典client_contact_role
	 */
	@TableField(value = "role_type")
	@ApiModelProperty(value = "角色 字典client_contact_role")
	private String roleType;

	/**
	 * 分类 字典client_contact_category
	 */
	@TableField(value = "category")
	@ApiModelProperty(value = "分类 字典client_contact_category")
	private String category;

	/**
	 * 联系方式
	 */
	@TableField(value = "phone")
	@ApiModelProperty(value = "联系方式")
	private String phone;

	/**
	 * 工作电话
	 */
	@TableField(value = "work_phone")
	@ApiModelProperty(value = "工作电话")
	private String workPhone;

	/**
	 * QQ号码
	 */
	@TableField(value = "qq")
	@ApiModelProperty(value = "QQ号码")
	private String qq;

	/**
	 * 电子邮箱
	 */
	@TableField(value = "email")
	@ApiModelProperty(value = "电子邮箱")
	private String email;

	/**
	 * 对我方态度 字典client_contact_attitude
	 */
	@TableField(value = "attitude")
	@ApiModelProperty(value = "对我方态度 字典client_contact_attitude")
	private String attitude;

	/**
	 * 工作配合度 字典client_contact_cooperate
	 */
	@TableField(value = "cooperate_status")
	@ApiModelProperty(value = "工作配合度 字典client_contact_cooperate")
	private String cooperateStatus;

	/**
	 * 发展阶段 字典client_contact_develop
	 */
	@TableField(value = "develop_stage")
	@ApiModelProperty(value = "发展阶段 字典client_contact_develop")
	private String developStage;

	/**
	 * 客户性格 字典client_contact_character
	 */
	@TableField(value = "`character`")
	@ApiModelProperty(value = "客户性格 字典client_contact_character")
	private String character;

	/**
	 * 拜访周期 字典client_contact_period
	 */
	@TableField(value = "visit_period")
	@ApiModelProperty(value = "拜访周期 字典client_contact_period")
	private String visitPeriod;

	/**
	 * 备注
	 */
	@TableField(value = "remark")
	@ApiModelProperty(value = "备注")
	private String remark;

}
