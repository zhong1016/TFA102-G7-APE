package com.article.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.article.model.AmessageDAO;
import com.article.model.ArticleDAO;
import com.article.model.ArticleVO;
import com.mem.model.MemDAO;
import com.mem.model.MemVO;
/**
 * Servlet implementation class control
 */
@WebServlet("/control")
public class control extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		List mList = new ArrayList();
		req.setCharacterEncoding("UTF-8");
		Integer articleNo = Integer.parseInt(req.getParameter("articleNo"));
		String name;
		
		System.out.println("### IN CONTROL");
		ArticleDAO dao = new ArticleDAO();
		
		ArticleVO articleVO	= dao.findPrimartKey(articleNo);
		Integer i = articleVO.getUSER_NO();
//		MemDAO memDAO = new MemDAO();
//		mList = memDAO.getAll();
//		
//		Iterator mit = mList.iterator();
//		
//		for (int j = 0; j < mList.size(); j++) {
//			if(i ==(((MemVO)mList.get(j)).getUserNo())) {
//				name =((MemVO) mList.get(j)).getUserlName();
//				req.setAttribute("author", name);
//			}
//		};
//		
		
		req.setAttribute("articleVO", articleVO);
		req.setAttribute("No", articleNo); 
		
		RequestDispatcher dispatcher =  req.getRequestDispatcher("/articleMain/view_post.jsp");
		dispatcher.forward(req, res);
	}

}
