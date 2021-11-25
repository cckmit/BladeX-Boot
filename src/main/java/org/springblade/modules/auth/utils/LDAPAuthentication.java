package org.springblade.modules.auth.utils;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.Control;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import java.util.Hashtable;

public class LDAPAuthentication {
	private final String URL = "ldap://10.188.188.240:389/";//（LDAP连接地址）
	private final String BASEDN = "CN=GS_JXC_Admin,OU=集团业务系统,DC=ChinaCCS,DC=cn";  // 根据自己情况进行修改
	private final String FACTORY = "com.sun.jndi.ldap.LdapCtxFactory"; //LDAP工厂类
	private LdapContext ctx = null;
	private final Control[] connCtls = null;

	private String account = "";
	private String password = "";

	public LDAPAuthentication(String userName, String pwd) {
		account = userName;
		password = pwd;
	}


	public final boolean authenticate() {
		Hashtable<String, String> env = new Hashtable<String, String>();
		boolean result = false;
		env.put(Context.INITIAL_CONTEXT_FACTORY, FACTORY);
		env.put(Context.PROVIDER_URL, URL + BASEDN);
		env.put(Context.SECURITY_AUTHENTICATION, "simple"); //（LDAP访问安全级别）
		// String root = "cn=demo1,dc=sys,dc=com";  // 根据自己情况修改
		env.put(Context.SECURITY_PRINCIPAL, account);   // 用户账号
		env.put(Context.SECURITY_CREDENTIALS, password);  // 用户密码

		try {
			ctx = new InitialLdapContext(env, connCtls);//（初始化上下文）
			System.out.println("认证成功");
			System.out.println(ctx);
			result = true;

		} catch (javax.naming.AuthenticationException e) {
			System.out.println("认证失败：");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("认证出错：");
			e.printStackTrace();
		}

		if (ctx != null) {
			try {
				ctx.close();
			} catch (NamingException e) {
				e.printStackTrace();
			}

		}

		return result;
	}


}
