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
package org.springblade.modules.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springblade.modules.project.vo.ChangeDetailVO;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 实体类
 *
 * @author BladeX
 * @since 2021-07-14
 */
@Data
@TableName("project_change")
@ApiModel(value = "Change对象", description = "Change对象")
public class Change implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(type = IdType.INPUT)
	@ApiModelProperty(value = "主键")
	private Long id;
	/**
	 * 修改用户主键
	 */
	@ApiModelProperty(value = "修改用户主键")
	private String changeUser;
	/**
	 * 修改时间
	 */
	@ApiModelProperty(value = "修改时间")
	private LocalDateTime changeTime;
	/**
	 * 商机主键
	 */
	@ApiModelProperty(value = "商机主键")
	private Long businessId;

	private List<ChangeDetailVO> changeDetailList;
}
