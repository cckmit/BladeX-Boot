
package org.springblade.modules.EnterpriseResource.wrapper;
import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.modules.EnterpriseResource.entity.AttachmentProve;
import org.springblade.modules.EnterpriseResource.vo.AttachmentProveVO;

import java.util.Objects;

/**
 * 社保证明表包装类,返回视图层所需的字段
 *
 * @author BladeX
 * @since 2021-09-23
 */
public class AttachmentProveWrapper extends BaseEntityWrapper<AttachmentProve, AttachmentProveVO> {

	public static AttachmentProveWrapper build() {
		return new AttachmentProveWrapper();
 	}

	@Override
	public AttachmentProveVO entityVO(AttachmentProve attachmentProve) {
		AttachmentProveVO attachmentProveVO = Objects.requireNonNull(BeanUtil.copy(attachmentProve, AttachmentProveVO.class));

		//User createUser = UserCache.getUser(attachmentProve.getCreateUser());
		//User updateUser = UserCache.getUser(attachmentProve.getUpdateUser());
		//attachmentProveVO.setCreateUserName(createUser.getName());
		//attachmentProveVO.setUpdateUserName(updateUser.getName());

		return attachmentProveVO;
	}

}
