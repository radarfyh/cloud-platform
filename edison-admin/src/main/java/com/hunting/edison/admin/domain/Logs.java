package com.hunting.edison.admin.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * log日志 模型
 * @author Louis & Edison
 * @date Oct 29, 2018
 */
@Entity
public class Logs extends BaseModel {

    private String userName;

    private String operation;

    private String method;

    private String params;

    @Column(name="execute_time")
    private Long time;

    private String ip;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}