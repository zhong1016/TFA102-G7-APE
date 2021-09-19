package com.ticket.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.mem.model.MemVO;
import com.ticket.model.*;


@WebServlet("/ApeServletRead")
public class TicServletRead extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=Big5");

		res.addHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = res.getWriter();
		TicService apeSvc = new TicService();
		List<TicVO> apes = apeSvc.getAll();
		HttpSession session = req.getSession();
		MemVO memVO = (MemVO) session.getAttribute("memVO");
		System.out.println("會員編號="+memVO.getUserNo());
		
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> map2 = new HashMap<>();
		JSONObject obj = null;
		int i = 1;
		for (TicVO ape : apes) {
//TICKET_NO, TICKET_NAME, TICKET_ID_CARD, TICKET_EMAIL, TICKET_QRCODE, EXHIBITION_NO
			if (ape.getUserId() == memVO.getUserNo()) {
				map.put("Name", ape.getTicketName());
				map.put("idCard", ape.getTicketIdCard());
				map.put("Email", ape.getTicketEmail());
				map.put("exhibitionNo", ape.getExhibitionNo());
				map.put("ticketNo",ape.getTicketNo());
				obj = new JSONObject(map);
				map2.put("data" + i	, obj);
			}
			i++;
		}
		System.out.println(obj);
		System.out.println(map2);

		JSONObject obj2 = new JSONObject(map2);
		out.print(obj2);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
