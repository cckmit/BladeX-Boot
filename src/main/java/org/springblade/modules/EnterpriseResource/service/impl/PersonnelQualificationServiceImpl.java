
package org.springblade.modules.EnterpriseResource.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.common.enums.RescoreEnum;
import org.springblade.common.utils.EncryptedIDUtil;
import org.springblade.common.utils.EncryptionDecryption;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.modules.EnterpriseResource.entity.AllFile;
import org.springblade.modules.EnterpriseResource.entity.PersonnelQualification;
import org.springblade.modules.EnterpriseResource.mapper.PersonnelQualificationMapper;
import org.springblade.modules.EnterpriseResource.service.IFileService;
import org.springblade.modules.EnterpriseResource.service.IPersonnelQualificationService;
import org.springblade.modules.EnterpriseResource.vo.PersonnelQualificationDemo;
import org.springblade.modules.EnterpriseResource.vo.PersonnelQualificationVO;
import org.springblade.modules.EnterpriseResource.vo.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 人员资质表 服务实现类
 *
 * @author BladeX
 * @since 2021-09-23
 */
@Service
public class PersonnelQualificationServiceImpl extends BaseServiceImpl<PersonnelQualificationMapper, PersonnelQualification> implements IPersonnelQualificationService {

	@Autowired
	private IFileService fileService;

	@Override
	public IPage<PersonnelQualificationVO> selectPersonnelQualificationPage(IPage<PersonnelQualificationVO> page, PersonnelQualificationVO personnelQualification) {
		return page.setRecords(baseMapper.selectPersonnelQualificationPage(page, personnelQualification));
	}

	@Override
	public List<PersonnelQualificationVO> selectListId(Long objectId) {
		return baseMapper.selectListId(objectId);
	}

	@Override
	public boolean saveFile(PersonnelQualificationDemo demo) {
		if(EncryptedIDUtil.isIDNumber(demo.getPersonnelQualification().getCertificateNumber())==true){
			demo.getPersonnelQualification().setCertificateNumber(EncryptionDecryption.encryptString(demo.getPersonnelQualification().getCertificateNumber()));
			baseMapper.insert(demo.getPersonnelQualification());
			Long A = demo.getPersonnelQualification().getId();
			for(AllFile tmp:demo.getList()){
				tmp.setObjectId(A);
				tmp.setObjectValue(RescoreEnum.RESCORE_PERSONNELQUALIFICATION.getValue());
				fileService.save(tmp);
			}

		}else {
		return  false;
		}
	return true;
	}


	@Override
	public void update(PersonnelQualificationDemo demo) {
		baseMapper.updateById(demo.getPersonnelQualification());
		for (AllFile tmp : demo.getList()) {
			fileService.updateById(tmp);
		}
	}
}
