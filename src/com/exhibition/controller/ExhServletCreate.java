package com.exhibition.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exhibition.model.ExhService;

@WebServlet("/ExhServletCreate")
public class ExhServletCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=Big5");
        res.addHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out = res.getWriter();
		String exhibitionTopic = req.getParameter("Topic");
		String exhibitionContent = req.getParameter("Content");
		String exhibitionArea = req.getParameter("Area");
		byte[] exhibitionPic = Base64.getDecoder()
				.decode(req.getParameter("img").substring(req.getParameter("img").lastIndexOf(',') + 1).getBytes());
		ExhService exhSvc = new ExhService();
		
		exhSvc.addexh(exhibitionPic, exhibitionTopic, exhibitionContent, exhibitionArea);
		out.print("{\"Amber\":true}");
	}
}
