package com.hunting.edison.admin.controller;

import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hunting.edison.admin.sevice.LogsService;
import com.hunting.edison.core.http.HttpResult;
import com.hunting.edison.core.page.MyPageRequest;

/**
 * log日志控制器
 * @author Louis & Edison
 * @date 2020/1/9
 */
@RestController
@RequestMapping("log")
public class LogsController {

	@Resource
	private LogsService logsService;

	@PreAuthorize("hasAuthority('sys:log:view')")
	@PostMapping(value="/findPage")
	public HttpResult findPage(@RequestBody MyPageRequest pageRequest) {
		return HttpResult.ok(logsService.findPage(pageRequest));
	}
}
