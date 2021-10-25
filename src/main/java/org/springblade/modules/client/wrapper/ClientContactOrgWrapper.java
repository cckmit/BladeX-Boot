package org.springblade.modules.client.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.modules.client.entity.ClientContactOrg;
import org.springblade.modules.client.vo.ClientContactOrgVO;

/**
 * 客户联系人组织视图包装类
 *
 * @author zhuyilong
 */
public class ClientContactOrgWrapper extends BaseEntityWrapper<ClientContactOrg, ClientContactOrgVO> {

	private static class SingletonHolder {
		private static final ClientContactOrgWrapper INSTANCE = new ClientContactOrgWrapper();
	}

	public static ClientContactOrgWrapper build() {
		return SingletonHolder.INSTANCE;
	}

	@Override
	public ClientContactOrgVO entityVO(ClientContactOrg entity) {
		return BeanUtil.copy(entity, ClientContactOrgVO.class);
	}
}
