
package org.springblade.modules.EnterpriseResource.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springblade.core.mp.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 企业资质表实体类
 *
 * @author BladeX
 * @since 2021-09-02
 */
@Data
@TableName("resource_aptitude")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Aptitude对象", description = "企业资质表")
public class Aptitude extends BaseEntity {

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
		@ApiModelProperty(value = "发证时间")
		@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
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
		private String   aptitudeType;
	/**
	* 附件地址
	*/
		@ApiModelProperty(value = "附件地址")
		private String attachmentAddress;
	/**
	* 是否长期有效（1.否 0.是）
	*/
		@ApiModelProperty(value = "是否长期有效（1.否 0.是）")
		private Integer protecteds;
	/**
	* 创建时间
	*/
		@ApiModelProperty(value = "创建时间")
		private Date createTime;


	/**
	 * 修改时间
	 */
	@ApiModelProperty(value = "修改时间")
	private Date updateTime;

	/**
	 * 是否删除
	 */
	@ApiModelProperty(value = "是否删除")
	private Integer isDeleted;


	/**
	 * 创建人
	 */
	@ApiModelProperty(value = "创建人")
	private String create_user;

	/**
	 * 修改人
	 */
	@ApiModelProperty(value = "修改人")
	private String update_user;


	/**
	 * 状态
	 */
	@ApiModelProperty(value = "状态")
	private Integer status;




	/**
	 * 部门ID
	 */
	@ApiModelProperty(value = "部门ID")
	private String create_dept;



}
