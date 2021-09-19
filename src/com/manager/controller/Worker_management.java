package com.manager.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.manager.model.ManagerService;
import com.manager.model.ManagerVO;

import java.util.Base64;

/**
 * Servlet implementation class Worker_management
 */
@WebServlet("/Worker_management")
public class Worker_management extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");


		String imageChange = req.getParameter("image_change");
		
		HttpSession session = req.getSession();

		
		Integer manager_no = ((ManagerVO)session.getAttribute("manager")).getManager_no();
		
		
		
		if (imageChange.equals("change")) {
			
			//接受AJAX參數
			String nickname = req.getParameter("name");
			String phone = req.getParameter("phone");
			String email = req.getParameter("email");
			String image = req.getParameter("image");	
			System.out.println(image);
			// 切割ajax傳來之base64碼
			String myString = image.substring(image.lastIndexOf(',') + 1);
	
			// 將base64碼轉換為byte[]
			byte[] pic = Base64.getDecoder().decode(myString.getBytes());
			
			ManagerService managerSvc = new ManagerService();
			managerSvc.updateManagerIncludePic(pic, nickname, phone, email, manager_no);
			
	
			//將新增的資訊回填session
			ManagerVO managerVO = (ManagerVO)session.getAttribute("manager");
			managerVO.setNickname(nickname);
			managerVO.setPhone(phone);
			managerVO.setEmail(email);
			session.setAttribute("manager", managerVO);
			

		} else {
						
			String nickname = req.getParameter("name");
			String phone = req.getParameter("phone");
			String email = req.getParameter("email");
			
			ManagerService managerSvc = new ManagerService();
			managerSvc.updateManagerExcludePic(nickname, phone, email, manager_no);
			
			ManagerVO managerVO = (ManagerVO)session.getAttribute("manager");
			managerVO.setNickname(nickname);
			managerVO.setPhone(phone);
			managerVO.setEmail(email);
			session.setAttribute("manager", managerVO);
				
		}

	}

}
