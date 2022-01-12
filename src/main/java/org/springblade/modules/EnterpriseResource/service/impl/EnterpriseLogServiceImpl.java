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

import org.springblade.modules.EnterpriseResource.entity.EnterpriseLog;
import org.springblade.modules.EnterpriseResource.vo.EnterpriseLogVO;
import org.springblade.modules.EnterpriseResource.mapper.EnterpriseLogMapper;
import org.springblade.modules.EnterpriseResource.service.IEnterpriseLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 日志表 服务实现类
 *
 * @author BladeX
 * @since 2022-01-06
 */
@Service
public class EnterpriseLogServiceImpl extends ServiceImpl<EnterpriseLogMapper, EnterpriseLog> implements IEnterpriseLogService {

	@Override
	public IPage<EnterpriseLogVO> selectEnterpriseLogPage(IPage<EnterpriseLogVO> page, EnterpriseLogVO enterpriseLog) {
		return page.setRecords(baseMapper.selectEnterpriseLogPage(page, enterpriseLog));
	}

}
