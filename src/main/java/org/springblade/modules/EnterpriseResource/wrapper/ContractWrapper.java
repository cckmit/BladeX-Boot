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
import org.springblade.modules.EnterpriseResource.entity.Contract;
import org.springblade.modules.EnterpriseResource.vo.ContractVO;
import java.util.Objects;

/**
 * 合同表包装类,返回视图层所需的字段
 *
 * @author BladeX
 * @since 2021-09-28
 */
public class ContractWrapper extends BaseEntityWrapper<Contract, ContractVO>  {

	public static ContractWrapper build() {
		return new ContractWrapper();
 	}

	@Override
	public ContractVO entityVO(Contract contract) {
		ContractVO contractVO = Objects.requireNonNull(BeanUtil.copy(contract, ContractVO.class));

		//User createUser = UserCache.getUser(contract.getCreateUser());
		//User updateUser = UserCache.getUser(contract.getUpdateUser());
		//contractVO.setCreateUserName(createUser.getName());
		//contractVO.setUpdateUserName(updateUser.getName());

		return contractVO;
	}

}
