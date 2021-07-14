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
package org.springblade.modules.project.service.impl;

import org.springblade.modules.project.entity.Clash;
import org.springblade.modules.project.vo.ClashVO;
import org.springblade.modules.project.mapper.ClashMapper;
import org.springblade.modules.project.service.IClashService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 *  服务实现类
 *
 * @author BladeX
 * @since 2021-07-13
 */
@Service
public class ClashServiceImpl extends ServiceImpl<ClashMapper, Clash> implements IClashService {

	@Override
	public IPage<ClashVO> selectClashPage(IPage<ClashVO> page, ClashVO clash) {
		return page.setRecords(baseMapper.selectClashPage(page, clash));
	}

}
