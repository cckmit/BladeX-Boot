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
import org.springblade.modules.EnterpriseResource.entity.Invoice;
import org.springblade.modules.EnterpriseResource.service.IFileService;
import org.springblade.modules.EnterpriseResource.vo.InvoiceDemo;
import org.springblade.modules.EnterpriseResource.vo.InvoiceVO;
import org.springblade.modules.EnterpriseResource.mapper.InvoiceMapper;
import org.springblade.modules.EnterpriseResource.service.IInvoiceService;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 发票表 服务实现类
 *
 * @author BladeX
 * @since 2021-09-28
 */
@Service
public class InvoiceServiceImpl extends BaseServiceImpl<InvoiceMapper, Invoice> implements IInvoiceService {

	@Autowired
	private IFileService fileService;

	@Override
	public IPage<InvoiceVO> selectInvoicePage(IPage<InvoiceVO> page, InvoiceVO invoice) {
		return page.setRecords(baseMapper.selectInvoicePage(page, invoice));
	}

	@Override
	public List<InvoiceVO> selectListId(Long objectId) {
		return baseMapper.selectListId(objectId);
	}

	@Override
	public Boolean saveFile(InvoiceDemo demo) {
		baseMapper.insert(demo.getInvoice());
		Long A = demo.getInvoice().getId();
		for(AllFile tmp:demo.getList()){
			tmp.setObjectId(A);
			tmp.setObjectValue(RescoreEnum.RESCORE_INVOICE.getValue());
			fileService.save(tmp);
		}
	return  true;
	}


	@Override
	public void update(InvoiceDemo demo) {
	baseMapper.updateById(demo.getInvoice());
		for (AllFile tmp : demo.getList()) {
			fileService.updateById(tmp);
		}

	}

}
