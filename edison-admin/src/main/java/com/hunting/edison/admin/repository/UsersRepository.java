package com.hunting.edison.admin.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.hunting.edison.admin.domain.Users;

/**
 * 用户Repository
 * @author Edison
 * @date 2020/1/9
 */
public interface UsersRepository extends PagingAndSortingRepository<Users, Integer> {
	Page<Users> findAll(Pageable page);
	List<Users> findByName(String name);
	Page<Users> findByName(String name, Pageable page);
	Page<Users> findByNameAndEmail(String name, String email, Pageable page);
}
