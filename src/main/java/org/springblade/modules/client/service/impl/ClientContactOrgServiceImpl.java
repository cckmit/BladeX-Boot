package org.springblade.modules.client.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springblade.common.utils.SnowflakeIdUtil;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.utils.SecureUtil;
import org.springblade.core.tool.utils.NumberUtil;
import org.springblade.modules.client.entity.BaseInfo;
import org.springblade.modules.client.entity.ClientContactOrg;
import org.springblade.modules.client.mapper.ClientContactOrgMapper;
import org.springblade.modules.client.service.ClientContactOrgService;
import org.springblade.modules.client.service.IBaseInfoService;
import org.springblade.modules.client.vo.ClientContactOrgVO;
import org.springblade.modules.client.wrapper.ClientContactOrgWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zhuyilong
 */
@Service
@RequiredArgsConstructor
public class ClientContactOrgServiceImpl extends ServiceImpl<ClientContactOrgMapper, ClientContactOrg> implements ClientContactOrgService {

	private final IBaseInfoService baseInfoService;

	@Override
	public List<ClientContactOrgVO> getOrgTreeList(ClientContactOrgVO condition) {
		QueryWrapper<ClientContactOrg> query = Condition.getQueryWrapper(condition);
		query.orderByAsc("sort");
		List<ClientContactOrg> list = list(query);
		List<ClientContactOrgVO> treeList = toTree(ClientContactOrgWrapper.build().listVO(list));
		// 包装到客户信息下
		Map<Long, List<ClientContactOrgVO>> clientTreeMap = treeList.stream().collect(Collectors.groupingBy(ClientContactOrg::getClientId));
		return clientTreeMap.keySet().stream().map(clientId -> {
			BaseInfo clientInfo = baseInfoService.getById(clientId);
			ClientContactOrgVO clientNode = new ClientContactOrgVO();
			if (clientInfo != null) {
				clientNode.setId(clientInfo.getId());
				clientNode.setName(clientInfo.getFullname());
			} else {
				clientNode.setId(1L);
				clientNode.setName("无归属客户");
			}
			clientNode.setClientId(clientId);
			clientNode.setPids("-1");
			clientNode.setRank(-1);
			clientNode.setChildren(clientTreeMap.get(clientId));
			return clientNode;
		}).collect(Collectors.toList());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ClientContactOrgVO saveOrUpdateOrg(ClientContactOrgVO entity) {
		BladeUser user = SecureUtil.getUser();
		if (entity.getId() == null) {
			// 新增
			entity.setId(SnowflakeIdUtil.getSnowflakeId());
			// 创建人信息
			entity.setCreateUser(user.getUserId());
			entity.setTenantId(user.getTenantId());
			entity.setCreateDept(NumberUtil.toLong(user.getDeptId()));
		} else {
			// 更新
			entity.setUpdateUser(user.getUserId());
			entity.setUpdateTime(new Date());
		}
		// 填充 pids 与 rank 、clientId
		setAdditionalInfo(entity);
		if (entity.getRank() == 0) {
			// 更新子级 clientId
			baseMapper.updateChildClientId(entity.getId(), entity.getClientId(), SecureUtil.getUserId());
		}
		saveOrUpdate(entity);
		return entity;
	}


	/**
	 * 填充附加信息 pids rank clientId
	 *
	 * @param entity
	 */
	private void setAdditionalInfo(ClientContactOrg entity) {
		Long pid = entity.getPid();
		// 顶级
		if (pid == null || pid == 0) {
			entity.setPids(entity.getId().toString());
			entity.setPid(0L);
			// 顶级 rank 为 0
			entity.setRank(0);
		} else {
			ClientContactOrg parentNode = getById(entity.getPid());
			entity.setPids(parentNode.getPids() + "," + entity.getId().toString());
			entity.setRank(parentNode.getRank() + 1);
			// 继承父级 clientId
			entity.setClientId(parentNode.getClientId());
		}
	}

	/**
	 * 形成树结构
	 *
	 * @param list 原生列表
	 * @return 列表树
	 */
	private List<ClientContactOrgVO> toTree(List<ClientContactOrgVO> list) {
		// pid -> list
		Map<Long, List<ClientContactOrgVO>> listMap = list.stream().collect(Collectors.groupingBy(ClientContactOrgVO::getPid));
		List<ClientContactOrgVO> root = listMap.get(0L);
		// 遍历填充子级
		list.forEach(i -> {
			// 子级
			List<ClientContactOrgVO> children = listMap.get(i.getId());
			i.setChildren(children);
		});
		return root;
	}
}
