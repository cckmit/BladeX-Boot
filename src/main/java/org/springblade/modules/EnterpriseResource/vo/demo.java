package org.springblade.modules.EnterpriseResource.vo;

import lombok.Data;
import org.springblade.modules.EnterpriseResource.entity.Aptitude;
import org.springblade.modules.EnterpriseResource.entity.File;

import java.util.List;

@Data
public class demo {
	private Aptitude  aptitude;
	private List<File> list;
}
