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
package org.springblade.modules.client.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.modules.client.entity.WriteContEntity;
import org.springblade.modules.client.mapper.WriteContMapper;
import org.springblade.modules.client.service.WriteContService;
import org.springframework.stereotype.Service;

/**
 *  服务实现类
 *
 * @author BladeX
 * @since 2021-06-26
 */
@Service
public class WriteContServiceImpl extends ServiceImpl<WriteContMapper, WriteContEntity> implements WriteContService {

}
