package com.javatest.model;

/**
 * 已审核过的报告列表
 *
 */
public class SimplePrintReport {
	/**
	 * 报告号
	 */
	private String reportNum;
	/**
	 * 审批人
	 */
	private String checkUser;
	/**
	 * 审批日期
	 */
	private String checkDate;

	

	public String getReportNum() {
		return reportNum;
	}

	public void setReportNum(String reportNum) {
		this.reportNum = reportNum;
	}

	public String getCheckUser() {
		return checkUser;
	}

	public void setCheckUser(String checkUser) {
		this.checkUser = checkUser;
	}

	public String getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

}
