package com.javatest.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.javatest.model.Dw;
import com.javatest.util.JDBCUtil;

import net.sf.json.JSONArray;

public class DwDao {
	private String dwtable="[dw]";
	
	public String searchAll(){
		String jsonStr="";
		try {
			JDBCUtil jdbcUtil = new JDBCUtil();
			Connection con = jdbcUtil.getCon();
			
			if (con != null) {
				Statement stmt=con.createStatement(
						ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				String sql = "SELECT name FROM "+dwtable;

				ResultSet rs = stmt.executeQuery(sql);
				rs.last();
				int rows = rs.getRow();
				if(rows == 0){
					jsonStr = "\"没有符合条件的记录!\"";
				}else{
					Dw[] dws = new Dw[rows];
					rs.first();
					for(int i = 0;i<rows;i++,rs.next()){
						Dw aqf = new Dw();
						aqf.setName(rs.getString("name").trim());
						dws[i] = aqf;
					}
	
					if(dws != null && dws.length != 0){
						JSONArray jsa = JSONArray.fromObject(dws);
						jsonStr = jsa.toString();
					}
				}
			}
		}catch (Exception e) {
				jsonStr = "\"查询失败!\"";
				e.printStackTrace();
				return  "{\"dws\":"+jsonStr+"\"}";
		}
		return "{\"dws\":"+jsonStr+"}";
	}

//新增的

	public String search(String page, String keyword){
		int pageNo = Integer.parseInt(page);
		String isWhere = keyword.isEmpty() ? " Where " : " AND ";
		String jsonStr="";
		String pages = "0";
		try {
			JDBCUtil jdbcUtil = new JDBCUtil();
			Connection con = jdbcUtil.getCon();
			
			if (con != null) {
				Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				String sql = "SELECT TOP 3 * FROM "+dwtable+keyword+isWhere+
						"  ID NOT IN(SELECT TOP "+((pageNo - 1)*3)+" ID FROM "+dwtable+keyword+
						" ORDER BY ID) ORDER BY ID";

				ResultSet rs = stmt.executeQuery(sql);
				rs.last();
				int rows = rs.getRow();
				if(rows == 0){
					jsonStr = "\"没有符合条件的记录!\"";
				}else{
					Dw[] dws = new Dw[rows];
					rs.first();
					for(int i = 0;i<rows;i++,rs.next()){
						Dw dw = new Dw();
						dw.setCode(rs.getString("code").trim());
						dw.setName(rs.getString("name").trim());
						dw.setOwner(rs.getString("owner").trim());
						dw.setTel(rs.getString("tel").trim());
						dw.setAddress(rs.getString("address").trim());
						
						dws[i] = dw;
					}
					
					sql = "SELECT COUNT(*) FROM "+dwtable+keyword;
					rs = stmt.executeQuery(sql);
					int recordCount = 0;
				      if (rs.next()) 
				      {
				              recordCount = rs.getInt(1);
				       }
					pages = Integer. toString(recordCount);
	
					if(dws != null && dws.length != 0){
						JSONArray jsa = JSONArray.fromObject(dws);
						jsonStr = jsa.toString();
					}
				}
			}
		}catch (Exception e) {
				jsonStr = "\"查询失败!\"";
				e.printStackTrace();
				return  "{\"dws\":"+jsonStr+",\"pages\":\""+pages+"\"}";
		}
		return "{\"dws\":"+jsonStr+",\"pages\":\""+pages+"\"}";
	}
	
	public boolean delete(String code) {
		JDBCUtil jdbcUtil = new JDBCUtil();
		try {
			Connection con = jdbcUtil.getCon();
			String sql = "delete from "+dwtable+" where code='"
					+code+"'";
			Statement stmt=con.createStatement();
			int ok = stmt.executeUpdate(sql);
			if(ok > 0 ){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean add(Dw a){
		JDBCUtil jdbcUtil = new JDBCUtil();
		try {
			Connection con = jdbcUtil.getCon();
			Statement stmt=con.createStatement();
			String sql = "insert into "+dwtable+"(code,name,owner,tel,address) values(N'"+
					a.getCode()+"', N'"+a.getName()+"', N'"+a.getOwner()+"', N'"+a.getTel()+"', N'"+a.getAddress()+"')";
			int ok = stmt.executeUpdate(sql);
			if(ok > 0 ){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean isExsit(String code){
		JDBCUtil jdbcUtil = new JDBCUtil();
		try {
			Connection con = jdbcUtil.getCon();
			String sql = "select * from "+dwtable+" where code='"
					+code+"'";
			Statement stmt=con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs!=null && rs.next()){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean update(Dw a){
		JDBCUtil jdbcUtil = new JDBCUtil();
		try {
			Connection con = jdbcUtil.getCon();
			String sql = "update "+dwtable+" set code=N'"+a.getCode()+"', name=N'"+a.getName()+"', owner=N'"+a.getOwner()+
					"', tel=N'"+a.getTel()+"', address=N'"+a.getAddress()+"' where code='"+a.getCode()+"'";
			Statement stmt=con.createStatement();
			int ok = stmt.executeUpdate(sql);
			if(ok > 0 ){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}






}
