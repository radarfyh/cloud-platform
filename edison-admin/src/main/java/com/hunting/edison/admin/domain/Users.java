package com.hunting.edison.admin.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;


/**
 * user用户 模型
 * @author Louis & Edison
 * @date Oct 29, 2018
 */

@Entity
public class Users extends BaseModel {
    private String name;
    
    private String userType;
    private String gender;
    private Integer age;
    private String address;
    private String fax;
    private String postcode;


	private String notes;

    private String password;

    private String salt;

    private String email;

    @Column(name="Telephone")
    private String mobile;

    private Byte status;

    @Transient
    private Integer deptId;

    @Transient
    private String deptName;
    
    private Byte delFlag;
    
    @Transient
    private String roleNames;
    
    //@OneToMany(fetch=FetchType.LAZY)  
    //@JoinColumn(name="id") 
    @Transient
    private List<UserRole> userRoles = new ArrayList<>();
    
    //@OneToMany(fetch=FetchType.LAZY)  
    //@JoinColumn(name="id") 
    @Transient
    private List<UserDept> userDepts = new ArrayList<>();
    
    //@OneToMany(fetch=FetchType.LAZY)  
    //@JoinColumn(name="roleId") 
    @Transient
    private List<Roles> roles = new ArrayList<>();
    
    //@OneToMany(fetch=FetchType.LAZY)  
    //@JoinColumn(name="deptId") 
    @Transient
    private List<Departments> departments = new ArrayList<>();

	public List<UserDept> getUserDepts() {
		return userDepts;
	}

	public void setUserDepts(List<UserDept> userDepts) {
		this.userDepts = userDepts;
	}

	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}

	public List<Departments> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Departments> departments) {
		this.departments = departments;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Byte getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Byte delFlag) {
		this.delFlag = delFlag;
	}

	public String getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

    public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
}