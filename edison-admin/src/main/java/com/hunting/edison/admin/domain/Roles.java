package com.hunting.edison.admin.domain;

import javax.persistence.Entity;

/**
 * role角色 模型
 * @author Louis & Edison
 * @date Oct 29, 2018
 */
@Entity
public class Roles extends BaseModel {
	// 角色代号,例如admin
	private String code;
	
    private String name;
    
    private Integer parentId;
    
    private String roleType;

    private String notes;

    private Byte delFlag;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Byte getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Byte delFlag) {
		this.delFlag = delFlag;
	}

}