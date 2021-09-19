package com.article.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.article.model.ArticleDAO;
import com.article.model.ArticleVO;
import com.mem.model.MemVO;
import com.mysql.cj.Session;
import com.mysql.cj.xdevapi.Result;

@WebServlet("/frontControl")
public class frontControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	  

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		
		req.setCharacterEncoding("UTF-8");
		HttpSession Session = req.getSession();
		Object o = Session.getAttribute("memVO");
		
		MemVO memVO =new MemVO();
		System.out.println("o=" + o);
		if (o == null ) {
			RequestDispatcher dispatcher =  req.getRequestDispatcher("http://localhost:8081/APE/members/Member.jsp");
			dispatcher.forward(req, res);
			return;
		}
		

		

		Integer articleNo = Integer.parseInt(req.getParameter("articleNo"));
		

		ArticleDAO dao = new ArticleDAO();
		dao.delete(articleNo);
		RequestDispatcher dispatcher =  req.getRequestDispatcher("/articleMain/articleFront.jsp");
		dispatcher.forward(req, res);
		
	}
	

	

}


