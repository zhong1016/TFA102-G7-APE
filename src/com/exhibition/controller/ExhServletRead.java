package com.exhibition.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.exhibition.model.ExhService;
import com.exhibition.model.ExhVO;

@WebServlet("/ExhServletRead")
public class ExhServletRead extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=Big5");
        res.addHeader("Access-Control-Allow-Origin","*");
        PrintWriter out = res.getWriter();
		String img = "http://localhost:8081/APE/ExhJavaPic?id=";
		String Area = req.getParameter("Area");
		System.out.println(Area);
		ExhService exhSvc = new ExhService();
		List<ExhVO> exhvo = exhSvc.getAll();
		Map<String,Object> map = new HashMap<>();
		Map<Integer,Object> map2 = new HashMap<>();
		JSONObject obj = null;
		Integer i = 1;
//EXHIBITION_NO, EXHIBITION_PIC, EXHIBITION_DATE, EXHIBITION_TOPIC, EXHIBITION_CONTENT EXHIBITION_AREA
		for(ExhVO e : exhvo) {
			if(Area.equals("0")) {
				map.put("Date", e.getExhibitionDate());
			 	map.put("Topic",e.getExhibitionTopic());
				map.put("Content",e.getExhibitionContent());
				map.put("img",img+e.getExhibitionNo());
				map.put("Area",e.getExhibitionArea());
				obj = new JSONObject(map);
				map2.put(i, obj);
				i++;
			}
			if(Area.equals(e.getExhibitionArea())) {
				map.put("No",e.getExhibitionNo());
				map.put("Date", e.getExhibitionDate());
			 	map.put("Topic",e.getExhibitionTopic());
				map.put("Content",e.getExhibitionContent());
				map.put("img",img+e.getExhibitionNo());
				obj = new JSONObject(map);
				map2.put(i, obj);
				i++;
			}
		}
		System.out.println(obj);
		System.out.println(map2);

		out.print(new JSONObject(map2));
	}
}
