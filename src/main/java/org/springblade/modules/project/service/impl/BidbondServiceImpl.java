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

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.project.entity.Bidbond;
import org.springblade.modules.project.mapper.BidbondMapper;
import org.springblade.modules.project.service.IBidbondService;
import org.springblade.modules.project.vo.BidbondVO;
import org.springframework.stereotype.Service;

/**
 *  服务实现类
 *
 * @author BladeX
 * @since 2021-09-01
 */
@Service
public class BidbondServiceImpl extends ServiceImpl<BidbondMapper, Bidbond> implements IBidbondService {

	@Override
	public IPage<BidbondVO> selectBidbondPage(IPage<BidbondVO> page, BidbondVO bidbond) {
		return page.setRecords(baseMapper.selectBidbondPage(page, bidbond));
	}

	@Override
	public IPage<BidbondVO> selectBondList(IPage<BidbondVO> page, BidbondVO bidbond){
		return page.setRecords(baseMapper.selectBondList(page, bidbond));
	}
	@Override
	public boolean BondCovery(String id){
		Bidbond bidbond = this.getById(id);
		if(bidbond.getBondStatus()!=50){
			throw new ServiceException("当前状态不可发起保证金回收流程");
		}
		bidbond.setBondStatus(52);
		this.saveOrUpdate(bidbond);
		return true;
	}
}
