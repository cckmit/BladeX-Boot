
package org.springblade.modules.EnterpriseResource.service;

import org.springblade.modules.EnterpriseResource.entity.ProjectCase;

import org.springblade.modules.EnterpriseResource.vo.ProjectCaseDemo;
import org.springblade.modules.EnterpriseResource.vo.ProjectCaseVO;
import org.springblade.core.mp.base.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 项目案例表 服务类
 *
 * @author BladeX
 * @since 2021-09-28
 */
public interface IProjectCaseService extends BaseService<ProjectCase> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param projectCase
	 * @return
	 */
	IPage<ProjectCaseVO> selectProjectCasePage(IPage<ProjectCaseVO> page, ProjectCaseVO projectCase);

	/**
	 * 根据主键查询对应附件
	 *
	 * @return
	 */
	List<ProjectCaseVO> selectListId(Long objectId);


	boolean saveFile(ProjectCaseDemo demo);

	void update(ProjectCaseDemo demo);


}
