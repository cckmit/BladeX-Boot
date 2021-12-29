
package org.springblade.modules.EnterpriseResource.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.modules.EnterpriseResource.entity.AllFile;

/**
 * 企业资产附件表数据传输对象实体类
 *
 * @author BladeX
 * @since 2021-09-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FileDTO extends AllFile {
	private static final long serialVersionUID = 1L;
	/**
	 * 附件域名
	 */
	@ApiModelProperty(value = "附件域名")
	private String domain;
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


	@ApiModelProperty(value = "文件名")
	private String fileName;
	/**
	 * 文件大小
	 */
	@ApiModelProperty(value = "文件大小")
	private String fileSize;
	/**
	 * 文件地址
	 */
	@ApiModelProperty(value = "文件地址")
	private String fileAddess;


	/**
	 * 主键ID
	 */
	@ApiModelProperty(value = "主键ID",hidden = true)
	private Long id;


}
