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
import org.springblade.common.constant.CommonConstant;
import org.springblade.modules.project.entity.Business;
import org.springblade.modules.project.entity.ChangeDetail;
import org.springblade.modules.project.vo.ChangeDetailVO;
import org.springblade.modules.project.mapper.ChangeDetailMapper;
import org.springblade.modules.project.service.IChangeDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 服务实现类
 *
 * @author BladeX
 * @since 2021-07-14
 */
@Service
public class ChangeDetailServiceImpl extends ServiceImpl<ChangeDetailMapper, ChangeDetail> implements IChangeDetailService {

	@Override
	public IPage<ChangeDetailVO> selectChangeDetailPage(IPage<ChangeDetailVO> page, ChangeDetailVO changeDetail) {
		return page.setRecords(baseMapper.selectChangeDetailPage(page, changeDetail));
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean save(ChangeDetail entity) {
		try {
			return super.save(entity);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 根据修改主键，获取修改明细信息
	 * @param changeId 修改记录主键
	 * @return
	 */
	@Override
	public List<ChangeDetailVO> getChangeDetailList(long changeId) {
		return baseMapper.selectChangeDetialList(changeId);
	}
}
