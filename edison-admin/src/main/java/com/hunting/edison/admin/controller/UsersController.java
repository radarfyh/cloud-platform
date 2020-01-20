package com.hunting.edison.admin.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hunting.edison.admin.constants.SysConstants;

import com.hunting.edison.admin.domain.Users;

import com.hunting.edison.admin.sevice.UsersService;
import com.hunting.edison.admin.util.PasswordUtils;
import com.hunting.edison.core.http.HttpResult;

import com.hunting.edison.core.page.MyPageRequest;



/**
 * 用户控制器
 * @author Louis & Edison
 * @date 2020/1/9
 */
@RestController
@RequestMapping("user")
public class UsersController {

	@Resource
	private UsersService usersService;
	//private UsersRepository usersRepository;
	
	//@Autowired
	//private ResourcesRepository resourcesRepository;
	
	//@Autowired
	//private RolesRepository rolesRepository;
	
	@PreAuthorize("hasAuthority('sys:user:add') AND hasAuthority('sys:user:edit')")
	@PostMapping(value="/save")
	public HttpResult save(@RequestBody Users record) {
		Users user = usersService.findById(record.getId());
		//Optional<Users> user = usersRepository.findById(record.getId());
				
		if(user != null) {
			if(SysConstants.ADMIN.equalsIgnoreCase(user.getName())) {
				return HttpResult.error("超级管理员不允许修改!");
			}
		}
		if(record.getPassword() != null) {
			String salt = PasswordUtils.getSalt();
			if(user == null) {
				// 新增用户
				if(usersService.findByName(record.getName()) != null) {
				//if(usersRepository.findByName(record.getName()) != null) {
					return HttpResult.error("用户名已存在!");
				}
				String password = PasswordUtils.encode(record.getPassword(), salt);
				record.setSalt(salt);
				record.setPassword(password);
			} else {
				// 修改用户, 且修改了密码
				if(!record.getPassword().equals(user.getPassword())) {
					String password = PasswordUtils.encode(record.getPassword(), salt);
					record.setSalt(salt);
					record.setPassword(password);
				}
			}
		}
		return HttpResult.ok(usersService.save(record));
		/*
		 * int result = 1; //String error; try { usersRepository.save(record); }
		 * catch(Exception e) { result = 0; //error = e.getMessage(); } return
		 * HttpResult.ok(result);
		 */
	}

	@PreAuthorize("hasAuthority('sys:user:delete')")
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<Users> records) {
		for(Users record:records) {
			Users user = usersService.findById(record.getId());
			//Optional<Users> user = usersRepository.findById(record.getId());
			if(user != null && SysConstants.ADMIN.equalsIgnoreCase(user.getName())) {
				return HttpResult.error("超级管理员不允许删除!");
			}
		}
		return HttpResult.ok(usersService.delete(records));
		/*
		 * int result = SysConstants.SUCCUSS; //String error; try {
		 * usersRepository.deleteAll(records); } catch(Exception e) { result =
		 * SysConstants.FAILURE; //error = e.getMessage(); }
		 * 
		 * return HttpResult.ok(result);
		 */
	}
	
	@PreAuthorize("hasAuthority('sys:user:view')")
	@GetMapping(value="/findByName")
	public HttpResult findByUserName(@RequestParam String name) {
		return HttpResult.ok(usersService.findByName(name));
		//return HttpResult.ok(usersRepository.findByName(name));
	}
	
	@PreAuthorize("hasAuthority('sys:user:view')")
	@GetMapping(value="/findPermissions")
	public HttpResult findPermissions(@RequestParam String name) {
		return HttpResult.ok(usersService.findPermissions(name));
		/*
		 * Set<String> perms = new HashSet<>(); List<Resources> menus =
		 * resourcesRepository.findByName(name); for(Resources menu:menus) {
		 * if(menu.getPerms() != null && !"".equals(menu.getPerms())) {
		 * perms.add(menu.getPerms()); } }
		 * 
		 * return HttpResult.ok(perms);
		 */
	}
	
	@PreAuthorize("hasAuthority('sys:user:view')")
	@GetMapping(value="/findUserRoles")
	public HttpResult findUserRoles(@RequestParam Integer userId) {
		return HttpResult.ok(usersService.findUserRoles(userId));
		
		/*
		 * Optional<Users> users = usersRepository.findById(userId); if
		 * (users.isPresent()) { List<UserRole> userRoles = users.get().getUserRoles();
		 * return HttpResult.ok(userRoles); } else { return
		 * HttpResult.ok(SysConstants.FAILURE); }
		 */
			
	}

	@PreAuthorize("hasAuthority('sys:user:view')")
	@PostMapping(value="/findPage")
	public HttpResult findPage(@RequestBody MyPageRequest pageRequest) {
		return HttpResult.ok(usersService.findPage(pageRequest));
		/*
		 * Page<Users> result = null;
		 * 
		 * String name = null; ColumnFilter columnFilter =
		 * pageRequest.getColumnFilter("name"); if(columnFilter != null) { name =
		 * columnFilter.getValue(); }
		 * 
		 * String email = null; columnFilter = pageRequest.getColumnFilter("email");
		 * if(columnFilter != null) { email = columnFilter.getValue(); }
		 * 
		 * Pageable page = new QPageRequest(pageRequest.getPageNum(),
		 * pageRequest.getPageSize());
		 * 
		 * if(name != null) { if(email != null) { result =
		 * usersRepository.findByNameAndEmail(name, email, page); } else { result =
		 * usersRepository.findByName(name, page); } } else { result =
		 * usersRepository.findAll(page); } // 加载用户角色信息 PageResult pageResult = new
		 * PageResult(); pageResult.setContent(result.getContent()); List<?> content =
		 * pageResult.getContent(); for(Object object:content) { Users user = (Users)
		 * object;
		 * 
		 * List<UserRole> userRoles; Optional<Users> users =
		 * usersRepository.findById(user.getId()); if (users.isPresent()) { userRoles =
		 * users.get().getUserRoles(); }
		 * 
		 * user.setUserRoles(userRoles); user.setRoleNames(getRoleNames(userRoles));
		 * 
		 * List<Roles> roles = findRoles(sysUser.getId()); sysUser.setRoles(roles); }
		 * return pageResult;
		 */
	}
	

}
