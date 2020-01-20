package com.hunting.edison.admin.domain;

import javax.persistence.Entity;

/**
 * area&department区域部门关系 模型
 * @author Louis & Edison
 * @date Oct 29, 2018
 */
@Entity
public class AreaDept extends BaseModel {

    private Integer areaId;

    private Integer deptId;

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

}