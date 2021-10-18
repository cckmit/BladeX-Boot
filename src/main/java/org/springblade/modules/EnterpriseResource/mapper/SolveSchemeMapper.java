
package org.springblade.modules.EnterpriseResource.mapper;

import org.springblade.modules.EnterpriseResource.entity.SolveScheme;
import org.springblade.modules.EnterpriseResource.vo.SolveSchemeVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

/**
 * 解决方案表 Mapper 接口
 *
 * @author BladeX
 * @since 2021-09-28
 */
public interface SolveSchemeMapper extends BaseMapper<SolveScheme> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param solveScheme
	 * @return
	 */
	List<SolveSchemeVO> selectSolveSchemePage(IPage page, SolveSchemeVO solveScheme);


	/**
	 * 根据主键查询对应附件
	 *
	 * @return
	 */
	List<SolveSchemeVO> selectListId(Long objectId);


}
