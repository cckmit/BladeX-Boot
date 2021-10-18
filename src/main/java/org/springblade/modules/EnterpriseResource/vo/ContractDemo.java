package org.springblade.modules.EnterpriseResource.vo;

import lombok.Data;
import org.springblade.modules.EnterpriseResource.entity.AllFile;
import org.springblade.modules.EnterpriseResource.entity.Contract;

import java.util.List;
@Data
public class ContractDemo {
	private Contract contract;
	private List<AllFile> list;
}
