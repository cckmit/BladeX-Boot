package org.springblade.modules.client.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springblade.core.tenant.mp.TenantEntity;

import java.io.Serializable;

/**
    * 客户联系人组织机构
 * @author zhuyilong
 */
@ApiModel(value="客户联系人组织机构")
@Data
@TableName(value = "client_contact_org")
public class ClientContactOrg extends TenantEntity implements Serializable {

	private static final long serialVersionUID = 8007834652271175255L;

    /**
     * 上级ID
     */
    @TableField(value = "pid")
    @ApiModelProperty(value="上级ID")
    private Long pid;

    /**
     * 上级IDs
     */
    @TableField(value = "pids")
    @ApiModelProperty(value="上级IDs")
    private String pids;

    /**
     * 层级，由 0 递增
     */
    @TableField(value = "`rank`")
    @ApiModelProperty(value="层级，由 0 递增")
    private Integer rank;

    /**
     * 组织部门名称
     */
    @TableField(value = "`name`")
    @ApiModelProperty(value="组织部门名称")
    private String name;

    /**
     * 显示顺序(默认升序)
     */
    @TableField(value = "sort")
    @ApiModelProperty(value="显示顺序(默认升序)")
    private Integer sort;

}
