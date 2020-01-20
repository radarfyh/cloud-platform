package com.hunting.edison.admin.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.hunting.edison.admin.domain.Roles;

/**
 * 角色Repository
 * @author Edison
 * @date 2020/1/9
 */
public interface RolesRepository extends PagingAndSortingRepository<Roles, Integer> {
	List<Roles> findAll();
	List<Roles> findByName(String name);
	Page<Roles> findAll(Pageable page);
	Page<Roles> findByName(String name, Pageable page);
}
