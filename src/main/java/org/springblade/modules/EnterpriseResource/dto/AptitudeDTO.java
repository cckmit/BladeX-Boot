
package org.springblade.modules.EnterpriseResource.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.modules.EnterpriseResource.entity.AllFile;
import org.springblade.modules.EnterpriseResource.entity.Aptitude;
import org.springblade.modules.EnterpriseResource.entity.modelFile;

import java.util.Date;
import java.util.List;

/**
 * 企业资质表数据传输对象实体类
 *
 * @author BladeX
 * @since 2021-09-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AptitudeDTO extends Aptitude {
	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	@ApiModelProperty(value = "主键ID",hidden = true)
	private Long id;

	/**
	 * 省级公司名称id(新)
	 *
	 */
	@ApiModelProperty(value = "省级公司名称id(新)")
	private Long provincialCompanyId;
	@ApiModelProperty(value = "省级公司名称(新)")
	private String provincialCompanyNames;

	/**
	 * 企业名称id(新)
	 *
	 */
	@ApiModelProperty(value = "企业名称id(新)")
	private Long aptitudeId;
	@ApiModelProperty(value = "企业名称")
	private String aptitudeNames;

	/**
	 * 证书类别ID
	 */
	@ApiModelProperty(value = "证书类别ID")
	private Long certificateType;
	@ApiModelProperty(value = "证书类别名称")
	private String certificateTypeName;

	/**
	 * 等级属性ID
	 */
	@ApiModelProperty(value = "等级属性ID")
	private Long classType;
	@ApiModelProperty(value = "等级属性名称")
	private String classTypeName;

	/**
	 * 业务领域id
	 */
	@ApiModelProperty(value = "业务领域id")
	private Long territoryId;
	@ApiModelProperty(value = "业务领域名称")
	private String territoryName;


	/**
	 * 行业属性id
	 */
	@ApiModelProperty(value = "行业属性id")
	private Long propertyId;
	@ApiModelProperty(value = "行业属性名称")
	private String propertyName;

	/**
	 * 业务类别id
	 */
	@ApiModelProperty(value = "业务类别id")
	private Long categoryId;
	@ApiModelProperty(value = "业务类别名称")
	private String categoryName;

	/**
	 * 名称及等级
	 */
	@ApiModelProperty(value = "名称及等级")
	private String classs;


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
	 * 栏目名称
	 */
	@ApiModelProperty(value = "栏目名称")
	private String areaName;



	/**
	 * 文件列表
	 */
	@ApiModelProperty(value = "文件列表")
	private List<modelFile> AllFileList;
}
