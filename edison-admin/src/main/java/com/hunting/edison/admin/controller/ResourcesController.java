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

import com.hunting.edison.admin.domain.Resources;
import com.hunting.edison.admin.sevice.ResourcesService;
import com.hunting.edison.core.http.HttpResult;

/**
 * 菜单控制器
 * @author Louis & Edison
 * @date 2020/1/9
 */
@RestController
@RequestMapping("menu")
public class ResourcesController {

	@Resource
	private ResourcesService resourcesService;
	
	@PreAuthorize("hasAuthority('sys:menu:add') AND hasAuthority('sys:menu:edit')")
	@PostMapping(value="/save")
	public HttpResult save(@RequestBody Resources record) {
		return HttpResult.ok(resourcesService.save(record));
	}

	@PreAuthorize("hasAuthority('sys:menu:delete')")
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<Resources> records) {
		return HttpResult.ok(resourcesService.delete(records));
	}

	@PreAuthorize("hasAuthority('sys:menu:view')")
	@GetMapping(value="/findNavTree")
	public HttpResult findNavTree(@RequestParam String userName) {
		return HttpResult.ok(resourcesService.findTree(userName, 1));
	}
	
	@PreAuthorize("hasAuthority('sys:menu:view')")
	@GetMapping(value="/findMenuTree")
	public HttpResult findMenuTree() {
		return HttpResult.ok(resourcesService.findTree(null, 0));
	}
}
