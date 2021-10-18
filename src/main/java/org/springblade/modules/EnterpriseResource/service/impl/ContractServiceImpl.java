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
import org.springblade.modules.EnterpriseResource.entity.Contract;
import org.springblade.modules.EnterpriseResource.service.IFileService;
import org.springblade.modules.EnterpriseResource.vo.ContractDemo;
import org.springblade.modules.EnterpriseResource.vo.ContractVO;
import org.springblade.modules.EnterpriseResource.mapper.ContractMapper;
import org.springblade.modules.EnterpriseResource.service.IContractService;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 合同表 服务实现类
 *
 * @author BladeX
 * @since 2021-09-28
 */
@Service
public class ContractServiceImpl extends BaseServiceImpl<ContractMapper, Contract> implements IContractService {

	@Autowired
	private IFileService fileService;

	@Override
	public IPage<ContractVO> selectContractPage(IPage<ContractVO> page, ContractVO contract) {
		return page.setRecords(baseMapper.selectContractPage(page, contract));
	}

	@Override
	public List<ContractVO> selectListId(Long objectId) {
		return baseMapper.selectListId(objectId);
	}

	@Override
	public Boolean saveFile(ContractDemo demo) {
		baseMapper.insert(demo.getContract());
		Long A = demo.getContract().getId();
		for(AllFile tmp:demo.getList()){
			tmp.setObjectId(A);
			tmp.setObjectValue(RescoreEnum.RESCORE_CONTRACT.getValue());
			fileService.save(tmp);
		}
 	return  true;
	}

	@Override
	public void update(ContractDemo demo) {
		baseMapper.updateById(demo.getContract());
		for (AllFile tmp : demo.getList()) {
			fileService.updateById(tmp);
		}

	}

}
