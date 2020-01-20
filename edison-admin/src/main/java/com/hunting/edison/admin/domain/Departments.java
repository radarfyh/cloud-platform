package com.hunting.edison.admin.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * department部门 模型
 * @author Louis & Edison
 * @date Oct 29, 2018
 */
@Entity
public class Departments extends BaseModel {
	private String code;

	private String name;
    
    private Integer parentId;
    
    private String departmentType;
    
    private String principal;
    
    private String departmentLevel;
    private String address;
    private String telephone;
    private String fax;
    private String postcode;
    private String notes;

    private Integer orderNum;

    private Byte delFlag;
    
    //@OneToMany(fetch=FetchType.LAZY)  
    //@JoinColumn(name="id") 
    // 非数据库字段
    @Transient
    private List<Departments> children;
    
    // 非数据库字段
    @Transient
    private String parentName;
    // 非数据库字段
    @Transient
    private Integer level;
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Byte getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Byte delFlag) {
		this.delFlag = delFlag;
	}

	public List<Departments> getChildren() {
		return children;
	}

	public void setChildren(List<Departments> children) {
		this.children = children;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getDepartmentType() {
		return departmentType;
	}

	public void setDepartmentType(String departmentType) {
		this.departmentType = departmentType;
	}
	
	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	
	public String getDepartmentLevel() {
		return departmentLevel;
	}

	public void setDepartmentLevel(String departmentLevel) {
		this.departmentLevel = departmentLevel;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
	
	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
    
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}