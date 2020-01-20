package com.hunting.edison.admin.domain;

import javax.persistence.Entity;

/**
 * ACLs访问控制列表/权限 模型
 * @author Louis & Edison
 * @date Oct 29, 2018
 */
@Entity
public class ACLs extends BaseModel {

    private Integer roleId;

    private Integer resourceId;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

}