
package org.springblade.modules.system.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.modules.system.entity.Manager;

/**
 * 视图实体类
 *
 * @author BladeX
 * @since 2021-06-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ManagerVO对象", description = "ManagerVO对象")
public class ManagerVO1 extends Manager {
	private static final long serialVersionUID = 1L;

	/*
	投标名称
	 */
	private String projectName;

	/*
	商机名称
	 */
	private String recordName;

	/*
	商机代码
	 */
	private String recordCode;
	/*
	当前状态
	 */
	private String bondStatus;

	/*
	下一级状态
 	*/
	private String status;

}
