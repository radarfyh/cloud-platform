package com.hunting.edison.admin.domain;

import javax.persistence.Entity;

/**
 * role&department角色部门关系 模型
 * @author Louis & Edison
 * @date Oct 29, 2018
 */
@Entity
public class RoleDept extends BaseModel {

    private Integer roleId;

    private Integer deptId;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

}