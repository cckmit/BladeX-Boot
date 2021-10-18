
package org.springblade.modules.EnterpriseResource.mapper;

import org.springblade.modules.EnterpriseResource.entity.ProjectCase;
import org.springblade.modules.EnterpriseResource.vo.ProjectCaseVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

/**
 * 项目案例表 Mapper 接口
 *
 * @author BladeX
 * @since 2021-09-28
 */
public interface ProjectCaseMapper extends BaseMapper<ProjectCase> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param projectCase
	 * @return
	 */
	List<ProjectCaseVO> selectProjectCasePage(IPage page, ProjectCaseVO projectCase);

	/**
	 * 根据主键查询对应附件
	 *
	 * @return
	 */
	List<ProjectCaseVO> selectListId(Long objectId);

}
