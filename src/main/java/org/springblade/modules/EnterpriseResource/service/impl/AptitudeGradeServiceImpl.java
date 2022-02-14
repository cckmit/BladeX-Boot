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

import org.springblade.modules.EnterpriseResource.entity.AptitudeGrade;
import org.springblade.modules.EnterpriseResource.vo.AptitudeGradeVO;
import org.springblade.modules.EnterpriseResource.mapper.AptitudeGradeMapper;
import org.springblade.modules.EnterpriseResource.service.IAptitudeGradeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 企业资质等级表 服务实现类
 *
 * @author BladeX
 * @since 2022-01-17
 */
@Service
public class AptitudeGradeServiceImpl extends ServiceImpl<AptitudeGradeMapper, AptitudeGrade> implements IAptitudeGradeService {

	@Override
	public IPage<AptitudeGradeVO> selectAptitudeGradePage(IPage<AptitudeGradeVO> page, AptitudeGradeVO aptitudeGrade) {
		return page.setRecords(baseMapper.selectAptitudeGradePage(page, aptitudeGrade));
	}

	@Override
	public AptitudeGrade selectName(String name) {
		return baseMapper.selectName(name);
	}

	@Override
	public AptitudeGrade selectId(Long id) {
		return baseMapper.selectId(id);
	}

}
