package com.hunting.edison.admin.sevice.impl;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hunting.edison.admin.constants.SysConstants;
import com.hunting.edison.admin.domain.Logs;
import com.hunting.edison.admin.repository.LogsRepository;
import com.hunting.edison.admin.sevice.LogsService;
import com.hunting.edison.core.page.MyPageHelper;
import com.hunting.edison.core.page.MyPageRequest;
import com.hunting.edison.core.page.MyPageResult;

/**
 * log管理
 * @author Louis & Edison
 * @date 2020/1/9
 */
@Service
public class LogsServiceImpl  implements LogsService {
	private static Logger log = LogManager.getLogger(LogsServiceImpl.class);

	@Resource
	private LogsRepository logsRepo;

	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int save(Logs record) {

		logsRepo.save(record);
		return SysConstants.SUCCUSS;
	}

	@Override
	public int add(Logs record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Integer id, Logs record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Logs record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterable<Logs> findAllSort(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Logs> findAll(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Logs record) {
		logsRepo.delete(record);
		return SysConstants.SUCCUSS;
	}

	@Override
	public int delete(List<Logs> records) {
		for(Logs record:records) {
			delete(record);
		}
		return SysConstants.SUCCUSS;
	}

	@Override
	public Logs findById(Integer id) {
		Optional<Logs> log = logsRepo.findById(id);
		if(log.isPresent()) {
			return log.get();
		}
		return null;
	}

	@Override
	public MyPageResult findPage(MyPageRequest pageRequest) {
		//调试信息
		log.log(Level.forName("NOTICE", 450), pageRequest.toJSON());

		Page<Logs> result = null;
		String name = MyPageHelper.getColumnFilterValue(pageRequest, "userName");
		
		Sort sort = Sort.by(Sort.Direction.DESC, "userName");
		Pageable page = PageRequest.of(pageRequest.getPageNum() - 1, pageRequest.getPageSize(), sort);
		if(name !=null && !name.isEmpty()) {
			result = logsRepo.findByUserName(name, page);
		} else {
			result = logsRepo.findAll(page);
		}
				
		// 转换格式
		MyPageResult pageResult = MyPageHelper.getPageResult(result);
		//调试信息
		log.log(Level.forName("NOTICE", 450), pageResult.toJSON());
		
		return pageResult;
	}
	
}
