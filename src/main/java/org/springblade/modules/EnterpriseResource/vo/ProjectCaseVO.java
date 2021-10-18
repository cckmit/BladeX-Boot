
package org.springblade.modules.EnterpriseResource.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import org.springblade.modules.EnterpriseResource.entity.ProjectCase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;

import java.time.LocalDate;

/**
 * 项目案例表视图实体类
 *
 * @author BladeX
 * @since 2021-09-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ProjectCaseVO对象", description = "项目案例表")
public class ProjectCaseVO extends ProjectCase {
	private static final long serialVersionUID = 1L;


	/**
	 * 项目名称
	 */
	@ApiModelProperty(value = "项目名称")
	private String projectName;
	/**
	 * 项目经理
	 */
	@ApiModelProperty(value = "项目经理")
	private String projectManager;
	/**
	 * 项目归属公司
	 */
	@ApiModelProperty(value = "项目归属公司")
	private String projectOwner;
	/**
	 * 行业
	 */
	@ApiModelProperty(value = "行业")
	private String industry;
	/**
	 * 合同金额（万元）
	 */
	@ApiModelProperty(value = "合同金额（万元）")
	private String contractAmount;
	/**
	 * 竣工日期
	 */
	@ApiModelProperty(value = "竣工日期")
	private LocalDate completionDate;
	/**
	 * 建设单位或部门
	 */
	@ApiModelProperty(value = "建设单位或部门")
	private String constructionUnit;
	/**
	 * 联系电话
	 */
	@ApiModelProperty(value = "联系电话")
	private String phone;
	/**
	 * 专业
	 */
	@ApiModelProperty(value = "专业")
	private String specialty;
	/**
	 * 启动日期
	 */
	@ApiModelProperty(value = "启动日期")
	private LocalDate activeDate;
	/**
	 * 终验日期
	 */
	@ApiModelProperty(value = "终验日期")
	@TableField("FAC_date")
	private LocalDate facDate;
	/**
	 * 项目背景
	 */
	@ApiModelProperty(value = "项目背景")
	private String projectProject;
	/**
	 * 项目目标
	 */
	@ApiModelProperty(value = "项目目标")
	private String projectTarget;
	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注")
	private String remark;


	//附件表的字段


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


}
