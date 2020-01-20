package com.hunting.edison.admin.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.hunting.edison.admin.domain.ACLs;

public interface AclsRepository extends PagingAndSortingRepository<ACLs, Integer> {
	void deleteByRoleId(Integer roleId);
	List<ACLs> findByRoleId(Integer roleId);
}
