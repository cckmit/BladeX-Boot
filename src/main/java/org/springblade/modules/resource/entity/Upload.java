package org.springblade.modules.resource.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.core.tenant.mp.TenantEntity;
/**
 * 上传文件实体
 *
 * @author Chill
 */
@Data
@TableName("blade_upload")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "upload对象", description = "上传字段")
public class Upload  extends TenantEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * 附件id
	 */
	@ApiModelProperty(value = "附件id")
	private String attachId;
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
	private String fileName;
	/**
	 * 附件拓展名
	 */
	@ApiModelProperty(value = "附件拓展名")
	private String fileSuffix;
	/**
	 * 附件大小
	 */
	@ApiModelProperty(value = "附件大小")
	private String fileSize;
	/**
	 * 投标模块中的文件类型
	 */
	@ApiModelProperty(value = "投标类型")
	private String fileType;
	/**
	 * 上传状态
	 */
	@ApiModelProperty(value = "上传状态")
	private String uploadTip;

}
