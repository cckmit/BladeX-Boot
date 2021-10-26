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

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.common.cache.DictCache;
import org.springblade.common.enums.DictEnum;
import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.modules.system.entity.Manager;
import org.springblade.modules.system.vo.ManagerVO;
import java.util.Objects;

/**
 * 包装类,返回视图层所需的字段
 *
 * @author BladeX
 * @since 2021-06-30
 */
public class ManagerWrapper extends BaseEntityWrapper<Manager, ManagerVO>  {

	public static ManagerWrapper build() {
		return new ManagerWrapper();
 	}

	@Override
	public ManagerVO entityVO(Manager manager) {
		ManagerVO managerVO = Objects.requireNonNull(BeanUtil.copy(manager, ManagerVO.class));

		//User createUser = UserCache.getUser(manager.getCreateUser());
		//User updateUser = UserCache.getUser(manager.getUpdateUser());
		//managerVO.setCreateUserName(createUser.getName());
		//managerVO.setUpdateUserName(updateUser.getName());
		String statusName = DictCache.getValue(DictEnum.YES_NO, manager.getIsLock());
		managerVO.setIsLockName(statusName);
		String isConstructorName = DictCache.getValue(DictEnum.YES_NO, manager.getIsConstructor());
		managerVO.setIsConstructorName(isConstructorName);
		return managerVO;
	}


}
