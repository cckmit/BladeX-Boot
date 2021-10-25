
package org.springblade.modules.system.service;

import org.springblade.modules.system.entity.Manager;
import org.springblade.modules.system.entity.Manager01;
import org.springblade.modules.system.vo.ManagerVO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.system.vo.ManagerVO1;

import java.util.List;

/**
 *  服务类
 *
 * @author BladeX
 * @since 2021-06-30
 */
public interface IManagerService extends IService<Manager> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param manager
	 * @return
	 */
	IPage<ManagerVO> selectManagerPage(IPage<ManagerVO> page, ManagerVO manager);



	/**
	 * list列表带用户名字
	 * @param page
	 * @param manager
	 * @return
	 */
	IPage<Manager01> selectManagerList(IPage<ManagerVO> page, ManagerVO manager);

	/**
	 * 单查详情以及显示用户名字
	 *
	 * @return
	 */
	Manager01 selectManagerDetail(Long id);

	/**
	 * 根据项目经理ID查询商机+投标表
	 *
	 * @return
	 */
	List<ManagerVO1> selectProjectBusiness(Long id);


	/*
	* 连表查询项目经理
	* */
	IPage<ManagerVO> selectManagerVOPage(IPage<ManagerVO> page, ManagerVO manager);
}
