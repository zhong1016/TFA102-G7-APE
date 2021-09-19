package com.activity.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import com.activity.model.ActivityJDBCDAO;
import com.activity.model.ActivityService;
import com.activity.model.ActivityVO;

/**
 * Servlet implementation class EventManagement
 */
@WebServlet("/EventManagement")
public class EventManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");	
		
		res.setHeader("Cache-Control", "no-store");
		res.setHeader("Pragma", "no-cache");
		res.setHeader("Expires", "0");
		
		
		//接收待審核活動編號
		Integer activityNo = Integer.parseInt(req.getParameter("activityNo"));
			
		ActivityService activitySvc = new ActivityService();
		ActivityVO activityVO =  activitySvc.getOneActivity(activityNo);
		
		HttpSession session = req.getSession();
		
		
		session.setAttribute("activityVO", activityVO);
			
		RequestDispatcher dispatcher  = req
				.getRequestDispatcher("/backstage/event_management1.jsp");
		dispatcher.forward(req, res);
		
			
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doGet(req, res);
	}

}
