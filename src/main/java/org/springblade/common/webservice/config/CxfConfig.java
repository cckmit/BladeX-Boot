package org.springblade.common.webservice.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;


import org.springblade.common.webservice.orgsev.IOrganizationInfoService;
import org.springblade.common.webservice.orgsev.impl.OrganizationInfoServiceImpl;
import org.springblade.common.webservice.usersev.IUserDataInfoService;
import org.springblade.common.webservice.usersev.impl.UserDataInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Component
@Configuration
public class CxfConfig {

	@Autowired
	private IOrganizationInfoService orgService;

	@Autowired
	private IUserDataInfoService userService;

	@Bean
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
		return endpoint;
	}

	@Bean
	public Endpoint userInfoSrv() {
		EndpointImpl endpoint = new EndpointImpl(springBus(), userService);
		endpoint.publish("/XyUserWebService");
		return endpoint;
	}

}
