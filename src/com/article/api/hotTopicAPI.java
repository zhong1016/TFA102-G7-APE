package com.article.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.article.model.ArticleDAO;
import com.article.model.ArticleVO;

@WebServlet("/hotTopicAPI")
public class hotTopicAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		res.addHeader("Access-Control-Allow-Origin", "*");

		// ArticleVO avo = new ArticleVO();
		

		ArrayList<ArticleVO>list = new ArrayList<ArticleVO>();
		ArticleDAO dao = new ArticleDAO();

		list = (ArrayList<ArticleVO>) dao.getAllByCount();

		// 產出五個物件
		PrintWriter out = res.getWriter();
		ArticleVO avo = new ArticleVO();
		ArticleVO avo1 = new ArticleVO();
		ArticleVO avo2 = new ArticleVO();
		ArticleVO avo3 = new ArticleVO();
		ArticleVO avo4 = new ArticleVO();

		avo = (ArticleVO) list.get(0);
		avo1 = (ArticleVO) list.get(1);
		avo2 = (ArticleVO) list.get(2);
		avo3 = (ArticleVO) list.get(3);
		avo4 = (ArticleVO) list.get(4);

		// 取值
		String str0 = avo.getARTICLE_CLASS(); // 取得主題
		String str1 = avo.getARTICLE(); // 文章主題
		int j = avo.getCOUNT();
		String str2 = String.valueOf(j);
		String str3 = avo.getARTICLE_TIME();

		String str4 = avo1.getARTICLE_CLASS(); // 取得主題
		String str5 = avo1.getARTICLE(); // 文章主題
		int d = avo1.getCOUNT();
		String str6 = String.valueOf(d);
		String str7 = avo1.getARTICLE_TIME();

		String str8 = avo2.getARTICLE_CLASS(); // 取得主題
		String str9 = avo2.getARTICLE(); // 文章主題
		int b = avo2.getCOUNT();
		String str10 = String.valueOf(b);
		String str11 = avo2.getARTICLE_TIME();

		String str12 = avo3.getARTICLE_CLASS(); // 取得主題
		String str13 = avo3.getARTICLE(); // 文章主題
		int c = avo3.getCOUNT();
		String str14 = String.valueOf(c);
		String str15 = avo3.getARTICLE_TIME();

		String str16 = avo4.getARTICLE_CLASS(); // 取得主題
		String str17 = avo4.getARTICLE(); // 文章主題
		int i = avo4.getCOUNT();
		String str18 = String.valueOf(i);
		String str19 = avo4.getARTICLE_TIME();

		Map map = new HashMap();
		//放value
		map.put("a1", str0);
		map.put("a2", str1);
		map.put("a3", str2);
		map.put("a4", str3);
		map.put("a5", str4);
		map.put("a6", str5);
		map.put("a7", str6);
		map.put("a8", str7);
		map.put("a9", str8);
		map.put("a10", str9);
		map.put("a11", str10);
		map.put("a12", str11);
		map.put("a13", str12);
		map.put("a14", str13);
		map.put("a15", str14);
		map.put("a16", str15);
		map.put("a17", str16);
		map.put("a18", str17);
		map.put("a19", str18);
		map.put("a20", str19);
		

		JSONObject js = new JSONObject(map);
		out.println(js);

	}

}
