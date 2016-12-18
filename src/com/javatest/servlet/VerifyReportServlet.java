package com.javatest.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javatest.dao.BeanTransformer;
import com.javatest.dao.ReportDao;
import com.javatest.model.SafeValReport;
/**
 * 录入报告（直接在html里通过表单提交发送请求到这里）
 *
 */
@WebServlet("/VerifyReportServlet")
public class VerifyReportServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VerifyReportServlet() {
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
		ReportDao dao = new ReportDao();
		SafeValReport model = new SafeValReport();
		BeanTransformer.parseBody2Model(request, model);
		if (dao.entryReport(model)) {
			response.sendRedirect("reportList.html");
		} else {
			PrintWriter out = null;
			try {
				out = response.getWriter();
				out.write("表格填写不正确!");
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
}
