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
package org.springblade.modules.project.mapper;

import org.springblade.modules.project.entity.Change;
import org.springblade.modules.project.vo.ChangeVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

/**
 *  Mapper 接口
 *
 * @author BladeX
 * @since 2021-07-14
 */
public interface ChangeMapper extends BaseMapper<Change> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param change
	 * @return
	 */
	List<ChangeVO> selectChangePage(IPage page, ChangeVO change);

	/**
	 * 根据商机主键，获取修改记录
	 * @param businessId 商机主键
	 * @return
	 */
	List<ChangeVO> getChangeList(long businessId);
}
