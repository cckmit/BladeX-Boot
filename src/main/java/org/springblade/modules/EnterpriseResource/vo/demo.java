package org.springblade.modules.EnterpriseResource.vo;

import lombok.Data;
import org.springblade.modules.EnterpriseResource.dto.FileDTO;
import org.springblade.modules.EnterpriseResource.entity.Aptitude;

import java.util.List;

@Data
public class demo {

	private Aptitude  aptitude;

	private List<FileDTO> list;
}
