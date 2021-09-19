package com.manager.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.manager.model.ManagerService;
import com.manager.model.ManagerVO;

@WebServlet("/ManagerUpdate")
public class ManagerUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

		protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			req.setCharacterEncoding("UTF-8");
			String action = req.getParameter("action");
			
			
			
			if("getOne_For_Update".equals(action)) {   //來自worker_management1.jsp 的請求
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
				
				try {
					/***************************1.接收請求參數****************************************/
					Integer manager_no = new Integer(req.getParameter("manager_no").trim());
					
					/***************************2.開始查詢資料****************************************/
					ManagerService managerSvc = new ManagerService();
					ManagerVO managerVO = managerSvc.getOneManager(manager_no);
							
					/***************************3.查詢完成,準備轉交(Send the Success view)************/
					req.setAttribute("ManagerVO", managerVO);         // 資料庫取出的ManagerVO物件,存入req
					String url = "/backstage/update_manager.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
					successView.forward(req, res);
					
	
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
//					errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/listAllEmp.jsp");
//					failureView.forward(req, res);
				}
			}
			
						
			if("update".equals(action)) {   //來自update_manager.jsp 修改的請求
			
				List<String> errorMsgs = new LinkedList<String>();		
				req.setAttribute("errorMsgs", errorMsgs);
				
				
				try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				//驗證帳號
				String account = req.getParameter("account");
				String accountReg  = "^[(a-zA-Z0-9_)]{6,10}$";
				
				if(account.trim().length() == 0) {
					errorMsgs.add("帳號:請勿空白");		
				}else if(!account.trim().matches(accountReg)){					
					errorMsgs.add("帳號:只能是英文字母、數字和_ , 且長度必需在2到10之間");						
				}
				
		
			
				//密碼驗證
				String password = req.getParameter("password").trim();
				String passwordReg  = "^[(a-zA-Z0-9_)]{6,10}$";
				
				if(password.trim().length() == 0) {
					errorMsgs.add("密碼:請勿空白");		
				}else if(!password.trim().matches(passwordReg)){					
					errorMsgs.add("密碼:只能是英文字母、數字和_ , 且長度必需在6到10之間");						
				}
												
				//暱稱驗證
				
				String nickname = req.getParameter("nickname");
				String nicknameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				
				if(nickname.trim().length() == 0) {
					errorMsgs.add("暱稱:請勿空白");		
				}else if(!nickname.trim().matches(nicknameReg)){					
					errorMsgs.add("暱稱:只能是英文字母、數字和_ , 且長度必需在2到10之間");						
				}
				
				
				//電話驗證
				
				String phone = req.getParameter("phone");
				String phoneReg = "^[(0-9)]{10}$";
				
				if(phone.trim().length() == 0) {
					errorMsgs.add("電話:請勿空白");		
				}else if(!phone.trim().matches(nicknameReg)){					
					errorMsgs.add("電話:電話長度為10");						
				}
				
				//EMAIL驗證
				
				String email = req.getParameter("email");
//				String emailReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				
				if(email.trim().length() == 0) {
					errorMsgs.add("信箱:請勿空白");		
				}
//				else if(!email.trim().matches(emailReg)){					
//					errorMsgs.add("信箱:只能是英文字母、數字和_ , 且長度必需在2到10之間");						
//				}
				
				Integer manager_no = new Integer(req.getParameter("managerNo").trim());	
				
					
				ManagerVO managerVO = new ManagerVO();
				managerVO.setManager_no(manager_no);
				managerVO.setAccount(account);
				managerVO.setPassword(password);
				managerVO.setNickname(nickname);
				managerVO.setPhone(phone);
				managerVO.setEmail(email);
				
				//目前使用者
				System.out.println(manager_no);
			
				HttpSession session = req.getSession();
				ManagerVO managerVO1 = (ManagerVO)session.getAttribute("manager");
				System.out.println(managerVO1.getManager_no());
				
				
				
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("ManagerVO", managerVO ); // 含有輸入格式錯誤的managerVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backstage/update_manager.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
					
				
				/***************************2.開始修改資料*****************************************/
				
				ManagerService managerSvc = new ManagerService();
				
				managerVO = managerSvc.update(manager_no, account, password, nickname, phone, email);
				
				
				//自己修改自己時  更新session使用者資訊
				if(manager_no.equals(managerVO1.getManager_no())) {
					session.setAttribute("manager", managerVO);
				}
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				req.setAttribute("ManagerVO", managerVO ); // 資料料庫UPDATE正確的ManagerVO 物件  並存進REQUSEST
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backstage/worker_management1.jsp");
				failureView.forward(req, res);	
		
				}catch (Exception e) {
//					errorMsgs.add("修改資料失敗:"+e.getMessage());
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/update_emp_input.jsp");
//					failureView.forward(req, res);

				}
			
									
			}			
						
			if("delete".equals(action)) {   //來自update_manager.jsp 刪除的請求
				
				
				try {
					/***************************1.接收請求參數****************************************/
					Integer manager_no = new Integer(req.getParameter("manager_no").trim());
					
					/***************************2.開始刪除資料****************************************/
					ManagerService managerSvc = new ManagerService();
					managerSvc.delete(manager_no);
							
					/***************************3.刪除完成,導回管理員列表頁面************/
					String url = "/backstage/worker_management1.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
					successView.forward(req, res);
					
	
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
			
			
			
			
			
			
			if("addManager".equals(action)) {   //來自worker_management2.jsp 新增的請求
			
				List<String> errorMsgs = new LinkedList<String>();		
				req.setAttribute("errorMsgs", errorMsgs);
				
				try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				//驗證帳號
				String account = req.getParameter("account");
				String accountReg  = "^[(a-zA-Z0-9_)]{6,10}$";
				
				if(account.trim().length() == 0) {
					errorMsgs.add("帳號:請勿空白");		
				}else if(!account.trim().matches(accountReg)){					
					errorMsgs.add("帳號:只能是英文字母、數字和_ , 且長度必需在6到10之間");						
				}
				
				ManagerService managerSvc1 = new ManagerService();
				ManagerVO managerVO1 = managerSvc1.findManagerByAccount(account);
				System.out.println(managerVO1);
				
				if(managerVO1 != null) {
					errorMsgs.add("帳號:此帳號已重複");
				}
				
				
				//密碼驗證
				String password = req.getParameter("password").trim();				
				String passwordConfirm = req.getParameter("passwordConfirm").trim();
			
				String passwordReg  = "^[(a-zA-Z0-9_)]{6,10}$";
				
				if(password.trim().length() == 0) {
					errorMsgs.add("密碼:請勿空白");		
				}else if(!password.trim().matches(passwordReg)){					
					errorMsgs.add("密碼:只能是英文字母、數字和_ , 且長度必需在6到10之間");						
				}else if(!password.equals(passwordConfirm)) {
					errorMsgs.add("密碼:請確認密碼");
				}
												
				//暱稱驗證
				
				String nickname = req.getParameter("nickname");
				String nicknameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				
				if(nickname.trim().length() == 0) {
					errorMsgs.add("暱稱:請勿空白");		
				}else if(!nickname.trim().matches(nicknameReg)){					
					errorMsgs.add("暱稱:只能是英文字母、數字和_ , 且長度必需在2到10之間");						
				}
				
				
				//電話驗證
				
				String phone = req.getParameter("phone");
				String phoneReg = "^[(0-9)]{10}$";
				
				if(phone.trim().length() == 0) {
					errorMsgs.add("電話:請勿空白");		
				}else if(!phone.trim().matches(nicknameReg)){					
					errorMsgs.add("電話:電話長度為10");						
				}
				
				//EMAIL驗證
				
				String email = req.getParameter("email");
				String emailReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				
				if(nickname.trim().length() == 0) {
					errorMsgs.add("信箱:請勿空白");		
				}else if(!nickname.trim().matches(nicknameReg)){					
					errorMsgs.add("信箱:只能是英文字母、數字和_ , 且長度必需在2到10之間");						
				}
									
				ManagerVO managerVO = new ManagerVO();
				managerVO.setAccount(account);
				managerVO.setPassword(password);
				managerVO.setNickname(nickname);
				managerVO.setPhone(phone);
				managerVO.setEmail(email);
				
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("ManagerVO", managerVO); // 含有輸入格式錯誤的managerVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backstage/worker_management2.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
					
				
				/***************************2.開始新增資料*****************************************/
				
				ManagerService managerSvc = new ManagerService();
				
				managerVO = managerSvc.addManager(account, password, nickname, phone, email);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				req.setAttribute("ManagerVO", managerVO); // 資料料庫UPDATE正確的ManagerVO 物件  並存進REQUSEST
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backstage/worker_management1.jsp");
				failureView.forward(req, res);	
		
				}catch (Exception e) {
//					errorMsgs.add("修改資料失敗:"+e.getMessage());
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/update_emp_input.jsp");
//					failureView.forward(req, res);
//					System.out.println("錯誤");
				}
			
									
			}
			
			
			
		
			
	}
		
}		

		
