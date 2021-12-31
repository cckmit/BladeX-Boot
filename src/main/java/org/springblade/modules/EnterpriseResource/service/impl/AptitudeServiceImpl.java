
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
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.constant.BladeConstant;
import org.springblade.core.tool.utils.*;
import org.springblade.modules.EnterpriseResource.dto.AptitudeDTO;
import org.springblade.modules.EnterpriseResource.entity.AllFile;
import org.springblade.modules.EnterpriseResource.entity.Aptitude;
import org.springblade.modules.EnterpriseResource.entity.AptitudeCatalogue;
import org.springblade.modules.EnterpriseResource.entity.modelFile;
import org.springblade.modules.EnterpriseResource.excel.AptitudeExcel;
import org.springblade.modules.EnterpriseResource.excel.UploadFile;
import org.springblade.modules.EnterpriseResource.mapper.AptitudeMapper;
import org.springblade.modules.EnterpriseResource.service.IAptitudeCatalogueService;
import org.springblade.modules.EnterpriseResource.service.IAptitudeService;
import org.springblade.modules.EnterpriseResource.service.IFileService;
import org.springblade.modules.EnterpriseResource.vo.AptitudeVO;
import org.springblade.modules.EnterpriseResource.vo.demo;
import org.springblade.modules.system.entity.Dept;
import org.springblade.modules.system.service.IDeptService;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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

	@Autowired
	private  IDeptService deptService;

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
		for(modelFile tmp:demo.getList()){
			AllFile file = new AllFile();
			file.setObjectId(A);
			file.setObjectValue(RescoreEnum.RESCORE_APTITUDE.getValue());
			file.setFileName(tmp.getOriginalName());
			file.setLink(tmp.getLink());
			file.setFileSize(tmp.getFileSize());
			file.setOriginalName(tmp.getOriginalName());
			file.setExtension(tmp.getFileSuffix());
			file.setName(tmp.getFileName());
			file.setElementIdex(tmp.getElementIdex());
			file.setDomain(tmp.getDomain());
			fileService.save(file);
		}
	return true;
	}
	@Override
	public void update(demo demo) {
		baseMapper.updateById(demo.getAptitude());
		List<AllFile> fileList01= fileService.selectFileListID(demo.getAptitude().getId());
		for (AllFile list:fileList01){
			fileService.removeById(list.getId());
		}
		for (modelFile tmp : demo.getList()) {
			AllFile file = new AllFile();
			Long A = demo.getAptitude().getId();
			file.setFileSize(tmp.getFileSize());
			file.setObjectId(A);
			file.setObjectValue(RescoreEnum.RESCORE_APTITUDE.getValue());
			file.setFileName(tmp.getOriginalName());
			file.setLink(tmp.getLink());
			file.setFileSize(tmp.getFileSize());
			file.setOriginalName(tmp.getOriginalName());
			file.setFileSuffix(tmp.getFileSuffix());
			file.setName(tmp.getFileName());
			file.setElementIdex(tmp.getElementIdex());
			file.setDomain(tmp.getDomain());
			fileService.saveOrUpdate(file);
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
			Aptitude.setCertificateTypeName(DictCache.getValue(DictEnum.aptitudeCertificateType,Aptitude.getCertificateType()));
			Aptitude.setClassTypeName(DictCache.getValue(DictEnum.classType,Aptitude.getClassType()));

			//省公司名称
			Dept deptId =  deptService.selectID(Aptitude.getProvincialCompanyId());
			Aptitude.setProvincialCompanyNames(deptId.getFullName());
			//公司名称
			Dept deptId01=  deptService.selectID(Aptitude.getAptitudeId());
			Aptitude.setAptitudeNames(deptId01.getFullName());
			//行业领域名称
			AptitudeCatalogue detail = aptitudeCatalogueService.selectAreaName(Aptitude.getTerritoryId());
			Aptitude.setTerritoryName(detail.getAreaName());
			//行业属性名称
			AptitudeCatalogue detail01 = aptitudeCatalogueService.selectAreaName(Aptitude.getPropertyId());
			Aptitude.setPropertyName(detail01.getAreaName());
			//业务类别名称
			AptitudeCatalogue detail02 = aptitudeCatalogueService.selectAreaName(Aptitude.getCategoryId());
			Aptitude.setCategoryName(detail02.getAreaName());
		});

		return aptitudeList;
	}

	@Override
//	@RabbitHandler
//	@RabbitListener(queuesToDeclare = @Queue("rabbitmq_queue_object"))
	public List<AptitudeExcel> selectLsitID(Long id) {
		List<AptitudeExcel> aptitudeList = baseMapper.selectLsitID(id);
		aptitudeList.forEach(Aptitude -> {
			//省公司名称
			Dept deptId =  deptService.selectID(Aptitude.getProvincialCompanyId());
			Aptitude.setProvincialCompanyNames(deptId.getFullName());
			//公司名称
			Dept deptId01=  deptService.selectID(Aptitude.getAptitudeId());
			Aptitude.setAptitudeNames(deptId01.getFullName());

			Aptitude.setCertificateTypeName(DictCache.getValue(DictEnum.aptitudeCertificateType,Aptitude.getCertificateType()));
			Aptitude.setClassTypeName(DictCache.getValue(DictEnum.classType,Aptitude.getClassType()));
			//行业领域名称
			AptitudeCatalogue detail = aptitudeCatalogueService.selectAreaName(Aptitude.getTerritoryId());
			Aptitude.setTerritoryName(detail.getAreaName());
			//行业属性名称
			AptitudeCatalogue detail01 = aptitudeCatalogueService.selectAreaName(Aptitude.getPropertyId());
			Aptitude.setPropertyName(detail01.getAreaName());
			//业务类别名称
			AptitudeCatalogue detail02 = aptitudeCatalogueService.selectAreaName(Aptitude.getCategoryId());
			Aptitude.setCategoryName(detail02.getAreaName());
		});

		return aptitudeList;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean submit(Aptitude aptitude, String imgName) {

		if (StringUtil.isBlank(aptitude.getTenantId())) {
			aptitude.setTenantId(BladeConstant.ADMIN_TENANT_ID);
		}
		Integer aptitudeCount = baseMapper.selectCount(Wrappers.<Aptitude>query().lambda().eq(Aptitude::getProvincialCompanyId, aptitude.getProvincialCompanyId()).eq(Aptitude::getAptitudeId, aptitude.getAptitudeId()).eq(Aptitude::getClassType, aptitude.getClassType()));
		if (aptitudeCount > 0 && Func.isEmpty(aptitude.getId())) {

			throw new ServiceException(StringUtil.format("当前企业资质 已存在!"));


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
			// 等级属性名称ID存入数据库
			aptitude.setClassType(Integer.valueOf(DictCache.getKey(DictEnum.classType,aptitudeExcel.getClassTypeName())));

			aptitude.setCertificateType(Integer.valueOf(DictCache.getKey(DictEnum.aptitudeCertificateType,aptitudeExcel.getCertificateTypeName())));


			//省公司名称
			Dept deptId =  deptService.selectselectName(aptitudeExcel.getProvincialCompanyNames());
			aptitude.setProvincialCompanyId(deptId.getId());
			//公司名称
			Dept deptId01=  deptService.selectselectName(aptitudeExcel.getAptitudeNames());
			aptitude.setAptitudeId(deptId01.getId());
			//行业领域id入库到企业资质
			AptitudeCatalogue selectTerritoryID = aptitudeCatalogueService.selectID(aptitudeExcel.getTerritoryName());
			aptitude.setTerritoryId(selectTerritoryID.getId());
			//行业属性id入库到企业资质
			AptitudeCatalogue selectPropertyID = aptitudeCatalogueService.selectID(aptitudeExcel.getPropertyName());
			aptitude.setPropertyId(selectPropertyID.getId());
			//业务类别id入库到企业资质
			AptitudeCatalogue selectCategoryID = aptitudeCatalogueService.selectID(aptitudeExcel.getCategoryName());
			aptitude.setCategoryId(selectCategoryID.getId());
			aptitude.setImgName(aptitudeExcel.getImageName());
			// 设置租户ID
			if (!AuthUtil.isAdministrator() || StringUtil.isBlank(aptitude.getTenantId())) {
				aptitude.setTenantId(AuthUtil.getTenantId());
			}
			try{
				this.submit(aptitude,imgName);
			}catch (ServiceException e){
				throw new ServiceException(StringUtil.format("当前企业资质 已存在!"));
			}


		});
	}

}
