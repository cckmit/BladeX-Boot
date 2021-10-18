
package org.springblade.modules.EnterpriseResource.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import org.springblade.modules.EnterpriseResource.entity.AttachmentProve;

/**
 * 社保证明表视图实体类
 *
 * @author BladeX
 * @since 2021-09-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "AttachmentProveVO对象", description = "社保证明表")
public class AttachmentProveVO extends AttachmentProve {
	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	@ApiModelProperty(value = "主键ID")
	private Long id;

	/**
	 * 名字
	 */
	@ApiModelProperty(value = "名字")
	private String name;
	/**
	 * 身份证号
	 */
	@ApiModelProperty(value = "身份证号")
	@TableField("ID_number")
	private String idNumber;
	/**
	 * 年份
	 */
	@ApiModelProperty(value = "年份")
	private String year;
	/**
	 * 月份
	 */
	@ApiModelProperty(value = "月份")
	private String month;
	/**
	 * pdf序号
	 */
	@ApiModelProperty(value = "pdf序号")
	private Integer serialNumber;
	/**
	 * pdf页
	 */
	@ApiModelProperty(value = "pdf页")
	private Integer page;
	/**
	 * 附件地址
	 */
	@ApiModelProperty(value = "附件地址")
	private String fileAddress;


}
