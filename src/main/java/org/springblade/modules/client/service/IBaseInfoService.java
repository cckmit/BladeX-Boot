/*
 *      Copyright (c) 2018-2028, Chill Zhuang All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the dreamlu.net developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: Chill 庄骞 (smallchill@163.com)
 */
package org.springblade.modules.client.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.modules.client.entity.BaseInfo;
import org.springblade.modules.client.vo.BaseInfoVO;

/**
 *  服务类
 *
 * @author BladeX
 * @since 2021-06-26
 */
public interface IBaseInfoService extends BaseService<BaseInfo> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param baseInfo
	 * @return
	 */
	IPage<BaseInfoVO> selectBaseInfoPage(IPage<BaseInfoVO> page, BaseInfoVO baseInfo);

	/**
	 * 更新用户公私海类型
	 *
	 * @param clientId 客户ID
	 * @param mode 1公海 2私海
	 * @return
	 */
	boolean updateClientMode(Long clientId, Integer mode);

	/**
	 * 客户信息列表(私海)
	 * @param page
	 * @param condition
	 * @return
	 */
	IPage<BaseInfoVO> pageClientInfo(IPage<BaseInfoVO> page, BaseInfoVO condition);

	/**
	 * 客户信息列表(公海)
	 * @param page
	 * @param condition
	 * @return
	 */
	IPage<BaseInfo> pageClientInfoPub(IPage<BaseInfo> page, BaseInfoVO condition);
}
