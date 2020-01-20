package com.hunting.edison.admin.sevice.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hunting.edison.admin.constants.SysConstants;

import com.hunting.edison.admin.domain.Departments;
import com.hunting.edison.admin.domain.Resources;
import com.hunting.edison.admin.domain.Roles;
import com.hunting.edison.admin.domain.UserDept;
import com.hunting.edison.admin.domain.UserRole;
import com.hunting.edison.admin.domain.Users;
import com.hunting.edison.admin.repository.DeptsRepository;
import com.hunting.edison.admin.repository.RolesRepository;
import com.hunting.edison.admin.repository.UserDeptRepository;
import com.hunting.edison.admin.repository.UserRoleRepository;
import com.hunting.edison.admin.repository.UsersRepository;
import com.hunting.edison.admin.sevice.ResourcesService;
import com.hunting.edison.admin.sevice.UsersService;
import com.hunting.edison.core.page.MyPageHelper;
import com.hunting.edison.core.page.MyPageRequest;
import com.hunting.edison.core.page.MyPageResult;

/**
 * user管理
 * @author Louis & Edison
 * @date 2020/1/9
 */
@Service
public class UsersServiceImpl  implements UsersService {
	//protected final Log logger = LogFactory.getLog(getClass());
	private static Logger log = LogManager.getLogger(UsersServiceImpl.class);
	
	@Resource
	private UsersRepository usersRepo;
	@Resource
	private ResourcesService resourcesService;
	@Resource
	private RolesRepository rolesRepo;
	@Resource
	private UserDeptRepository userDeptRepo;
	@Resource
	private UserRoleRepository userRoleRepo;
	@Resource
	private DeptsRepository deptsRepo;

	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterable<Users> findAllSort(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Users> findAll(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int add(Users record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Integer id, Users record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Users record) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public List<Departments> findDepts(Integer userId) {
		
		List<Departments> departments = new ArrayList<>();
		
		List<UserDept> userDepts = findUserDepts(userId);
		
		for(Iterator<UserDept> iter=userDepts.iterator(); iter.hasNext();) {
			UserDept userDept = iter.next();
			//Departments department = sysDeptMapper.selectByPrimaryKey(userDept.getDeptId());
			Optional<Departments> department = deptsRepo.findById(userDept.getDeptId());
			if(!department.isPresent()) {
				continue ;
			}
			departments.add(department.get());
		}
		return departments;
	}

	@Override
	public List<Roles> findRoles(Integer userId) {
		List<Roles> roles = new ArrayList<>();
		
		List<UserRole> userRoles = findUserRoles(userId);
		
		for(Iterator<UserRole> iter=userRoles.iterator(); iter.hasNext();) {
			UserRole userRole = iter.next();
			Optional<Roles> role = rolesRepo.findById(userRole.getRoleId());
			if(!role.isPresent()) {
				continue ;
			}
			roles.add(role.get());
		}
		return roles;
	}

	@Override
	public List<UserDept> findUserDepts(Integer userId) {
		
		return userDeptRepo.findByUserId(userId);
	}
	
	@Override
	public List<UserRole> findUserRoles(Integer userId) {
		return userRoleRepo.findByUserId(userId);
	}
	
	@Transactional
	@Override
	public int save(Users record) {
		Integer id = null;
		if(record.getId() == null || record.getId() == 0) {
			// 新增用户
			usersRepo.save(record);
			
			id = record.getId();
		} else {
			// 更新用户信息
			usersRepo.save(record);
		}
		
		if(id != null && id == 0) {
			return SysConstants.SUCCUSS;
		}
		
		// 更新用户角色关系
		if(id != null) {
			for(UserRole userRole:record.getUserRoles()) {
				userRole.setUserId(id);
			}
		} else {
			userRoleRepo.deleteByUserId(record.getId());
		}
		for(UserRole userRole:record.getUserRoles()) {
			userRoleRepo.save(userRole);
		}
		
		
		
		// 更新用户部门关系
		if(id != null) {
			for(UserDept userDept:record.getUserDepts()) {
				userDept.setUserId(id);
			}
		} else {
			userDeptRepo.deleteByUserId(record.getId());
		}
		for(UserDept userDept:record.getUserDepts()) {
			userDeptRepo.save(userDept);
		}

		return SysConstants.SUCCUSS;
	}

	@Override
	public int delete(Users record) {
		usersRepo.deleteById(record.getId());
		return SysConstants.SUCCUSS;
	}

	@Override
	public int delete(List<Users> records) {
		for(Users record:records) {
			delete(record);
		}
		return SysConstants.SUCCUSS;
	}

	@Override
	public Users findById(Integer id) {
		Optional<Users> user = usersRepo.findById(id);
		if (user.isPresent()) {
			return user.get();
		}

		return null;
	}
	
	@Override
	public Users findByName(String name) {
		List<Users> users = usersRepo.findByName(name);
		if (!users.isEmpty()) {
			return users.get(0);
		}

		return null;
	}
	
	@Override
	public MyPageResult findPage(MyPageRequest pageRequest) {
		//调试信息
		log.log(Level.forName("NOTICE", 450), pageRequest.toJSON());
		
		Page<Users> result = null;
		String name = MyPageHelper.getColumnFilterValue(pageRequest, "name");
		String email = MyPageHelper.getColumnFilterValue(pageRequest, "email");
		
		Sort sort = Sort.by(Sort.Direction.DESC, "name", "email");
		Pageable page = PageRequest.of(pageRequest.getPageNum() - 1, pageRequest.getPageSize(), sort);
		if(name != null && !name.isEmpty()) {
			if(email != null && !email.isEmpty()) {
				result = usersRepo.findByNameAndEmail(name, email, page);
			} else {
				result = usersRepo.findByName(name, page);
			}
		} else {
			result = usersRepo.findAll(page);
		}
				
		// 转换格式
		MyPageResult pageResult = MyPageHelper.getPageResult(result);
        
		// 加载用户角色信息
		findUserRoles(pageResult);
		// 加载部门信息
		findUserDepts(pageResult);
		//调试信息
		log.log(Level.forName("NOTICE", 450), pageResult.toJSON());

		return pageResult;
	}

	
	/**
	 * 加载用户角色
	 * @param pageResult
	 */
	private void findUserRoles(MyPageResult pageResult) {
		List<?> content = pageResult.getContent();
		for(Object object:content) {
			Users user = (Users) object;
			
			List<UserRole> userRoles = findUserRoles(user.getId());
			user.setUserRoles(userRoles);
			user.setRoleNames(getRoleNames(userRoles));
			
			List<Roles> roles = findRoles(user.getId());
			user.setRoles(roles);
		}
	}

	private String getRoleNames(List<UserRole> userRoles) {
		StringBuilder sb = new StringBuilder();
		for(Iterator<UserRole> iter=userRoles.iterator(); iter.hasNext();) {
			UserRole userRole = iter.next();
			Optional<Roles> role = rolesRepo.findById(userRole.getRoleId());
			if(!role.isPresent()) {
				continue ;
			}
			sb.append(role.get().getName());
			if(iter.hasNext()) {
				sb.append(", ");
			}
		}
		return sb.toString();
	}

	/**
	 * 加载用户部门
	 * @param pageResult
	 */
	private void findUserDepts(MyPageResult pageResult) {
		List<?> content = pageResult.getContent();
		for(Object object:content) {
			Users user = (Users) object;
			List<Departments> departments = findDepts(user.getId());
			user.setDepartments(departments);
			
			if(departments.isEmpty()) {
				continue;
			}
			// 获取第一个部门的id，并修改用户关联信息
			user.setDeptId(departments.get(0).getId());
			// 获取部门名称，以逗号分隔
			StringBuilder sb = new StringBuilder();
			for(Departments dept:departments) {
				if(dept == null) {
					continue;
				}
				if(sb.length() == 0) {
					sb.append(dept.getName());
				}
				else {
					sb.append("," + dept.getName());
				}
			}
			user.setDeptName(sb.toString());
		}
	}


	
	@Override
	public Set<String> findPermissions(String userName) {	
		Set<String> perms = new HashSet<>();
		List<Resources> menus = resourcesService.findByUser(userName);
		for(Resources menu:menus) {
			if(menu.getPerms() != null && !"".equals(menu.getPerms())) {
				perms.add(menu.getPerms());
			}
		}
		//输出调试信息
        log.log(Level.forName("NOTICE", 450),perms);
		
		return perms;
	}


}
