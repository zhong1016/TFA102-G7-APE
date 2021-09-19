package com.manager.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.HashMap;

import javax.naming.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.json.JSONObject;

import com.manager.model.ManagerJDBCDAO;
import com.manager.model.ManagerVO;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

 
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	    
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			String verify = "denied";
			
		
			req.setCharacterEncoding("UTF-8");	
			
			
			String account = req.getParameter("account");
			String password = req.getParameter("password");
			
			ManagerJDBCDAO dao = new ManagerJDBCDAO();
			ManagerVO managerVO = dao.findByAccount(account);
			
			HttpSession session = req.getSession();
			
			if(managerVO != null) {
				if(managerVO.getPassword().equals(password)) {
					verify ="access";
					session.setAttribute("manager", managerVO);
				}				
			}

			HashMap map =new HashMap();
			map.put("verify", verify);			
			JSONObject obj = new JSONObject(map);
			PrintWriter out = res.getWriter();
			out.print(obj);
				
	}

}
