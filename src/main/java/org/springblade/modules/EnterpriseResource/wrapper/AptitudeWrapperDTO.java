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

import org.springblade.common.cache.DictCache;
import org.springblade.common.enums.DictEnum;
import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.modules.EnterpriseResource.dto.AptitudeDTO;
import org.springblade.modules.EnterpriseResource.entity.Aptitude;
import org.springblade.modules.EnterpriseResource.vo.AptitudeVO;

import java.util.Objects;

/**
 * 企业资质表包装类,返回视图层所需的字段
 *
 * @author BladeX
 * @since 2021-09-02
 */
public class AptitudeWrapperDTO extends BaseEntityWrapper<Aptitude, AptitudeDTO> {

	public static AptitudeWrapperDTO build() {
		return new AptitudeWrapperDTO();
 	}

	@Override
	public AptitudeDTO entityVO(Aptitude aptitude) {
		AptitudeDTO aptitudeDTO = Objects.requireNonNull(BeanUtil.copy(aptitude, AptitudeDTO.class));

		//User createUser = UserCache.getUser(aptitude.getCreateUser());
		//User updateUser = UserCache.getUser(aptitude.getUpdateUser());
		//aptitudeVO.setCreateUserName(createUser.getName());
		//aptitudeVO.setUpdateUserName(updateUser.getName());

		return aptitudeDTO;
	}

}
