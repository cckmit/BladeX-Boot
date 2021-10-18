package org.springblade.modules.EnterpriseResource.vo;

import lombok.Data;
import org.springblade.modules.EnterpriseResource.entity.PersonnelQualification;

import java.util.List;
@Data
public class PersonnelQualificationDemo {
	private PersonnelQualification personnelQualification;
	private List<FileVO> list;
}

