package org.springblade.modules.EnterpriseResource.service.impl;

import org.springblade.modules.EnterpriseResource.controller.FileController;
import org.springframework.boot.system.ApplicationHome;

import java.io.File;

/**
 * @author wucan
 * @date 2022/2/14
 */
public class test {

	public static void main(String[] args) {
		test muDemo = new test();
		muDemo.showURL();

	}

	public void showURL() {
		ApplicationHome ah = new ApplicationHome(AptitudeServiceImpl.class);
		String docStorePath = ah.getSource().getParentFile().toString();
		System.out.println(docStorePath);
	}
}
