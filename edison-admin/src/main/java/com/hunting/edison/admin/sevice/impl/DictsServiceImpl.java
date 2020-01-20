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
import com.hunting.edison.admin.domain.Dictionaries;
import com.hunting.edison.admin.repository.DictsRepository;
import com.hunting.edison.admin.sevice.DictsService;
import com.hunting.edison.core.page.MyPageHelper;
import com.hunting.edison.core.page.MyPageRequest;
import com.hunting.edison.core.page.MyPageResult;

/**
 * Dictionary管理
 * @author Louis & Edison
 * @date 2020/1/9
 */
@Service
public class DictsServiceImpl  implements DictsService {
	private static Logger log = LogManager.getLogger(DictsServiceImpl.class);

	@Resource
	private DictsRepository dictsRepo;

	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int save(Dictionaries record) {

		dictsRepo.save(record);
		return SysConstants.SUCCUSS;
	}

	@Override
	public int add(Dictionaries record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Integer id, Dictionaries record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Dictionaries record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterable<Dictionaries> findAllSort(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Dictionaries> findAll(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Dictionaries record) {
		dictsRepo.deleteById(record.getId());
		return SysConstants.SUCCUSS;
	}

	@Override
	public int delete(List<Dictionaries> records) {
		for(Dictionaries record:records) {
			delete(record);
		}
		return SysConstants.SUCCUSS;
	}

	@Override
	public Dictionaries findById(Integer id) {
		Optional<Dictionaries> dict = dictsRepo.findById(id);
		if(dict.isPresent()) {
			return dict.get();
		}
		return null;
	}

	@Override
	public MyPageResult findPage(MyPageRequest pageRequest) {
		//调试信息
		log.log(Level.forName("NOTICE", 450), pageRequest.toJSON());

		Page<Dictionaries> result = null;
		String label = MyPageHelper.getColumnFilterValue(pageRequest, "label");
		
		Sort sort = Sort.by(Sort.Direction.DESC, "label");
		Pageable page = PageRequest.of(pageRequest.getPageNum() - 1, pageRequest.getPageSize(), sort);
		if(label != null && !label.isEmpty()) {
			result = dictsRepo.findByLabel(label, page);
		} else {
			result = dictsRepo.findAll(page);
		}
				
		// 转换格式
		MyPageResult pageResult = MyPageHelper.getPageResult(result);
		
		//调试信息
		log.log(Level.forName("NOTICE", 450), pageResult.toJSON());
		return pageResult;
	}

	@Override
	public List<Dictionaries> findByLabel(String label) {
		return dictsRepo.findByLabel(label);
	}

}
