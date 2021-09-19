package com.article.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/checkReportAPI")
public class checkReportAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HashMap map = new HashMap(); //���|���O
	HashMap map_title = new HashMap(); //���|�D�D
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	doPost(req, res);
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		res.addHeader("Access-Control-Allow-Origin", "*");
		
		PrintWriter out= res.getWriter();
		
		HttpSession session = req.getSession();
		System.out.println("session=" + session.getAttribute("memVO"));
		if (session.getAttribute("memVO") == null) {
			out.println(false);
			return;
		}else {
			out.println(true);
		}
	}
}
