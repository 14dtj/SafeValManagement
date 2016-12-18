package com.javatest.model;

public class SimpleAqf {
	/**
	 * 型号
	 */
	private String version;
	/**
	 * 公称通径
	 */
	private String tj;
	/**
	 * 工作介质
	 */
	private String medium;
	/**
	 * 工作压力
	 */
	private String workPressure;
	/**
	 * 整定压力
	 */
	private String intePressure;
	/**
	 * 附属设备代码
	 */
	private String attachEquip;
	/**
	 * 安装位置
	 */
	private String location;
	/**
	 * 检报告编号
	 */
	private String reportId;
	/**
	 * 检验费用
	 */
	private String fee;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getTj() {
		return tj;
	}

	public void setTj(String tj) {
		this.tj = tj;
	}

	public String getMedium() {
		return medium;
	}

	public void setMedium(String medium) {
		this.medium = medium;
	}

	public String getWorkPressure() {
		return workPressure;
	}

	public void setWorkPressure(String workPressure) {
		this.workPressure = workPressure;
	}

	public String getIntePressure() {
		return intePressure;
	}

	public void setIntePressure(String intePressure) {
		this.intePressure = intePressure;
	}

	public String getAttachEquip() {
		return attachEquip;
	}

	public void setAttachEquip(String attachEquip) {
		this.attachEquip = attachEquip;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

}
