
package org.springblade.modules.system.vo;

import org.springblade.modules.system.entity.ManagerLog;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;

/**
 * 视图实体类
 *
 * @author BladeX
 * @since 2021-10-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ManagerLogVO对象", description = "ManagerLogVO对象")
public class ManagerLogVO extends ManagerLog {
	private static final long serialVersionUID = 1L;
	/*
	是否锁定
	 */
	private String isLockName;




}
