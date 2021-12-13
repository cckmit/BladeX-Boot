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
package org.springblade.modules.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springblade.modules.system.entity.XyOrgInfo;
import org.springblade.modules.system.vo.XyOrgInfoVO;
import org.springblade.modules.system.mapper.XyOrgInfoMapper;
import org.springblade.modules.system.service.IXyOrgInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 服务实现类
 *
 * @author BladeX
 * @since 2021-12-11
 */
@Service
public class XyOrgInfoServiceImpl extends ServiceImpl<XyOrgInfoMapper, XyOrgInfo> implements IXyOrgInfoService {

	@Override
	public IPage<XyOrgInfoVO> selectXyOrgInfoPage(IPage<XyOrgInfoVO> page, XyOrgInfoVO xyOrgInfo) {
		return page.setRecords(baseMapper.selectXyOrgInfoPage(page, xyOrgInfo));
	}

	@Override
	public XyOrgInfo getOrgInfo(String sapxxh) {
		return baseMapper.getOrgInfo(sapxxh);

	}

}
