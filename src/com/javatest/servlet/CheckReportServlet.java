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
import com.javatest.dao.UserDao;

/**
 * 审核报告
 *
 */
@WebServlet("/CheckReportServlet")
public class CheckReportServlet extends HttpServlet {
	private String username;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CheckReportServlet() {
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
			json = dao.getVerifiedReports();
			break;
		case "getspecific":
			String id = request.getParameter("id");
			json = dao.getVerifiedSpecificReport(id);
			System.out.println(id);
			System.out.println(json);
			json = json.substring(0, json.length() - 1);
			UserDao ud = new UserDao();
			String name = ud.getRealName(username);
			json = json + ",\"checkUser\":\"" + name + "\"}";
			System.out.println(json);
			break;
		case "pass":
			String id1 = request.getParameter("id");
			String checkUser = request.getParameter("checkUser");
			dao.passReport(checkUser, id1);
			break;
		case "deny":
			String id2 = request.getParameter("id");
			String reason = request.getParameter("reason");
			String checkUser1 = request.getParameter("checkUser");
			dao.denyReport(checkUser1, id2, reason);
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
