package org.springblade.modules.client.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springblade.modules.client.entity.VisitInfo;

import java.util.Date;

/**
 * @author zhuyilong
 */
@Data
public class VisitInfoVO extends VisitInfo {

	private static final long serialVersionUID = 8001988393329646491L;

	/**
	 * 检索 - 开始时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date startDate;

	/**
	 * 检索 - 结束时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date endDate;
}
