
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
	 * 省级公司名称
	 */
	@ApiModelProperty(value = "省级公司名称")
	private String provincialCompanyNames;

	/**
	 * 证书类别名称
	 */
	@ApiModelProperty(value = "证书类别名称")
	private String certificateTypeName;

	/**
	 * 企业名称
	 */
	@ApiModelProperty(value = "企业名称")
	private String aptitudeNames;

	/**
	 * 等级属性名称
	 */
	@ApiModelProperty(value = "等级属性名称")
	private String classTypeName;
}
