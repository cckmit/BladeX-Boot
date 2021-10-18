
package org.springblade.modules.EnterpriseResource.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.EnterpriseResource.entity.PersonnelQualification;
import org.springblade.modules.EnterpriseResource.vo.AttachmentProveVO;
import org.springblade.modules.EnterpriseResource.vo.PersonnelQualificationVO;

import java.util.List;

/**
 * 人员资质表 Mapper 接口
 *
 * @author BladeX
 * @since 2021-09-23
 */
public interface PersonnelQualificationMapper extends BaseMapper<PersonnelQualification> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param personnelQualification
	 * @return
	 */
	List<PersonnelQualificationVO> selectPersonnelQualificationPage(IPage page, PersonnelQualificationVO personnelQualification);


	/**
	 * 根据主键查询对应附件
	 *
	 * @return
	 */
	List<PersonnelQualificationVO> selectListId(Long objectId);


}
