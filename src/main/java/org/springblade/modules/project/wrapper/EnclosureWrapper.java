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
import org.springblade.modules.project.entity.Enclosure;
import org.springblade.modules.project.vo.EnclosureVO;
import java.util.Objects;

/**
 * 包装类,返回视图层所需的字段
 *
 * @author BladeX
 * @since 2021-08-07
 */
public class EnclosureWrapper extends BaseEntityWrapper<Enclosure, EnclosureVO>  {

	public static EnclosureWrapper build() {
		return new EnclosureWrapper();
 	}

	@Override
	public EnclosureVO entityVO(Enclosure enclosure) {
		EnclosureVO enclosureVO = Objects.requireNonNull(BeanUtil.copy(enclosure, EnclosureVO.class));

		//User createUser = UserCache.getUser(enclosure.getCreateUser());
		//User updateUser = UserCache.getUser(enclosure.getUpdateUser());
		//enclosureVO.setCreateUserName(createUser.getName());
		//enclosureVO.setUpdateUserName(updateUser.getName());

		return enclosureVO;
	}

}
