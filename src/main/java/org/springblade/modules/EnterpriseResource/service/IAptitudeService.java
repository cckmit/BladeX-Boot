package org.springblade.modules.EnterpriseResource.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.modules.EnterpriseResource.entity.Aptitude;
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


	void saveFile(demo demo);

	void update(demo demo);
}
