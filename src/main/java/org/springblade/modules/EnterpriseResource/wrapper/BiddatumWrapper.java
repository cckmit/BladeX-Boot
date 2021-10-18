
package org.springblade.modules.EnterpriseResource.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.modules.EnterpriseResource.entity.Biddatum;
import org.springblade.modules.EnterpriseResource.vo.BiddatumVO;

import java.util.Objects;

/**
 * 投标资料表包装类,返回视图层所需的字段
 *
 * @author BladeX
 * @since 2021-09-23
 */
public class BiddatumWrapper extends BaseEntityWrapper<Biddatum, BiddatumVO> {

	public static BiddatumWrapper build() {
		return new BiddatumWrapper();
 	}

	@Override
	public BiddatumVO entityVO(Biddatum biddatum) {
		BiddatumVO biddatumVO = Objects.requireNonNull(BeanUtil.copy(biddatum, BiddatumVO.class));

		//User createUser = UserCache.getUser(biddatum.getCreateUser());
		//User updateUser = UserCache.getUser(biddatum.getUpdateUser());
		//biddatumVO.setCreateUserName(createUser.getName());
		//biddatumVO.setUpdateUserName(updateUser.getName());

		return biddatumVO;
	}

}
