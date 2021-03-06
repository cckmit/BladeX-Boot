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

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.system.entity.Tenant;
import org.springblade.modules.system.vo.DeptVO;
import org.springblade.modules.system.vo.TenantVO;

import java.util.List;
import java.util.Map;

/**
 *  Mapper 接口
 *
 * @author Chill
 */
public interface TenantMapper extends BaseMapper<Tenant> {
	/**
	 * 懒加载租户列表
	 *
	 * @param parentId
	 * @param param
	 * @return
	 */
	List<TenantVO> lazyList(Long parentId, Map<String, Object> param);
	/**
	 * 获取树形节点
	 *
	 * @return
	 */
	List<TenantVO> tree();

	/**
	 * 懒加载获取树形节点
	 *
	 * @param parentId
	 * @return
	 */
	List<TenantVO> lazyTree(Long parentId);
	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param tenant
	 * @return
	 */
	List<Tenant> selectTenantPage(IPage page, Tenant tenant);

	/**
	 * 通过tenantId 查找主键id
	 *
	 * @param tenantId
	 * @return
	 */
	Tenant selectId(String tenantId);


}
