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
@WebServlet("/AreaControl")
public class AreaControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {		
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String aaa = req.getParameter("area"); //tao
		System.out.println(aaa);
		List list = new ArrayList();
		
		ArticleDAO dao = new ArticleDAO();
		list =  dao.getAllByArea(aaa); 
		
		req.setAttribute("area", list);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/articleMain/fourmArea.jsp");
		dispatcher.forward(req, res);
	}


}
