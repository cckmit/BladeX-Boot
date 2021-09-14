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

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.springblade.modules.EnterpriseResource.entity.Aptitude;
import org.springblade.modules.EnterpriseResource.vo.AptitudeVO;

import java.util.List;

/**
 * 企业资质表 Mapper 接口
 *
 * @author BladeX
 * @since 2021-09-02
 */
public interface AptitudeMapper extends BaseMapper<Aptitude> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param aptitude
	 * @return
	 */
	List<AptitudeVO> selectAptitudePage(IPage page, AptitudeVO aptitude);


	/**
	 *
	 * 模糊查询（证书名称）
	 * @param page
	 * @param aptitude
	 * @return
	 */
	List<AptitudeVO> selectAptitudeDim(IPage page, @Param("aptitude") AptitudeVO aptitude);

	/**
	 * 根据主键查询对应附件
	 *
	 * @return
	 */
	List<AptitudeVO> selectListId(Long aptitudeId);

}
