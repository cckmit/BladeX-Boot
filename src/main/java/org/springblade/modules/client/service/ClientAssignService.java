package org.springblade.modules.client.service;

import org.springblade.modules.client.entity.ClientAssign;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.modules.client.vo.ClientAssignVO;

import java.util.List;

/**
 * @author zhuyilong
 */
public interface ClientAssignService extends IService<ClientAssign>{

	/**
	 * 客户分配信息列表
	 *
	 * @param condition
	 * @return
	 */
	List<ClientAssignVO> listClientAssign(ClientAssignVO condition);

	/**
	 * 客户分配信息保存
	 *
	 * @param info
	 */
	void saveClientAssign(ClientAssignVO info);
}
