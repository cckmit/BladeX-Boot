
package org.springblade.modules.EnterpriseResource.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.EnterpriseResource.entity.Biddatum;
import org.springblade.modules.EnterpriseResource.vo.BiddatumVO;

import java.util.List;


/**
 * 投标资料表 Mapper 接口
 *
 * @author BladeX
 * @since 2021-09-23
 */
public interface BiddatumMapper extends BaseMapper<Biddatum> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param biddatum
	 * @return
	 */
	List<BiddatumVO> selectBiddatumPage(IPage page, BiddatumVO biddatum);


	/**
	 * 根据主键查询对应附件
	 *
	 * @return
	 */
	List<BiddatumVO> selectListId(Long objectId);

}
