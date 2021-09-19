package com.article.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.article.model.ArticleVO;
import com.mysql.cj.Session;
@WebServlet("/chatController")
public class chatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {		
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		res.addHeader("Access-Control-Allow-Origin", "*");
		String booleanValue ;

		HttpSession session = req.getSession(); // get the Server information by request
		
		if (session.getAttribute("memVO") == null) {
			booleanValue = "no";
		}else {
			booleanValue = "yes" ;
		}
		
		
		res.getWriter().println(booleanValue); // return value to Ajax
	}

}
