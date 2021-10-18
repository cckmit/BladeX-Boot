
package org.springblade.modules.EnterpriseResource.vo;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import org.springblade.modules.EnterpriseResource.entity.PersonnelQualification;

import java.time.LocalDate;
import java.util.Date;

/**
 * 人员资质表视图实体类
 *
 * @author BladeX
 * @since 2021-09-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PersonnelQualificationVO对象", description = "人员资质表")
public class PersonnelQualificationVO extends PersonnelQualification {
	private static final long serialVersionUID = 1L;
	/**
	 * 人员名字
	 */
	@ApiModelProperty(value = "人员名字")
	private String personnelName;
	/**
	 * 身份证证号
	 */
	@ApiModelProperty(value = "身份证证号")
	@TableField("ID_number")
	private String idNumber;
	/**
	 * 用工性质
	 */
	@ApiModelProperty(value = "用工性质")
	@TableField("Nature_employment")
	private String natureEmployment;
	/**
	 * 证书名称
	 */
	@ApiModelProperty(value = "证书名称")
	private String certificateName;
	/**
	 * 证书编号
	 */
	@ApiModelProperty(value = "证书编号")
	private String certificateNumber;
	/**
	 * 证书类型
	 */
	@ApiModelProperty(value = "证书类型")
	private Integer certificateType;
	/**
	 * 证书等级
	 */
	@ApiModelProperty(value = "证书等级")
	private Integer certificateGrade;
	/**
	 * 发证单位
	 */
	@ApiModelProperty(value = "发证单位")
	private String issueUnit;
	/**
	 * 证书使用情况
	 */
	@ApiModelProperty(value = "证书使用情况")
	private Integer certificateUsage;
	/**
	 * 是否长期有效
	 */
	@ApiModelProperty(value = "是否长期有效")
	private Integer protecteds;
	/**
	 * 有效期
	 */
	@ApiModelProperty(value = "有效期")
	private LocalDate periodValidity;
	/**
	 * 附件地址
	 */
	@ApiModelProperty(value = "附件地址")
	private String fileAddress;

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
	//@TableId(value = "aptitude_id", type = IdType.AUTO)
	private Long objectId;

	/**
	 * 父类枚举id
	 */
	@ApiModelProperty(value = "父类枚举id")
	//@TableId(value = "aptitude_id", type = IdType.AUTO)
	private Integer objectValue;



	/**
	 * 是否删除
	 */
	@ApiModelProperty(value = "是否删除")
	private Integer isDeleted;

	/**
	 * 文件格式
	 */
	@ApiModelProperty(value = "文件格式")
	private String fileSuffix;




}
