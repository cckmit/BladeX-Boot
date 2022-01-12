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
package org.springblade.modules.EnterpriseResource.service;

import org.springblade.modules.EnterpriseResource.entity.EnterpriseLog;
import org.springblade.modules.EnterpriseResource.vo.EnterpriseLogVO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 日志表 服务类
 *
 * @author BladeX
 * @since 2022-01-06
 */
public interface IEnterpriseLogService extends IService<EnterpriseLog> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param enterpriseLog
	 * @return
	 */
	IPage<EnterpriseLogVO> selectEnterpriseLogPage(IPage<EnterpriseLogVO> page, EnterpriseLogVO enterpriseLog);

}
