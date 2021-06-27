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
package org.springblade.modules.client.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.modules.client.entity.BaseInfo;
import org.springblade.modules.client.vo.BaseInfoVO;
import java.util.Objects;

/**
 * 包装类,返回视图层所需的字段
 *
 * @author BladeX
 * @since 2021-06-27
 */
public class BaseInfoWrapper extends BaseEntityWrapper<BaseInfo, BaseInfoVO>  {

	public static BaseInfoWrapper build() {
		return new BaseInfoWrapper();
 	}

	@Override
	public BaseInfoVO entityVO(BaseInfo baseInfo) {
		BaseInfoVO baseInfoVO = Objects.requireNonNull(BeanUtil.copy(baseInfo, BaseInfoVO.class));

		//User createUser = UserCache.getUser(baseInfo.getCreateUser());
		//User updateUser = UserCache.getUser(baseInfo.getUpdateUser());
		//baseInfoVO.setCreateUserName(createUser.getName());
		//baseInfoVO.setUpdateUserName(updateUser.getName());

		return baseInfoVO;
	}

}
