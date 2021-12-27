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
import org.springblade.modules.EnterpriseResource.entity.Aptitude;
import org.springblade.modules.EnterpriseResource.vo.AptitudeVO;

import java.util.Objects;

/**
 * 企业资质表包装类,返回视图层所需的字段
 *
 * @author BladeX
 * @since 2021-09-02
 */
public class AptitudeWrapperVO extends BaseEntityWrapper<AptitudeVO, AptitudeVO> {

	public static AptitudeWrapperVO build() {
		return new AptitudeWrapperVO();
 	}

	@Override
	public AptitudeVO entityVO(AptitudeVO aptitude) {
		AptitudeVO aptitudeVO = Objects.requireNonNull(BeanUtil.copy(aptitude, AptitudeVO.class));

		//User createUser = UserCache.getUser(aptitude.getCreateUser());
		//User updateUser = UserCache.getUser(aptitude.getUpdateUser());
		//aptitudeVO.setCreateUserName(createUser.getName());
		//aptitudeVO.setUpdateUserName(updateUser.getName());
		String certificateTypeName = DictCache.getValue(DictEnum.aptitudeCertificateType,aptitude.getCertificateType());
		aptitudeVO.setCertificateTypeName(certificateTypeName);

		String provincialCompanyNames = DictCache.getValue(DictEnum.provincialCompanyName,aptitude.getCertificateType());
		aptitudeVO.setProvincialCompanyNames(provincialCompanyNames);

		String aptitudeNames = DictCache.getValue(DictEnum.aptitudeName,aptitude.getCertificateType());
		aptitudeVO.setAptitudeNames(aptitudeNames);

		String classTypeName = DictCache.getValue(DictEnum.classType,aptitude.getCertificateType());
		aptitudeVO.setClassTypeName(classTypeName);

		return aptitudeVO;
	}

}
