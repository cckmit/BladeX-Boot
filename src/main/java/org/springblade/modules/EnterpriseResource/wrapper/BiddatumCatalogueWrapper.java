
package org.springblade.modules.EnterpriseResource.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.modules.EnterpriseResource.entity.BiddatumCatalogue;
import org.springblade.modules.EnterpriseResource.vo.BiddatumCatalogueVO;
import java.util.Objects;

/**
 * 投标资料目录表包装类,返回视图层所需的字段
 *
 * @author BladeX
 * @since 2021-10-18
 */
public class BiddatumCatalogueWrapper extends BaseEntityWrapper<BiddatumCatalogue, BiddatumCatalogueVO>  {

	public static BiddatumCatalogueWrapper build() {
		return new BiddatumCatalogueWrapper();
 	}

	@Override
	public BiddatumCatalogueVO entityVO(BiddatumCatalogue biddatumCatalogue) {
		BiddatumCatalogueVO biddatumCatalogueVO = Objects.requireNonNull(BeanUtil.copy(biddatumCatalogue, BiddatumCatalogueVO.class));

		//User createUser = UserCache.getUser(biddatumCatalogue.getCreateUser());
		//User updateUser = UserCache.getUser(biddatumCatalogue.getUpdateUser());
		//biddatumCatalogueVO.setCreateUserName(createUser.getName());
		//biddatumCatalogueVO.setUpdateUserName(updateUser.getName());

		return biddatumCatalogueVO;
	}

}
