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
package org.springblade.modules.EnterpriseResource.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.modules.EnterpriseResource.entity.ProjectCase;
import org.springblade.modules.EnterpriseResource.vo.ProjectCaseVO;
import java.util.Objects;

/**
 * 项目案例表包装类,返回视图层所需的字段
 *
 * @author BladeX
 * @since 2021-09-28
 */
public class ProjectCaseWrapper extends BaseEntityWrapper<ProjectCase, ProjectCaseVO>  {

	public static ProjectCaseWrapper build() {
		return new ProjectCaseWrapper();
 	}

	@Override
	public ProjectCaseVO entityVO(ProjectCase projectCase) {
		ProjectCaseVO projectCaseVO = Objects.requireNonNull(BeanUtil.copy(projectCase, ProjectCaseVO.class));

		//User createUser = UserCache.getUser(projectCase.getCreateUser());
		//User updateUser = UserCache.getUser(projectCase.getUpdateUser());
		//projectCaseVO.setCreateUserName(createUser.getName());
		//projectCaseVO.setUpdateUserName(updateUser.getName());

		return projectCaseVO;
	}

}
