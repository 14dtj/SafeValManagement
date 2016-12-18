package com.javatest.model;

/**
 * 对应附录5
 * 
 * @author tjDu
 *
 */
public class SafeValReport {
	private String id;
	/**
	 * 安全阀型号
	 */
	private String xh;
	/**
	 * 公称通径
	 */
	private String tj;
	/**
	 * 工作介质
	 */
	private String gzjz;
	/**
	 * 整定压力
	 */
	private String zdyl;
	/**
	 * 设备代码
	 */
	private String fsdm;
	/**
	 * 安装位置
	 */
	private String azwz;
	/**
	 * 产品编号
	 */
	private String wh;
	/**
	 * 使用单位
	 */
	private String dw;
	/**
	 * 流道直径
	 */
	private String ldzj;
	/**
	 * 制造单位
	 */
	private String zzdw;
	/**
	 * 制造许可证编号
	 */
	private String xkbh;
	/**
	 * 压力级别范围
	 */
	private String ylfw;
	/**
	 * 出厂日期
	 */
	private String ccrq;

	/**
	 * 校验方式
	 */
	private String verifyMode;
	/**
	 * 执行标准
	 */
	private String operateNorm;
	/**
	 * 校验介质
	 */
	private String checkMedium;
	/**
	 * 校验介质温度
	 */
	private String checkMediumTemp;
	/**
	 * 检查、修理、更换等情况记载
	 */
	private String record;
	/**
	 * 第1次实际整定压力
	 */
	private String firstRealPressure;
	/**
	 * 第2次实际整定压力
	 */
	private String secondRealPressure;
	/**
	 * 第3次实际整定压力
	 */
	private String thirdRealPressure;
	/**
	 * 第1次密封试验压力
	 */
	private String firstTrailPressure;
	/**
	 * 第2次实际整定压力
	 */
	private String secondTrailPressure;
	/**
	 * 第3次实际整定压力
	 */
	private String thirdTrailPressure;
	/**
	 * 校验结论
	 */
	private String conclusion;
	/**
	 * 校验有效期
	 */
	private String validYear;
	/**
	 * 意见通知书编号
	 */
	private String adviceNoticeId;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 校验日期
	 */
	private String verifyDate;
	/**
	 * 校验台编号
	 */
	private String checkPlatformId;
	/**
	 * 压力表编号
	 */
	private String pressureGaugeId;
	/**
	 * 压力表精度
	 */
	private String pressureGaugeAccu;
	/**
	 * 压力表量程
	 */
	private String pressureGaugeRange;

	public String getVerifyMode() {
		return verifyMode;
	}

	public void setVerifyMode(String verifyMode) {
		this.verifyMode = verifyMode;
	}

	public String getOperateNorm() {
		return operateNorm;
	}

	public void setOperateNorm(String operateNorm) {
		this.operateNorm = operateNorm;
	}

	public String getCheckMedium() {
		return checkMedium;
	}

	public void setCheckMedium(String checkMedium) {
		this.checkMedium = checkMedium;
	}

	public String getCheckMediumTemp() {
		return checkMediumTemp;
	}

	public void setCheckMediumTemp(String checkMediumTemp) {
		this.checkMediumTemp = checkMediumTemp;
	}

	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	public String getFirstRealPressure() {
		return firstRealPressure;
	}

	public void setFirstRealPressure(String firstRealPressure) {
		this.firstRealPressure = firstRealPressure;
	}

	public String getSecondRealPressure() {
		return secondRealPressure;
	}

	public void setSecondRealPressure(String secondRealPressure) {
		this.secondRealPressure = secondRealPressure;
	}

	public String getThirdRealPressure() {
		return thirdRealPressure;
	}

	public void setThirdRealPressure(String thirdRealPressure) {
		this.thirdRealPressure = thirdRealPressure;
	}

	public String getFirstTrailPressure() {
		return firstTrailPressure;
	}

	public void setFirstTrailPressure(String firstTrailPressure) {
		this.firstTrailPressure = firstTrailPressure;
	}

	public String getSecondTrailPressure() {
		return secondTrailPressure;
	}

	public void setSecondTrailPressure(String secondTrailPressure) {
		this.secondTrailPressure = secondTrailPressure;
	}

	public String getThirdTrailPressure() {
		return thirdTrailPressure;
	}

	public void setThirdTrailPressure(String thirdTrailPressure) {
		this.thirdTrailPressure = thirdTrailPressure;
	}

	public String getConclusion() {
		return conclusion;
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}

	public String getValidYear() {
		return validYear;
	}

	public void setValidYear(String validYear) {
		this.validYear = validYear;
	}

	public String getAdviceNoticeId() {
		return adviceNoticeId;
	}

	public void setAdviceNoticeId(String adviceNoticeId) {
		this.adviceNoticeId = adviceNoticeId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getVerifyDate() {
		return verifyDate;
	}

	public void setVerifyDate(String verifyDate) {
		this.verifyDate = verifyDate;
	}

	public String getCheckPlatformId() {
		return checkPlatformId;
	}

	public void setCheckPlatformId(String checkPlatformId) {
		this.checkPlatformId = checkPlatformId;
	}

	public String getPressureGaugeId() {
		return pressureGaugeId;
	}

	public void setPressureGaugeId(String pressureGaugeId) {
		this.pressureGaugeId = pressureGaugeId;
	}

	public String getPressureGaugeAccu() {
		return pressureGaugeAccu;
	}

	public void setPressureGaugeAccu(String pressureGaugeAccu) {
		this.pressureGaugeAccu = pressureGaugeAccu;
	}

	public String getPressureGaugeRange() {
		return pressureGaugeRange;
	}

	public void setPressureGaugeRange(String pressureGaugeRange) {
		this.pressureGaugeRange = pressureGaugeRange;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getTj() {
		return tj;
	}

	public void setTj(String tj) {
		this.tj = tj;
	}

	public String getGzjz() {
		return gzjz;
	}

	public void setGzjz(String gzjz) {
		this.gzjz = gzjz;
	}

	public String getZdyl() {
		return zdyl;
	}

	public void setZdyl(String zdyl) {
		this.zdyl = zdyl;
	}

	public String getFsdm() {
		return fsdm;
	}

	public void setFsdm(String fsdm) {
		this.fsdm = fsdm;
	}

	public String getAzwz() {
		return azwz;
	}

	public void setAzwz(String azwz) {
		this.azwz = azwz;
	}

	public String getWh() {
		return wh;
	}

	public void setWh(String wh) {
		this.wh = wh;
	}

	public String getDw() {
		return dw;
	}

	public void setDw(String dw) {
		this.dw = dw;
	}

	public String getLdzj() {
		return ldzj;
	}

	public void setLdzj(String ldzj) {
		this.ldzj = ldzj;
	}

	public String getZzdw() {
		return zzdw;
	}

	public void setZzdw(String zzdw) {
		this.zzdw = zzdw;
	}

	public String getXkbh() {
		return xkbh;
	}

	public void setXkbh(String xkbh) {
		this.xkbh = xkbh;
	}

	public String getYlfw() {
		return ylfw;
	}

	public void setYlfw(String ylfw) {
		this.ylfw = ylfw;
	}

	public String getCcrq() {
		return ccrq;
	}

	public void setCcrq(String ccrq) {
		this.ccrq = ccrq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
