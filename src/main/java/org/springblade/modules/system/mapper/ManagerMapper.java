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
package org.springblade.modules.system.mapper;

import org.springblade.modules.system.entity.Manager;
import org.springblade.modules.system.vo.ManagerVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 *  Mapper 接口
 *
 * @author BladeX
 * @since 2021-06-30
 */
public interface ManagerMapper extends BaseMapper<Manager> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param manager
	 * @return
	 */
	List<ManagerVO> selectManagerPage(IPage page, ManagerVO manager);


	/**
	 * 单查详情以及显示用户名字
	 *
	 * @return
	 */
	Manager selectManagerDetail(Long id);


	/**
	 * 根据项目经理ID查询商机+投标表
	 *
	 * @return
	 */
	List<ManagerVO> selectProjectBusiness(IPage page,Long id);



	/**
	 * list列表带用户名字
	 * @param page
	 * @param manager
	 * @return
	 */
	List<Manager> selectManagerList(IPage page, ManagerVO manager);




	/*
	* 连表查询项目经理
	* */
	List<ManagerVO> selectManagerVOPage(IPage page, ManagerVO manager);



}
