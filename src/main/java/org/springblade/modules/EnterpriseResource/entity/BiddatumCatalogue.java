
package org.springblade.modules.EnterpriseResource.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import org.springblade.core.mp.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 投标资料目录表实体类
 *
 * @author BladeX
 * @since 2021-10-18
 */
@Data
@TableName("resource_biddatum_catalogue")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "BiddatumCatalogue对象", description = "投标资料目录表")
public class BiddatumCatalogue extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	* 目录名称
	*/
		@ApiModelProperty(value = "目录名称")
		private String catalogueName;
	/**
	* 备注
	*/
		@ApiModelProperty(value = "备注")
		private String remark;
	/**
	 * 本表父集
	 */
		@ApiModelProperty(value = "本表父集")
		private Integer parentid;

	/**
	 * 投标资料表父级
	 */
	@ApiModelProperty(value = "投标资料表父级")
	private Integer biddatum_pid;


}
