package org.springblade.modules.client.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.modules.client.entity.ClientContact;
import org.springblade.modules.client.vo.ClientContactVO;

import java.util.List;

/**
 * @author zhuyilong
 */
public interface ClientContactService extends IService<ClientContact> {

	/**
	 * 客户联系人分页
	 *
	 * @param page
	 * @param condition
	 * @return
	 */
	IPage<ClientContactVO> pageContact(IPage<ClientContactVO> page, ClientContactVO condition);

	/**
	 * 客户联系人详情
	 *
	 * @param condition
	 * @return 联系人详情
	 */
	ClientContactVO getDetail(ClientContactVO condition);

	/**
	 * 保存
	 *
	 * @param entity
	 * @return
	 */
	boolean saveContact(ClientContact entity);

	/**
	 * 更新
	 *
	 * @param entity
	 * @return
	 */
	boolean updateContact(ClientContact entity);

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	boolean batchDelContact(List<Long> ids);

	/**
	 * 通过客户联系人ID获取客户ID
	 *
	 * @param contactId
	 * @return
	 */
	Long getClientByContact(Long contactId);
}
