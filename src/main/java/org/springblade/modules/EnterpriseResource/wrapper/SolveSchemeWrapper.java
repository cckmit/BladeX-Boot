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
import org.springblade.modules.EnterpriseResource.entity.SolveScheme;
import org.springblade.modules.EnterpriseResource.vo.SolveSchemeVO;
import java.util.Objects;

/**
 * 解决方案表包装类,返回视图层所需的字段
 *
 * @author BladeX
 * @since 2021-09-28
 */
public class SolveSchemeWrapper extends BaseEntityWrapper<SolveScheme, SolveSchemeVO>  {

	public static SolveSchemeWrapper build() {
		return new SolveSchemeWrapper();
 	}

	@Override
	public SolveSchemeVO entityVO(SolveScheme solveScheme) {
		SolveSchemeVO solveSchemeVO = Objects.requireNonNull(BeanUtil.copy(solveScheme, SolveSchemeVO.class));

		//User createUser = UserCache.getUser(solveScheme.getCreateUser());
		//User updateUser = UserCache.getUser(solveScheme.getUpdateUser());
		//solveSchemeVO.setCreateUserName(createUser.getName());
		//solveSchemeVO.setUpdateUserName(updateUser.getName());

		return solveSchemeVO;
	}

}
