package org.springblade.common.webservice.usersev.impl;

import com.yunpian.sdk.util.JsonUtil;
import org.springblade.common.webservice.usersev.IUserDataInfoService;
import org.springblade.common.webservice.entity.UserDataInfo;
import org.springblade.core.log.annotation.ApiLog;
import org.springblade.core.log.logger.BladeLogger;
import org.springblade.core.tool.api.R;
import org.springblade.modules.system.entity.XyUserInfo;
import org.springblade.modules.system.service.IXyUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebService;

@Component(value = "userDataInfoServiceImpl")
@WebService(serviceName = "XyUserWebService",
	targetNamespace = "http://usersev.webservice.common.springblade.org",
	endpointInterface = "org.springblade.common.webservice.usersev.IUserDataInfoService")
public class UserDataInfoServiceImpl implements IUserDataInfoService {

	@Autowired
	private IXyUserInfoService userServer;

	@Autowired
	private BladeLogger logger;

	@ApiLog("翔云用户信息推送")
	@Override
	public void sendMessageResult(UserDataInfo arg0) {
		if (!arg0.getSAPXXH().isEmpty()) {
			try
			{
				XyUserInfo info = new XyUserInfo();

				info.setSapxxh(arg0.getSAPXXH());
				info.setBpCode(arg0.getBP_CODE());
				info.setName(arg0.getNAME());
				info.setAdId(arg0.getAD_ID());
				info.setGenda(arg0.getGENDA());
				info.setRegion(arg0.getREGION());
				info.setSapCounty(arg0.getSAP_COUNTY());
				info.setSapRegion(arg0.getSAP_REGION());
				info.setAddr(arg0.getADDR());
				info.setPersonalId(arg0.getPERSONAL_ID());
				info.setDept(arg0.getDEPT());
				info.setSapCompany(arg0.getSAP_COMPANY());
				info.setSapPlant(arg0.getSAP_PLANT());
				info.setAccountGroup(arg0.getACCOUNT_GROUP());
				info.setMobile(arg0.getMOBILE());
				info.setBirthDate(arg0.getBIRTH_DATE());
				info.setEmail(arg0.getEMAIL());
				info.setEmployType(arg0.getEMPLOY_TYPE());
				info.setReportingTo(arg0.getREPORTING_TO());
				info.setVendorInd(arg0.getVENDOR_IND());
				info.setCustomerInd(arg0.getCUSTOMER_IND());
				info.setVendorCateCode(arg0.getVENDOR_CATE_CODE());
				info.setVendorCateName(arg0.getVENDOR_CATE_NAME());
				info.setEmploymentStatus(arg0.getEMPLOYMENT_STATUS());
				info.setSapPurchOrg(arg0.getSAP_PURCH_ORG());
				info.setPostCode(arg0.getPOST_CODE());
				info.setStatus(arg0.getSTATUS());
				info.setPost(arg0.getPOST());


				XyUserInfo user = userServer.getUserInfo(arg0.getSAPXXH());
				if (user != null) {
					info.setId(user.getId());
				}

				userServer.saveOrUpdate(info);
				logger.info("xyUserData", JsonUtil.toJson(arg0));
			}catch (Exception ex)
			{
				logger.error("xyUserData", ex.getMessage());
			}

		}
	}
}
