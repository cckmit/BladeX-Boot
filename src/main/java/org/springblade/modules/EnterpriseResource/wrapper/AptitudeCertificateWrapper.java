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
import org.springblade.modules.EnterpriseResource.entity.AptitudeCertificate;
import org.springblade.modules.EnterpriseResource.vo.AptitudeCertificateVO;
import java.util.Objects;

/**
 * 企业资质证书类别表包装类,返回视图层所需的字段
 *
 * @author BladeX
 * @since 2022-01-17
 */
public class AptitudeCertificateWrapper extends BaseEntityWrapper<AptitudeCertificate, AptitudeCertificateVO>  {

	public static AptitudeCertificateWrapper build() {
		return new AptitudeCertificateWrapper();
 	}

	@Override
	public AptitudeCertificateVO entityVO(AptitudeCertificate aptitudeCertificate) {
		AptitudeCertificateVO aptitudeCertificateVO = Objects.requireNonNull(BeanUtil.copy(aptitudeCertificate, AptitudeCertificateVO.class));

		//User createUser = UserCache.getUser(aptitudeCertificate.getCreateUser());
		//User updateUser = UserCache.getUser(aptitudeCertificate.getUpdateUser());
		//aptitudeCertificateVO.setCreateUserName(createUser.getName());
		//aptitudeCertificateVO.setUpdateUserName(updateUser.getName());

		return aptitudeCertificateVO;
	}

}
