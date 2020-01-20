package com.hunting.edison.admin.domain;

import javax.persistence.Entity;

/**
 * user&department用户部门关系 模型
 * @author Louis & Edison
 * @date Oct 29, 2018
 */
@Entity
public class UserDept extends BaseModel {

    private Integer userId;

    private Integer deptId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

}