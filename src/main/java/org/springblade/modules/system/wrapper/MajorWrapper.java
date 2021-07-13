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
package org.springblade.modules.system.wrapper;

import org.springblade.common.cache.MajorCache;
import org.springblade.common.cache.RegionCache;
import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.node.ForestNodeMerger;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.modules.system.entity.Major;
import org.springblade.modules.system.vo.MajorVO;

import java.util.List;
import java.util.Objects;

/**
 * 包装类,返回视图层所需的字段
 *
 * @author Chill
 */
public class MajorWrapper extends BaseEntityWrapper<Major, MajorVO> {

	public static MajorWrapper build() {
		return new MajorWrapper();
	}

	@Override
	public MajorVO entityVO(Major region) {
		MajorVO majorVO = Objects.requireNonNull(BeanUtil.copy(region, MajorVO.class));
		Major parentMajor = MajorCache.getByCode(region.getParentCode());
		majorVO.setParentName(parentMajor.getMajorName());
		return majorVO;
	}

	public List<MajorVO> listNodeLazyVO(List<MajorVO> list) {
		return ForestNodeMerger.merge(list);
	}

}
