
package org.springblade.modules.EnterpriseResource.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.modules.EnterpriseResource.entity.File;
import org.springblade.modules.EnterpriseResource.vo.FileVO;
import java.util.Objects;

/**
 * 企业资产附件表包装类,返回视图层所需的字段
 *
 * @author BladeX
 * @since 2021-09-02
 */
public class FileWrapper extends BaseEntityWrapper<File, FileVO> {

	public static FileWrapper build() {
		return new FileWrapper();
 	}

	@Override
	public FileVO entityVO(File file) {
		FileVO fileVO = Objects.requireNonNull(BeanUtil.copy(file, FileVO.class));

		//User createUser = UserCache.getUser(file.getCreateUser());
		//User updateUser = UserCache.getUser(file.getUpdateUser());
		//fileVO.setCreateUserName(createUser.getName());
		//fileVO.setUpdateUserName(updateUser.getName());

		return fileVO;
	}

}
