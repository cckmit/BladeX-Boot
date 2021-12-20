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

import org.springblade.modules.EnterpriseResource.entity.AptitudeCatalogue;
import org.springblade.modules.EnterpriseResource.vo.AptitudeCatalogueVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

/**
 *  Mapper 接口
 *
 * @author BladeX
 * @since 2021-11-26
 */
public interface AptitudeCatalogueMapper extends BaseMapper<AptitudeCatalogue> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param aptitudeCatalogue
	 * @return
	 */
	List<AptitudeCatalogueVO> selectAptitudeCataloguePage(IPage page, AptitudeCatalogueVO aptitudeCatalogue);

	/**
	 * 根据id 获取名字
	 *
	 * @return
	 */
	AptitudeCatalogue selectAreaName (Long id);

	/**
	 * 根据名字 获取id
	 *
	 * @return
	 */
	AptitudeCatalogue selectID (String areaName);

}
