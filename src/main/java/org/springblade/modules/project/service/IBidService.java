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

import org.springblade.modules.project.dto.BidApplyDTO;
import org.springblade.modules.project.dto.BidToVoidDTO;
import org.springblade.modules.project.entity.Bid;
import org.springblade.modules.project.vo.BidVO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 *  服务类
 *
 * @author BladeX
 * @since 2021-07-18
 */
public interface IBidService extends IService<Bid> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param bid
	 * @return
	 */
	IPage<BidVO> selectBidPage(IPage<BidVO> page, BidVO bid);

	/**
	 * 根据商机主键获取投标信息
	 * @param businessId
	 * @return
	 */
	Bid getBidByBusinessId(long businessId);

	/**
	 * 终止投标流程
	 * @param cancelDTO 终止实体
	 * @return
	 */
	Boolean stopBid(BidToVoidDTO cancelDTO);

	/**
	 * 发起投标申请
	 * @param applyDTO
	 * @return
	 */
	Boolean saveBidApply(BidApplyDTO applyDTO);

	boolean pushToBid(long businessId);

}
