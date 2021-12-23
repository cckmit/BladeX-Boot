package org.springblade.common.webservice.interceptor;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.service.model.ServiceInfo;
import org.springframework.stereotype.Component;

@Component
public class CxfIgnoreTargetNamespaceInInterceptors extends AbstractPhaseInterceptor<Message> {

	public CxfIgnoreTargetNamespaceInInterceptors() {
		super(Phase.RECEIVE);
	}

	@Override
	public void handleMessage(Message message) throws Fault {
		for (ServiceInfo si : message.getExchange().getService().getServiceInfos()) {
			//这个就是忽略客户端不带命名空间的关键，可查看类DocLiteralInInterceptor的handleMessage方法
			si.setProperty("soap.force.doclit.bare", Boolean.TRUE);
		}
	}
}
