
package org.springblade.modules.EnterpriseResource.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.common.enums.RescoreEnum;
import org.springblade.common.utils.EncryptedIDUtil;
import org.springblade.common.utils.EncryptionDecryption;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.modules.EnterpriseResource.entity.AllFile;
import org.springblade.modules.EnterpriseResource.entity.AttachmentProve;
import org.springblade.modules.EnterpriseResource.mapper.AttachmentProveMapper;
import org.springblade.modules.EnterpriseResource.service.IAttachmentProveService;
import org.springblade.modules.EnterpriseResource.service.IFileService;
import org.springblade.modules.EnterpriseResource.vo.AttachmentProveDemo;
import org.springblade.modules.EnterpriseResource.vo.AttachmentProveVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 社保证明表 服务实现类
 *
 * @author BladeX
 * @since 2021-09-23
 */
@Service
public class AttachmentProveServiceImpl extends BaseServiceImpl<AttachmentProveMapper, AttachmentProve> implements IAttachmentProveService {

	@Autowired
	private IFileService fileService;

	@Override
	public IPage<AttachmentProveVO> selectAttachmentProvePage(IPage<AttachmentProveVO> page, AttachmentProveVO attachmentProve) {
		return page.setRecords(baseMapper.selectAttachmentProvePage(page, attachmentProve));
	}

	@Override
	public List<AttachmentProveVO> selectListId(Long objectId) {
		return baseMapper.selectListId(objectId);
	}

	@Override
	public boolean saveFile(AttachmentProveDemo demo) {
		if (EncryptedIDUtil.isIDNumber(demo.getAttachmentProve().getIdNumber())==true){
			demo.getAttachmentProve().setIdNumber(EncryptionDecryption.getPhoneStr(demo.getAttachmentProve().getIdNumber()));
			baseMapper.insert(demo.getAttachmentProve());
		}else {
			return false;
		}
		return true;
	}

	@Override
	public void update(AttachmentProveDemo demo) {
		baseMapper.updateById(demo.getAttachmentProve());
	}

}
