package com.javatest.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.javatest.model.Aqf;
import com.javatest.model.Wtd;
import com.javatest.util.JDBCUtil;

import net.sf.json.JSONArray;

public class WtdDao {
	private String aqftable = "[aqf]";
	private String wtdtable = "[wtd]";
	// private static long lastTime = 0;
	// private static String lastWtdNum = null;
	// private static int interval = 2000;

	public String searchAqf(String page, String keyword) {
		int pageNo = Integer.parseInt(page);
		String isWhere = keyword.isEmpty() ? " Where " : " AND ";
		String jsonStr = "";
		String pages = "0";
		try {
			JDBCUtil jdbcUtil = new JDBCUtil();
			Connection con = jdbcUtil.getCon();

			if (con != null) {
				Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				String sql = "SELECT TOP 5 * FROM " + aqftable + keyword + isWhere + "  ID NOT IN(SELECT TOP "
						+ ((pageNo - 1) * 5) + " ID FROM " + aqftable + keyword + " ORDER BY ID) ORDER BY ID";

				ResultSet rs = stmt.executeQuery(sql);
				rs.last();
				int rows = rs.getRow();
				if (rows == 0) {
					jsonStr = "\"没有符合条件的记录!\"";
				} else {
					Aqf[] aqfs = new Aqf[rows];
					rs.first();
					for (int i = 0; i < rows; i++, rs.next()) {
						Aqf aqf = new Aqf();
						aqf.setXh(rs.getString("xh").trim());
						aqf.setTj(rs.getString("tj").trim());
						aqf.setGzjz(rs.getString("gzjz").trim());
						aqf.setGzyl(rs.getString("gzyl").trim());
						aqf.setZdyl(rs.getString("zdyl").trim());
						aqf.setFsdm(rs.getString("fsdm").trim());
						aqf.setAzwz(rs.getString("azwz").trim());
						aqf.setWh(rs.getString("wh").trim());
						aqf.setName(rs.getString("name").trim());
						aqf.setDw(rs.getString("dw").trim());
						aqf.setZt(rs.getString("zt"));
						aqfs[i] = aqf;
					}

					sql = "SELECT COUNT(*) FROM " + aqftable + keyword;
					rs = stmt.executeQuery(sql);
					int recordCount = 0;
					if (rs.next()) {
						recordCount = rs.getInt(1);
					}
					pages = Integer.toString(recordCount);

					if (aqfs != null && aqfs.length != 0) {
						JSONArray jsa = JSONArray.fromObject(aqfs);
						jsonStr = jsa.toString();
					}
				}
			}
		} catch (Exception e) {
			jsonStr = "\"查询失败!\"";
			e.printStackTrace();
			return "{\"aqfs\":" + jsonStr + ",\"pages\":\"" + pages + "\"}";
		}
		return "{\"aqfs\":" + jsonStr + ",\"pages\":\"" + pages + "\"}";
	}

	public boolean delete(String dh) {
		JDBCUtil jdbcUtil = new JDBCUtil();
		try {
			Connection con = jdbcUtil.getCon();
			String sql = "delete from " + wtdtable + " where dh='" + dh + "'";
			Statement stmt = con.createStatement();
			int ok = stmt.executeUpdate(sql);
			if (ok > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean isExsit(String wh) {
		JDBCUtil jdbcUtil = new JDBCUtil();
		try {
			Connection con = jdbcUtil.getCon();
			String sql = "select * from " + wtdtable + " where wh='" + wh + "'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs != null && rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(Wtd a) {
		JDBCUtil jdbcUtil = new JDBCUtil();
		try {
			Connection con = jdbcUtil.getCon();
			String sql = "update " + wtdtable + " " + "set department=N'" + a.getDh() + "', dw=N'" + a.getDw()
					+ "', jy=N'" + a.getJy() + "', fee='" + a.getFee() + "' where wh='" + a.getWh() + "'";
			Statement stmt = con.createStatement();
			int ok = stmt.executeUpdate(sql);
			if (ok > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean add(Wtd a, String department) {
		System.out.println(department);
		JDBCUtil jdbcUtil = new JDBCUtil();
		try {
			Connection con = jdbcUtil.getCon();
			Statement stmt = con.createStatement();

			Date date = new Date();
			int year = date.getYear() + 1900;
			ResultSet rs;
			// 报告编号
			int month = date.getMonth() + 1;
			String sql2 = "select count(*) as count from wtd where department='" + department + "' and Year(time)="
					+ year + " and Month(time)=" + month + ";";
			rs = stmt.executeQuery(sql2);
			int index = 0;
			if (rs.next()) {
				index = rs.getInt("count") + 1;
			}
			String reportNum = "ALX-" + department + "-" + year + month + "-";
			String temp1 = index + "";
			for (int i = 0; i < 5 - temp1.length(); i++) {
				reportNum += "0";
			}
			reportNum += temp1;
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String time = df.format(date);
			String sql = "insert into " + wtdtable + "(department,dw,wh,jy,fee,time,dh,reportNum) " + "values(N'"
					+ department + "', N'" + a.getDw() + "', N'" + a.getWh() + "', N'" + a.getJy() + "', '" + a.getFee()
					+ "','" + time + "','" + a.getDh() + "','" + reportNum + "');";
			int ok = stmt.executeUpdate(sql);
			jdbcUtil.closeCon(con);
			if (ok > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public String searchWtd(String page, String keyword) {
		int pageNo = Integer.parseInt(page);
		String isWhere = keyword.isEmpty() ? " Where " : " AND ";
		String jsonStr = "";
		String pages = "0";
		try {
			JDBCUtil jdbcUtil = new JDBCUtil();
			Connection con = jdbcUtil.getCon();

			if (con != null) {
				Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				String sql = "SELECT TOP 5 * FROM " + wtdtable + keyword + isWhere + "  ID NOT IN(SELECT TOP "
						+ ((pageNo - 1) * 5) + " ID FROM " + wtdtable + keyword + " ORDER BY ID) ORDER BY ID";

				ResultSet rs = stmt.executeQuery(sql);
				rs.last();
				int rows = rs.getRow();
				if (rows == 0) {
					jsonStr = "\"没有符合条件的记录!\"";
				} else {
					Wtd[] wtds = new Wtd[rows];
					rs.first();
					for (int i = 0; i < rows; i++, rs.next()) {
						Wtd wtd = new Wtd();
						wtd.setDh(rs.getString("dh").trim());
						wtd.setDw(rs.getString("dw").trim());
						wtd.setWh(rs.getString("wh").trim());
						wtd.setJy(rs.getString("jy").trim());
						wtd.setFee(rs.getString("fee").trim());
						wtd.setTime(rs.getString("time"));
						wtds[i] = wtd;
					}

					sql = "SELECT COUNT(*) FROM " + wtdtable + keyword;
					rs = stmt.executeQuery(sql);
					int recordCount = 0;
					if (rs.next()) {
						recordCount = rs.getInt(1);
					}
					pages = Integer.toString(recordCount);

					if (wtds != null && wtds.length != 0) {
						JSONArray jsa = JSONArray.fromObject(wtds);
						jsonStr = jsa.toString();
					}
				}
			}
		} catch (Exception e) {
			jsonStr = "\"查询失败!\"";
			e.printStackTrace();
			return "{\"wtds\":" + jsonStr + ",\"pages\":\"" + pages + "\"}";
		}
		return "{\"wtds\":" + jsonStr + ",\"pages\":\"" + pages + "\"}";
	}

	public String getWtdNum(String department) {
		JDBCUtil jdbcUtil = new JDBCUtil();
		String wtdNum = "";
		try {
			Connection con = jdbcUtil.getCon();
			Statement stmt = con.createStatement();
			// 委托单编号
			Date date = new Date();
			int year = date.getYear() + 1900;
			String sql1 = "select dh from wtd where department='" + department + "' and Year(time)=" + year
					+ " order by dh desc;";
			ResultSet rs = stmt.executeQuery(sql1);
			while (rs.next()) {
				String temp = rs.getString("dh").trim();
				if (temp.length() == 16) {
					String index = temp.substring(11);
					wtdNum = temp.substring(0, 11);
					int x = Integer.parseInt(index) + 1;
					String num3 = x + "";
					for (int i = 0; i < 5 - num3.length(); i++) {
						wtdNum += "0";
					}
					wtdNum += num3;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wtdNum;
	}
}
