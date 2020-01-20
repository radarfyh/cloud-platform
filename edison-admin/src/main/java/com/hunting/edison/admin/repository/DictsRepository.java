package com.hunting.edison.admin.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.hunting.edison.admin.domain.Dictionaries;
/**
 * 数据字典Repository
 * @author Edison
 * @date 2020/1/9
 */
public interface DictsRepository extends PagingAndSortingRepository<Dictionaries, Integer> {
	List<Dictionaries> findByLabel(String Label);
	Page<Dictionaries> findByLabel(String Label, Pageable page);
}
