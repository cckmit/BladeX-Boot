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

import org.springblade.modules.system.entity.Manager;
import org.springblade.modules.system.vo.ManagerVO;
import org.springblade.modules.system.mapper.ManagerMapper;
import org.springblade.modules.system.service.IManagerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 *  服务实现类
 *
 * @author BladeX
 * @since 2021-06-30
 */
@Service
public class ManagerServiceImpl extends ServiceImpl<ManagerMapper, Manager> implements IManagerService {

	@Override
	public IPage<ManagerVO> selectManagerPage(IPage<ManagerVO> page, ManagerVO manager) {
		return page.setRecords(baseMapper.selectManagerPage(page, manager));
	}

	@Override
	public  IPage<ManagerVO> selectManagerVOPage(IPage<ManagerVO> page, ManagerVO manager){
		return  page.setRecords(baseMapper.selectManagerVOPage(page,manager));
	}
/****************************************************************************************************************/

	@Override
	public Manager selectManagerDetail(Long id) {
		return baseMapper.selectManagerDetail(id);
}

	@Override
	public IPage<ManagerVO> selectProjectBusiness(IPage<ManagerVO> page, Long id) {
		return page.setRecords(baseMapper.selectProjectBusiness(page,id));
	}


	@Override
	public IPage<Manager> selectManagerList(IPage page, ManagerVO manager) {
		return page.setRecords(baseMapper.selectManagerList(page, manager));
	}

}
