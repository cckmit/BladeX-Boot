
package org.springblade.modules.resource.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.core.tenant.mp.TenantEntity;

/**
 * 附件表实体类
 *
 * @author Chill
 */
@Data
@TableName("blade_attach")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Attach对象", description = "附件表")
public class Attach extends TenantEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 附件地址
	 */
	@ApiModelProperty(value = "附件地址")
	private String link;
	/**
	 * 附件域名
	 */
	@ApiModelProperty(value = "附件域名")
	private String domain;
	/**
	 * 附件名称
	 */
	@ApiModelProperty(value = "附件名称")
	private String name;
	/**
	 * 附件原名
	 */
	@ApiModelProperty(value = "附件原名")
	private String originalName;
	/**
	 * 附件拓展名
	 */
	@ApiModelProperty(value = "附件拓展名")
	private String extension;
	/**
	 * 附件大小
	 */
	@ApiModelProperty(value = "附件大小")
	private Long attachSize;
	/**
	 * 投标模块中的文件类型
	 */
	@ApiModelProperty(value = "投标类型")
	private String bidType;

}
