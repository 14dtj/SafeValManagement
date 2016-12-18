package com.javatest.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.javatest.model.SimpleUser;
import com.javatest.util.JDBCUtil;

import net.sf.json.JSONArray;

public class UserManageDao {
	private Connection con;
	private JDBCUtil jdbcUtil;

	public UserManageDao() {
		jdbcUtil = new JDBCUtil();
		try {
			con = jdbcUtil.getCon();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * 系统管理员查看所有用户及其权限
	 * 
	 * @return
	 */
	public String getUsers() {
		List<SimpleUser> models = new ArrayList<>();
		if (con != null) {
			Statement stmt = null;
			try {
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			String sql = "select name as username,dep as department,role from [dbo].[user];";
			try {
				ResultSet rs = stmt.executeQuery(sql);
				ResultSetMetaData rsmd = rs.getMetaData();
				while (rs.next()) {
					SimpleUser model = new SimpleUser();
					BeanTransformer.transform2Model(model, rsmd, rs);
					models.add(model);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		JSONArray jsa = JSONArray.fromObject(models);
		jdbcUtil.closeCon(con);
		return jsa.toString();
	}

	/**
	 * 修改角色对应的权限信息
	 * 
	 * @param role
	 * @param choices
	 * @return
	 */
	public boolean updatePower(String role, String[] choices) {
		String powers = "";
		for (int i = 0; i < choices.length; i++) {
			String str = choices[i];
			if (i == choices.length - 1) {
				powers += str;
			} else {
				powers += str + "&";
			}
		}
		HtmlUpdater.updateHtml(role, powers);
		String sql = "update powerManage set power='" + powers + "' where role='" + role + "';";
		if (con != null) {
			Statement stmt = null;
			try {
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				int ok = stmt.executeUpdate(sql);
				if (ok <= 0) {
					sql = "insert into powerManage(role,power) values('" + role + "','" + powers + "');";
					ok = stmt.executeUpdate(sql);
					if (ok > 0) {
						jdbcUtil.closeCon(con);
						return true;
					}
				} else {
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		jdbcUtil.closeCon(con);
		return false;
	}

}
