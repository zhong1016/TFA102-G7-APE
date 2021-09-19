package com.exhibition.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.RequestWrapper;

import com.exhibition.model.ExhService;

@WebServlet("/ExhServletUpdate")
public class ExhServletUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=Big5");
		res.addHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = res.getWriter();
		String exhibitionNo = req.getParameter("No");
		String exhibitionDate = req.getParameter("Date");
		String exhibitionTopic = req.getParameter("Topic");
		String exhibitionContent = req.getParameter("Content");
		ExhService exhSvc = new ExhService();
		if (req.getParameter("img").indexOf("data:") != -1) {

			byte[] exhibitionPic = Base64.getDecoder()
					.decode(req.getParameter("img").substring(req.getParameter("img").lastIndexOf(',') + 1).getBytes());
			System.out.println(exhibitionNo + " " + exhibitionDate + " " + exhibitionTopic + " " + exhibitionContent
					+ " " + exhibitionPic);
			exhSvc.updateExh(exhibitionPic, java.sql.Date.valueOf(exhibitionDate), exhibitionTopic, exhibitionContent,
					Integer.parseInt(exhibitionNo));
		} else {
			exhSvc.updateExh2(java.sql.Date.valueOf(exhibitionDate), exhibitionTopic, exhibitionContent,
					Integer.parseInt(exhibitionNo));
		}
		
		out.print("{\"Amber\":true}");
	}
}
