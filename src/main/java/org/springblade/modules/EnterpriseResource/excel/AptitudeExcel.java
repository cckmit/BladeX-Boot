package org.springblade.modules.EnterpriseResource.excel;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@ColumnWidth(25)
@HeadRowHeight(20)
@ContentRowHeight(18)
public class AptitudeExcel implements Serializable {
	private static final long serialVersionUID = 1L;

	@ExcelIgnore
	@ExcelProperty("省级公司名称ID")
	private Long provincialCompanyId;
	@ExcelProperty("省级公司名称")
	private String provincialCompanyNames;

	@ExcelIgnore
	@ExcelProperty("企业名称ID")
	private Long aptitudeId;
	@ColumnWidth(20)
	@ExcelProperty("企业名称")
	private String aptitudeNames;

	@ExcelIgnore
	@ExcelProperty("证书类别名称ID")
	private Integer certificateType;
	@ColumnWidth(20)
	@ExcelProperty("证书类别名称")
	private String certificateTypeName;

	@ExcelIgnore
	@ExcelProperty("等级属性名称ID")
	private Integer classType;
	@ColumnWidth(20)
	@ExcelProperty("等级属性名称")
	private String classTypeName;

	@ExcelIgnore
	@ExcelProperty("业务领域ID")
	private Long territoryId;
	@ExcelIgnore
	@ExcelProperty("行业属性ID")
	private Long propertyId;
	@ExcelIgnore
	@ExcelProperty("业务类别ID")
	private Long categoryId;

	@ColumnWidth(20)
	@ExcelProperty("业务领域名称")
	private String territoryName;
	@ColumnWidth(20)
	@ExcelProperty("行业属性名称")
	private String propertyName;
	@ColumnWidth(20)
	@ExcelProperty("业务类别名称")
	private String categoryName;

	@ExcelProperty("名称及等级")
	private String classs;

	@ColumnWidth(30)
	@ExcelProperty("证书号码")
	private String certificateNumber;

	@ExcelIgnore
	@ExcelProperty("发证机关")
	private String issuingAuthority;

	@ColumnWidth(40)
	@ExcelProperty("资质/认证范围")
	private String rangeApplication;

	@ColumnWidth(20)
	@ExcelProperty("发证日期")
	private Date issueDate;

	@ColumnWidth(20)
	@ExcelProperty("有效期(截止日期)")
	private Date periodValidity;

	@ColumnWidth(20)
	@ExcelProperty("图片名称")
	private String imageName;
}

