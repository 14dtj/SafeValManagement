package com.javatest.model;

public class SimpleReport {
	/**
	 * 安全阀位号
	 */
	private String safeName;
	/**
	 * 安全阀名字
	 */
	private String safeId;

	public String getSafeId() {
		return safeId;
	}

	public void setSafeId(String safeId) {
		this.safeId = safeId;
	}

	public String getSafeName() {
		return safeName;
	}

	public void setSafeName(String safeName) {
		this.safeName = safeName;
	}

}
