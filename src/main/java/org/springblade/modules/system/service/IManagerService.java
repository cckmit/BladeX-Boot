
package org.springblade.modules.system.service;

import org.springblade.modules.system.entity.Manager;
import org.springblade.modules.system.vo.ManagerVO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

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
	IPage<Manager> selectManagerList(IPage<ManagerVO> page, ManagerVO manager);

	/**
	 * 单查详情以及显示用户名字
	 *
	 * @return
	 */
	Manager selectManagerDetail(Long id);




	/*
	* 连表查询项目经理
	* */
	IPage<ManagerVO> selectManagerVOPage(IPage<ManagerVO> page, ManagerVO manager);
}
