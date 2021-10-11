package org.springblade.modules.client.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springblade.modules.client.entity.ClientContact;
import org.springblade.modules.client.vo.ClientContactVO;

/**
 * @author zhuyilong
 */
public interface ClientContactMapper extends BaseMapper<ClientContact> {

	ClientContactVO getDetail(Long id);

}
