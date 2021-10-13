package org.springblade.modules.client.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springblade.common.cache.UserCache;
import org.springblade.core.secure.utils.SecureUtil;
import org.springblade.modules.client.entity.ClientContact;
import org.springblade.modules.client.mapper.ClientContactMapper;
import org.springblade.modules.client.service.ClientContactService;
import org.springblade.modules.client.vo.ClientContactVO;
import org.springblade.modules.client.wrapper.ClientContactWrapper;
import org.springblade.modules.system.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhuyilong
 */
@Service
@RequiredArgsConstructor
public class ClientContactServiceImpl extends ServiceImpl<ClientContactMapper, ClientContact> implements ClientContactService {

	@Override
	public IPage<ClientContactVO> pageContact(IPage<ClientContactVO> page, ClientContactVO condition) {
		List<ClientContactVO> records = baseMapper.listContact(page, condition);
		page.setRecords(records);
		return page;
	}

	@Override
	public ClientContactVO getDetail(ClientContactVO condition) {
		ClientContactVO detail = ClientContactWrapper.build().entityVO(getById(condition.getId()));
		// 填充创建人信息
		User user = UserCache.getUser(detail.getCreateUser());
		if (user != null) {
			detail.setCreateUserName(user.getRealName());
		}
		return detail;
	}

	@Override
	public boolean saveContact(ClientContact entity) {
		// 记录创建人
		// entity.setCreateUser(SecureUtil.getUserId());
		// entity.setTenantId(SecureUtil.getTenantId());
		// entity.setCreateDept(NumberUtil.toLong(SecureUtil.getDeptId()));
		return save(entity);
	}

	@Override
	public boolean updateContact(ClientContact entity) {
		// 记录更新人
		entity.setUpdateUser(SecureUtil.getUserId());
		return updateById(entity);
	}

	@Override
	public boolean batchDelContact(List<Long> ids) {
		return removeByIds(ids);
	}
}
