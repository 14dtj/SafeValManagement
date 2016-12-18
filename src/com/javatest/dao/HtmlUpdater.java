package com.javatest.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class HtmlUpdater {
	private static String[] menuList;
	private static String[] titles = { "User manage", "Safety value manage", "Department manage", "Assignment manage", "Entry report", "Check report", "Authority manage" };
	private static String[] htmls = { "user.html", "aqf.html", "company.html", "wtd.html", "reportList.html",
			"checkReportList.html", "powerManage.html" };

	public static void updateHtml(String role, String powers) {
		menuList = powers.split("&");
		String menu = RoleHelper.getMenu(role);
		String filePath = HtmlUpdater.class.getResource("").getPath();
		filePath = filePath.substring(0, filePath.length() - 1);
		String[] paths = filePath.split("/");
		int length = filePath.length();
		String result = "";
		for (int i = paths.length - 1; i >= 0; i--) {
			if (paths[i].equals("java_test")) {
				break;
			}
			result = filePath.substring(0, length - paths[i].length() - 1);
			length = length - paths[i].length() - 1;
		}
		result = result + "/" + menu;
		System.out.println(result);
		HtmlUpdater.write(result, HtmlUpdater.read(result));
	}

	public static String read(String filePath) {
		BufferedReader br = null;
		String line = null;
		StringBuffer buf = new StringBuffer();

		try {
			br = new BufferedReader(new FileReader(filePath));
			while ((line = br.readLine()) != null) {
				if (line.contains("<li>")) {
					break;
				}
				buf.append(line);
				buf.append(System.getProperty("line.separator"));
			}
			for (String str : menuList) {
				String title = getTitle(str);
				buf.append("<li><a href=\"" + str + "\" title=\"" + title + "\" target=right>" + title + "</a></li> ");
				buf.append(System.getProperty("line.separator"));
			}
			while ((line = br.readLine()) != null) {
				if (line.contains("<li>")) {
					continue;
				}
				buf.append(line);
				buf.append(System.getProperty("line.separator"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					br = null;
				}
			}
		}

		return buf.toString();
	}

	public static void write(String filePath, String content) {
		OutputStreamWriter pw = null;
		try {
			pw = new OutputStreamWriter(new FileOutputStream(filePath),"GBK");
			pw.write(content);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		BufferedWriter bw = null;
//		try {
//			bw = new BufferedWriter(new FileWriter(filePath,"utf-8"));
//			bw.write(content);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (bw != null) {
//				try {
//					bw.close();
//				} catch (IOException e) {
//					bw = null;
//				}
//			}
//		}
	}

	private static String getTitle(String html) {
		int index = 0;
		for (int i = 0; i < htmls.length; i++) {
			if (html.equals(htmls[i])) {
				index = i;
				break;
			}
		}
		return titles[index];
	}
}
