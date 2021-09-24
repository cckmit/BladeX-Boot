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

import org.springblade.modules.project.entity.Change;
import org.springblade.modules.project.entity.ChangeDetail;
import org.springblade.modules.project.vo.ChangeVO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 *  服务类
 *
 * @author BladeX
 * @since 2021-07-14
 */
public interface IChangeService extends IService<Change> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param change
	 * @return
	 */
	IPage<ChangeVO> selectChangePage(IPage<ChangeVO> page, ChangeVO change);

	/**
	 * 保存修改值
	 * @param businessId
	 * @param detailList
	 * @return
	 */
	Boolean saveChange(long businessId, List<ChangeDetail> detailList);

	/**
	 * 根据商机主键，获取修改记录
	 * @param businessId 商机主键
	 * @return
	 */
	List<Change> getChangeList(long businessId);
}
