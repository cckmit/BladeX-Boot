
package org.springblade.modules.EnterpriseResource.mapper;
import org.springblade.modules.EnterpriseResource.entity.Contract;
import org.springblade.modules.EnterpriseResource.vo.ContractVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

/**
 * 合同表 Mapper 接口
 *
 * @author BladeX
 * @since 2021-09-28
 */
public interface ContractMapper extends BaseMapper<Contract> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param contract
	 * @return
	 */
	List<ContractVO> selectContractPage(IPage page, ContractVO contract);


	/**
	 * 根据主键查询对应附件
	 *
	 * @return
	 */
	List<ContractVO> selectListId(Long objectId);

}
