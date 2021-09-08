
package org.springblade.modules.EnterpriseResource.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.EnterpriseResource.entity.File;
import org.springblade.modules.EnterpriseResource.vo.FileVO;

import java.util.List;

/**
 * 企业资产附件表 Mapper 接口
 *
 * @author BladeX
 * @since 2021-09-02
 */
public interface FileMapper extends BaseMapper<File> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param file
	 * @return
	 */
	List<FileVO> selectFilePage(IPage page, FileVO file);

}
