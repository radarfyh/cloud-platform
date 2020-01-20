package com.hunting.edison.admin.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hunting.edison.admin.domain.Departments;
import com.hunting.edison.admin.sevice.DeptsService;
import com.hunting.edison.core.http.HttpResult;
import com.hunting.edison.core.page.MyPageRequest;
import com.hunting.edison.core.page.MyPageResult;

/**
 * department部门/机构控制器
 * @author Louis & Edison
 * @date 2020/1/9
 */
@RestController
@RequestMapping("departments")
public class DeptsController {

	@Resource
	private DeptsService deptsService;
	
	@PreAuthorize("hasAuthority('sys:dept:add')")
	@PostMapping(value="/")
	public HttpResult add(@RequestBody Departments record) {
		return HttpResult.ok(deptsService.add(record));
	}
	
	@PreAuthorize("hasAuthority('sys:dept:edit')")
	@PutMapping(value="/{id}")
	public HttpResult update(@PathVariable Integer id, @RequestBody Departments record) {
		return HttpResult.ok(deptsService.update(id, record));
	}

	@PreAuthorize("hasAuthority('sys:dept:delete')")
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<Departments> records) {
		return HttpResult.ok(deptsService.delete(records));
	}
	
	@PreAuthorize("hasAuthority('sys:dept:delete')")
	@DeleteMapping(value="/{id}")
	public HttpResult delete(@PathVariable Integer id) {
		return HttpResult.ok(deptsService.delete(id));
	}
	
	@PreAuthorize("hasAuthority('sys:dept:view')")
	@GetMapping(value="/{id}")
	public HttpResult findById(@PathVariable Integer id) {
		return HttpResult.ok(deptsService.findById(id));
	}

	@PreAuthorize("hasAuthority('sys:dept:view')")
	@GetMapping(value="/findTree")
	public HttpResult findTree() {
		return HttpResult.ok(deptsService.findTree());
	}
	
	@PreAuthorize("hasAuthority('sys:dept:view')")
	@GetMapping(value="/{length}/{start}")
	public HttpResult findPage(@PathVariable("length") int length, @PathVariable("start") int start) {
		
		MyPageRequest pageRequest = new MyPageRequest();
		pageRequest.setPageSize(length);
		pageRequest.setPageNum(start/length);
		
		MyPageResult pageResult = deptsService.findPage(pageRequest);
		
		return HttpResult.ok(pageResult);
	}

}
