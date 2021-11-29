
package org.springblade.modules.EnterpriseResource.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 实体类
 *
 * @author BladeX
 * @since 2021-11-26
 */
@Data
@TableName("resource_aptitude_catalogue")
@ApiModel(value = "AptitudeCatalogue对象", description = "AptitudeCatalogue对象")
public class AptitudeCatalogue implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	/**
	* 行业属性
	*/
		@ApiModelProperty(value = "行业属性")
		private Integer industryProperty;
	/**
	* 业务类别
	*/
		@ApiModelProperty(value = "业务类别")
		private Integer businessType;
	/**
	* 本表父级
	*/
		@ApiModelProperty(value = "本表父级")
		private Integer pId;
	/**
	* 各个栏目名字
	*/
		@ApiModelProperty(value = "各个栏目名字")
		private String areaName;


}
