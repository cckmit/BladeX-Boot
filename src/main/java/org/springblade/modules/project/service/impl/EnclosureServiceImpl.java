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

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springblade.modules.project.entity.Bid;
import org.springblade.modules.project.entity.Enclosure;
import org.springblade.modules.project.vo.EnclosureVO;
import org.springblade.modules.project.mapper.EnclosureMapper;
import org.springblade.modules.project.service.IEnclosureService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 *  服务实现类
 *
 * @author BladeX
 * @since 2021-08-07
 */
@Service
public class EnclosureServiceImpl extends ServiceImpl<EnclosureMapper, Enclosure> implements IEnclosureService {

	@Override
	public IPage<EnclosureVO> selectEnclosurePage(IPage<EnclosureVO> page, EnclosureVO enclosure) {
		return page.setRecords(baseMapper.selectEnclosurePage(page, enclosure));
	}

	/**
	 * 根据投标主键，获取相关附件信息
	 * @param bidId
	 * @return
	 */
	@Override
	public List<Enclosure> getEnclosurList(long bidId) {
		LambdaQueryWrapper<Enclosure> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(Enclosure::getBidId, bidId);

		return baseMapper.selectList(queryWrapper);
	}
}
