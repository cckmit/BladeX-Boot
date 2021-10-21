
package org.springblade.modules.EnterpriseResource.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import org.springblade.modules.EnterpriseResource.entity.Aptitude;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 企业资质表视图实体类
 *
 * @author BladeX
 * @since 2021-09-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "AptitudeVO对象", description = "企业资质表")
public class AptitudeVO extends Aptitude {
	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	@ApiModelProperty(value = "主键ID")
	//@TableId(value = "aptitude_id", type = IdType.AUTO)
	private Long id;
	/**
	 * 证书名称
	 */
	@ApiModelProperty(value = "证书名称")
	private String certificatesType;
	/**
	 * 等级
	 */
	@ApiModelProperty(value = "等级")
	private String classs;
	/**
	 * 证书编号
	 */
	@ApiModelProperty(value = "证书编号")
	private String certificateNumber;
	/**
	 * 适用范围
	 */
	@ApiModelProperty(value = "适用范围")
	private String rangeApplication;
	/**
	 * 发证机关
	 */
	@ApiModelProperty(value = "发证机关")
	private String issuingAuthority;
	/**
	 * 发证时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	@ApiModelProperty(value = "发证时间")
	private Date issueDate;
	/**
	 * 下次年审时间
	 */

	@ApiModelProperty(value = "下次年审时间")
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Date nyearAudittime;
	/**
	 * 有效期
	 */
	@ApiModelProperty(value = "有效期")
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Date periodValidity;
	/**
	 * 证件更新时间
	 */
	@ApiModelProperty(value = "证件更新时间")
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Date certificateRenewal;
	/**
	 * 类型（1.iOS系列 2.安卓系列 3.其他系列）
	 */
	@ApiModelProperty(value = "类型（1.iOS系列 2.安卓系列 3.其他系列）")
	private Integer  type;
	/**
	 * 附件地址
	 */
	@ApiModelProperty(value = "附件地址")
	private String attachmentAddress;
	/**
	 * 是否长期有效（1.否 0.是）
	 */
	@ApiModelProperty(value = "是否长期有效（1.否 0.是）")
	private Long protecteds;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime creationTime;


	/**
	 * 是否删除
	 */
	@ApiModelProperty(value = "是否删除")
	private Integer isDeleted;

	/**
	 * 文件名
	 */
	@ApiModelProperty(value = "文件名")
	private String fileName;
	/**
	 * 文件大小
	 */
	@ApiModelProperty(value = "文件大小")
	private String fileSize;
	/**
	 * 文件类型()
	 */
	@ApiModelProperty(value = "文件类型()")
	private Integer fileType;
	/**
	 * 文件状态
	 */
	@ApiModelProperty(value = "文件状态")
	private Integer fileStatus;
	/**
	 * 文件地址
	 */
	@ApiModelProperty(value = "文件地址")
	private String fileAddess;

	/**
	 * 父类ID
	 */
	@ApiModelProperty(value = "父类ID")
	private Long objectId;


	/**
	 * 文件格式
	 */
	@ApiModelProperty(value = "文件格式")
	private String fileSuffix;


	/**
	 * 父类枚举id
	 */
	@ApiModelProperty(value = "父类枚举id")
	private Integer objectValue;


}
