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
import org.springblade.modules.system.entity.Dept;
import org.springblade.modules.system.vo.DeptVO;

import java.util.List;
import java.util.Map;

/**
 * Mapper 接口
 *
 * @author Chill
 */
public interface DeptMapper extends BaseMapper<Dept> {

	/**
	 * 懒加载部门列表
	 *
	 * @param tenantId
	 * @param parentId
	 * @param param
	 * @return
	 */
	List<DeptVO> lazyList(String tenantId, Long parentId, Map<String, Object> param);

	/**
	 * 获取树形节点
	 *
	 * @param tenantId
	 * @return
	 */
	List<DeptVO> tree(String tenantId);

	/**
	 * 懒加载获取树形节点
	 *
	 * @param tenantId
	 * @param parentId
	 * @return
	 */
	List<DeptVO> lazyTree(String tenantId, Long parentId);

	/**
	 * 获取部门名
	 *
	 * @param ids
	 * @return
	 */
	List<String> getDeptNames(Long[] ids);

	/**
	 * 获取部门id
	 *
	 * @param code
	 * @return
	 */
	List<String> getDeptId(String code);


	/**
	 * 根据pid拿到当前id的所有集合
	 *
	 * @param id
	 * @return
	 */
	List<Dept> selectPid(Long id);


	/**
	 * 根据名字拿对应id
	 *
	 * @param name
	 * @return
	 */
	Dept selectselectName(String name);

	/**
	 * 根据id拿对应名字
	 *
	 * @param id
	 * @return
	 */
	Dept selectID(Long id);


	/**
	 * 查询省公司名称
	 *
	 * @return
	 */
	List<Dept> selectCompany();


	/**
	 * 查询企业名称
	 *
	 * @return
	 */
	List<Dept> selectEnterprise(Long pid);
}
