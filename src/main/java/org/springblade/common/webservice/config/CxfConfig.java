package org.springblade.common.webservice.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;


import org.springblade.common.webservice.orgsev.impl.OrganizationInfoServiceImpl;
import org.springblade.common.webservice.usersev.impl.UserDataInfoServiceImpl;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class CxfConfig {

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
		EndpointImpl endpoint = new EndpointImpl(springBus(), new OrganizationInfoServiceImpl());
		endpoint.publish("/XyOrgWebService");
		return endpoint;
	}

	@Bean
	public Endpoint userInfoSrv() {
		EndpointImpl endpoint = new EndpointImpl(springBus(), new UserDataInfoServiceImpl());
		endpoint.publish("/XyUserWebService");
		return endpoint;
	}

}
