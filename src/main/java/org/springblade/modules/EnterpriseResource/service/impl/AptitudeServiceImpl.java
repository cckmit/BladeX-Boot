
package org.springblade.modules.EnterpriseResource.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.common.cache.DictCache;
import org.springblade.common.cache.SysCache;
import org.springblade.common.enums.DictEnum;
import org.springblade.common.enums.RescoreEnum;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.utils.StringUtil;
import org.springblade.modules.EnterpriseResource.dto.AptitudeDTO;
import org.springblade.modules.EnterpriseResource.entity.AllFile;
import org.springblade.modules.EnterpriseResource.entity.Aptitude;
import org.springblade.modules.EnterpriseResource.entity.AptitudeCatalogue;
import org.springblade.modules.EnterpriseResource.excel.AptitudeExcel;
import org.springblade.modules.EnterpriseResource.mapper.AptitudeMapper;
import org.springblade.modules.EnterpriseResource.service.IAptitudeCatalogueService;
import org.springblade.modules.EnterpriseResource.service.IAptitudeService;
import org.springblade.modules.EnterpriseResource.service.IFileService;
import org.springblade.modules.EnterpriseResource.vo.AptitudeVO;
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

	@Autowired
	private  IAptitudeCatalogueService aptitudeCatalogueService;


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
	public IPage<Aptitude> selectcatalogueLsit(IPage page, Long id) {
		return page.setRecords(baseMapper.selectcatalogueLsit(page,id));
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

	@Override
	public AptitudeDTO selectFileLsit(Long id) {
		return baseMapper.selectFileLsit(id);
	}

	@Override
	public AptitudeDTO selectDetail(Long id) {
		return baseMapper.selectDetail(id);
	}

	@Override
	public List<AptitudeExcel> exportAptitude(Wrapper<Aptitude> queryWrapper) {
		List<AptitudeExcel> aptitudeList = baseMapper.exportAptitude(queryWrapper);
		aptitudeList.forEach(Aptitude -> {
			Aptitude.setProvincialCompanyNames(DictCache.getValue(DictEnum.provincialCompanyName, Aptitude.getProvincialCompanyName()));
			Aptitude.setAptitudeNames(DictCache.getValue(DictEnum.aptitudeName,Aptitude.getAptitudeName()));
			Aptitude.setCertificateTypeName(DictCache.getValue(DictEnum.aptitudeCertificateType,Aptitude.getCertificateType()));
			Aptitude.setClassTypeName(DictCache.getValue(DictEnum.classType,Aptitude.getClassType()));
			AptitudeCatalogue detail = aptitudeCatalogueService.selectAreaName(Aptitude.getColumnId());
			Aptitude.setAreaName(detail.getAreaName());
		});




		return aptitudeList;
	}


}
