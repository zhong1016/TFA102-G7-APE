package com.article.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.article.model.ArticleDAO;
import com.article.model.ArticleVO;
@WebServlet("/reportControl")

public class reportControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	  

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		

		Integer rptno = Integer.parseInt(req.getParameter("rptno"));
		
		System.out.println("reptno=" + rptno);
		
		
		req.setAttribute("No", rptno); 
		
		RequestDispatcher dispatcher =  req.getRequestDispatcher("/articleMain/report.jsp");
		dispatcher.forward(req, res);
	}

}
