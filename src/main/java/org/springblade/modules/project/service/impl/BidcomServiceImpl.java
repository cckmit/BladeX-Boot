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

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.modules.project.entity.Bidcom;
import org.springblade.modules.project.mapper.BidcomMapper;
import org.springblade.modules.project.service.IBidcomService;
import org.springblade.modules.project.vo.BidcomVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  服务实现类
 *
 * @author BladeX
 * @since 2021-09-16
 */
@Service
public class BidcomServiceImpl extends ServiceImpl<BidcomMapper, Bidcom> implements IBidcomService {

	@Override
	public List<BidcomVO> selectBidcomList(Long bidid){
		return baseMapper.selectBidcomList(bidid);
	}

}
