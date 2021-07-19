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

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import lombok.AllArgsConstructor;
import org.springblade.modules.project.entity.Change;
import org.springblade.modules.project.entity.ChangeDetail;
import org.springblade.modules.project.service.IChangeDetailService;
import org.springblade.modules.project.vo.ChangeVO;
import org.springblade.modules.project.mapper.ChangeMapper;
import org.springblade.modules.project.service.IChangeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 服务实现类
 *
 * @author BladeX
 * @since 2021-07-14
 */
@Service
@AllArgsConstructor

public class ChangeServiceImpl extends ServiceImpl<ChangeMapper, Change> implements IChangeService {

	private final IChangeDetailService detailService;

	@Override
	public IPage<ChangeVO> selectChangePage(IPage<ChangeVO> page, ChangeVO change) {
		return page.setRecords(baseMapper.selectChangePage(page, change));
	}


	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean saveChange(Change log, List<ChangeDetail> detailList) {

		log.setChangeTime(LocalDateTime.now());
		long id = IdWorker.getId(log);
		log.setId(id);

		for (ChangeDetail detail : detailList) {
			detail.setChangeId(log.getId());
		}

		return this.save(log) && detailService.saveBatch(detailList);
	}
}
