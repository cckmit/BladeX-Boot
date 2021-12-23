package org.springblade.common.webservice.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;

import org.apache.cxf.message.Message;
import org.springblade.common.webservice.interceptor.CxfIgnoreTargetNamespaceInInterceptors;
import org.springblade.common.webservice.interceptor.IpFilter;
import org.springblade.common.webservice.orgsev.IOrganizationInfoService;
import org.springblade.common.webservice.usersev.IUserDataInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Configuration
public class CxfConfig {

	@Autowired
	private IpFilter ipFilter;

	@Autowired
	private CxfIgnoreTargetNamespaceInInterceptors tnspIgnore;

	@Autowired
	private IOrganizationInfoService orgService;

	@Autowired
	private IUserDataInfoService userService;

	//这里需要注意  由于springmvc 的核心类 为DispatcherServlet
	//此处若不重命名此bean的话 原本的mvc就被覆盖了。可查看配置类：DispatcherServletAutoConfiguration
	//一种方法是修改方法名称 或者指定bean名称
	//这里需要注意 若beanName命名不是 cxfServletRegistration 时，会创建两个CXFServlet的。
	//具体可查看下自动配置类：Declaration org.apache.cxf.spring.boot.autoconfigure.CxfAutoConfiguration
	//也可以不设置此bean 直接通过配置项 cxf.path 来修改访问路径的
	@Bean(value = "cxfServletRegistration")
	public ServletRegistrationBean disServlet() {
		return new ServletRegistrationBean(new CXFServlet(), "/webService/*");
	}


	@Bean(name = Bus.DEFAULT_BUS_ID)
	public SpringBus springBus() {
		return new SpringBus();
	}


	@Bean
	public Endpoint orgInfoSrv() {
		EndpointImpl endpoint = new EndpointImpl(springBus(), orgService);
		endpoint.publish("/XyOrgWebService");

		//endpoint.getInInterceptors().add(ipFilter);//添加IP拦截器
		endpoint.getInInterceptors().add(tnspIgnore);//设置忽略targetnamespace的拦截器
		return endpoint;
	}

	@Bean
	public Endpoint userInfoSrv() {
		EndpointImpl endpoint = new EndpointImpl(springBus(), userService);
		endpoint.publish("/XyUserWebService");
		// 设置忽略targetnamespace的拦截器

		endpoint.getInInterceptors().add(tnspIgnore);//设置忽略targetnamespace的拦截器
		return endpoint;
	}

	/**
	 * cxf webservice服务端设置服务端忽略target namespace的校验
	 *
	 * @param endpoint 服务端
	 */
	public void setIgnoreTargetNamespaceInInterceptors(EndpointImpl endpoint) {
		List<Interceptor<? extends Message>> inInterceptors = endpoint.getInInterceptors();
		inInterceptors.add(new CxfIgnoreTargetNamespaceInInterceptors());
		endpoint.setInInterceptors(inInterceptors);
	}

}
