package org.springblade.modules.EnterpriseResource.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wucan
 * @date 2021/12/29
 */
@Data
public class modelFile {

	/**
	 * 不知名id
	 */
	@ApiModelProperty(value = "不知名id")
	private String attachId;
	/**
	 * 附件域名
	 */
	@ApiModelProperty(value = "附件域名")
	private String domain;
	/**
	 * 不知名字段
	 */
	@ApiModelProperty(value = "不知名字段")
	private String elementIdex;
	/**
	 * 附件原名
	 */
	@ApiModelProperty(value = "附件原名")
	private String originalName;



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
	private String link;


	/**
	 * 文件类型
	 */
	@ApiModelProperty(value = "文件类型")
	private String fileSuffix;

	/**
	 * 全称
	 */
	@ApiModelProperty(value = "全称")
	private String name;

}
