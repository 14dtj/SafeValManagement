package com.javatest.dao;

public class RoleHelper {
	private final static String[] userTypes = { "管理员", "普通员工", "检验员", "相关审批人员" };
	private final static String[] userIndex = { "main.html", "common.html", "checker.html", "examine.html" };
	private final static String[] userMenu = { "menu.html", "commonMenu.html", "checkerMenu.html", "examineMenu.html" };

	public static String getIndex(String type) {
		int index = 0;
		for (int i = 0; i < userTypes.length; i++) {
			if (type.equals(userTypes[i])) {
				index = i;
				break;
			}
		}
		return userIndex[index];
	}

	public static String getMenu(String type) {
		int index = 0;
		for (int i = 0; i < userTypes.length; i++) {
			if (type.equals(userTypes[i])) {
				index = i;
				break;
			}
		}
		return userMenu[index];
	}
}
