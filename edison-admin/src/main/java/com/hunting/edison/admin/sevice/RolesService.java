package com.hunting.edison.admin.sevice;

import java.util.List;

import com.hunting.edison.admin.domain.ACLs;
import com.hunting.edison.admin.domain.Resources;
import com.hunting.edison.admin.domain.Roles;
import com.hunting.edison.core.service.CurdService;

/**
 * role管理
 * @author Louis & Edison
 * @date Oct 29, 2018
 */
public interface RolesService extends CurdService<Roles> {

	/**
	 * 查询全部
	 * @return
	 */
	List<Roles> findAll();

	/**
	 * 查询角色菜单集合
	 * @return
	 */
	List<Resources> findRoleMenus(Integer roleId);

	/**
	 * 保存角色菜单
	 * @param records
	 * @return
	 */
	int saveRoleMenus(List<ACLs> records);

	/**
	 * 根据名称查询
	 * @param name
	 * @return
	 */
	List<Roles> findByName(String name);

}
