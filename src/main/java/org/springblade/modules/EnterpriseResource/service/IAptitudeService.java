package org.springblade.modules.EnterpriseResource.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.springblade.core.mp.base.BaseService;
import org.springblade.modules.EnterpriseResource.dto.AptitudeDTO;
import org.springblade.modules.EnterpriseResource.entity.Aptitude;
import org.springblade.modules.EnterpriseResource.excel.AptitudeExcel;
import org.springblade.modules.EnterpriseResource.vo.AptitudeVO;
import org.springblade.modules.EnterpriseResource.vo.demo;


import java.util.List;

/**
 * 企业资质表 服务类
 *
 * @author BladeX
 * @since 2021-09-02
 */
public interface IAptitudeService extends BaseService<Aptitude> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param aptitude
	 * @return
	 */
	IPage<AptitudeVO> selectAptitudePage(IPage<AptitudeVO> page, AptitudeVO aptitude);

	/**
	 *
	 * 模糊查询（证书名称）
	 * @param page
	 * @param aptitude
	 * @return
	 */
	IPage<AptitudeVO> selectAptitudeDim(IPage page, AptitudeVO aptitude);


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
	IPage<Aptitude> selectcatalogueLsit(IPage page,Long id);


	/**
	 *
	 * 根据TenantID查询父子级数据
	 *
	 * @return
	 */
	IPage<Aptitude> selectTenantLsit(IPage page,Long id);


	/**
	 *
	 * 根据TenantID查询父子级数据不带页面
	 *
	 * @return
	 */
	List<AptitudeExcel> selectLsitID(Long id);

	/**
	 *
	 * 不同类型查询相关联的信息
	 *
	 * @return
	 */
	List<AptitudeVO> aptitudeTypeId(Long aptitudeType);


	boolean saveFile(demo demo);

	void update(demo demo);


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


	/**
	 * 导入企业资质数据
	 *
	 * @param data
	 * @param isCovered
	 * @return
	 */
	void importAptitude(List<AptitudeExcel> data, Boolean isCovered,String imgName);


	/**
	 * 新增企业资质
	 *
	 * @param aptitude
	 * @return
	 */
	Boolean submit(Aptitude aptitude,String imgName);
}
