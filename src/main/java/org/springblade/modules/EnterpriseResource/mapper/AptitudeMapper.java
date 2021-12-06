
package org.springblade.modules.EnterpriseResource.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.springblade.modules.EnterpriseResource.dto.AptitudeDTO;
import org.springblade.modules.EnterpriseResource.entity.Aptitude;
import org.springblade.modules.EnterpriseResource.excel.AptitudeExcel;
import org.springblade.modules.EnterpriseResource.vo.AptitudeVO;
import org.springblade.modules.system.entity.User;


import java.util.List;

/**
 * 企业资质表 Mapper 接口
 *
 * @author BladeX
 * @since 2021-09-02
 */
public interface AptitudeMapper extends BaseMapper<Aptitude> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param aptitude
	 * @return
	 */
	List<AptitudeVO> selectAptitudePage(IPage page, AptitudeVO aptitude);


	/**
	 *
	 * 模糊查询（证书名称）
	 * @param page
	 * @param aptitude
	 * @return
	 */
	List<AptitudeVO> selectAptitudeDim(IPage page, @Param("aptitude") AptitudeVO aptitude);

	/**
	 * 根据主键查询对应附件
	 *
	 * @return
	 */
	List<AptitudeVO> selectListId(Long objectId);

	/**
	 *
	 * 根据主键查询父子级数据
	 *
	 * @return
	 */
	List<Aptitude> selectcatalogueLsit(IPage page,Long id);

	/**
	 *
	 * 不同类型查询相关联的信息
	 *
	 * @return
	 */
	List<AptitudeVO> aptitudeTypeId(Long aptitudeType);

	/**
	 *
	 *  根据id 查询该id下面的所有附件地址
	 *
	 * @return
	 */
	AptitudeDTO selectFileLsit (Long id);

	/**
	 *
	 *  根据id 查询详情以及栏目名称
	 *
	 * @return
	 */
	AptitudeDTO selectDetail (Long id);



	/**
	 * 获取导出企业资质数据
	 *
	 * @param queryWrapper
	 * @return
	 */
	List<AptitudeExcel> exportAptitude(@Param("ew") Wrapper<Aptitude> queryWrapper);

}
