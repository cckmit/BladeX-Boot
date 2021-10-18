package org.springblade.modules.EnterpriseResource.vo;

import lombok.Data;
import org.checkerframework.checker.optional.qual.PolyPresent;
import org.springblade.modules.EnterpriseResource.entity.AllFile;
import org.springblade.modules.EnterpriseResource.entity.ProjectCase;

import java.util.List;

@Data
public class ProjectCaseDemo {
	private ProjectCase projectCase;
	private List<AllFile> list;

}
