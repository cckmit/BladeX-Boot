package org.springblade.modules.project.excel;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

/**
 * @author kidd
 */
@Data
@ColumnWidth(25)
@HeadRowHeight(20)
@ContentRowHeight(18)
public class DeptExcel {
	@ExcelIgnore
	@ColumnWidth(25)
	@ExcelProperty("id")
	private String id;

	@ColumnWidth(20)
	@ExcelProperty("Code")
	private String code;

	@ColumnWidth(20)
	@ExcelProperty("parentId")
	private String parentId;

	@ColumnWidth(50)
	@ExcelProperty("fullName")
	private String fullName;

	@ColumnWidth(50)
	@ExcelProperty("shortName")
	private String shortName;

	@ColumnWidth(50)
	@ExcelProperty("level")
	private String level;

	@ColumnWidth(50)
	@ExcelProperty("sort")
	private String sort;
}
