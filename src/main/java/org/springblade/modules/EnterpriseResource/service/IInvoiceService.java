
package org.springblade.modules.EnterpriseResource.service;

import org.springblade.modules.EnterpriseResource.entity.Invoice;
import org.springblade.modules.EnterpriseResource.vo.InvoiceDemo;
import org.springblade.modules.EnterpriseResource.vo.InvoiceVO;
import org.springblade.core.mp.base.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 发票表 服务类
 *
 * @author BladeX
 * @since 2021-09-28
 */
public interface IInvoiceService extends BaseService<Invoice> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param invoice
	 * @return
	 */
	IPage<InvoiceVO> selectInvoicePage(IPage<InvoiceVO> page, InvoiceVO invoice);


	/**
	 * 根据主键查询对应附件
	 *
	 * @return
	 */
	List<InvoiceVO> selectListId(Long objectId);


	Boolean saveFile(InvoiceDemo demo);

	void update(InvoiceDemo demo);

}
