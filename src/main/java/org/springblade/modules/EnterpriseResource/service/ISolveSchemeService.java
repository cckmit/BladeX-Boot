
package org.springblade.modules.EnterpriseResource.service;

import org.springblade.modules.EnterpriseResource.entity.SolveScheme;
import org.springblade.modules.EnterpriseResource.vo.SolveSchemeDemo;
import org.springblade.modules.EnterpriseResource.vo.SolveSchemeVO;
import org.springblade.core.mp.base.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 解决方案表 服务类
 *
 * @author BladeX
 * @since 2021-09-28
 */
public interface ISolveSchemeService extends BaseService<SolveScheme> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param solveScheme
	 * @return
	 */
	IPage<SolveSchemeVO> selectSolveSchemePage(IPage<SolveSchemeVO> page, SolveSchemeVO solveScheme);


	/**
	 * 根据主键查询对应附件
	 *
	 * @return
	 */
	List<SolveSchemeVO> selectListId(Long objectId);


	boolean saveFile(SolveSchemeDemo demo);

	void update(SolveSchemeDemo demo);

}
