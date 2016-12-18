package com.javatest.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.javatest.model.SimpleAqf;
import com.javatest.model.WtdPrint;
import com.javatest.util.JDBCUtil;

import net.sf.json.JSONObject;

public class WtdPrintDao {
	private Connection con;
	private JDBCUtil jdbcUtil;

	public WtdPrintDao() {
		jdbcUtil = new JDBCUtil();
		try {
			con = jdbcUtil.getCon();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * 获得打印界面需要的信息
	 * 
	 * @param id
	 * @return {@link WtdPrint}
	 */
	public String getPrintInfo(String id) {
		WtdPrint model = null;
		if (con != null) {
			Statement stmt = null;
			try {
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			String sql = "select dw.name as unit,address,owner as contact,tel from dw,wtd where dw.code=wtd.dw and wtd.dh='"
					+ id + "';";
			try {
				ResultSet rs = stmt.executeQuery(sql);
				ResultSetMetaData rsmd = rs.getMetaData();
				if (rs.next()) {
					model = new WtdPrint();
					BeanTransformer.transform2Model(model, rsmd, rs);
				}
				sql = "select distinct aqf.xh as version,aqf.tj,aqf.gzjz as medium, gzyl as workPressure,zdyl as intePressure,aqf.fsdm as attachEquip,aqf.azwz as location, reportNum as reportId,fee from wtd,aqf where aqf.wh=wtd.wh and wtd.dh='"
						+ id + "';";
				rs = stmt.executeQuery(sql);
				rsmd = rs.getMetaData();
				List<SimpleAqf> list = new ArrayList<>();
				while (rs.next()) {
					SimpleAqf sa = new SimpleAqf();
					BeanTransformer.transform2Model(sa, rsmd, rs);
					list.add(sa);
				}
				model.setReportList(list);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		jdbcUtil.closeCon(con);
		return JSONObject.fromObject(model).toString();
	}
}
