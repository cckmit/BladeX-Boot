
package org.springblade.modules.EnterpriseResource.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.common.enums.RescoreEnum;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.modules.EnterpriseResource.entity.AllFile;
import org.springblade.modules.EnterpriseResource.entity.Aptitude;
import org.springblade.modules.EnterpriseResource.mapper.AptitudeMapper;
import org.springblade.modules.EnterpriseResource.service.IAptitudeService;
import org.springblade.modules.EnterpriseResource.service.IFileService;
import org.springblade.modules.EnterpriseResource.vo.AptitudeVO;
import org.springblade.modules.EnterpriseResource.vo.AttachmentProveVO;
import org.springblade.modules.EnterpriseResource.vo.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 企业资质表 服务实现类
 *
 * @author BladeX
 * @since 2021-09-02
 */
@Service
public class AptitudeServiceImpl extends BaseServiceImpl<AptitudeMapper, Aptitude> implements IAptitudeService {

	@Autowired
	private  IFileService fileService;


	@Override
	public IPage<AptitudeVO> selectAptitudePage(IPage<AptitudeVO> page, AptitudeVO aptitude) {
		return page.setRecords(baseMapper.selectAptitudePage(page, aptitude));
	}

	@Override
	public IPage<AptitudeVO> selectAptitudeDim(IPage page, AptitudeVO aptitude) {
		return page.setRecords(baseMapper.selectAptitudeDim(page,aptitude));
	}

	@Override
	public List<AptitudeVO> selectListId(Long objectId) {
		return baseMapper.selectListId(objectId);
	}

	@Override
	public List<AptitudeVO> aptitudeTypeId(Long aptitudeType) {
		return baseMapper.aptitudeTypeId(aptitudeType);
	}

	@Override
	public boolean saveFile(demo demo) {
		BladeUser currUser = AuthUtil.getUser();
		demo.getAptitude().setTenantId(currUser.getTenantId());
		baseMapper.insert(demo.getAptitude());
		Long A = demo.getAptitude().getId();
		for(AllFile tmp:demo.getList()){
			tmp.setObjectId(A);
			tmp.setObjectValue(RescoreEnum.RESCORE_APTITUDE.getValue());
			tmp.setFileName(demo.getAptitude().getCertificatesName());
			fileService.save(tmp);
		}
	return true;
	}
	@Override
	public void update(demo demo) {
		baseMapper.updateById(demo.getAptitude());
		for (AllFile tmp : demo.getList()) {
			fileService.updateById(tmp);

		}
	}



}
