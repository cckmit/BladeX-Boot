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
package org.springblade.modules.project.service;

import org.springblade.modules.project.entity.Business;
import org.springblade.modules.project.vo.BusinessVO;
import org.springblade.core.mp.base.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *  服务类
 *
 * @author BladeX
 * @since 2021-07-03
 */
public interface IBusinessService extends BaseService<Business> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param business
	 * @return
	 */
	IPage<BusinessVO> selectBusinessPage(IPage<BusinessVO> page, BusinessVO business);


	/**
	 * 开启流程
	 *
	 * @param 集客备案实体
	 * @return boolean
	 */
	boolean startProcess(Business business);


//	/**
//	 * 推送至投标管理
//	 * @param businessId
//	 * @return
//	 */
//	boolean pushToBid(long businessId);
}
