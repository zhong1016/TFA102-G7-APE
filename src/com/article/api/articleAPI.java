package com.article.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.article.model.ArticleDAO;
import com.article.model.ArticleVO;
import com.mem.model.MemVO;



@WebServlet("/articleAPI")
public class articleAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		res.addHeader("Access-Control-Allow-Origin", "*");
		String member;
		MemVO mvo = new MemVO();
		HttpSession session = req.getSession();
		mvo = (MemVO)session.getAttribute("memVO"); //get the memVO object
		Integer mvoName = (Integer)mvo.getUserNo();
		
		
		for (int i = 1; i <=4; i++) {
			System.out.println(req.getParameter("t"+i));
		}
		System.out.println("======================");
		
		System.out.println(req.getParameter("t2"));
		ArticleVO  avo = new ArticleVO();
		avo.setAREA(req.getParameter("t2")); 
		avo.setARTICLE(req.getParameter("t4")); 
		avo.setARTICLE_CLASS(req.getParameter("t3")); 
		avo.setARTICLE_MAIN(req.getParameter("t1")); //�峹����
		avo.setARTICLE_STATUS(true);
		avo.setCOUNT(0);
		avo.setUSER_NO(mvoName);
		ArticleDAO dao = new ArticleDAO();
		dao.insert(avo);
	}
}