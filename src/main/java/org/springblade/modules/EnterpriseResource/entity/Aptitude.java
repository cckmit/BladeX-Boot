
package org.springblade.modules.EnterpriseResource.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
	@ApiModelProperty(value = "主键ID",hidden = true)
	private Long id;

	/**
	 * 省级公司名称ID
	 */
	@ApiModelProperty(value = "省级公司名称ID")
	private Integer provincialCompanyName;
	/**
	 * 企业名称ID
	 */
	@ApiModelProperty(value = "企业名称ID")
	private Integer aptitudeName;

	/**
	 * 企业名称
	 */
	@ApiModelProperty(value = "企业名称")
	@TableField(exist = false) //表示该属性不为数据库表字段
	private String aptitudeNames;
	/**
	 * 证书类别ID
	 */
	@ApiModelProperty(value = "证书类别ID")
	private Integer certificateType;
	/**
	 * 业务领域ID
	 */
	@ApiModelProperty(value = "业务领域ID")
	private Integer businessArea;
	/**
	 * 行业属性ID
	 */
	@ApiModelProperty(value = "行业属性ID")
	private Integer industryProperty;
	/**
	 * 业务类型ID
	 */
	@ApiModelProperty(value = "业务类型ID")
	private Integer businessType;
	/**
	 * 名称及等级
	 */
	@ApiModelProperty(value = "名称及等级")
	private String classs;
	/**
	 * 等级属性ID
	 */
	@ApiModelProperty(value = "等级属性ID")
	private Integer classType;
	/**
	 * 证书号码
	 */
	@ApiModelProperty(value = "证书号码")
	private String certificateNumber;
	/**
	 * 发证日期
	 */
	@ApiModelProperty(value = "发证日期")
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Date issueDate;
	/**
	 * 有效期（截止日期）
	 */
	@ApiModelProperty(value = "有效期（截止日期）")
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Date periodValidity;
	/**
	 * 发证机关
	 */
	@ApiModelProperty(value = "发证机关")
	private String issuingAuthority;
	/**
	 * 资质/认证范围
	 */
	@ApiModelProperty(value = "资质/认证范围")
	private String rangeApplication;
	/**
	 * 附件地址(资质图片地址)
	 */
	@ApiModelProperty(value = "附件地址(资质图片地址)")
	private String attachmentAddress;






	/**
	 * 是否删除
	 */
	@ApiModelProperty(value = "是否删除")
	private Integer isDeleted;

	/**
	 * 租户ID
	 */
	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	/**
	 * 创建人
	 */
	@ApiModelProperty(value = "创建人",hidden = true)
	private Long createUser;
	/**
	 * 状态
	 */
	@ApiModelProperty(value = "状态",hidden = true)
	private Integer status;
	/**
	 * 修改人
	 */
	@ApiModelProperty(value = "修改人",hidden = true)
	private Long updateUser;
	/**
	 * 部门ID
	 */
	@ApiModelProperty(value = "部门ID",hidden = true)
	private Long createDept;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间",hidden = true)
	private Date createTime;
	/**
	 * 修改时间
	 */
	@ApiModelProperty(value = "修改时间",hidden = true)
	private Date updateTime;



}
