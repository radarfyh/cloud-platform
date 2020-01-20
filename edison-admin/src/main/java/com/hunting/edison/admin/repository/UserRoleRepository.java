package com.hunting.edison.admin.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.hunting.edison.admin.domain.UserRole;

/**
 * 用户角色Repository
 * @author Edison
 * @date 2020/1/9
 */
public interface UserRoleRepository extends PagingAndSortingRepository<UserRole, Integer> {
	List<UserRole> findByUserId(Integer userId);
	void deleteByUserId(Integer userId);
}
