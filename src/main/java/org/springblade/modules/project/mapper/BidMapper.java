
package org.springblade.modules.project.mapper;

import org.springblade.modules.project.dto.BidListDTO;
import org.springblade.modules.project.entity.Bid;
import org.springblade.modules.project.vo.BidVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

/**
 *  Mapper 接口
 *
 * @author BladeX
 * @since 2021-07-18
 */
public interface BidMapper extends BaseMapper<Bid> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param bid
	 * @return
	 */
	List<BidVO> selectBidPage(IPage page, BidVO bid);

	List<BidListDTO> selectBidList(IPage page, BidListDTO bid);



/***********************************************手机端接口*********************************************************************/
	/**
	 *
	 *手机端列表+各种高级筛选查询
	 *
	 * @param page
	 * @param bid
	 * @return
	 */
List<BidVO> selectBidListVO (IPage page ,BidVO bid);


}
