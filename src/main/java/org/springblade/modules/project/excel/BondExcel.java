package org.springblade.modules.project.excel;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author kidd
 */
@Data
@ColumnWidth(25)
@HeadRowHeight(20)
@ContentRowHeight(18)
public class BondExcel {
	//id保留
	@ExcelIgnore
	@ColumnWidth(25)
	@ExcelProperty("项目备案id")
	private String id;
	//项目id
	@ColumnWidth(25)
	@ExcelProperty("RecordId")
	private String recordId;
	//保证金id
	@ColumnWidth(25)
	@ExcelProperty("BondId")
	private String bondId;
	//保证金金额
	@ColumnWidth(25)
	@ExcelProperty("MarginAmount")
	private BigDecimal marginAmount;
	//拨付方式
	@ColumnWidth(25)
	@ExcelProperty("PaymentForm")
	private String paymentForm;
	//合作方拨付日期
	@ColumnWidth(25)
	@ExcelProperty("PartnersPaymentTime")
	private LocalDateTime partnersPaymentTime;
	//保证金状态
	@ColumnWidth(25)
	@ExcelProperty("BondState")
	private String bondState;
}
