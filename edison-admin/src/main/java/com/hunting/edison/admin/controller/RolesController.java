package com.hunting.edison.admin.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hunting.edison.admin.constants.SysConstants;
import com.hunting.edison.admin.domain.ACLs;
import com.hunting.edison.admin.domain.Roles;
import com.hunting.edison.admin.repository.RolesRepository;
import com.hunting.edison.admin.sevice.RolesService;
import com.hunting.edison.core.http.HttpResult;
import com.hunting.edison.core.page.MyPageRequest;

/**
 * 角色控制器
 * @author Louis & Edison
 * @date 2020/1/9
 */
@RestController
@RequestMapping("role")
public class RolesController {

	@Resource
	private RolesService rolesService;
	@Resource
	private RolesRepository rolesRepo;
	
	@PreAuthorize("hasAuthority('sys:role:add') AND hasAuthority('sys:role:edit')")
	@PostMapping(value="/save")
	public HttpResult save(@RequestBody Roles record) {
		Roles role = rolesService.findById(record.getId());
		if(role != null) {
			if(SysConstants.ADMIN.equalsIgnoreCase(role.getName())) {
				return HttpResult.error("超级管理员不允许修改!");
			}
		}
		// 新增角色
		if((record.getId() == null || record.getId() ==0) && !rolesService.findByName(record.getName()).isEmpty()) {
			return HttpResult.error("角色名已存在!");
		}
		return HttpResult.ok(rolesService.save(record));
	}

	@PreAuthorize("hasAuthority('sys:role:delete')")
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<Roles> records) {
		return HttpResult.ok(rolesService.delete(records));
	}

	@PreAuthorize("hasAuthority('sys:role:view')")
	@PostMapping(value="/findPage")
	public HttpResult findPage(@RequestBody MyPageRequest pageRequest) {
		return HttpResult.ok(rolesService.findPage(pageRequest));
	}
	
	@PreAuthorize("hasAuthority('sys:role:view')")
	@GetMapping(value="/findAll")
	public HttpResult findAll() {
		return HttpResult.ok(rolesService.findAll());
	}
	
	@PreAuthorize("hasAuthority('sys:role:view')")
	@GetMapping(value="/findRoleMenus")
	public HttpResult findRoleMenus(@RequestParam Integer roleId) {
		return HttpResult.ok(rolesService.findRoleMenus(roleId));
	}
	
	@PreAuthorize("hasAuthority('sys:role:view')")
	@PostMapping(value="/saveRoleMenus")
	public HttpResult saveRoleMenus(@RequestBody List<ACLs> records) {
		for(ACLs record:records) {
			Optional<Roles> role = rolesRepo.findById(record.getRoleId());
			if(role.isPresent() && SysConstants.ADMIN.equalsIgnoreCase(role.get().getName())) {
				// 如果是超级管理员，不允许修改
				return HttpResult.error("超级管理员拥有所有菜单权限，不允许修改！");
			}
		}
		return HttpResult.ok(rolesService.saveRoleMenus(records));
	}
}
