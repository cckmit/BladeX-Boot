package org.springblade.modules.client.service;

import org.springblade.modules.client.entity.ClientContactOrg;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.modules.client.vo.ClientContactOrgVO;

import java.util.List;

/**
 * @author zhuyilong
 */
public interface ClientContactOrgService extends IService<ClientContactOrg>{

	/**
	 * 组织树
	 *
	 * @param condition
	 * @return
	 */
	List<ClientContactOrgVO> getOrgTreeList(ClientContactOrgVO condition);

	/**
	 * 保存/更新组织
	 *
	 * @param entity
	 * @return
	 */
	ClientContactOrgVO saveOrUpdateOrg(ClientContactOrgVO entity);

	/**
	 * 获取组织详情（包含全限定层级名）
	 *
	 * @param condition
	 * @return
	 */
	ClientContactOrgVO getFullContactOrg(ClientContactOrgVO condition);
}
