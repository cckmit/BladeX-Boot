package org.springblade.modules.client.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.springblade.modules.client.entity.ClientContactOrg;
import org.springblade.modules.client.vo.ClientContactOrgVO;

import java.util.List;

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

	/**
	 * 组织联系人列表
	 *
	 * @param page
	 * @param condition
	 * @return
	 */
	List<ClientContactOrgVO> listOrgClient(@Param("page") IPage<ClientContactOrgVO> page, @Param("condition") ClientContactOrgVO condition);
}
