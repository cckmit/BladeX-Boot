package org.springblade.modules.system.wrapper;
import org.springblade.common.cache.DictCache;
import org.springblade.common.enums.DictEnum;
import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.modules.system.vo.ManagerVO;
import org.springblade.modules.system.vo.ManagerVO1;

import java.util.Objects;

/**
 * 包装类,返回视图层所需的字段
 *
 * @author BladeX
 * @since 2021-07-03
 */
public class BusinessManagerWrapper extends BaseEntityWrapper<ManagerVO, ManagerVO>  {

	public static BusinessManagerWrapper build() {
		return new BusinessManagerWrapper();
 	}

	@Override
	public ManagerVO entityVO(ManagerVO business) {
		ManagerVO managerVO = Objects.requireNonNull(BeanUtil.copy(business, ManagerVO.class));

		//User createUser = UserCache.getUser(business.getCreateUser());
		//User updateUser = UserCache.getUser(business.getUpdateUser());
		//businessVO.setCreateUserName(createUser.getName());
		//businessVO.setUpdateUserName(updateUser.getName());
		String biddingTypeName = DictCache.getValue(DictEnum.project_BiddingType,business.getBiddingType());
		business.setBiddingTypeName(biddingTypeName);

		String industryName = DictCache.getValue(DictEnum.project_Industry,business.getIndustry());
		business.setIndustryName(industryName);

		String regionName = DictCache.getValue(DictEnum.region,business.getRegion());
		business.setRecordName(regionName);

		String clientTypeName = DictCache.getValue(DictEnum.client_type,business.getClientType());
		business.setClientTypeName(clientTypeName);
		String projectCatrgoryName = DictCache.getValue(DictEnum.projectCatrgory,business.getProjectCatrgory());
		business.setProjectCatrgoryName(projectCatrgoryName);

		return managerVO;
	}


}
