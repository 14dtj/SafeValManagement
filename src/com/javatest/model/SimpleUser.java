package com.javatest.model;

public class SimpleUser {
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 所属部门
	 */
	private String department;
	/**
	 * 职位
	 */
	private String role;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
