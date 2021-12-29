
package org.springblade.modules.EnterpriseResource.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import liquibase.pro.packaged.L;
import oracle.sql.DATE;
import org.springblade.core.mp.base.BaseEntity;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 企业资产附件表实体类
 *
 * @author BladeX
 * @since 2021-09-02
 */
@Data
@TableName("resource_file")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "File对象", description = "企业资产附件表")
public class AllFile extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	* 主键ID
	*/
		@ApiModelProperty(value = "主键ID",hidden = true)
		private Long id;

	/**
	 * 附件域名
	 */
	@ApiModelProperty(value = "附件域名")
	private String domain;

	/**
	 * 附件原名
	 */
	@ApiModelProperty(value = "附件原名")
	private String originalName;

	/**
	* 文件名
	*/

	/**
	 * 附件拓展名
	 */
	@ApiModelProperty(value = "附件拓展名")
	private String extension;


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
		@ApiModelProperty(value = "文件类型")
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
	//@TableId()
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
