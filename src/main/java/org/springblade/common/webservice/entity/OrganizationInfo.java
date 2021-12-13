package org.springblade.common.webservice.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class OrganizationInfo implements Serializable {

	/**
	 * SAP消息号
	 */
	private String SAPXXH;

	/**
	 * 组织结构编码
	 */
	private String ORG_NUMBER;

	/**+
	 * 组织结构简称
	 */
	private String ORG_NAME_ABBR;

	/**
	 * 组织结构全称
	 */
	private String ORG_NAME_FULL;

	/**
	 * 负责人代码
	 */
	private String RESP_PERSON;

	/**
	 * 所属地区编码
	 */
	private String REGION;

	/**
	 * 上级组织结构编码
	 */
	private String UPPER_ORG;

	/**
	 * 有效期开始
	 */
	private String EFFECT_START;

	/**
	 * 有效期结束
	 */
	private String EFFECT_END;

	/**
	 * 层级代码
	 */
	private String LEVEL;

	/**
	 * 组织结构分类    1-省公司 |  2-公司  |  3-分公司  |  4-部门
	 */
	private String ORG_TYPE;

	/**
	 * 详细地址
	 */
	private String ADDR;

	/**
	 * 邮政编码
	 */
	private String POST_CODE;

	/**
	 * 本位币代码
	 */
	private String CURRENCY;

	/**
	 * SAP公司代码
	 */
	private String SAP_COMP;

	/**
	 * SAP采购组织
	 */
	private String SAP_PURCH_ORG;

	/**
	 * SAP工厂代码
	 */
	private String SAP_PLANT;

	/**
	 * SAP销售组织
	 */
	private String SAP_SALES_ORG;

	/**
	 * 是否暂估销项税  Y-是 N-否
	 */
	private String TAX_IND;

	/**
	 * 利润中心编码
	 */
	private String PROFIT_CENTER;

	/**
	 * 成本中心编码
	 */
	private String COST_CENTER;

	/**
	 * 是否核算对象  Y-是 N-否
	 */
	private String ACCT_OBJ_IND;

	/**
	 * 部门属性  01-生产 | 02-管理 | 03-销售 |04-研发
	 */
	private String DEPT_TYPE;

	/**
	 * 完整路径
	 */
	private String ORG_PATH;

	/**
	 * 完整路径名称
	 */
	private String ORG_PATH_NAME;

	/**
	 * 所属分公司
	 */
	private String COMP_CODE;

	/**
	 * 状态  0-正常 1-删除
	 */
	private String STATUS;

	/**
	 * 排序
	 */
	private String SORT;

	/**
	 * 是否末级  0-是 1-否
	 */
	private String LEAF_NODE_IND;

	/**
	 * 是否法人 Y-是 N-否
	 */
	private String LEGAL_ENTITY;


}
