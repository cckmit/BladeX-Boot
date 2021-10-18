
package org.springblade.modules.EnterpriseResource.mapper;

;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.EnterpriseResource.entity.AttachmentProve;
import org.springblade.modules.EnterpriseResource.vo.AttachmentProveVO;

import java.util.List;


/**
 * 社保证明表 Mapper 接口
 *
 * @author BladeX
 * @since 2021-09-23
 */
public interface AttachmentProveMapper extends BaseMapper<AttachmentProve> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param attachmentProve
	 * @return
	 */
	List<AttachmentProveVO> selectAttachmentProvePage(IPage page, AttachmentProveVO attachmentProve);


	/**
	 * 根据主键查询对应附件
	 *
	 * @return
	 */
	List<AttachmentProveVO> selectListId(Long objectId);

}
