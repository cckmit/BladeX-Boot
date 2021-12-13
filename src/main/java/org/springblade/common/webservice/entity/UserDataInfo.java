package org.springblade.common.webservice.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserDataInfo implements Serializable {
	/**
	 * SAP消息号
	 */
	private String SAPXXH;
	/**
	 * 员工供应商编码
	 */
	private String BP_CODE;
	/**
	 * 姓名
	 */
	private String NAME;
	/**
	 * AD帐号
	 */
	private String AD_ID;
	/**
	 * 性别
	 */
	private String GENDA;
	/**
	 * 地区代码
	 */
	private String REGION;
	/**
	 * SAP国家代码
	 */
	private String SAP_COUNTY;
	/**
	 * SAP地区代码
	 */
	private String SAP_REGION;
	/**
	 * 详细地址
	 */
	private String ADDR;
	/**
	 * 身份证号
	 */
	private String PERSONAL_ID;
	/**
	 * 所属部门编码
	 */
	private String DEPT;
	/**
	 * SAP公司代码
	 */
	private String SAP_COMPANY;
	/**
	 * SAP工厂代码
	 */
	private String SAP_PLANT;
	/**
	 * 账户组
	 */
	private String ACCOUNT_GROUP;
	/**
	 * 手机号
	 */
	private String MOBILE;
	/**
	 * 出生日期
	 */
	private String BIRTH_DATE;
	/**
	 * 电子邮箱
	 */
	private String EMAIL;
	/**
	 * 员工类别
	 */
	private String EMPLOY_TYPE;
	/**
	 * 直属领导
	 */
	private String REPORTING_TO;
	/**
	 * 供应商标识
	 */
	private String VENDOR_IND;
	/**
	 * 客户表识
	 */
	private String CUSTOMER_IND;
	/**
	 * 供应商分类代码
	 */
	private String VENDOR_CATE_CODE;
	/**
	 * 供应商分类名称
	 */
	private String VENDOR_CATE_NAME;
	/**
	 * 入职状态
	 */
	private String EMPLOYMENT_STATUS;
	/**
	 * SAP采购组织
	 */
	private String SAP_PURCH_ORG;
	/**
	 * 邮编
	 */
	private String POST_CODE;
	/**
	 * 状态
	 */
	private String STATUS;
	/**
	 * 职务
	 */
	private String POST;
}
