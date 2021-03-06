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
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.modules.project.dto.BidundertakePageDTO;
import org.springblade.modules.project.entity.Bidundertake;
import org.springblade.modules.project.vo.BidundertakeVO;

/**
 *  服务类
 *
 * @author BladeX
 * @since 2021-09-09
 */
public interface IBidundertakeService extends IService<Bidundertake> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param bidundertake
	 * @return
	 */
	IPage<BidundertakeVO> selectBidundertakePage(IPage<BidundertakeVO> page, BidundertakeVO bidundertake);

	IPage<BidundertakePageDTO> selectUndertakeList(IPage<BidundertakePageDTO> page, BidundertakePageDTO bidundertake);
}
