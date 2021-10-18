
package org.springblade.modules.EnterpriseResource.wrapper;
import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.modules.EnterpriseResource.entity.PersonnelQualification;
import org.springblade.modules.EnterpriseResource.vo.PersonnelQualificationVO;

import java.util.Objects;

/**
 * 人员资质表包装类,返回视图层所需的字段
 *
 * @author BladeX
 * @since 2021-09-23
 */
public class PersonnelQualificationWrapper extends BaseEntityWrapper<PersonnelQualification, PersonnelQualificationVO> {

	public static PersonnelQualificationWrapper build() {
		return new PersonnelQualificationWrapper();
 	}

	@Override
	public PersonnelQualificationVO entityVO(PersonnelQualification personnelQualification) {
		PersonnelQualificationVO personnelQualificationVO = Objects.requireNonNull(BeanUtil.copy(personnelQualification, PersonnelQualificationVO.class));

		//User createUser = UserCache.getUser(personnelQualification.getCreateUser());
		//User updateUser = UserCache.getUser(personnelQualification.getUpdateUser());
		//personnelQualificationVO.setCreateUserName(createUser.getName());
		//personnelQualificationVO.setUpdateUserName(updateUser.getName());

		return personnelQualificationVO;
	}

}
