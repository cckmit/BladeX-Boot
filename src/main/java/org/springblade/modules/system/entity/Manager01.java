
package org.springblade.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 实体类
 *
 * @author BladeX
 * @since 2021-06-30
 */
@Data
@TableName("blade_manager")
@ApiModel(value = "Manager对象", description = "Manager对象")
public class Manager01 implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* 主键
	*/
		@ApiModelProperty(value = "主键")
		private Long id;

	/**
	 * 用户表外键
	 */
	@ApiModelProperty(value = "用户表外键")
	private Long userId;

	/**
	* 是否锁定
	*/
	@ApiModelProperty(value = "是否锁定")
	private Integer isLock;


	/**
	 * 用户真名
	 */
	@ApiModelProperty(value = "用户真名",hidden = true)
	private String realName;

	/**
	 * 组织
	 */
	@ApiModelProperty(value = "组织",hidden = true)
	private String deptName;

	/**
	* 是否一级建造师
	*/
		@ApiModelProperty(value = "是否一级建造师")
		private Integer isConstructor;
	/**
	* 备注
	*/
		@ApiModelProperty(value = "备注")
		private String remark;


}
