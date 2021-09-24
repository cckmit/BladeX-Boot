
package org.springblade.modules.EnterpriseResource.service.impl;

;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.modules.EnterpriseResource.entity.AllFile;
import org.springblade.modules.EnterpriseResource.mapper.FileMapper;
import org.springblade.modules.EnterpriseResource.service.IFileService;
import org.springblade.modules.EnterpriseResource.vo.FileVO;
import org.springframework.stereotype.Service;


/**
 * 企业资产附件表 服务实现类
 *
 * @author BladeX
 * @since 2021-09-02
 */
@Service
public class FileServiceImpl extends BaseServiceImpl<FileMapper, AllFile> implements IFileService {

	@Override
	public IPage<FileVO> selectFilePage(IPage<FileVO> page, FileVO file) {
		return page.setRecords(baseMapper.selectFilePage(page, file));
	}


}
