package com.hunting.edison.admin.sevice;

import java.util.List;

import com.hunting.edison.admin.domain.Dictionaries;
import com.hunting.edison.core.service.CurdService;

/**
 * dictionary管理
 * @author Louis & Edison
 * @date Oct 29, 2018
 */
public interface DictsService extends CurdService<Dictionaries> {

	/**
	 * 根据名称查询
	 * @param label
	 * @return
	 */
	List<Dictionaries> findByLabel(String label);
}
