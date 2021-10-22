
package org.springblade.modules.system.service.impl;

import org.springblade.modules.system.entity.ManagerLog;
import org.springblade.modules.system.vo.ManagerLogVO;
import org.springblade.modules.system.mapper.ManagerLogMapper;
import org.springblade.modules.system.service.IManagerLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 *  服务实现类
 *
 * @author BladeX
 * @since 2021-10-21
 */
@Service
public class ManagerLogServiceImpl extends ServiceImpl<ManagerLogMapper, ManagerLog> implements IManagerLogService {

	@Override
	public IPage<ManagerLogVO> selectManagerLogPage(IPage<ManagerLogVO> page, ManagerLogVO managerLog) {
		return page.setRecords(baseMapper.selectManagerLogPage(page, managerLog));
	}

}
