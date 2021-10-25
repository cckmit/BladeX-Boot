package org.springblade.modules.client.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springblade.modules.client.entity.ClientAssign;
import org.springblade.modules.client.vo.ClientAssignVO;

import java.util.List;

/**
 * @author zhuyilong
 */
public interface ClientAssignMapper extends BaseMapper<ClientAssign> {

	/**
	 * 客户分配列表
	 *
	 * @param condition
	 * @return
	 */
	List<ClientAssignVO> listClientAssign(@Param("condition") ClientAssignVO condition);

	/**
	 * 客户分配信息删除
	 *
	 * @param condition
	 * @return
	 */
	int delClientAssign(@Param("condition") ClientAssignVO condition);
}
