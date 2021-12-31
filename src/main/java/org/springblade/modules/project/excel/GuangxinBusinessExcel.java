package org.springblade.modules.project.excel;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author kidd
 */
@Data
@ColumnWidth(25)
@HeadRowHeight(20)
@ContentRowHeight(18)
public class GuangxinBusinessExcel {
	//id保留
//	@ExcelIgnore
	@ColumnWidth(25)
	@ExcelProperty("项目备案id")
	private String id;
	//使用于商机名称以及商机分类
	@ColumnWidth(25)
	@ExcelProperty("项目备案名称")
	private String recordName;
	//使用于项目招标方式
	@ColumnWidth(25)
	@ExcelProperty("项目来源")
	private String biddingType;
	//使用于项目投资金额
	@ColumnWidth(25)
	@ExcelProperty("投资规模万元")
	private BigDecimal investmentAmount;
	//使用于专业
	@ColumnWidth(25)
	@ExcelProperty("专业类别1")
	private String major1;
	@ColumnWidth(25)
	@ExcelProperty("专业类别2")
	private String major2;
	//使用于行业和客户类型
	@ColumnWidth(25)
	@ExcelProperty("客户类型")
	private String industry;
	//使用于项目实施区域
	@ColumnWidth(25)
	@ExcelProperty("省")
	private String region1;
	@ColumnWidth(25)
	@ExcelProperty("市")
	private String region2;
	//使用于预计投标日期
	@ColumnWidth(25)
	@ExcelProperty("预计项目招标时间")
	private Date tenderDate;
	//使用于项目建设内容
	@ColumnWidth(25)
	@ExcelProperty("项目基本情况")
	private String projectContent;
	//使用于拓展模式
	@ColumnWidth(25)
	@ExcelProperty("拓展模式")
	private String expandMode;
	//使用于客户名字
	@ColumnWidth(25)
	@ExcelProperty("客户经理名称")
	private String clientName;
	//使用于客户类型
	@ColumnWidth(25)
	@ExcelProperty("是否延续型客户")
	private String clientType;
	//使用于甲方联系人
	@ColumnWidth(25)
	@ExcelProperty("业主联系人姓名")
	private String clientContact;
	//使用于联系方式
	@ColumnWidth(25)
	@ExcelProperty("业主联系人电话")
	private String clientPhone;
	//使用于申请时间
	@ColumnWidth(25)
	@ExcelProperty("录入时间")
	private Date applyTime;
	//使用于分公司主键
	@ColumnWidth(25)
	@ExcelProperty("公司代码")
	private String branchCompany;
	//使用于商机状态
	@ColumnWidth(25)
	@ExcelProperty("项目备案情况")
	private String recordStatus;
}
