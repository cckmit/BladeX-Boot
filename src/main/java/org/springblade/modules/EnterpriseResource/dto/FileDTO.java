
package org.springblade.modules.EnterpriseResource.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.modules.EnterpriseResource.entity.File;

/**
 * 企业资产附件表数据传输对象实体类
 *
 * @author BladeX
 * @since 2021-09-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FileDTO extends File {
	private static final long serialVersionUID = 1L;

}
