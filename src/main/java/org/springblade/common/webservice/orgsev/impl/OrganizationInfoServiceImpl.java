package org.springblade.common.webservice.orgsev.impl;

import com.yunpian.sdk.util.JsonUtil;
import org.springblade.common.webservice.entity.OrganizationInfo;
import org.springblade.common.webservice.orgsev.IOrganizationInfoService;
import org.springblade.core.log.annotation.ApiLog;
import org.springblade.core.log.logger.BladeLogger;
import org.springblade.modules.system.entity.XyOrgInfo;
import org.springblade.modules.system.service.IXyOrgInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebService;

@Component
@WebService(serviceName = "XyOrgWebService",
	targetNamespace = "http://orgsev.webservice.common.springblade.org",
	endpointInterface = "org.springblade.common.webservice.orgsev.IOrganizationInfoService")
public class OrganizationInfoServiceImpl implements IOrganizationInfoService {

	@Autowired
	private IXyOrgInfoService orgServer;

	@Autowired
	private BladeLogger logger;

	@ApiLog("翔云组织架构信息推送")
	@Override
	public void sendMessageResult(OrganizationInfo arg0) {
		if (!arg0.getSAPXXH().isEmpty()) {

			logger.info("xyOrgData", JsonUtil.toJson(arg0));

			XyOrgInfo info = new XyOrgInfo();
			info.setSapxxh(arg0.getSAPXXH());
			info.setOrgNumber(arg0.getORG_NUMBER());
			info.setOrgNameAbbr(arg0.getORG_NAME_ABBR());
			info.setOrgNameFull(arg0.getORG_NAME_FULL());
			info.setRespPerson(arg0.getRESP_PERSON());
			info.setRegion(arg0.getREGION());
			info.setUpperOrg(arg0.getUPPER_ORG());
			info.setEffectStart(arg0.getEFFECT_START());
			info.setEffectEnd(arg0.getEFFECT_END());
			info.setLevel(arg0.getLEVEL());
			info.setOrgType(arg0.getORG_TYPE());
			info.setAddr(arg0.getADDR());
			info.setPostCode(arg0.getPOST_CODE());
			info.setCurrency(arg0.getCURRENCY());
			info.setSapComp(arg0.getSAP_COMP());
			info.setSapPurchOrg(arg0.getSAP_PURCH_ORG());
			info.setSapPlant(arg0.getSAP_PLANT());
			info.setSapSalesOrg(arg0.getSAP_SALES_ORG());
			info.setTaxInd(arg0.getTAX_IND());
			info.setProfitCenter(arg0.getPROFIT_CENTER());
			info.setCostCenter(arg0.getCOST_CENTER());
			info.setAcctObjInd(arg0.getACCT_OBJ_IND());
			info.setDeptType(arg0.getDEPT_TYPE());
			info.setOrgPath(arg0.getORG_PATH());
			info.setOrgPathName(arg0.getORG_PATH_NAME());
			info.setCompCode(arg0.getCOMP_CODE());
			info.setStatus(arg0.getSTATUS());
			info.setSort(arg0.getSORT());
			info.setLeafNodeInd(arg0.getLEAF_NODE_IND());
			info.setLegalEntity(arg0.getLEGAL_ENTITY());


			XyOrgInfo org = orgServer.getOrgInfo(arg0.getSAPXXH());
			if (org != null) {
				info.setId(org.getId());
			}

			orgServer.saveOrUpdate(info);
		}
	}
}
