
package org.springblade.modules.EnterpriseResource.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.modules.EnterpriseResource.entity.AllFile;
import org.springblade.modules.EnterpriseResource.vo.FileVO;

import java.util.List;

/**
 * 企业资产附件表 服务类
 *
 * @author BladeX
 * @since 2021-09-02
 */
public interface IFileService extends BaseService<AllFile> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param file
	 * @return
	 */
	IPage<FileVO> selectFilePage(IPage<FileVO> page, FileVO file);

	/**
	 * 根据主表id找到对应附件
	 *
	 * @param id
	 * @return
	 */
	List<AllFile> selectFileListID(Long id);

}
