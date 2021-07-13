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
package org.springblade.modules.system.service;

import org.springblade.modules.system.entity.Major;
import org.springblade.modules.system.vo.MajorVO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.system.vo.RegionVO;

import java.util.List;
import java.util.Map;

/**
 *  服务类
 *
 * @author BladeX
 * @since 2021-07-08
 */
public interface IMajorService extends IService<Major> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param major
	 * @return
	 */
	IPage<MajorVO> selectMajorPage(IPage<MajorVO> page, MajorVO major);

	/**
	 * 懒加载列表
	 *
	 * @param parentCode
	 * @param param
	 * @return
	 */
	List<MajorVO> lazyTree(String parentCode, Map<String, Object> param);

}
