package com.article.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.article.model.ArticleDAO;
import com.mysql.cj.Session;
@WebServlet("/countAPI")
public class countAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	doPost(req, res);
	}

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		res.addHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out= res.getWriter();
		
		HttpSession session = req.getSession();
		System.out.println("session=" + session.getAttribute("member"));
		//session.setAttribute("member", "abc");  //set the memberID to Ajax
		if (session.getAttribute("memVO") == null) {
			System.out.println("�o�䦳�����");
			out.println(false);
			return;
		}
		
		String n = req.getParameter("count");
		System.out.println("count=" + req.getParameter("count")); //���X�峹�s���A�Ψӧ�s���g�ƶq
		
		String p = req.getParameter("count"); //���X�峹�s��
		int i = Integer.parseInt(p);
		ArticleDAO dao = new ArticleDAO();
		dao.updateCount(i);
		
	}

}
