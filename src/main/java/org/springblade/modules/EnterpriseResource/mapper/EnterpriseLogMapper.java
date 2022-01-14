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
package org.springblade.modules.EnterpriseResource.mapper;

import liquibase.pro.packaged.S;
import org.springblade.modules.EnterpriseResource.entity.EnterpriseLog;
import org.springblade.modules.EnterpriseResource.vo.EnterpriseLogVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

/**
 * 日志表 Mapper 接口
 *
 * @author BladeX
 * @since 2022-01-06
 */
public interface EnterpriseLogMapper extends BaseMapper<EnterpriseLog> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param enterpriseLog
	 * @return
	 */
	List<EnterpriseLogVO> selectEnterpriseLogPage(IPage page, EnterpriseLogVO enterpriseLog);

	/**
	 *
	 * 根据状态0查询状态的集合

	 * @return
	 */
	List<EnterpriseLogVO> selectStatus0 ();


	/**
	 *
	 * 根据状态1查询不同状态的集合
	 *

	 * @return
	 */
	List<EnterpriseLogVO> selectStatus1 ();

}
