package com.hunting.edison.admin.sevice;

import java.util.List;
import java.util.Set;

import com.hunting.edison.admin.domain.Departments;
import com.hunting.edison.admin.domain.Roles;
import com.hunting.edison.admin.domain.UserDept;
import com.hunting.edison.admin.domain.UserRole;
import com.hunting.edison.admin.domain.Users;
import com.hunting.edison.core.service.CurdService;

/**
 * user管理
 * @author Louis & Edison
 * @date Oct 29, 2018
 */
public interface UsersService extends CurdService<Users> {

	Users findByName(String username);

	/**
	 * 查找用户的菜单权限标识集合
	 * @param userName
	 * @return
	 */
	Set<String> findPermissions(String userName);

	/**
	 * 查找用户角色关系集合
	 * @param userId
	 * @return
	 */
	List<UserRole> findUserRoles(Integer userId);
	
	/**
	 * 查找角色集合
	 * @param userId
	 * @return
	 */
	List<Roles> findRoles(Integer userId);
	
	/**
	 * 查找用户部门关系集合
	 * @param userId
	 * @return
	 */
	List<UserDept> findUserDepts(Integer userId);
	
	/**
	 * 查找部门集合
	 * @param userId
	 * @return
	 */
	List<Departments> findDepts(Integer userId);
}
