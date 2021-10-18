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


import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.modules.EnterpriseResource.entity.Biddatum;
import org.springblade.modules.EnterpriseResource.vo.AttachmentProveDemo;
import org.springblade.modules.EnterpriseResource.vo.AttachmentProveVO;
import org.springblade.modules.EnterpriseResource.vo.BiddatumVO;

import java.util.List;

/**
 * 投标资料表 服务类
 *
 * @author BladeX
 * @since 2021-09-23
 */
public interface IBiddatumService extends BaseService<Biddatum> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param biddatum
	 * @return
	 */
	IPage<BiddatumVO> selectBiddatumPage(IPage<BiddatumVO> page, BiddatumVO biddatum);

	/**
	 * 根据主键查询对应附件
	 *
	 * @return
	 */
	List<BiddatumVO> selectListId(Long objectId);


	void saveFile(BiddatumVO demo);

	void update(BiddatumVO demo);

}
