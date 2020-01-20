package com.hunting.edison.admin.sevice.impl;

import java.util.ArrayList;
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

import com.hunting.edison.admin.domain.Departments;
import com.hunting.edison.admin.repository.DeptsRepository;
import com.hunting.edison.admin.sevice.DeptsService;
import com.hunting.edison.core.page.MyPageHelper;
import com.hunting.edison.core.page.MyPageRequest;
import com.hunting.edison.core.page.MyPageResult;

/**
 * department/organization管理
 * @author Louis & Edison
 * @date 2020/1/9
 */
@Service
public class DeptsServiceImpl implements DeptsService {
	private static Logger log = LogManager.getLogger(DeptsServiceImpl.class);

	@Resource
	private DeptsRepository deptsRepo;

	@Override
	public int save(Departments record) {
		deptsRepo.save(record);
		return SysConstants.SUCCUSS;
	}

	@Override
	public int add(Departments record) {
		deptsRepo.save(record);
		return SysConstants.SUCCUSS;		
	}

	@Override
	public int update(Integer id, Departments record) {
		if(record.getId() != id) {
			record.setId(id);
		}
		deptsRepo.save(record);
		
		return SysConstants.SUCCUSS;
	}
	
	@Override
	public int update(Departments record) {
		deptsRepo.save(record);
		
		return SysConstants.SUCCUSS;
	}

	@Override
	public int delete(Departments record) {
		deptsRepo.deleteById(record.getId());
		
		return SysConstants.SUCCUSS;
	}

	@Override
	public int delete(Integer id) {
		deptsRepo.deleteById(id);
		
		return SysConstants.SUCCUSS;
	}

	@Override
	public int delete(List<Departments> records) {
		for(Departments record:records) {
			delete(record);
		}
		return SysConstants.SUCCUSS;
	}

	@Override
	public Departments findById(Integer id) {
		Optional<Departments> dept = deptsRepo.findById(id);
		if(dept.isPresent()) {
			return dept.get();
		}
		return null;
	}

	@Override
	public MyPageResult findPage(MyPageRequest pageRequest) {
		//调试信息
		log.log(Level.forName("NOTICE", 450), pageRequest.toJSON());
		
		Page<Departments> result = null;
		Pageable page = PageRequest.of(pageRequest.getPageNum() - 1, pageRequest.getPageSize());

		result = deptsRepo.findAll(page);
				
		// 转换格式
		MyPageResult pageResult = MyPageHelper.getPageResult(result);
		//调试信息
		log.log(Level.forName("NOTICE", 450), pageResult.toJSON());
		return pageResult;
	}

	@Override
	public List<Departments> findTree() {
		List<Departments> sysDepts = new ArrayList<>();
		List<Departments> depts = deptsRepo.findAll();
		for (Departments dept : depts) {
			if (dept.getParentId() == null || dept.getParentId() == 0) {
				dept.setLevel(0);
				sysDepts.add(dept);
			}
		}
		findChildren(sysDepts, depts);
		return sysDepts;
	}

	private void findChildren(List<Departments> sysDepts, List<Departments> depts) {
		for (Departments sysDept : sysDepts) {
			List<Departments> children = new ArrayList<>();
			for (Departments dept : depts) {
				if (sysDept.getId() != null && sysDept.getId().equals(dept.getParentId())) {
					dept.setParentName(dept.getName());
					dept.setLevel(sysDept.getLevel() + 1);
					children.add(dept);
				}
			}
			sysDept.setChildren(children);
			findChildren(children, depts);
		}
	}

	@Override
	public Iterable<Departments> findAllSort(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Departments> findAll(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

}
