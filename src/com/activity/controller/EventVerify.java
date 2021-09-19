package com.activity.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.activity.model.ActivityJDBCDAO;
import com.activity.model.ActivityService;
import com.activity.model.ActivityVO;

/**
 * Servlet implementation class EventVerify
 */
@WebServlet("/EventVerify")
public class EventVerify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doPost(req, res);
			
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		req.setCharacterEncoding("UTF-8");
		res.setHeader("Cache-Control", "no-store");
		res.setHeader("Pragma", "no-cache");
		res.setHeader("Expires", "0");
		
		//取得是通過還未通過的參數
		String verify = req.getParameter("verify");
		
		
		
		HttpSession session = req.getSession();
		ActivityService activitySvc = new ActivityService();
		ActivityVO activityVO = (ActivityVO)session.getAttribute("activityVO");
		
		
		if("pass".equals(verify)) {
			activityVO.setStatus("a1");
			activitySvc.updateStatus(activityVO);
			
		}else {
			activityVO.setStatus("a2");
			activitySvc.updateStatus(activityVO);
		}
		
		
		RequestDispatcher failureView = req
				.getRequestDispatcher("/backstage/event_management.jsp");
		failureView.forward(req, res);
	

	}

}
