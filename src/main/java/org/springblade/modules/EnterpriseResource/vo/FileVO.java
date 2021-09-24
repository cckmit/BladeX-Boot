package org.springblade.modules.EnterpriseResource.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import org.springblade.modules.EnterpriseResource.entity.AllFile;


/**
 * 企业资产附件表视图实体类
 *
 * @author BladeX
 * @since 2021-09-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "FileVO对象", description = "企业资产附件表")
public class FileVO extends AllFile {
	private static final long serialVersionUID = 1L;

}
