package com.ticket.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.mem.model.MemVO;
import com.ticket.model.*;


@WebServlet("/ApeServletCreate")
public class TicServletCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 建立Qrcode物件
		TicJavaQrcode apeJQ = new TicJavaQrcode();
		String Qrcode = apeJQ.returnAuthCode();
		Integer condition = 1;
		// 選擇對應的編碼
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=Big5");
		// 同意所有網域進入
		res.addHeader("Access-Control-Allow-Origin", "*");
		// 回應伺服器
		PrintWriter out = res.getWriter();
		HttpSession session = req.getSession();
		MemVO memVO = (MemVO) session.getAttribute("memVO");
		System.out.println("會員編號="+memVO.getUserNo());
		// 取得前端Ajax的值
		String Name = req.getParameter("Name");
		String Email = req.getParameter("Email");
		String idCard = req.getParameter("idCard");
		String exhibitionNo = req.getParameter("exhibitionNo");
	
		System.out.println(Name + " " + Email + " " + idCard + " " + Qrcode + " " + exhibitionNo);
	
		TicService apeSvc = new TicService();
		apeSvc.addApe(Name, Integer.parseInt(idCard), Email, Qrcode, Integer.parseInt(exhibitionNo),memVO.getUserNo());

		TicJavaMail apeMail = new TicJavaMail();
		String subject = "艾普藝森國際展覽票券通知";
		String messageText = "您好!" + Name + " (票券已領取成功) " + "\n" + " 若非您本人操作，請馬上聯繫艾普藝森國際展覽票券通知"+"\n"+"TEL:02-22667070";
		apeMail.sendMail(Email, subject, messageText);
		
		HashMap<String,Object> map = new HashMap<>();
		map.put("qrcode", Qrcode);
		JSONObject obj = new JSONObject(map);
		out.println(obj);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
