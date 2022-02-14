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

import org.springblade.modules.EnterpriseResource.entity.AptitudeCertificate;
import org.springblade.modules.EnterpriseResource.vo.AptitudeCertificateVO;
import org.springblade.modules.EnterpriseResource.mapper.AptitudeCertificateMapper;
import org.springblade.modules.EnterpriseResource.service.IAptitudeCertificateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 企业资质证书类别表 服务实现类
 *
 * @author BladeX
 * @since 2022-01-17
 */
@Service
public class AptitudeCertificateServiceImpl extends ServiceImpl<AptitudeCertificateMapper, AptitudeCertificate> implements IAptitudeCertificateService {

	@Override
	public IPage<AptitudeCertificateVO> selectAptitudeCertificatePage(IPage<AptitudeCertificateVO> page, AptitudeCertificateVO aptitudeCertificate) {
		return page.setRecords(baseMapper.selectAptitudeCertificatePage(page, aptitudeCertificate));
	}

	@Override
	public AptitudeCertificate selectName(String name) {
		return baseMapper.selectName(name);
	}

	@Override
	public AptitudeCertificate selectId1(Long id) {
		return baseMapper.selectId1(id);
	}

}
