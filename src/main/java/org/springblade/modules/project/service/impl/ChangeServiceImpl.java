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
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.modules.project.entity.Change;
import org.springblade.modules.project.entity.ChangeDetail;
import org.springblade.modules.project.mapper.ChangeMapper;
import org.springblade.modules.project.service.IChangeDetailService;
import org.springblade.modules.project.service.IChangeService;
import org.springblade.modules.project.vo.ChangeVO;
import org.springframework.stereotype.Service;
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


	/**
	 * 保存修改值
	 *
	 * @param businessId
	 * @param detailList
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean saveChange(long businessId, List<ChangeDetail> detailList) {

		if (Func.isEmpty(businessId) || Func.isNull(detailList) || detailList.stream().count() == 0) {
			return false;
		}


		Change log = new Change();
		log.setBusinessId(businessId);
		log.setChangeTime(LocalDateTime.now());
		log.setChangeUser(AuthUtil.getUser().getUserId());

		long id = IdWorker.getId(log);
		log.setId(id);

		for (ChangeDetail detail : detailList) {
			detail.setChangeId(id);
		}

		return this.save(log) && detailService.saveBatch(detailList);
	}

	/**
	 * 根据商机主键，获取修改记录
	 *
	 * @param businessId 商机主键
	 * @return
	 */
	@Override
	public List<Change> getChangeList(long businessId) {
		return baseMapper.getChangeList(businessId);
	}
}
