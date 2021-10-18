
package org.springblade.modules.EnterpriseResource.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.modules.EnterpriseResource.entity.Biddatum;
import org.springblade.modules.EnterpriseResource.mapper.BiddatumMapper;
import org.springblade.modules.EnterpriseResource.service.IBiddatumService;
import org.springblade.modules.EnterpriseResource.vo.BiddatumVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 投标资料表 服务实现类
 *
 * @author BladeX
 * @since 2021-09-23
 */
@Service
public class BiddatumServiceImpl extends BaseServiceImpl<BiddatumMapper, Biddatum> implements IBiddatumService {

	@Override
	public IPage<BiddatumVO> selectBiddatumPage(IPage<BiddatumVO> page, BiddatumVO biddatum) {
		return page.setRecords(baseMapper.selectBiddatumPage(page, biddatum));
	}

	@Override
	public List<BiddatumVO> selectListId(Long objectId) {
		return baseMapper.selectListId(objectId);


	}

	@Override
	public void saveFile(BiddatumVO demo) {
		baseMapper.insert(demo);

	}

	@Override
	public void update(BiddatumVO demo) {

	}

}
