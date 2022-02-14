package org.springblade.modules.EnterpriseResource.service.impl;

import org.springblade.modules.EnterpriseResource.controller.FileController;

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
		// 第一种：获取类加载的根路径   D:\git\daotie\daotie\target\classes
		File f = new File(this.getClass().getResource("/").getPath());
		System.out.println("path1: "+f);
	}
}
