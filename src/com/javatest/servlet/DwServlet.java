package com.javatest.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javatest.dao.DwDao;
import com.javatest.model.Dw;

@WebServlet("/DwServlet")
public class DwServlet extends HttpServlet {
	private static final long serialVersionUID = 2L;
    public DwServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("userLogin")!=null) {
			doPost(request, response);
		}else{
			response.sendRedirect("login.html");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		DwDao dwDao = new DwDao();
		String flag = request.getParameter("flag");
		String jsonStr = "";
		String keyword = "";
		String page = request.getParameter("page");

		switch (flag) {
		case "srhall":
			jsonStr = dwDao.searchAll();
			break;
		case "srh":
			String code = request.getParameter("code");
			String owner = request.getParameter("owner");
			String name = request.getParameter("name");
			String tel = request.getParameter("tel");
			String address = request.getParameter("address");
	
			if(!code.isEmpty()){
				keyword += " WHERE code LIKE '%"+code+"%' ";
			}
			if(!owner.isEmpty()){
				if(!keyword.isEmpty()){
					keyword += " AND ";
				}else{
					keyword += " WHERE ";
				}
				keyword += "owner LIKE N'%"+owner+"%'";
			}
			if(!name.isEmpty()){
				if(!keyword.isEmpty()){
					keyword += " AND ";
				}else{
					keyword += " WHERE ";
				}
				keyword += "name LIKE N'%"+name+"%'";
			}
			if(!tel.isEmpty()){
				if(!keyword.isEmpty()){
					keyword += " AND ";
				}else{
					keyword += " WHERE ";
				}
				keyword += "tel=N'"+tel+"'";
			}
			if(!address.isEmpty()){
				if(!keyword.isEmpty()){
					keyword += " AND ";
				}else{
					keyword += " WHERE ";
				}
				keyword += "address=N'"+address+"'";
			}
			jsonStr = dwDao.search(page, keyword);
			break;
		case "delete":
			code = request.getParameter("code");
			if (dwDao.delete(code)) {
				jsonStr = "{\"msg\":\"delete success.\"}";
			}else{
				jsonStr = "{\"msg\":\"delete failed.\"}";
			}
			break;
		case "add":
			Dw a = new Dw();
			code = request.getParameter("code").trim();
			owner = request.getParameter("owner");
			name = request.getParameter("name");
			tel = request.getParameter("tel");
			address = request.getParameter("address");
			

			a.setCode(code);
			a.setOwner(owner);
			a.setName(name);
			a.setTel(tel);
			a.setAddress(address);
			
			
			if (dwDao.isExsit(code)) {
				if(dwDao.update(a)){
					jsonStr = "{\"msg\":\"update success.\"}";
				}else{
					jsonStr = "{\"msg\":\"update failed.\"}";
				}
			}else {
				if(dwDao.add(a)){
					jsonStr = "{\"msg\":\"insert success.\"}";
				}else{
					jsonStr = "{\"msg\":\"insert failed.\"}";
				}
			}
			break;
		default:
			response.sendRedirect("login.html");
			break;
		}
		PrintWriter out = null;
		try {
		    out = response.getWriter();
		    out.write(jsonStr);
		    out.flush();
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    if (out != null) {
		        out.close();
		    }
		}
	}
}
