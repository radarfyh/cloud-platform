package com.hunting.edison.admin.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * area区域 模型
 * @author Louis & Edison
 * @date Oct 29, 2018
 */
@Entity
public class Areas extends BaseModel {

    private String name;
    
    private Integer parentId;
    
    private Long acreage;
    
    private Long population;
    
    private Long households;
    
    private String notes;

    private Integer orderNum;

    private Byte delFlag;
    
    @Transient
    private List<Areas> children;
    
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
	
	public Long getAcreage() {
		return acreage;
	}

	public void setAcreage(Long acreage) {
		this.acreage = acreage;
	}
	
	public Long getPopulation() {
		return population;
	}

	public void setPopulation(Long population) {
		this.population = population;
	}
	
	public Long getHouseholds() {
		return households;
	}

	public void setHouseholds(Long households) {
		this.households = households;
	}
	
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
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

	public List<Areas> getChildren() {
		return children;
	}

	public void setChildren(List<Areas> children) {
		this.children = children;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

}