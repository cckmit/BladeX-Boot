package org.springblade.modules.EnterpriseResource.vo;

import lombok.Data;
import org.springblade.modules.EnterpriseResource.entity.AllFile;
import org.springblade.modules.EnterpriseResource.entity.Invoice;

import java.util.List;
@Data
public class InvoiceDemo {
	private Invoice invoice;
	private List<AllFile> list;
}

