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
package org.springblade.modules.auth.granter;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.qcloud.cos.utils.Md5Utils;
import lombok.AllArgsConstructor;
import org.springblade.common.cache.CacheNames;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.redis.cache.BladeRedis;
import org.springblade.core.tool.utils.DigestUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.core.tool.utils.StringUtil;
import org.springblade.core.tool.utils.WebUtil;
import org.springblade.modules.auth.enums.UserEnum;
import org.springblade.modules.auth.provider.ITokenGranter;
import org.springblade.modules.auth.provider.TokenParameter;
import org.springblade.modules.auth.utils.LDAPAuthentication;
import org.springblade.modules.auth.utils.TokenUtil;
import org.springblade.modules.system.entity.Tenant;
import org.springblade.modules.system.entity.User;
import org.springblade.modules.system.entity.UserInfo;
import org.springblade.modules.system.service.ITenantService;
import org.springblade.modules.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 验证码TokenGranter
 *
 * @author Chill
 */
@Component
@AllArgsConstructor
public class CaptchaTokenGranter implements ITokenGranter {

	public static final String GRANT_TYPE = "captcha";

	private final IUserService userService;
	private final ITenantService tenantService;
	private final BladeRedis bladeRedis;

	@Autowired
	LDAPAuthentication ldap ;

	@Override
	public UserInfo grant(TokenParameter tokenParameter) {
		HttpServletRequest request = WebUtil.getRequest();

		String key = request.getHeader(TokenUtil.CAPTCHA_HEADER_KEY);
		String code = request.getHeader(TokenUtil.CAPTCHA_HEADER_CODE);
		// 获取验证码
		String redisCode = bladeRedis.get(CacheNames.CAPTCHA_KEY + key);
		// 判断验证码
		if (code == null || !StringUtil.equalsIgnoreCase(redisCode, code)) {
			throw new ServiceException(TokenUtil.CAPTCHA_NOT_CORRECT);
		}

		String tenantId = tokenParameter.getArgs().getStr("tenantId");
		String username = tokenParameter.getArgs().getStr("username");
		String password = tokenParameter.getArgs().getStr("password");
		UserInfo userInfo = null;


		//在此之上使用清洁密码
		if (Func.isNoneBlank(username, DigestUtil.hex(password))) {

			// 使用AD登录
			/*
			 *
			 * 线上方法认证
			 *
			 */
//			LDAPAuthentication ldap = new LDAPAuthentication(username, password);
//			boolean result = ldap.authenticate();
			/*
			 *
			 * 本地方法强行不走认证
			 *
			 */
//			boolean result = false;
			boolean result = ldap.authenticate(username, password);

			if (result) {
				User user = userService.getOne(Wrappers.<User>query().lambda().eq(User::getAccount, username).or().eq(User::getPhone, username));
				if (user != null) {
					userInfo = userService.userInfo(user.getId());
				}
				else
				{
					throw new ServiceException(TokenUtil.USER_HAS_NO_ROLE);
				}
			} else {
				//根据帐号密码获取租户信息
				password =	Md5Utils.md5Hex(password);

				List<User> users = userService.userInfo(username);
				if (users.size() == 0 || users.size() > 1) {
					throw new ServiceException(TokenUtil.USER_GET_TENANT_ERROR);
				} else {
					tenantId = users.get(0).getTenantId();
				}

				// 获取租户信息
				Tenant tenant = tenantService.getByTenantId(tenantId);
				if (TokenUtil.judgeTenant(tenant)) {
					throw new ServiceException(TokenUtil.USER_HAS_NO_TENANT_PERMISSION);
				}


				// 获取用户类型
				String userType = tokenParameter.getArgs().getStr("userType");
				// 根据不同用户类型调用对应的接口返回数据，用户可自行拓展
				if (userType.equals(UserEnum.WEB.getName())) {
					userInfo = userService.userInfo(tenantId, username, DigestUtil.hex(password), UserEnum.WEB);
				} else if (userType.equals(UserEnum.APP.getName())) {
					userInfo = userService.userInfo(tenantId, username, DigestUtil.hex(password), UserEnum.APP);
				} else {
					userInfo = userService.userInfo(tenantId, username, DigestUtil.hex(password), UserEnum.OTHER);
				}
			}
		}
		return userInfo;
	}

}
