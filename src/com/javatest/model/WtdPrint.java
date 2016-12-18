package com.javatest.model;

import java.util.List;

public class WtdPrint {
	/**
	 * 使用单位
	 */
	private String unit;
	/**
	 * 单位地址
	 */
	private String address;
	/**
	 * 联系人
	 */
	private String contact;
	/**
	 * 联系电话
	 */
	private String tel;
	/**
	 * 安全阀信息
	 */
	private List<SimpleAqf> reportList;

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public List<SimpleAqf> getReportList() {
		return reportList;
	}

	public void setReportList(List<SimpleAqf> reportList) {
		this.reportList = reportList;
	}

}
