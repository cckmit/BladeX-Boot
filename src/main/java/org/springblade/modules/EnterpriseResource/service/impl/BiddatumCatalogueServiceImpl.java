/*
 *      Copyright (c) 2018-2028, Chill Zhuang All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the dreamlu.net developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: Chill 庄骞 (smallchill@163.com)
 */
package org.springblade.modules.EnterpriseResource.service.impl;

import org.springblade.modules.EnterpriseResource.entity.BiddatumCatalogue;
import org.springblade.modules.EnterpriseResource.vo.BiddatumCatalogueVO;
import org.springblade.modules.EnterpriseResource.mapper.BiddatumCatalogueMapper;
import org.springblade.modules.EnterpriseResource.service.IBiddatumCatalogueService;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 投标资料目录表 服务实现类
 *
 * @author BladeX
 * @since 2021-10-18
 */
@Service
public class BiddatumCatalogueServiceImpl extends BaseServiceImpl<BiddatumCatalogueMapper, BiddatumCatalogue> implements IBiddatumCatalogueService {

	@Override
	public IPage<BiddatumCatalogueVO> selectBiddatumCataloguePage(IPage<BiddatumCatalogueVO> page, BiddatumCatalogueVO biddatumCatalogue) {
		return page.setRecords(baseMapper.selectBiddatumCataloguePage(page, biddatumCatalogue));
	}

	@Override
	public List<BiddatumCatalogueVO> selectCatalog() {
		return baseMapper.selectCatalog();
	}

}
