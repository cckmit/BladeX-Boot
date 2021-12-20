package org.springblade.common.webservice.orgsev;

import oracle.jdbc.proxy.annotation.Methods;
import org.springblade.common.webservice.entity.OrganizationInfo;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService(targetNamespace = "http://orgsev.webservice.common.springblade.org",name = "XyOrgWebService")
public interface IOrganizationInfoService {

	@WebMethod()
	public void sendMessageResult(@WebParam(name = "arg0", targetNamespace = "") OrganizationInfo arg0);
}
