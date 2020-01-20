package com.hunting.edison.admin.domain;

import javax.persistence.Entity;

/**
 * user&role用户角色关系 模型
 * @author Louis & Edison
 * @date Oct 29, 2018
 */
@Entity
public class UserRole extends BaseModel {

    private Integer userId;

    private Integer roleId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

}