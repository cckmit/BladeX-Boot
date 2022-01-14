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
import org.springblade.modules.EnterpriseResource.entity.DeriveRecord;
import org.springblade.modules.EnterpriseResource.vo.DeriveRecordVO;
import java.util.Objects;

/**
 * 记录表包装类,返回视图层所需的字段
 *
 * @author BladeX
 * @since 2022-01-13
 */
public class DeriveRecordWrapper extends BaseEntityWrapper<DeriveRecord, DeriveRecordVO>  {

	public static DeriveRecordWrapper build() {
		return new DeriveRecordWrapper();
 	}

	@Override
	public DeriveRecordVO entityVO(DeriveRecord deriveRecord) {
		DeriveRecordVO deriveRecordVO = Objects.requireNonNull(BeanUtil.copy(deriveRecord, DeriveRecordVO.class));

		//User createUser = UserCache.getUser(deriveRecord.getCreateUser());
		//User updateUser = UserCache.getUser(deriveRecord.getUpdateUser());
		//deriveRecordVO.setCreateUserName(createUser.getName());
		//deriveRecordVO.setUpdateUserName(updateUser.getName());

		return deriveRecordVO;
	}

}
