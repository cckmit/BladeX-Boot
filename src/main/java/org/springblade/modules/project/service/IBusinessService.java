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

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.modules.project.dto.BusinessDTO;
import org.springblade.modules.project.entity.Business;
import org.springblade.modules.project.vo.BusinessVO;

import java.util.List;

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
	 * 开启商机报备流程
	 *
	 * @param business
	 * @return boolean
	 */
	boolean startProcess(Business business);

	/**
	 * 流程
	 *
	 * @param businessdto
	 * @return boolean
	 */
    boolean com(BusinessDTO businessdto);

	/**
	 * 流程详细信息
	 *
	 * @param business
	 * @return boolean
	 */
	BusinessDTO flowDetail(Business business);

	/*****************************************手机端接口****************************************************/

	/**
	 * 手机端列表+模糊查询+升序
	 *
	 * @param page
	 * @param business
	 * @return
	 */
	IPage<Business> selectBusinessLsit(IPage page, BusinessVO business);


	/**
	 * 手机端列表+模糊查询+时间降序
	 *
	 * @param page
	 * @param business
	 * @return
	 */
	IPage<Business> selectDescendingOrderTime(IPage page, BusinessVO business);

	/**
	 * 手机端列表+模糊查询+金额升序
	 *
	 * @param page
	 * @param business
	 * @return
	 */
	IPage<Business> selectAscendingOrderMoney(IPage page, BusinessVO business);

	/**
	 * 手机端列表+模糊查询+金额降序
	 *
	 * @param page
	 * @param business
	 * @return
	 */
	IPage<Business> selectDescendingOrderMoney(IPage page, BusinessVO business);

}
