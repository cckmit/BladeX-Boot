package org.springblade.modules.client.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.modules.client.entity.ClientAssign;
import org.springblade.modules.client.vo.ClientAssignVO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 客户分配视图包装类
 *
 * @author zhuyilong
 */
public class ClientAssignWrapper extends BaseEntityWrapper<ClientAssign, ClientAssignVO> {

	private static class SingletonHolder {
		private static final ClientAssignWrapper INSTANCE = new ClientAssignWrapper();
	}

	public static ClientAssignWrapper build() {
		return SingletonHolder.INSTANCE;
	}

	@Override
	public ClientAssignVO entityVO(ClientAssign entity) {
		return BeanUtil.copy(entity, ClientAssignVO.class);
	}

	public List<ClientAssign> list(List<ClientAssignVO> list) {
		return new ArrayList<>(list);
	}
}
