
package org.springblade.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 系统字典枚举类
 *
 * @author Chill
 */
@Getter
@AllArgsConstructor
public enum DictEnum {

	/**
	 * 性别
	 */
	SEX("sex"),
	/**
	 * 通知类型
	 */
	NOTICE("notice"),
	/**
	 * 菜单类型
	 */
	MENU_CATEGORY("menu_category"),
	/**
	 * 按钮功能
	 */
	BUTTON_FUNC("button_func"),
	/**
	 * 是否
	 */
	YES_NO("yes_no"),
	/**
	 * 流程类型
	 */
	FLOW("flow"),
	/**
	 * 机构类型
	 */
	ORG_CATEGORY("org_category"),
	/**
	 * 数据权限
	 */
	DATA_SCOPE_TYPE("data_scope_type"),
	/**
	 * 接口权限
	 */
	API_SCOPE_TYPE("api_scope_type"),
	/**
	 * 权限类型
	 */
	SCOPE_CATEGORY("scope_category"),
	/**
	 * 对象存储类型
	 */
	OSS("oss"),
	/**
	 * 短信服务类型
	 */
	SMS("sms"),
	/**
	 * 岗位类型
	 */
	POST_CATEGORY("post_category"),
	/**
	 * 行政区划
	 */
	REGION("region"),
	/**
	 * 用户平台
	 */
	USER_TYPE("user_type"),

	/**
	 * 招标方式
	 */
	project_BiddingType("project_BiddingType"),

	/**
	 * 商机分类
	 */
	projectCatrgory("projectCatrgory"),

	/**
	 * 行业
	 */
	project_Industry("project_Industry"),

	/**
	 * 行政区划
	 */
	region("region"),

	/**
	 * 客户类别
	 */
	client_type("client_type"),
	/**
	 * 商机报备状态名称
	 */
	businessmobileStatus("businessmobileStatus"),

	;



	final String name;

}
