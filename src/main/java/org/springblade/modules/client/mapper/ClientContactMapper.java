package org.springblade.modules.client.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.springblade.modules.client.entity.ClientContact;
import org.springblade.modules.client.vo.ClientContactVO;

import java.util.List;

/**
 * @author zhuyilong
 */
public interface ClientContactMapper extends BaseMapper<ClientContact> {

	/**
	 * 客户联系人详情
	 *
	 * @param id
	 * @return
	 */
	ClientContactVO getDetail(Long id);

	/**
	 * 客户联系人列表
	 *
	 * @param page
	 * @param condition
	 * @return
	 */
	List<ClientContactVO> listContact(@Param("page") IPage<ClientContactVO> page, @Param("condition") ClientContactVO condition);

	/**
	 * 通过客户联系人ID获取客户ID
	 *
	 * @param Long
	 * @return
	 */
	Long getClientByContact(@Param("contactId") Long Long);
}
