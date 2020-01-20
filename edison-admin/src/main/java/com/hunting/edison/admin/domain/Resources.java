package com.hunting.edison.admin.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * resource资源 模型
 * @author Louis & Edison
 * @date Oct 29, 2018
 */
@Entity
public class Resources extends BaseModel {

    private Integer parentId;

    private String name;

    // 资源URL,
    // 类型：1.普通页面（如用户管理， /sys/user） 
    // 2.嵌套完整外部页面，以http(s)开头的链接 
    // 3.嵌套服务器页面，使用iframe:前缀+目标URL(如SQL监控， iframe:/druid/login.html, iframe:前缀会替换成服务器地址)
    // 4.表记录，使用rec:{tablename}/{id}，其中多个tablename使用逗号分隔，多个id使用逗号分隔
    private String url;

    // 授权(多个用逗号分隔，如：sys:user:add,sys:user:edit)
    private String perms;

    // 类型 0：目录/元数据   1：菜单   2：按钮 3：信息项/数据记录
    private Integer resourceType;

    private String icon;

    private Integer orderNum;

    private Byte delFlag;

    // 非数据库字段
    @Transient
    private String parentName;
    // 非数据库字段
    @Transient
    private Integer level;
    // 非数据库字段
    @Transient
    private List<Resources> children;
    
	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPerms() {
		return perms;
	}

	public void setPerms(String perms) {
		this.perms = perms;
	}


	public Integer getResourceType() {
		return resourceType;
	}

	public void setResourceType(Integer resourceType) {
		this.resourceType = resourceType;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
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

	public List<Resources> getChildren() {
		return children;
	}

	public void setChildren(List<Resources> children) {
		this.children = children;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
}