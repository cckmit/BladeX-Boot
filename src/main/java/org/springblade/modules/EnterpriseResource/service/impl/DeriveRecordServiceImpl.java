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
package org.springblade.modules.EnterpriseResource.service.impl;

import org.springblade.modules.EnterpriseResource.entity.DeriveRecord;
import org.springblade.modules.EnterpriseResource.vo.DeriveRecordVO;
import org.springblade.modules.EnterpriseResource.mapper.DeriveRecordMapper;
import org.springblade.modules.EnterpriseResource.service.IDeriveRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 记录表 服务实现类
 *
 * @author BladeX
 * @since 2022-01-13
 */
@Service
public class DeriveRecordServiceImpl extends ServiceImpl<DeriveRecordMapper, DeriveRecord> implements IDeriveRecordService {

	@Override
	public IPage<DeriveRecordVO> selectDeriveRecordPage(IPage<DeriveRecordVO> page, DeriveRecordVO deriveRecord) {
		return page.setRecords(baseMapper.selectDeriveRecordPage(page, deriveRecord));
	}

}
