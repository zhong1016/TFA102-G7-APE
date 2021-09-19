package com.article.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.article.model.ArticleDAO;
import com.article.model.ArticleVO;

@WebServlet("/viewPostAPI")
public class viewPostAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	doPost(req, res);
	}

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		res.addHeader("Access-Control-Allow-Origin", "*");
		String n = req.getParameter("name");
		
		Integer i = Integer.parseInt(n);
		System.out.println(req.getParameter("name"));
//		System.out.println(req.getAttribute("name"));
//		
//		System.out.println("++++++++++++++++++++++++++++++++++++++");
		ArticleDAO ado = new ArticleDAO();
		ArticleVO avo = new ArticleVO();
		avo = ado.findPrimartKey(i);
		
		
		
		/*
		System.out.println(avo.getARTICLE_CLASS()); //���o���O
		System.out.println(avo.getARTICLE()); //���o�D�D
		System.out.println(avo.getARTICLE_TIME()); //���o�ɶ�
		System.out.println(avo.getARTICLE_MAIN()); //���o����
		*/
		
		String a = avo.getARTICLE_CLASS(); //���O
		String b = avo.getARTICLE(); //�D�D
		String c = avo.getARTICLE_TIME(); //�ɶ�
		String d = avo.getARTICLE_MAIN();//����
//		String e = "hk4g";
		Integer e = avo.getUSER_NO();
		System.out.println("e=" +e);
		System.out.println("d=" +d);
		Map map = new HashMap();
		map.put("a1", a);
		map.put("a2", b);
		map.put("a3", c);
		map.put("a4", d);
		map.put("a5", e);
		
		PrintWriter out = res.getWriter();
		JSONObject json =  new JSONObject(map);
		out.println(json);
		System.out.println("++++++++++++++++++++++++++++++++++++++");
		System.out.println(json);
		System.out.println("�峹�s����:" + req.getParameter("articleNo"));
		System.out.println("�峹�s����(a):" + req.getAttribute("articleNo"));
//		System.out.println("�峹�s����(a):" + req.getAttribute("articleNo"));
//		req.setAttribute("a1", json);
		
//		res.getWriter().write("{\"a1\" : \""+a+"\"}");
//		res.getWriter().write("{\"a2\" : \""+b+"\"}");
//		res.getWriter().write("{\"a3\" : \""+c+"\"}");
//		res.getWriter().write("{\"a4\" : \"+d+\"}");
//		res.getWriter().write("{\"a5\" : \"�Ѱ�\"}");
    }

}
