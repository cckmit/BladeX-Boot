package org.springblade.common.webservice.usersev;

import org.springblade.common.webservice.entity.UserDataInfo;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace = "http://usersev.webservice.common.springblade.org",name = "XyUserWebService")
public interface IUserDataInfoService {
	@WebMethod()
	public void sendMessageResult(@WebParam(name = "arg0", targetNamespace = "") UserDataInfo arg0);
}
