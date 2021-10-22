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
package org.springblade.modules.system.wrapper;

import org.springblade.common.cache.DictCache;
import org.springblade.common.enums.DictEnum;
import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.modules.system.entity.ManagerLog;
import org.springblade.modules.system.vo.ManagerLogVO;
import java.util.Objects;

/**
 * 包装类,返回视图层所需的字段
 *
 * @author BladeX
 * @since 2021-10-21
 */
public class ManagerLogWrapper extends BaseEntityWrapper<ManagerLog, ManagerLogVO>  {

	public static ManagerLogWrapper build() {
		return new ManagerLogWrapper();
 	}

	@Override
	public ManagerLogVO entityVO(ManagerLog managerLog) {
		ManagerLogVO managerLogVO = Objects.requireNonNull(BeanUtil.copy(managerLog, ManagerLogVO.class));

		//User createUser = UserCache.getUser(managerLog.getCreateUser());
		//User updateUser = UserCache.getUser(managerLog.getUpdateUser());
		//managerLogVO.setCreateUserName(createUser.getName());
		//managerLogVO.setUpdateUserName(updateUser.getName());
		String statusName = DictCache.getValue(DictEnum.YES_NO, managerLog.getWhetherUnlock());
		managerLogVO.setIsLockName(statusName);
		return managerLogVO;
	}

}
