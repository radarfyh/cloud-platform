package com.hunting.edison.admin.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.hunting.edison.admin.domain.UserDept;

/**
 * 用户部门Repository
 * @author Edison
 * @date 2020/1/9
 */
public interface UserDeptRepository extends PagingAndSortingRepository<UserDept, Integer> {
	List<UserDept> findByUserId(Integer userId);
	void deleteByUserId(Integer userId);
}
