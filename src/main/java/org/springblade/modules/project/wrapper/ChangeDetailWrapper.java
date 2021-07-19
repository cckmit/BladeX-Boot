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

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.modules.project.entity.ChangeDetail;
import org.springblade.modules.project.vo.ChangeDetailVO;
import java.util.Objects;

/**
 * 包装类,返回视图层所需的字段
 *
 * @author BladeX
 * @since 2021-07-14
 */
public class ChangeDetailWrapper extends BaseEntityWrapper<ChangeDetail, ChangeDetailVO>  {

	public static ChangeDetailWrapper build() {
		return new ChangeDetailWrapper();
 	}

	@Override
	public ChangeDetailVO entityVO(ChangeDetail changeDetail) {
		ChangeDetailVO changeDetailVO = Objects.requireNonNull(BeanUtil.copy(changeDetail, ChangeDetailVO.class));

		//User createUser = UserCache.getUser(changeDetail.getCreateUser());
		//User updateUser = UserCache.getUser(changeDetail.getUpdateUser());
		//changeDetailVO.setCreateUserName(createUser.getName());
		//changeDetailVO.setUpdateUserName(updateUser.getName());

		return changeDetailVO;
	}

}
