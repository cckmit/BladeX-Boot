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
package org.springblade.modules.client.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.secure.utils.SecureUtil;
import org.springblade.modules.client.entity.BaseInfo;
import org.springblade.modules.client.mapper.BaseInfoMapper;
import org.springblade.modules.client.service.IBaseInfoService;
import org.springblade.modules.client.vo.BaseInfoVO;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 *  服务实现类
 *
 * @author BladeX
 * @since 2021-06-26
 */
@Service
public class BaseInfoServiceImpl extends BaseServiceImpl<BaseInfoMapper, BaseInfo> implements IBaseInfoService {

	@Override
	public IPage<BaseInfoVO> selectBaseInfoPage(IPage<BaseInfoVO> page, BaseInfoVO baseInfo) {
		return page.setRecords(baseMapper.selectBaseInfoPage(page, baseInfo));
	}

	@Override
	public boolean updateClientMode(Long clientId, Integer mode) {
		BaseInfo update = new BaseInfo();
		update.setMode(mode);
		update.setId(clientId);
		update.setUpdateTime(new Date());
		update.setUpdateUser(SecureUtil.getUserId());
		return updateById(update);
	}

	@Override
	public IPage<BaseInfoVO> pageClientInfo(IPage<BaseInfoVO> page, BaseInfoVO condition) {
		List<BaseInfoVO> list = baseMapper.listClientInfo(page, condition);
		page.setRecords(list);
		return page;
	}

	@Override
	public IPage<BaseInfo> pageClientInfoPub(IPage<BaseInfo> page, BaseInfoVO condition) {
		List<BaseInfo> list = baseMapper.listClientInfoPub(page, condition);
		page.setRecords(list);
		return page;
	}
}
