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

import com.hunting.edison.admin.domain.Dictionaries;
import com.hunting.edison.admin.sevice.DictsService;
import com.hunting.edison.core.http.HttpResult;
import com.hunting.edison.core.page.MyPageRequest;

/**
 * dictionary字典控制器
 * @author Louis & Edison
 * @date 2020/1/9
 */
@RestController
@RequestMapping("dict")
public class DictsController {

	@Resource
	private DictsService dictsService;
	
	@PreAuthorize("hasAuthority('sys:dict:add') AND hasAuthority('sys:dict:edit')")
	@PostMapping(value="/save")
	public HttpResult save(@RequestBody Dictionaries record) {
		return HttpResult.ok(dictsService.save(record));
	}

	@PreAuthorize("hasAuthority('sys:dict:delete')")
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<Dictionaries> records) {
		return HttpResult.ok(dictsService.delete(records));
	}

	@PreAuthorize("hasAuthority('sys:dict:view')")
	@PostMapping(value="/findPage")
	public HttpResult findPage(@RequestBody MyPageRequest pageRequest) {
		return HttpResult.ok(dictsService.findPage(pageRequest));
	}
	
	@PreAuthorize("hasAuthority('sys:dict:view')")
	@GetMapping(value="/findByLable")
	public HttpResult findByLable(@RequestParam String label) {
		return HttpResult.ok(dictsService.findByLabel(label));
	}
}
