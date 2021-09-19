package com.manager_msg.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.*;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.manager.model.ManagerVO;
import com.manager_msg.model.Manager_msgJDBCDAO;
import com.manager_msg.model.Manager_msgVO;
/**
 * Servlet implementation class Homepage
 */
@WebServlet("/Homepage")
public class Homepage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=utf-8");
		//拿出輸入內容
		String content = req.getParameter("content");
		
		//資料庫時間拿出後做格式化
		Long time = Long.parseLong(req.getParameter("time"));
		Timestamp time1 = new Timestamp(time);
		DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String realtime = sdf.format(time1);
		
		HttpSession session = req.getSession();
		ManagerVO managerVO = (ManagerVO)session.getAttribute("manager");	
		String nickname = managerVO.getNickname();
		System.out.println("AJAX成功");
		HashMap map =new HashMap();
		map.put("content", content);
		map.put("time", realtime);
		map.put("nickname",nickname);
			
		//
		
		
		Manager_msgVO msgVO = new Manager_msgVO();
		Manager_msgJDBCDAO msgDAO = new Manager_msgJDBCDAO();
		msgVO.setEstablished_time(time1);
		msgVO.setWork_record(content);
		msgVO.setManagerVO(managerVO);
		msgDAO.insert(msgVO);

		//將msgVO包裝成JSON回傳
		
		
		JSONObject obj = new JSONObject(map);
		PrintWriter out = res.getWriter();
		out.print(obj);
		
	}

}
