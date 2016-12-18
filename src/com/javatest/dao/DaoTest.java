package com.javatest.dao;

import com.javatest.model.SafeValReport;
import com.javatest.model.User;
import com.javatest.model.Wtd;

public class DaoTest {
	public static void main(String[] args) {
		// ReportDao dao = new ReportDao();
		// System.out.println(dao.getCheckedSpecificReport("ALX-BF-201612-"));
		DaoTest.testGetWtdNum();
	}

	public static void testUserDao() {
		UserDao dao = new UserDao();
		User user = new User();
		user.setDep("s");
		user.setPanel("main.html");
		user.setPassword("123456");
		user.setReal("tt");
		user.setRole("管理员");
		user.setUsername("ssx");
		dao.addUser(user);
	}

	public static void testUserManageDao() {
		UserManageDao dao = new UserManageDao();
		System.out.println(dao.getUsers());
	}

	public static void testWtdPrintDao() {
		WtdPrintDao dao = new WtdPrintDao();
		System.out.println(dao.getPrintInfo("test"));
	}

	public static void testPowerDao() {
		UserManageDao dao = new UserManageDao();
		String[] strs = { "user.html&company.html", "aqf.html" };
		dao.updatePower("管", strs);
	}

	public static void testWtdDao() {
		WtdDao dao = new WtdDao();
		Wtd model = new Wtd();
		model.setDh("BF");
		model.setDw("unit1");
		model.setFee("100");
		model.setJy("dg");
		model.setWh("id0");
		// dao.add(model);
	}

	public static void testReportDao() {
		ReportDao dao = new ReportDao();
		SafeValReport model = new SafeValReport();
		model.setXh("test");
		model.setTj("0");
		model.setGzjz("test");
		model.setFsdm("test");
		model.setAzwz("test");
		model.setWh("test");
		model.setDw("test");
		model.setLdzj("test");
		model.setZzdw("test");
		model.setXkbh("test");
		model.setYlfw("test");
		model.setCcrq("test");
		model.setVerifyMode("test");
		model.setOperateNorm("test");
		model.setCheckMedium("test");
		model.setCheckMediumTemp("test");
		model.setRecord("test");
		model.setFirstRealPressure("test");
		model.setSecondRealPressure("test");
		model.setThirdRealPressure("test");
		model.setConclusion("test");
		model.setValidYear("test");
		model.setAdviceNoticeId("test");
		model.setRemark("test");
		model.setVerifyDate("2016-12-12");
		// model.setCheckDate("2016-12-12");
		model.setCheckPlatformId("test");
		model.setPressureGaugeId("test");
		model.setPressureGaugeAccu("test");
		model.setPressureGaugeRange("test");
		System.out.println(dao.entryReport(model));
	}

	public static void testPrintReportDao() {
		ReportDao dao = new ReportDao();
		System.out.println(dao.getReportListBySearch("tj", "ALX-BF-201612-00005", "2016-12-11"));
	}

	public static void testGetWtdNum() {
		WtdDao dao = new WtdDao();
		System.out.println(dao.getWtdNum("NF"));
	}
}
