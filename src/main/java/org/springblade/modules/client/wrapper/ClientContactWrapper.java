package org.springblade.modules.client.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.modules.client.entity.ClientContact;
import org.springblade.modules.client.vo.ClientContactVO;

/**
 * 客户联系人视图包装类
 *
 * @author zhuyilong
 */
public class ClientContactWrapper extends BaseEntityWrapper<ClientContact, ClientContactVO> {

	private static class SingletonHolder {
		private static final ClientContactWrapper INSTANCE = new ClientContactWrapper();
	}

	public static ClientContactWrapper build() {
		return SingletonHolder.INSTANCE;
	}

	@Override
	public ClientContactVO entityVO(ClientContact entity) {
		return BeanUtil.copy(entity, ClientContactVO.class);
	}
}
