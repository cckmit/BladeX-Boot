package org.springblade.modules.EnterpriseResource.vo;

import lombok.Data;
import org.springblade.modules.EnterpriseResource.entity.AllFile;
import org.springblade.modules.EnterpriseResource.entity.Aptitude;
import org.springblade.modules.EnterpriseResource.entity.modelFile;

import java.util.List;

@Data
public class demo {

	private Aptitude  aptitude;

	private List<modelFile> list;
}
