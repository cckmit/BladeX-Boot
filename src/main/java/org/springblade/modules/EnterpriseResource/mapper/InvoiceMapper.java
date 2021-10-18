
package org.springblade.modules.EnterpriseResource.mapper;

import org.springblade.modules.EnterpriseResource.entity.Invoice;
import org.springblade.modules.EnterpriseResource.vo.InvoiceVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

/**
 * 发票表 Mapper 接口
 *
 * @author BladeX
 * @since 2021-09-28
 */
public interface InvoiceMapper extends BaseMapper<Invoice> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param invoice
	 * @return
	 */
	List<InvoiceVO> selectInvoicePage(IPage page, InvoiceVO invoice);


	/**
	 * 根据主键查询对应附件
	 *
	 * @return
	 */
	List<InvoiceVO> selectListId(Long objectId);

}
