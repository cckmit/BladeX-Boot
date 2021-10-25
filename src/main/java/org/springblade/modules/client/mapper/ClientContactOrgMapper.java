package org.springblade.modules.client.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springblade.modules.client.entity.ClientContactOrg;

public interface ClientContactOrgMapper extends BaseMapper<ClientContactOrg> {

	/**
	 * 更新子级所属客户ID
	 *
	 * @param pid          父级ID
	 * @param clientId
	 * @param updateUserId
	 * @return
	 */
	int updateChildClientId(@Param("pid") Long pid, @Param("clientId") Long clientId, @Param("updateUserId") Long updateUserId);
}
