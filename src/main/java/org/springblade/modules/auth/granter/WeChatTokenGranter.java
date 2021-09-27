package org.springblade.modules.auth.granter;

import lombok.AllArgsConstructor;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.tool.utils.Func;
import org.springblade.modules.auth.provider.ITokenGranter;
import org.springblade.modules.auth.provider.TokenParameter;
import org.springblade.modules.auth.utils.TokenUtil;
import org.springblade.modules.system.entity.Tenant;
import org.springblade.modules.system.entity.UserInfo;
import org.springblade.modules.system.service.ITenantService;
import org.springblade.modules.system.service.IUserService;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class WeChatTokenGranter implements ITokenGranter {

	public static final String GRANT_TYPE = "wechat";

	private final IUserService userService;
	private final ITenantService tenantService;

	@Override
	public UserInfo grant(TokenParameter tokenParameter) {
		String tenantId = tokenParameter.getArgs().getStr("tenantId");
		String account = tokenParameter.getArgs().getStr("account");
		String openId = tokenParameter.getArgs().getStr("openId");
		UserInfo userInfo = null;
		if (Func.isNoneBlank(account, openId)) {
			// 获取租户信息
			Tenant tenant = tenantService.getByTenantId(tenantId);
			if (TokenUtil.judgeTenant(tenant)) {
				throw new ServiceException(TokenUtil.USER_HAS_NO_TENANT_PERMISSION);
			}

			userInfo = userService.getUserInfo(tenantId, account, openId);

		}
		return userInfo;

	}

}
