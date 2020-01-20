package com.hunting.edison.admin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.hunting.edison.admin.domain.Logs;

/**
 * 日志Repository
 * @author Edison
 * @date 2020/1/9
 */
public interface LogsRepository extends PagingAndSortingRepository<Logs, Integer> {
	Page<Logs> findByUserName(String userName, Pageable page);
}
