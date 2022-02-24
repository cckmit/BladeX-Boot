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
public class BidExcel {
	//id保留
	@ExcelIgnore
	@ColumnWidth(25)
	@ExcelProperty("项目备案id")
	private String id;
	//项目备案名称
	@ColumnWidth(25)
	@ExcelProperty("projectRecordName")
	private String projectRecordName;
	//客户经理
	@ColumnWidth(25)
	@ExcelProperty("projectManager")
	private String projectManager;
	//旧数据主键
	@ColumnWidth(25)
	@ExcelProperty("BidOpenRecordId")
	private String bidOpenRecordId;
	//投标金额
	@ColumnWidth(25)
	@ExcelProperty("BidMoney")
	private BigDecimal bidMoney;
	//招标平台
	@ColumnWidth(25)
	@ExcelProperty("BidPlatform")
	private String bidPlatform;
	//中标公示网址
	@ColumnWidth(25)
	@ExcelProperty("WinBidWebsite")
	private String winBidWebsite;
	//创建时间
	@ColumnWidth(25)
	@ExcelProperty("CreateDate")
	private Date createDate;
	//是否框架
	@ColumnWidth(25)
	@ExcelProperty("IsFrame")
	private String isFrame;
	//投标状态
	@ColumnWidth(25)
	@ExcelProperty("BidStatus")
	private String bidStatus;
	//是否流标
	@ColumnWidth(25)
	@ExcelProperty("IsFailBid")
	private String isFailBid;
	//部门code
	@ColumnWidth(25)
	@ExcelProperty("Code2")
	private String code2;
}
