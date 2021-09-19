package com.report.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.article.model.ReportDAO;
import com.article.model.ReportVO;;

/**
 * Servlet implementation class ReportReview
 */
@WebServlet("/ReportReview")
public class ReportReview extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");

		if ("reportReview".equals(action)) { // 接收report.jsp的審核請求
			Integer REPORT_NO = Integer.parseInt(req.getParameter("reportNo"));

			ReportDAO dao = new ReportDAO();

			ReportVO reportVO = dao.findPrimaryKey(REPORT_NO);
			
			System.out.println(reportVO);
			req.setAttribute("reportVO", reportVO);

			RequestDispatcher dispatcher = req.getRequestDispatcher("/backstage/report1.jsp");

			dispatcher.forward(req, res);
		}

		if ("reportVerify".equals(action)) { // 接收report1.jsp的請求

			Integer REPORT_NO = Integer.parseInt(req.getParameter("reportNo"));

			// 抓取審核結果
			String verify = req.getParameter("verify");
			
			
			//抓取審核意見欄
			String opinion = req.getParameter("opinion");
			

			if ("pass".equals(verify)) {

				ReportDAO dao = new ReportDAO();

				// 找出欲更改之檢舉
				ReportVO reportVO = dao.findPrimaryKey(REPORT_NO);

				reportVO.setREPORT_STATUS("a1");

				// 更新檢舉
				dao.update(reportVO);

							
				//寄檢舉通知信
				String mail = "apetfa102team07@gmail.com";
				String ch_name = "陳駿同";
				req.setAttribute("email", mail);
				req.setAttribute("ch_name", ch_name);
				req.setAttribute("opinion", opinion);
					
				RequestDispatcher dispatcher = req.getRequestDispatcher("/MailService");
				
				dispatcher.forward(req, res);
			
				
			} else {
				ReportDAO dao = new ReportDAO();

				// 找出欲更改之檢舉
				ReportVO reportVO = dao.findPrimaryKey(REPORT_NO);

				reportVO.setREPORT_STATUS("a2");

				// 更新檢舉
				dao.update(reportVO);

				//寄檢舉通知信
				String mail = "apetfa102team07@gmail.com";
				String ch_name = "陳駿同";
				req.setAttribute("email", mail);
				req.setAttribute("ch_name", ch_name);
				req.setAttribute("opinion", opinion);
					
				RequestDispatcher dispatcher = req.getRequestDispatcher("/MailService");
				
				dispatcher.forward(req, res);

			}

		}

	}

}
