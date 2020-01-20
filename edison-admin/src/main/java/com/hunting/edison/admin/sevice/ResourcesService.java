package com.hunting.edison.admin.sevice;

import java.util.List;

import com.hunting.edison.admin.domain.Resources;
import com.hunting.edison.core.service.CurdService;

/**
 * resource管理
 * @author Louis & Edison
 * @date Oct 29, 2018
 */
public interface ResourcesService extends CurdService<Resources> {

	/**
	 * 查询菜单树,用户ID和用户名为空则查询全部
	 * @param menuType 获取菜单类型，0：获取所有菜单，包含按钮，1：获取所有菜单，不包含按钮
	 * @param userId 
	 * @return
	 */
	List<Resources> findTree(String userName, int menuType);

	/**
	 * 根据用户名查找菜单列表
	 * @param userName
	 * @return
	 */
	List<Resources> findByUser(String userName);
}
