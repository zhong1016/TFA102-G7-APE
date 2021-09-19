package com.article.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.article.model.ArticleDAO;
import com.article.model.ArticleVO;

/**
 * Servlet implementation class fourmAPI
 */
@WebServlet("/fourmAPI")
public class fourmAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		res.addHeader("Access-Control-Allow-Origin", "*");
		
		ArticleVO avo = new ArticleVO();
		ArticleDAO dao = new ArticleDAO();
		List<ArticleVO> list = new ArrayList();
		List<ArticleVO> jlist = new ArrayList();
		
		//撈出全部資料
		list = dao.getAll();
		avo = list.get(0);
		JSONObject js = new JSONObject(avo);
		System.out.println(js);
	}

}
