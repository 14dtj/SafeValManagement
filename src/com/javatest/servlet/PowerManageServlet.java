package com.javatest.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javatest.dao.UserManageDao;

@WebServlet("/PowerManage")
public class PowerManageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PowerManageServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("userLogin") != null) {
			doPost(request, response);
		} else {
			response.sendRedirect("login.html");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserManageDao dao = new UserManageDao();
		String role = request.getParameter("role");
		String[] choices = request.getParameterValues("power");
		String jsonStr = "";
		if (choices == null) {
			jsonStr = "请选择权限！";
		} else {
			if (dao.updatePower(role, choices)) {
				jsonStr = "修改成功";
			} else {
				jsonStr = "数据库更新失败！";
			}
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
