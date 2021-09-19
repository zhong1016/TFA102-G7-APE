package com.ticket.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ticket.model.*;

@WebServlet("/ApeServletUpdate")
public class TicServletUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=Big5");
		// �P�N�Ҧ�����i�J
		res.addHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = res.getWriter();

		String ticketName = req.getParameter("ticketName");
		String ticketIdCard = req.getParameter("ticketIdCard");
		String ticketEmail = req.getParameter("ticketEmail");
		String ticketNo = req.getParameter("ticketNo");
		System.out.println(ticketName + " " + ticketIdCard + " " + ticketEmail + " " + ticketNo);

		TicService apeSvc = new TicService();
		apeSvc.updateApe(ticketName, Integer.parseInt(ticketIdCard), ticketEmail, Integer.parseInt(ticketNo));
		out.print("{\"Amber\":true}");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
