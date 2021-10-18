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

import org.springblade.common.enums.RescoreEnum;
import org.springblade.modules.EnterpriseResource.entity.AllFile;
import org.springblade.modules.EnterpriseResource.entity.SolveScheme;
import org.springblade.modules.EnterpriseResource.service.IFileService;
import org.springblade.modules.EnterpriseResource.vo.SolveSchemeDemo;
import org.springblade.modules.EnterpriseResource.vo.SolveSchemeVO;
import org.springblade.modules.EnterpriseResource.mapper.SolveSchemeMapper;
import org.springblade.modules.EnterpriseResource.service.ISolveSchemeService;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 解决方案表 服务实现类
 *
 * @author BladeX
 * @since 2021-09-28
 */
@Service
public class SolveSchemeServiceImpl extends BaseServiceImpl<SolveSchemeMapper, SolveScheme> implements ISolveSchemeService {


	@Autowired
	private IFileService fileService;

	@Override
	public IPage<SolveSchemeVO> selectSolveSchemePage(IPage<SolveSchemeVO> page, SolveSchemeVO solveScheme) {
		return page.setRecords(baseMapper.selectSolveSchemePage(page, solveScheme));
	}

	@Override
	public List<SolveSchemeVO> selectListId(Long objectId) {
		return baseMapper.selectListId(objectId);
	}

	@Override
	public boolean saveFile(SolveSchemeDemo demo) {
	baseMapper.insert(demo.getSolveScheme());
	Long A = demo.getSolveScheme().getId();
	for (AllFile temp:demo.getList()){
		temp.setObjectId(A);
		temp.setObjectValue(RescoreEnum.RESCORE_SOLVECHEM.getValue());
		fileService.save(temp);
		}
	return  true;
	}

	@Override
	public void update(SolveSchemeDemo demo) {
		baseMapper.updateById(demo.getSolveScheme());
		for (AllFile temp:demo.getList()){
			fileService.updateById(temp);
		}

	}

}
