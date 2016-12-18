package com.javatest.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.javatest.model.CheckSafeValReport;
import com.javatest.model.PrintSafeValReport;
import com.javatest.model.SafeValReport;
import com.javatest.model.SimplePrintReport;
import com.javatest.model.SimpleReport;
import com.javatest.util.JDBCUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ReportDao {
	private Connection con;
	private JDBCUtil jdbcUtil;

	public ReportDao() {
		jdbcUtil = new JDBCUtil();
		try {
			con = jdbcUtil.getCon();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * 获得该检验员可以检验的所有未检验的安全阀报告
	 * 
	 * @param username
	 * @return
	 */
	public String getUnVerifyReports(String username) {
		return getReportList("未检验", username);
	}

	public String getSpecificReport(String reportId) {
		SafeValReport model = null;
		if (con != null) {
			Statement stmt = null;
			try {
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			String sql = "select aqf.id,xh,tj,gzjz,zdyl,fsdm,azwz,aqf.wh,aqf.dw,ldzj,zzdw,xkbh,ylfw,ccrq,department from aqf,wtd where aqf.wh='"
					+ reportId + "' and wtd.wh=aqf.wh;";
			try {
				ResultSet rs = stmt.executeQuery(sql);
				ResultSetMetaData rsmd = rs.getMetaData();
				if (rs.next()) {
					model = new SafeValReport();
					BeanTransformer.transform2Model(model, rsmd, rs);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		jdbcUtil.closeCon(con);
		return JSONObject.fromObject(model).toString();
	}

	public String getVerifiedSpecificReport(String reportId) {
		CheckSafeValReport model = null;
		if (con != null) {
			Statement stmt = null;
			try {
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			String sql = "select aqf.id as id,aqf.dw as dw,address,tel,aqf.fsdm as fsdm,aqf.azwz as azwz,aqf.xh as xh,aqf.tj as tj,aqf.zzdw as zzdw ,gzyl,aqf.gzjz as gzjz,firstRealPressure,secondRealPressure,thirdRealPressure,firstTrailPressure,secondTrailPressure,thirdTrailPressure,conclusion as verifyResult,record,verifyDate,validYear,aqf.zdyl as zdyl ,operateNorm ,verifyMode,checkMedium as verifyMedium,jy as verifyUser,reportNum from checkReport,aqf,dw,wtd where dw.code=aqf.dw and aqf.wh=wtd.wh and aqf.wh=checkReport.wh and aqf.wh='"
					+ reportId + "';";
			try {
				ResultSet rs = stmt.executeQuery(sql);
				ResultSetMetaData rsmd = rs.getMetaData();
				if (rs.next()) {
					model = new CheckSafeValReport();
					BeanTransformer.transform2Model(model, rsmd, rs);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		jdbcUtil.closeCon(con);
		return JSONObject.fromObject(model).toString();
	}

	public boolean entryReport(SafeValReport model) {
		if (con != null) {
			Statement stmt = null;
			try {
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			String sql = "insert into checkReport(xh,tj,gzjz,fsdm,azwz,wh,dw,ldzj,zzdw,xkbh,ylfw,ccrq,"
					+ "verifyMode,operateNorm,checkMedium,checkMediumTemp,record,firstRealPressure,secondRealPressure,"
					+ "thirdRealPressure,firstTrailPressure,secondTrailPressure,thirdTrailPressure,conclusion,validYear,"
					+ "adviceNoticeId,remark,verifyDate,checkPlatformId,pressureGaugeId,pressureGaugeAccu,pressureGaugeRange)"
					+ " values('" + model.getXh() + "'," + model.getTj() + ",'" + model.getGzjz() + "','"
					+ model.getFsdm() + "'," + "'" + model.getAzwz() + "','" + model.getWh() + "','" + model.getDw()
					+ "','" + model.getLdzj() + "'," + "'" + model.getZzdw() + "','" + model.getXkbh() + "','"
					+ model.getYlfw() + "','" + model.getCcrq() + "'," + "'" + model.getVerifyMode() + "','"
					+ model.getOperateNorm() + "','" + model.getCheckMedium() + "'," + "'" + model.getCheckMediumTemp()
					+ "','" + model.getRecord() + "','" + model.getFirstRealPressure() + "'," + "'"
					+ model.getSecondRealPressure() + "','" + model.getThirdRealPressure() + "','"
					+ model.getFirstTrailPressure() + "'," + "'" + model.getSecondTrailPressure() + "','"
					+ model.getThirdTrailPressure() + "','" + model.getConclusion() + "','" + model.getValidYear()
					+ "'," + "'" + model.getAdviceNoticeId() + "','" + model.getRemark() + "','" + model.getVerifyDate()
					+ "','" + model.getCheckPlatformId() + "','" + model.getPressureGaugeId() + "','"
					+ model.getPressureGaugeAccu() + "','" + model.getPressureGaugeRange() + "');";
			try {
				int ok = stmt.executeUpdate(sql);
				if (ok > 0) {
					sql = "update aqf set zt='已检验' where wh='" + model.getWh() + "';";
					stmt.executeUpdate(sql);
					jdbcUtil.closeCon(con);
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		jdbcUtil.closeCon(con);
		return false;
	}

	/**
	 * 获得状态为已检验的所有安全阀报告
	 * 
	 * @return
	 */
	public String getVerifiedReports() {
		return getReportList("已检验", null);
	}

	/**
	 * 审核通过
	 * 
	 * @param username
	 * @param reportId
	 */
	public void passReport(String username, String reportId) {
		if (con != null) {
			Statement stmt = null;
			try {
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String sql = "insert into checkRecord(reportId,checkUser,isPass,time) values('" + reportId + "','"
					+ username + "',1,'" + df.format(new Date()) + "');";
			try {
				stmt.executeUpdate(sql);
				sql = "update aqf set zt = '已审核' where wh='" + reportId + "';";
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 审核不通过
	 * 
	 * @param username
	 * @param reportId
	 * @param reason
	 */
	public void denyReport(String username, String reportId, String reason) {
		if (con != null) {
			Statement stmt = null;
			try {
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String date = df.format(new Date());
			String sql = "insert into checkRecord(reportId,checkUser,advice,isPass,time) values('" + reportId + "','"
					+ username + "','" + reason + "',0,'" + date + "');";
			try {
				stmt.executeUpdate(sql);
				sql = "update aqf set zt = '未检验' where wh='" + reportId + "';";
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private String getReportList(String state, String username) {
		List<SimpleReport> result = new ArrayList<>();
		if (con != null) {
			Statement stmt = null;
			try {
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			String sql = "select role from [user] where name='" + username + "';";
			String role = "";
			try {
				ResultSet rs1 = stmt.executeQuery(sql);
				if (rs1.next()) {
					role = rs1.getString("role").trim();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if (username == null || role.equals("管理员")) {
				sql = "select aqf.name as safeName,wtd.wh as safeId from aqf,wtd where aqf.zt='" + state
						+ "'and aqf.wh=wtd.wh;";
			} else {
				sql = "select aqf.name as safeName,wtd.wh as safeId from aqf,wtd where wtd.jy='" + username
						+ "' and aqf.zt='" + state + "'and aqf.wh=wtd.wh;";
			}
			try {
				ResultSet rs = stmt.executeQuery(sql);
				ResultSetMetaData rsmd = rs.getMetaData();
				while (rs.next()) {
					SimpleReport model = new SimpleReport();
					BeanTransformer.transform2Model(model, rsmd, rs);
					result.add(model);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		JSONArray jsa = JSONArray.fromObject(result);
		jdbcUtil.closeCon(con);
		return jsa.toString();
	}

	/**
	 * 获取已审核过的报告列表
	 * 
	 * @param username
	 * @return
	 */
	public String getPrintReportList(String username) {
		List<SimplePrintReport> result = new ArrayList<>();
		if (con != null) {
			Statement stmt = null;
			try {
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			String sql = "select role from [user] where name='" + username + "';";
			String role = "";
			try {
				ResultSet rs1 = stmt.executeQuery(sql);
				if (rs1.next()) {
					role = rs1.getString("role").trim();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if (username == null || role.equals("管理员")) {
				sql = "select reportNum,checkUser,checkRecord.time as checkDate from checkRecord,wtd,aqf where checkRecord.reportId=wtd.wh and aqf.zt='已审核' and aqf.wh=wtd.wh;";
			} else {
				sql = "select reportNum,checkUser,checkRecord.time as checkDate from checkRecord,wtd,aqf where checkRecord.reportId=wtd.wh and aqf.zt='已审核' and aqf.wh=wtd.wh and checkUser='"
						+ username + "';";
			}
			try {
				ResultSet rs = stmt.executeQuery(sql);
				ResultSetMetaData rsmd = rs.getMetaData();
				while (rs.next()) {
					SimplePrintReport model = new SimplePrintReport();
					BeanTransformer.transform2Model(model, rsmd, rs);
					result.add(model);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		JSONArray jsa = JSONArray.fromObject(result);
		jdbcUtil.closeCon(con);
		return jsa.toString();
	}

	/**
	 * 获取已审核过的报告的具体信息
	 * 
	 * @param reportId
	 * @return
	 */
	public String getCheckedSpecificReport(String reportId) {
		PrintSafeValReport model = null;
		if (con != null) {
			Statement stmt = null;
			try {
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			String sql = "select aqf.id as id,aqf.dw as dw,address,tel,aqf.fsdm as fsdm,aqf.azwz as azwz,aqf.xh as xh,aqf.tj as tj,aqf.zzdw as zzdw ,gzyl,aqf.gzjz as gzjz,firstRealPressure,secondRealPressure,thirdRealPressure,firstTrailPressure,secondTrailPressure,thirdTrailPressure,conclusion as verifyResult,record,verifyDate,validYear,aqf.zdyl as zdyl ,operateNorm ,verifyMode,checkMedium as verifyMedium,jy as verifyUser,checkUser,reportNum from checkReport,aqf,dw,wtd,checkRecord where dw.code=aqf.dw and aqf.wh=wtd.wh and aqf.wh=checkReport.wh and wtd.reportNum='"
					+ reportId + "' and checkRecord.reportId =aqf.wh;";
			try {
				ResultSet rs = stmt.executeQuery(sql);
				ResultSetMetaData rsmd = rs.getMetaData();
				if (rs.next()) {
					model = new PrintSafeValReport();
					BeanTransformer.transform2Model(model, rsmd, rs);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		jdbcUtil.closeCon(con);
		return JSONObject.fromObject(model).toString();
	}

	/**
	 * 根据审批人查询报告
	 * 
	 * @param checkUser
	 * @return
	 */
	public String getReportListByUser(String checkUser) {
		List<SimplePrintReport> result = new ArrayList<>();
		if (con != null) {
			Statement stmt = null;
			try {
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			String sql = "select reportNum,checkUser,checkRecord.time as checkDate from checkRecord,wtd,aqf where checkRecord.reportId=wtd.wh and aqf.zt='已审核' and aqf.wh=wtd.wh and checkUser='"
					+ checkUser + "';";

			try {
				ResultSet rs = stmt.executeQuery(sql);
				ResultSetMetaData rsmd = rs.getMetaData();
				while (rs.next()) {
					SimplePrintReport model = new SimplePrintReport();
					BeanTransformer.transform2Model(model, rsmd, rs);
					result.add(model);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		JSONArray jsa = JSONArray.fromObject(result);
		jdbcUtil.closeCon(con);
		return jsa.toString();
	}

	/**
	 * 根据报告号模糊查询
	 * 
	 * @param reportNum
	 * @return
	 */
	public String getReportListByReport(String reportNum) {
		List<SimplePrintReport> result = new ArrayList<>();
		if (con != null) {
			Statement stmt = null;
			try {
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			String sql = "select reportNum,checkUser,checkRecord.time as checkDate from checkRecord,wtd,aqf where checkRecord.reportId=wtd.wh and aqf.zt='已审核' and aqf.wh=wtd.wh and reportNum like '"
					+ reportNum + "%';";

			try {
				ResultSet rs = stmt.executeQuery(sql);
				ResultSetMetaData rsmd = rs.getMetaData();
				while (rs.next()) {
					SimplePrintReport model = new SimplePrintReport();
					BeanTransformer.transform2Model(model, rsmd, rs);
					result.add(model);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		JSONArray jsa = JSONArray.fromObject(result);
		jdbcUtil.closeCon(con);
		return jsa.toString();
	}

	/**
	 * 根据日期查询报告
	 * 
	 * @param checkDate
	 * @return
	 */
	public String getReportListByDate(String checkDate) {
		List<SimplePrintReport> result = new ArrayList<>();
		if (con != null) {
			Statement stmt = null;
			try {
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			String sql = "select reportNum,checkUser,checkRecord.time as checkDate from checkRecord,wtd,aqf where checkRecord.reportId=wtd.wh and aqf.zt='已审核' and aqf.wh=wtd.wh and checkRecord.time='"
					+ checkDate + "';";

			try {
				ResultSet rs = stmt.executeQuery(sql);
				ResultSetMetaData rsmd = rs.getMetaData();
				while (rs.next()) {
					SimplePrintReport model = new SimplePrintReport();
					BeanTransformer.transform2Model(model, rsmd, rs);
					result.add(model);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		JSONArray jsa = JSONArray.fromObject(result);
		jdbcUtil.closeCon(con);
		return jsa.toString();
	}

	/**
	 * 多重条件查询
	 * 
	 * @param sql
	 * @return
	 */
	public String getReportListBySearch(String checkUser, String reportNum, String checkDate) {
		String keyword = "";
		if ((checkUser != null) && (checkUser != "") && (!checkUser.isEmpty())) {
			keyword += "and checkUser='" + checkUser + "'";
		}
		if ((reportNum != null) && (reportNum != "") && (!reportNum.isEmpty())) {
			keyword += "and reportNum='" + reportNum + "'";
		}
		if ((checkDate != null) && (checkDate != "") && (!checkDate.isEmpty())) {
			keyword += "and checkRecord.time='" + checkDate + "'";
		}
		List<SimplePrintReport> result = new ArrayList<>();
		if (con != null) {
			Statement stmt = null;
			try {
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			String sql = "select reportNum,checkUser,checkRecord.time as checkDate from checkRecord,wtd,aqf where checkRecord.reportId=wtd.wh and aqf.zt='已审核' and aqf.wh=wtd.wh "
					+ keyword + ";";

			try {
				ResultSet rs = stmt.executeQuery(sql);
				ResultSetMetaData rsmd = rs.getMetaData();
				while (rs.next()) {
					SimplePrintReport model = new SimplePrintReport();
					BeanTransformer.transform2Model(model, rsmd, rs);
					result.add(model);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		JSONArray jsa = JSONArray.fromObject(result);
		jdbcUtil.closeCon(con);
		return jsa.toString();
	}
}
