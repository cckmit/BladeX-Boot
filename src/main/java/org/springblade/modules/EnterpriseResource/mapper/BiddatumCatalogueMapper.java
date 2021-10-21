
package org.springblade.modules.EnterpriseResource.mapper;

import org.springblade.modules.EnterpriseResource.entity.BiddatumCatalogue;
import org.springblade.modules.EnterpriseResource.vo.BiddatumCatalogueVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

/**
 * 投标资料目录表 Mapper 接口
 *
 * @author BladeX
 * @since 2021-10-18
 */
public interface BiddatumCatalogueMapper extends BaseMapper<BiddatumCatalogue> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param biddatumCatalogue
	 * @return
	 */
	List<BiddatumCatalogueVO> selectBiddatumCataloguePage(IPage page, BiddatumCatalogueVO biddatumCatalogue);

	/**
	 * 第一级栏目集合
	 *
	 * @return
	 */
	List<BiddatumCatalogueVO> selectCatalog();

}
