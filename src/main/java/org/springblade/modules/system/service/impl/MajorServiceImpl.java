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

import org.springblade.modules.system.entity.Major;
import org.springblade.modules.system.vo.MajorVO;
import org.springblade.modules.system.mapper.MajorMapper;
import org.springblade.modules.system.service.IMajorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;
import java.util.Map;

/**
 *  服务实现类
 *
 * @author BladeX
 * @since 2021-07-08
 */
@Service
public class MajorServiceImpl extends ServiceImpl<MajorMapper, Major> implements IMajorService {

	@Override
	public IPage<MajorVO> selectMajorPage(IPage<MajorVO> page, MajorVO major) {
		return page.setRecords(baseMapper.selectMajorPage(page, major));
	}

	@Override
	public List<MajorVO> lazyTree(String parentCode, Map<String, Object> param) {
		return baseMapper.lazyTree(parentCode,param);
	}
}
