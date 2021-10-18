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
package org.springblade.modules.EnterpriseResource.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.modules.EnterpriseResource.entity.Invoice;
import org.springblade.modules.EnterpriseResource.vo.InvoiceVO;
import java.util.Objects;

/**
 * 发票表包装类,返回视图层所需的字段
 *
 * @author BladeX
 * @since 2021-09-28
 */
public class InvoiceWrapper extends BaseEntityWrapper<Invoice, InvoiceVO>  {

	public static InvoiceWrapper build() {
		return new InvoiceWrapper();
 	}

	@Override
	public InvoiceVO entityVO(Invoice invoice) {
		InvoiceVO invoiceVO = Objects.requireNonNull(BeanUtil.copy(invoice, InvoiceVO.class));

		//User createUser = UserCache.getUser(invoice.getCreateUser());
		//User updateUser = UserCache.getUser(invoice.getUpdateUser());
		//invoiceVO.setCreateUserName(createUser.getName());
		//invoiceVO.setUpdateUserName(updateUser.getName());

		return invoiceVO;
	}

}
