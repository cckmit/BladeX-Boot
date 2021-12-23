
package org.springblade.modules.EnterpriseResource.service.impl;

import org.springblade.common.enums.RescoreEnum;
import org.springblade.common.utils.EncryptedIDUtil;
import org.springblade.common.utils.EncryptionDecryption;
import org.springblade.modules.EnterpriseResource.entity.AllFile;
import org.springblade.modules.EnterpriseResource.entity.ProjectCase;
import org.springblade.modules.EnterpriseResource.service.IFileService;
import org.springblade.modules.EnterpriseResource.vo.ProjectCaseDemo;
import org.springblade.modules.EnterpriseResource.vo.ProjectCaseVO;
import org.springblade.modules.EnterpriseResource.mapper.ProjectCaseMapper;
import org.springblade.modules.EnterpriseResource.service.IProjectCaseService;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 项目案例表 服务实现类
 *
 * @author BladeX
 * @since 2021-09-28
 */
@Service
public class ProjectCaseServiceImpl extends BaseServiceImpl<ProjectCaseMapper, ProjectCase> implements IProjectCaseService {


	@Autowired
	private IFileService fileService;

	@Override
	public IPage<ProjectCaseVO> selectProjectCasePage(IPage<ProjectCaseVO> page, ProjectCaseVO projectCase) {
		return page.setRecords(baseMapper.selectProjectCasePage(page, projectCase));
	}

	@Override
	public List<ProjectCaseVO> selectListId(Long objectId) {
		return baseMapper.selectListId(objectId);
	}

	@Override
	public boolean saveFile(ProjectCaseDemo demo) {
		if(EncryptedIDUtil.CheckMobilePhoneNum(demo.getProjectCase().getPhone())==true){
			demo.getProjectCase().setPhone(EncryptionDecryption.encryptString(demo.getProjectCase().getPhone()));
			baseMapper.insert(demo.getProjectCase());
			Long A =demo.getProjectCase().getId();
			for (AllFile temp:demo.getList()){
				temp.setObjectId(A);
				temp.setObjectValue(RescoreEnum.RESCORE_PROIECTCASE.getValue());
				fileService.save(temp);
			}
		}else {
	return false;
		}
	return true;
	}

	@Override
	public void update(ProjectCaseDemo demo) {
	baseMapper.updateById(demo.getProjectCase());
	for (AllFile temp:demo.getList()){
		fileService.updateById(temp);
	}
	}

}
