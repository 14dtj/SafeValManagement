package com.javatest.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javatest.dao.ReportDao;
/**
 * 显示未检验报告列表和具体信息
 *
 */
@WebServlet("/ReportServlet")
public class ReportServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;

	public ReportServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("userLogin") != null) {
			username = (String) session.getAttribute("userLogin");
			doPost(request, response);
		} else {
			response.sendRedirect("login.html");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ReportDao dao = new ReportDao();
		String flag = request.getParameter("flag");
		String json = "";
		switch (flag) {
		case "getList":
			json = dao.getUnVerifyReports(username);
			break;
		case "getspecific":
			String id = request.getParameter("id");
			json = dao.getSpecificReport(id);
			break;
		}
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(json);
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
