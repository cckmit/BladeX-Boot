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
package org.springblade.modules.project.wrapper;

import org.springblade.common.cache.DictCache;
import org.springblade.common.enums.DictEnum;
import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.modules.project.entity.Business;
import org.springblade.modules.project.vo.BusinessVO;
import java.util.Objects;

/**
 * 包装类,返回视图层所需的字段
 *
 * @author BladeX
 * @since 2021-07-03
 */
public class BusinessWrapper extends BaseEntityWrapper<Business, BusinessVO>  {

	public static BusinessWrapper build() {
		return new BusinessWrapper();
 	}

	@Override
	public BusinessVO entityVO(Business business) {
		BusinessVO businessVO = Objects.requireNonNull(BeanUtil.copy(business, BusinessVO.class));

		//User createUser = UserCache.getUser(business.getCreateUser());
		//User updateUser = UserCache.getUser(business.getUpdateUser());
		//businessVO.setCreateUserName(createUser.getName());
		//businessVO.setUpdateUserName(updateUser.getName());
		String businessStatusName = DictCache.getValue(DictEnum.businessmobileStatus,business.getRecordStatus());
		String projectBiddingTypeName = DictCache.getValue(DictEnum.project_BiddingType,business.getBiddingType());
		businessVO.setProjectBiddingTypeName(projectBiddingTypeName);
		businessVO.setBusinessStatusName(businessStatusName);
		return businessVO;
	}

}
