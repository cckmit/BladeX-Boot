package org.springblade.modules.client.service.impl;

import lombok.RequiredArgsConstructor;
import org.springblade.modules.client.service.IBaseInfoService;
import org.springblade.modules.client.vo.ClientAssignVO;
import org.springblade.modules.client.wrapper.ClientAssignWrapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.modules.client.mapper.ClientAssignMapper;
import org.springblade.modules.client.entity.ClientAssign;
import org.springblade.modules.client.service.ClientAssignService;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhuyilong
 */
@Service
@RequiredArgsConstructor
public class ClientAssignServiceImpl extends ServiceImpl<ClientAssignMapper, ClientAssign> implements ClientAssignService{

	private final IBaseInfoService clientService;

	@Override
	public List<ClientAssignVO> listClientAssign(ClientAssignVO condition) {
		return baseMapper.listClientAssign(condition);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveClientAssign(ClientAssignVO info) {
		// 删除旧数据
		baseMapper.delClientAssign(info);
		boolean hasList = info.getList() != null && !info.getList().isEmpty();
		// 保存新数据
		if (hasList) {
			List<ClientAssign> list = ClientAssignWrapper.build().list(info.getList());
			list.forEach(i -> i.setClientId(info.getClientId()));
			saveBatch(list);
			// 更新客户为私海类型
			clientService.updateClientMode(info.getClientId(), 2);
		} else {
			// 更新客户为公海类型
			clientService.updateClientMode(info.getClientId(), 1);
		}
	}
}
