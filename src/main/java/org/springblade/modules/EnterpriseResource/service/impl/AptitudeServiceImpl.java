
package org.springblade.modules.EnterpriseResource.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.commons.fileupload.FileItem;
import org.springblade.common.cache.DictCache;
import org.springblade.common.enums.DictEnum;
import org.springblade.common.enums.RescoreEnum;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.constant.BladeConstant;
import org.springblade.core.tool.utils.*;
import org.springblade.modules.EnterpriseResource.dto.AptitudeDTO;
import org.springblade.modules.EnterpriseResource.entity.AllFile;
import org.springblade.modules.EnterpriseResource.entity.Aptitude;
import org.springblade.modules.EnterpriseResource.entity.AptitudeCatalogue;
import org.springblade.modules.EnterpriseResource.excel.AptitudeExcel;
import org.springblade.modules.EnterpriseResource.excel.UploadFile;
import org.springblade.modules.EnterpriseResource.mapper.AptitudeMapper;
import org.springblade.modules.EnterpriseResource.service.IAptitudeCatalogueService;
import org.springblade.modules.EnterpriseResource.service.IAptitudeService;
import org.springblade.modules.EnterpriseResource.service.IFileService;
import org.springblade.modules.EnterpriseResource.vo.AptitudeVO;
import org.springblade.modules.EnterpriseResource.vo.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;
import java.util.Objects;

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
	public IPage<Aptitude> selectTenantLsit(IPage page, Long id) {
		return page.setRecords(baseMapper.selectTenantLsit(page,id));
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

	@Override
	public List<AptitudeExcel> selectLsitID(Long id) {
		List<AptitudeExcel> aptitudeList = baseMapper.selectLsitID(id);
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

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean submit(Aptitude aptitude,String imgName) {

		if (StringUtil.isBlank(aptitude.getTenantId())) {
			aptitude.setTenantId(BladeConstant.ADMIN_TENANT_ID);
		}
		Integer aptitudeCount = baseMapper.selectCount(Wrappers.<Aptitude>query().lambda().eq(Aptitude::getProvincialCompanyName, aptitude.getProvincialCompanyName()).eq(Aptitude::getAptitudeName, aptitude.getAptitudeName()).eq(Aptitude::getClassType, aptitude.getClassType()));
		if (aptitudeCount > 0 && Func.isEmpty(aptitude.getId())) {
			String a= DictCache.getValue(DictEnum.provincialCompanyName,aptitude.getProvincialCompanyName());
			String b = DictCache.getValue(DictEnum.aptitudeName,aptitude.getAptitudeName());
			String c = DictCache.getValue(DictEnum.classType,aptitude.getClassType());
			throw new ServiceException(StringUtil.format("当前企业资质 [{}],[{}],[{}] 已存在!", a,b,c));
		}
		Boolean gainId = save(aptitude);
		String a = aptitude.getImgName();
		String  imgsAddress =imgName+a;
		FileItem fileItem = UploadFile.createFileItem(imgsAddress);
		MultipartFile mfile = new CommonsMultipartFile(fileItem);
		Long gitId = aptitude.getId();
		UploadFile.put(mfile,a,gitId);
		return gainId;
	}


	@Override
	@Transactional(rollbackFor = Exception.class)
	public void importAptitude(List<AptitudeExcel> data, Boolean isCovered, String imgName) {

		data.forEach(aptitudeExcel -> {
			Aptitude aptitude = Objects.requireNonNull(BeanUtil.copy(aptitudeExcel, Aptitude.class));
			//获取省公司名称ID存入到数据库
			aptitude.setProvincialCompanyName(Integer.valueOf(DictCache.getKey(DictEnum.provincialCompanyName, aptitudeExcel.getProvincialCompanyNames())));
			// 获取证书类别名称ID存入数据库
			aptitude.setCertificateType(Integer.valueOf(DictCache.getKey(DictEnum.aptitudeCertificateType,aptitudeExcel.getCertificateTypeName())));
			// 等级属性名称ID存入数据库
			aptitude.setClassType(Integer.valueOf(DictCache.getKey(DictEnum.classType,aptitudeExcel.getClassTypeName())));

			// 获取企业名称ID存入数据库
			aptitude.setAptitudeName(Integer.valueOf(DictCache.getKey(DictEnum.aptitudeName,aptitudeExcel.getAptitudeNames())));

			AptitudeCatalogue detail = aptitudeCatalogueService.selectID(aptitudeExcel.getAreaName());
			//aptitude.setColumnId(detail.getId());
			aptitude.setImgName(aptitudeExcel.getImageName());
			// 设置租户ID
			if (!AuthUtil.isAdministrator() || StringUtil.isBlank(aptitude.getTenantId())) {
				aptitude.setTenantId(AuthUtil.getTenantId());
			}
			this.submit(aptitude,imgName);

		});
	}

}
