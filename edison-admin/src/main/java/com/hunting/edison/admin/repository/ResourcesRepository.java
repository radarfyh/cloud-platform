package com.hunting.edison.admin.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.hunting.edison.admin.domain.Resources;

/**
 * 资源Repository
 * @author Edison
 * @date 2020/1/9
 */
public interface ResourcesRepository extends PagingAndSortingRepository<Resources, Integer> {
	List<Resources> findByName(String name);
	
	List<Resources> findAll();
	//List<Resources> findByUserName(String userName);
}
