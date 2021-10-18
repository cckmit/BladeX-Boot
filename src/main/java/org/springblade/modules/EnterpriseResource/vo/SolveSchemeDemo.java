package org.springblade.modules.EnterpriseResource.vo;

import lombok.Data;
import org.springblade.modules.EnterpriseResource.entity.AllFile;
import org.springblade.modules.EnterpriseResource.entity.SolveScheme;

import java.util.List;
@Data
public class SolveSchemeDemo {
	private SolveScheme solveScheme;
	private List<AllFile> list;
}
