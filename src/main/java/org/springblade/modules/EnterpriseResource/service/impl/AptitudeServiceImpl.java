
package org.springblade.modules.EnterpriseResource.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.commons.fileupload.FileItem;
import org.springblade.common.cache.DictCache;
import org.springblade.common.enums.DictEnum;
import org.springblade.common.enums.RescoreEnum;
import org.springblade.common.utils.PictureProcessing;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.constant.BladeConstant;
import org.springblade.core.tool.utils.*;
import org.springblade.modules.EnterpriseResource.dto.AptitudeDTO;
import org.springblade.modules.EnterpriseResource.entity.*;
import org.springblade.modules.EnterpriseResource.excel.AptitudeExcel;
import org.springblade.modules.EnterpriseResource.excel.UploadFile;
import org.springblade.modules.EnterpriseResource.mapper.AptitudeMapper;
import org.springblade.modules.EnterpriseResource.mapper.DeriveRecordMapper;
import org.springblade.modules.EnterpriseResource.service.*;
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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * ??????????????? ???????????????
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

	@Autowired
	private  IDeriveRecordService deriveRecordService;

	@Autowired
	private  IEnterpriseLogService enterpriseLogService;

	@Autowired
	private  IAptitudeCertificateService aptitudeCertificateService;

	@Autowired
	private  IAptitudeGradeService aptitudeGradeService;

	//windows??????
	private static   String pathName = File.separator+"temporaryFiles";
	//?????????????????????
	//private static   String pathName = "temporaryFiles";

	//????????????????????????
	private static   String AddsName = File.separator+ "enterpriseFolder";
	//????????????????????????
	private static   String dec = File.separator+"unzipFolder";

	//????????????????????????
	private static   String watermarkPath = File.separator+"watermarkFolder";
	@Override
	public IPage<AptitudeVO> selectAptitudePage(IPage<AptitudeVO> page, AptitudeVO aptitude) {
		return page.setRecords(baseMapper.selectAptitudePage(page, aptitude));
	}

	@Override
	public IPage<AptitudeVO> selectLongTerm(IPage<AptitudeVO> page, AptitudeVO aptitude) {
		return page.setRecords(baseMapper.selectLongTerm(page, aptitude));
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
		saveOrUpdate(demo.getAptitude());
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
			file.setName(tmp.getName());
			file.setElementIdex(tmp.getElementIdex());
			file.setDomain(tmp.getDomain());
			fileService.saveOrUpdate(file);
		}
	}

	@Override
	public AptitudeDTO selectFileLsit(Long id) {
		AptitudeDTO aptitude = baseMapper.selectFileLsit(id);
		//???????????????
		Dept deptId =  deptService.selectID(aptitude.getProvincialCompanyId());
		aptitude.setProvincialCompanyNames(deptId.getFullName());
		//????????????
		Dept deptId01=  deptService.selectID(aptitude.getAptitudeId());
		aptitude.setAptitudeNames(deptId01.getFullName());
		//??????????????????
		AptitudeCatalogue detail = aptitudeCatalogueService.selectAreaName(aptitude.getTerritoryId());
		aptitude.setTerritoryName(detail.getAreaName());
		//??????????????????
		AptitudeCatalogue detail01 = aptitudeCatalogueService.selectAreaName(aptitude.getPropertyId());
		aptitude.setPropertyName(detail01.getAreaName());
		//??????????????????
		AptitudeCatalogue detail02 = aptitudeCatalogueService.selectAreaName(aptitude.getCategoryId());
		aptitude.setCategoryName(detail02.getAreaName());

		//??????????????????
		AptitudeCertificate  aptitudeCatalogue = aptitudeCertificateService.selectId1(aptitude.getCertificateType());
		aptitude.setCertificateTypeName(aptitudeCatalogue.getCertificateName());
		//Aptitude.setCertificateTypeName(DictCache.getValue(DictEnum.aptitudeCertificateType,Aptitude.getCertificateType()));
		//????????????
		AptitudeGrade 	aptitudeGrade = aptitudeGradeService.selectId(aptitude.getClassType());
		aptitude.setClassTypeName(aptitudeGrade.getGradeName());
		//Aptitude.setClassTypeName(DictCache.getValue(DictEnum.classType,Aptitude.getClassType()));
		return aptitude;
	}


	@Override
	public AptitudeDTO selectDetail(Long id) {
		return baseMapper.selectDetail(id);

	}

	@Override
	public List<AptitudeExcel> exportAptitude(Wrapper<Aptitude> queryWrapper) {
		List<AptitudeExcel> aptitudeList = baseMapper.exportAptitude(queryWrapper);
		aptitudeList.forEach(Aptitude -> {

			//??????????????????
			AptitudeCertificate  aptitudeCatalogue = aptitudeCertificateService.selectId1(Aptitude.getCertificateType());
			Aptitude.setCertificateTypeName(aptitudeCatalogue.getCertificateName());
			//Aptitude.setCertificateTypeName(DictCache.getValue(DictEnum.aptitudeCertificateType,Aptitude.getCertificateType()));
			//????????????
			AptitudeGrade 	aptitudeGrade = aptitudeGradeService.selectId(Aptitude.getClassType());
			Aptitude.setClassTypeName(aptitudeGrade.getGradeName());
			//Aptitude.setClassTypeName(DictCache.getValue(DictEnum.classType,Aptitude.getClassType()));

			//???????????????
			Dept deptId =  deptService.selectID(Aptitude.getProvincialCompanyId());
			Aptitude.setProvincialCompanyNames(deptId.getFullName());
			//????????????
			Dept deptId01=  deptService.selectID(Aptitude.getAptitudeId());
			Aptitude.setAptitudeNames(deptId01.getFullName());
			//??????????????????
			AptitudeCatalogue detail = aptitudeCatalogueService.selectAreaName(Aptitude.getTerritoryId());
			Aptitude.setTerritoryName(detail.getAreaName());
			//??????????????????
			AptitudeCatalogue detail01 = aptitudeCatalogueService.selectAreaName(Aptitude.getPropertyId());
			Aptitude.setPropertyName(detail01.getAreaName());
			//??????????????????
			AptitudeCatalogue detail02 = aptitudeCatalogueService.selectAreaName(Aptitude.getCategoryId());
			Aptitude.setCategoryName(detail02.getAreaName());
		});

		return aptitudeList;
	}

	@Override
	@Transactional
	@RabbitHandler
	@RabbitListener(queuesToDeclare = @Queue("rabbitmq_queue_object"))
	public void selectLsitID(String aptitudeIds) {
		JSONObject json= JSONObject.parseObject(aptitudeIds);
		Object  objectLong = json.get("ids01");
		long recordId = Long.valueOf(String.valueOf(objectLong)).longValue();
		JSON object= (JSON) json.get("aptitude");
		Aptitude aptitude = JSONObject.toJavaObject(object ,Aptitude.class);
		List <Long> SSS = (List<Long>) json.get("idss");

		if (SSS==null){
			List<AptitudeExcel> aptitudeList = baseMapper.selectLsitID(aptitude);
			aptitudeList.forEach(Aptitude -> {

				//??????????????????
				AptitudeCertificate  aptitudeCatalogue = aptitudeCertificateService.selectId1(Aptitude.getCertificateType());
				Aptitude.setCertificateTypeName(aptitudeCatalogue.getCertificateName());
				//Aptitude.setCertificateTypeName(DictCache.getValue(DictEnum.aptitudeCertificateType,Aptitude.getCertificateType()));
				//????????????
				AptitudeGrade 	aptitudeGrade = aptitudeGradeService.selectId(Aptitude.getClassType());
				Aptitude.setClassTypeName(aptitudeGrade.getGradeName());
				//Aptitude.setClassTypeName(DictCache.getValue(DictEnum.classType,Aptitude.getClassType()));
				//???????????????
				Dept deptId =  deptService.selectID(Aptitude.getProvincialCompanyId());
				Aptitude.setProvincialCompanyNames(deptId.getFullName());
				//????????????
				Dept deptId01=  deptService.selectID(Aptitude.getAptitudeId());
				Aptitude.setAptitudeNames(deptId01.getFullName());
				//??????????????????
				AptitudeCatalogue detail = aptitudeCatalogueService.selectAreaName(Aptitude.getTerritoryId());
				Aptitude.setTerritoryName(detail.getAreaName());
				//??????????????????
				AptitudeCatalogue detail01 = aptitudeCatalogueService.selectAreaName(Aptitude.getPropertyId());
				Aptitude.setPropertyName(detail01.getAreaName());
				//??????????????????
				AptitudeCatalogue detail02 = aptitudeCatalogueService.selectAreaName(Aptitude.getCategoryId());
				Aptitude.setCategoryName(detail02.getAreaName());
			});
			String docStorePath = System.getProperty( "user.dir" );
			File temp = new File(docStorePath+pathName);
			//????????????????????????  ???????????????
			if (!temp.exists()) {
				temp.mkdir();
			}
			try {
				String str = UploadFile.getCode(5);
				String Address = docStorePath+pathName+File.separator+str+".xlsx";
				OutputStream outputStream = new FileOutputStream(Address);
				EasyExcel.write(outputStream ,AptitudeExcel.class);
				EasyExcel.write(outputStream,AptitudeExcel.class).sheet("????????????").doWrite(aptitudeList);
				EnterpriseLog enterpriseLogId = enterpriseLogService.getById(recordId);
				enterpriseLogId.setStatus(1);
				enterpriseLogId.setAddress(Address);
				enterpriseLogService.updateById(enterpriseLogId);
				List<DeriveRecord> list = new ArrayList<>();
				for (AptitudeExcel  a:aptitudeList){
				DeriveRecord 	record = new DeriveRecord();
					record.setMasterId(a.getId());
					record.setModuleId(recordId);
					Integer enterpriseId= RescoreEnum.RESCORE_APTITUDE.getValue();
					record.setEnterpriseId(Integer.toUnsignedLong(enterpriseId));
					list.add(record);
				}
				deriveRecordService.saveBatch(list);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}
		if (SSS!=null){
			List<AptitudeExcel> aptitudeList = new ArrayList();
			SSS.forEach((id) -> {
				AptitudeExcel List = baseMapper.selectIds(id);
				aptitudeList.add(List);
			});

			aptitudeList.forEach(Aptitude -> {

				//??????????????????
				AptitudeCertificate  aptitudeCatalogue = aptitudeCertificateService.selectId1(Aptitude.getCertificateType());
				Aptitude.setCertificateTypeName(aptitudeCatalogue.getCertificateName());
				//Aptitude.setCertificateTypeName(DictCache.getValue(DictEnum.aptitudeCertificateType,Aptitude.getCertificateType()));
				//????????????
				AptitudeGrade 	aptitudeGrade = aptitudeGradeService.selectId(Aptitude.getClassType());
				Aptitude.setClassTypeName(aptitudeGrade.getGradeName());
				//Aptitude.setClassTypeName(DictCache.getValue(DictEnum.classType,Aptitude.getClassType()));

				//???????????????
				Dept deptId =  deptService.selectID(Aptitude.getProvincialCompanyId());
				Aptitude.setProvincialCompanyNames(deptId.getFullName());
				//????????????
				Dept deptId01=  deptService.selectID(Aptitude.getAptitudeId());
				Aptitude.setAptitudeNames(deptId01.getFullName());
				//??????????????????
				AptitudeCatalogue detail = aptitudeCatalogueService.selectAreaName(Aptitude.getTerritoryId());
				Aptitude.setTerritoryName(detail.getAreaName());
				//??????????????????
				AptitudeCatalogue detail01 = aptitudeCatalogueService.selectAreaName(Aptitude.getPropertyId());
				Aptitude.setPropertyName(detail01.getAreaName());
				//??????????????????
				AptitudeCatalogue detail02 = aptitudeCatalogueService.selectAreaName(Aptitude.getCategoryId());
				Aptitude.setCategoryName(detail02.getAreaName());
			});
			String docStorePath = System.getProperty( "user.dir" );
			File temp = new File(docStorePath+pathName);
			//????????????????????????  ???????????????
			if (!temp.exists()) {
				temp.mkdir();
			}
			try {
				String str = UploadFile.getCode(5);
				String Address =docStorePath+pathName+File.separator+str+".xlsx";
				OutputStream outputStream = new FileOutputStream(Address);
				EasyExcel.write(outputStream ,AptitudeExcel.class);
				EasyExcel.write(outputStream,AptitudeExcel.class).sheet("????????????").doWrite(aptitudeList);
				EnterpriseLog enterpriseLogId = enterpriseLogService.getById(recordId);
				enterpriseLogId.setStatus(1);
				enterpriseLogId.setAddress(Address);
				enterpriseLogService.updateById(enterpriseLogId);
				List<DeriveRecord> list = new ArrayList<>();
				for (AptitudeExcel  a:aptitudeList){
					DeriveRecord 	record = new DeriveRecord();
					record.setMasterId(a.getId());
					record.setModuleId(recordId);
					Integer enterpriseId= RescoreEnum.RESCORE_APTITUDE.getValue();
					record.setEnterpriseId(Integer.toUnsignedLong(enterpriseId));
					list.add(record);
				}
				deriveRecordService.saveBatch(list);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}


	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean submit(Aptitude aptitude, String imgName,String number) {
		if (StringUtil.isBlank(aptitude.getTenantId())) {
			aptitude.setTenantId(BladeConstant.ADMIN_TENANT_ID);
		}
		Integer aptitudeCount = baseMapper.selectCount(Wrappers.<Aptitude>query().lambda().eq(Aptitude::getProvincialCompanyId, aptitude.getProvincialCompanyId()).eq(Aptitude::getAptitudeId, aptitude.getAptitudeId()).eq(Aptitude::getClassType, aptitude.getClassType()));
		if (aptitudeCount > 0 && Func.isEmpty(aptitude.getId())) {
			throw new ServiceException(StringUtil.format("?????????????????? ?????????!"));
		}
		Boolean gainId = save(aptitude);
		File  file = new File(imgName);
		File [] fs = file.listFiles();
		for (File fileList:fs){
			String str = fileList.getName();
			String str1=str.substring(0, str.indexOf("-"));
			str.startsWith(number);
			if (str1.equals(number)){
				String  imgsAddress =imgName+File.separator+str;
				String docStorePath = System.getProperty( "user.dir" );
				File filePath = new File(docStorePath);
				File temp = new File(filePath+AddsName+dec+watermarkPath);
				//????????????????????????  ???????????????
				if (!temp.exists()) {
					temp.mkdir();
				}
				String  textPath =filePath+AddsName+dec+watermarkPath+File.separator+str;
				//?????????
				String srcImgPath = fileList.getAbsolutePath();
				// ????????????
				String logoText = "????????????????????????????????????,???????????????????????????";
				//??????????????????
				String targerTextPath2 = textPath;
				System.out.println("?????????????????????????????????...");
				// ??????????????????????????????
				PictureProcessing.ImageByText(logoText, srcImgPath, targerTextPath2, -40);
				System.out.println("?????????????????????????????????...");

				FileItem fileItem = UploadFile.createFileItem(imgsAddress);
				MultipartFile mfile = new CommonsMultipartFile(fileItem);

				FileItem fileItem1 = UploadFile.createFileItem(textPath);
				MultipartFile mfile1 = new CommonsMultipartFile(fileItem1);
				Long gitId = aptitude.getId();
				UploadFile.put(mfile,str,gitId,mfile1);


			}
		}
		return gainId;
	}



	@Override
	@Transactional(rollbackFor = Exception.class)
	public void importAptitude(List<AptitudeExcel> data, Boolean isCovered, String imgName) {

		data.forEach(aptitudeExcel -> {
			Aptitude aptitude = Objects.requireNonNull(BeanUtil.copy(aptitudeExcel, Aptitude.class));
			// ??????????????????ID???????????????
			AptitudeGrade 	aptitudeGrade = aptitudeGradeService.selectName(aptitudeExcel.getClassTypeName());
			aptitude.setClassType(aptitudeGrade.getId());
			//??????????????????id
			AptitudeCertificate  aptitudeCatalogue = aptitudeCertificateService.selectName(aptitudeExcel.getCertificateTypeName());
			aptitude.setCertificateType(aptitudeCatalogue.getId());
			//Aptitude.setCertificateTypeName(DictCache.getValue(DictEnum.aptitudeCertificateType,Aptitude.getCertificateType()));

			//???????????????
			Dept deptId =  deptService.selectselectName(aptitudeExcel.getProvincialCompanyNames());
			aptitude.setProvincialCompanyId(deptId.getId());
			//????????????
			Dept deptId01=  deptService.selectselectName(aptitudeExcel.getAptitudeNames());
			aptitude.setAptitudeId(deptId01.getId());
			//????????????id?????????????????????
			AptitudeCatalogue selectTerritoryID = aptitudeCatalogueService.selectID(aptitudeExcel.getTerritoryName(),0);
			aptitude.setTerritoryId(selectTerritoryID.getId());
			//????????????id?????????????????????

			AptitudeCatalogue selectPropertyID = aptitudeCatalogueService.selectID(aptitudeExcel.getPropertyName(),selectTerritoryID.getId().intValue());
			aptitude.setPropertyId(selectPropertyID.getId());
			//????????????id?????????????????????
			AptitudeCatalogue selectCategoryID = aptitudeCatalogueService.selectID(aptitudeExcel.getCategoryName(),selectPropertyID.getId().intValue());
			aptitude.setCategoryId(selectCategoryID.getId());
			// ????????????ID
			if (!AuthUtil.isAdministrator() || StringUtil.isBlank(aptitude.getTenantId())) {
				aptitude.setTenantId(AuthUtil.getTenantId());
			}
			try{

				this.submit(aptitude,imgName,aptitudeExcel.getSerialNumber());
			}catch (ServiceException e){
				throw new ServiceException(StringUtil.format("?????????????????? ?????????!"));
			}
		});
	}



}
