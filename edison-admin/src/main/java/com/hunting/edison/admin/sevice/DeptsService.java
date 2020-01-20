package com.hunting.edison.admin.sevice;

import java.util.List;

import com.hunting.edison.admin.domain.Departments;
import com.hunting.edison.core.service.CurdService;

/**
 * department/organization管理
 * @author Louis & Edison
 * @date Oct 29, 2018
 */
public interface DeptsService extends CurdService<Departments> {

	/**
	 * 查询机构树
	 * @param userId 
	 * @return
	 */
	List<Departments> findTree();
}
