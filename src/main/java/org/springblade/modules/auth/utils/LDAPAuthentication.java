package org.springblade.modules.auth.utils;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.ldap.Control;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import java.util.Hashtable;

public class LDAPAuthentication {
	private final String URL = "ldap://10.188.188.240:389/";//（LDAP连接地址）
	private final String BASEDN = "DC=ChinaCCS,DC=cn";  // 根据自己情况进行修改
	private final String FACTORY = "com.sun.jndi.ldap.LdapCtxFactory"; //LDAP工厂类
	private DirContext ctx = null;
	private final Control[] connCtls = null;

	private String account = "";
	private String password = "";

	public LDAPAuthentication(String userName, String pwd) {
		account = userName + "@chinaccs.cn";
		password = pwd;

	}

	//		env.put(Context.INITIAL_CONTEXT_FACTORY, FACTORY);
//		env.put(Context.PROVIDER_URL, URL + BASEDN);
//		env.put(Context.SECURITY_AUTHENTICATION, "simple"); //（LDAP访问安全级别）
//		env.put(Context.SECURITY_PRINCIPAL, account);   // 用户账号
//		env.put(Context.SECURITY_CREDENTIALS, password);  // 用户密码

	public final boolean authenticate() {

		Hashtable<String, String> env = new Hashtable<String, String>();
		boolean result = false;

		env.put(Context.SECURITY_AUTHENTICATION, "simple"); //（LDAP访问安全级别）
		env.put(Context.SECURITY_PRINCIPAL, account);   // 用户账号
		env.put(Context.SECURITY_CREDENTIALS, password);  // 用户密码
		env.put(Context.INITIAL_CONTEXT_FACTORY, FACTORY);
		env.put(Context.PROVIDER_URL, URL);

		try {
			ctx = new InitialDirContext(env);//（初始化上下文）
			System.out.println("认证成功");
			System.out.println(ctx);
			result = true;
		} catch (Exception e) {
			System.out.println("认证出错：");

			if (e.toString().indexOf("LDAP: error code") > 0) {
				String errorCode = null;
				String childErrorCode = null;
				String errorMsg = e.toString();
				String code = "";
				// 得到ldap返回的错误码
				int startNum = errorMsg.indexOf("LDAP: error code") + 17;
				int endNum = startNum + 2;
				errorCode = errorMsg.substring(startNum, endNum);

				if ("49".equals(errorCode)) {
					startNum = errorMsg.indexOf("data") + 5;
					endNum = startNum + 3;
					childErrorCode = errorMsg.substring(startNum, endNum);

					/*
					 * 525 - 用户没有找到
					 * 52e - 证书不正确
					 * 530 - not permitted to logon at this time
					 * 532 - 密码期满
					 * 533 - 帐户不可用
					 * 701 - 账户期满
					 * 773 - 用户必须重设密码
					 */
					if ("52e".equals(childErrorCode)) {
						code = "密码或凭证无效";
					} else if ("775".equals(childErrorCode)) {
						code = "用户账户锁定";
					} else if ("532".equals(childErrorCode)) {
						code = "用户密码过期";
					} else if ("533".equals(childErrorCode)) {
						code = "用户冻结";
					} else {
						code = "其他错误";
					}
				} else {
					e.printStackTrace();
					code = "其他错误";
				}
				System.out.println("错误信息：" + code);
			}
			e.printStackTrace();


		} finally {
			try {
				if (ctx != null) {
					ctx.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}


}
