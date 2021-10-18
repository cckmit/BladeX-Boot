
package org.springblade.modules.EnterpriseResource.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import org.springblade.core.mp.base.BaseEntity;
import java.time.LocalDate;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 合同表实体类
 *
 * @author BladeX
 * @since 2021-09-28
 */
@Data
@TableName("resource_contract")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Contract对象", description = "合同表")
public class Contract extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@ApiModelProperty(value = "主键ID",hidden = true)
	private Long id;
	/**
	* 合同形式
	*/
		@ApiModelProperty(value = "合同形式")
		private Integer contractForm;
	/**
	* 甲方订单编号
	*/
		@ApiModelProperty(value = "甲方订单编号")
		private String partyOrder;
	/**
	* 合同名称
	*/
		@ApiModelProperty(value = "合同名称")
		private String contractName;
	/**
	* 合同编号
	*/
		@ApiModelProperty(value = "合同编号")
		private String contractNumber;
	/**
	* 专业
	*/
		@ApiModelProperty(value = "专业")
		private String profession;
	/**
	* 地址
	*/
		@ApiModelProperty(value = "地址")
		private String address;
	/**
	* 建议实施单位
	*/
		@ApiModelProperty(value = "建议实施单位")
		private String recommendedUnits;
	/**
	* 项目部
	*/
		@ApiModelProperty(value = "项目部")
		private String projectDepartment;

	/**
	 * 最小经营单元
	 */
		private String minimumOperatingunit;
	/**
	* 省公司统一编号
	*/
		@ApiModelProperty(value = "省公司统一编号")
		private String companyUnifiednumber;
	/**
	* 客户名称
	*/
		@ApiModelProperty(value = "客户名称")
		private String customerName;
	/**
	* 运营商
	*/
		@ApiModelProperty(value = "运营商")
		private Integer operator;
	/**
	* 合同签订名额
	*/
		@ApiModelProperty(value = "合同签订名额")
		private String signedNamcontract;
	/**
	* 合同签订名额（不含税）
	*/
		@ApiModelProperty(value = "合同签订名额（不含税）")
		private String numberExcludingtax;
	/**
	* 对方联系电话
	*/
		@ApiModelProperty(value = "对方联系电话")
		private String contactNumber;
	/**
	* 签署日期
	*/
		@ApiModelProperty(value = "签署日期")
		private LocalDate signingDate;
	/**
	* 甲方合同编号
	*/
		@ApiModelProperty(value = "甲方合同编号")
		private String partyNumber;
	/**
	* 对方联系人
	*/
		@ApiModelProperty(value = "对方联系人")
		private String contactPerson;
	/**
	* 税率
	*/
		@ApiModelProperty(value = "税率")
		private String taxRate;
	/**
	* 存档属性
	*/
		@ApiModelProperty(value = "存档属性")
		private String archiveAttribute;
	/**
	* 存档数量
	*/
		@ApiModelProperty(value = "存档数量")
		private Integer archiveNumber;
	/**
	* 合同页数
	*/
		@ApiModelProperty(value = "合同页数")
		private String contractPages;
	/**
	* 原件位置
	*/
		@ApiModelProperty(value = "原件位置")
		private String originalLocation;
	/**
	* 档案室文件编号
	*/
		@ApiModelProperty(value = "档案室文件编号")
		private String fileRoomnumber;
	/**
	* 文件上传人
	*/
		@ApiModelProperty(value = "文件上传人")
		private String fileUploader;
	/**
	* 其他备注
	*/
		@ApiModelProperty(value = "其他备注")
		private String otherNote;
	/**
	* 附件地址
	*/
		@ApiModelProperty(value = "附件地址")
		private String fileAddress;







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
