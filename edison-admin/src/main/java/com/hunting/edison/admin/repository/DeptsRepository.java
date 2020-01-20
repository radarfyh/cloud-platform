package com.hunting.edison.admin.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.hunting.edison.admin.domain.Departments;

/**
 * 部门Repository
 * @author Edison
 * @date 2020/1/9
 */
public interface DeptsRepository extends PagingAndSortingRepository<Departments, Integer> {
	List<Departments> findAll();
}
