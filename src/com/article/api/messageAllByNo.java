package com.article.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.article.model.AmessageDAO;
import com.article.model.AmessageVO;

@WebServlet("/messageAllByNo")
public class messageAllByNo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		res.addHeader("Access-Control-Allow-Origin", "*");

//		AmessageVO avo = new AmessageVO();
		AmessageDAO dao = new AmessageDAO();
		System.out.println("=======");
		List list = dao.getAllByNo(1);
		System.out.println(list.get(0));
//		System.out.println(dao.getAllByNo(1));
		System.out.println("=========");
//		JSONObject js = new JSONObject(dao.getAllByNo(1));
//		System.out.println(js);
		

	}

}
