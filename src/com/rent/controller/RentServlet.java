package com.rent.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.activity.model.ActivityService;
import com.activity.model.ActivityVO;
import com.mem.model.MemVO;
import com.rent.model.RentService;
import com.rent.model.RentVO;
import com.utils.mail.MailService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class RentServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession(false);

		Map<String, String[]> params = req.getParameterMap();
		Set<String> keys = params.keySet();
		System.out.println("=================進入ActivityServlet=================");
		for (String key : keys) {
			String[] values = params.get(key);
			String s = Arrays.toString(values);
			// 因為 Arrays.toString()方法會回傳包含[]的一個列表，所以必須使用 substring 去頭去尾。
			s = s.substring(1, s.length() - 1);
			System.out.println(key + " : " + s);
		}

		System.out.println("=================結束ActivityServlet==取得參數============");
		if ("getOne_For_Display".equals(action)) { // 來自rentIndex.jsp的請求(轉往rentMarket)

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				if (session.getAttribute("memVO") == null) {
					String location = req.getParameter("location");
					session.setAttribute("location", location);
					RequestDispatcher dispatcher = req.getRequestDispatcher("/members/Member.jsp");
					dispatcher.forward(req, res);
					return;// 程式中斷
				}
				MemVO memVO = (MemVO) session.getAttribute("memVO");
				Integer userNo = memVO.getUserNo();

				String str = req.getParameter("activityNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("錯誤的活動編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/activity/rentIndex.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer activityNo = new Integer(str);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/activity/rentIndex.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				ActivityService activitySvc = new ActivityService();
				ActivityVO activityVO = activitySvc.getOneActivity(activityNo);
				if (activityVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/activity/rentIndex.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("activityVO", activityVO); // 資料庫取出的activityVO物件,存入req
				String url = "/activity/rentMarket.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 rentMarket.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/activity/rentIndex.jsp");
				failureView.forward(req, res);
			}
		}
		if ("insert".equals(action)) { // 來自rentMarket.jsp的請求(狀態"r0"送出審核，轉往menberRent.jsp)

			if (session.getAttribute("memVO") == null) {
				RequestDispatcher dispatcher = req.getRequestDispatcher("/members/Member.jsp");
				dispatcher.forward(req, res);
				return;// 程式中斷
			}

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				Integer activityNo = new Integer(req.getParameter("activityNo"));
				String rentName = req.getParameter("rentName");
				String rentNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (rentName == null || rentName.trim().length() == 0) {
					errorMsgs.add("活動名稱: 請勿空白");
				} else if (!rentName.trim().matches(rentNameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("活動名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				String rentIntroduction = req.getParameter("rentIntroduction").trim();
				if (rentIntroduction == null || rentIntroduction.trim().length() == 0) {
					errorMsgs.add("請填寫活動簡介");
				}
				Part part = req.getPart("rentPic");
				String status = "r0";
				Integer rentPrice = new Integer(req.getParameter("rentPrice"));
				String reservation = req.getParameter("booking");

				MemVO memVO = (MemVO) session.getAttribute("memVO");
				Integer userNo = memVO.getUserNo();

//				Integer userNo = new Integer(req.getParameter("userNo"));
//				Integer userNo = new Integer(1); // 目前先寫死

				RentVO rentVO = new RentVO();
				byte[] rentPic = null;
				rentPic = rentVO.getBytesByPart(part);

				rentVO.setRentName(rentName);
				rentVO.setRentIntroduction(rentIntroduction);
				rentVO.setRentPic(rentPic);
				rentVO.setStatus(status);
				rentVO.setRentPrice(rentPrice);
				rentVO.setReservation(reservation);
				rentVO.setActivityNo(activityNo);
				rentVO.setUserNo(userNo);

				ActivityService activitySvc = new ActivityService();
				ActivityVO activityVO = activitySvc.getOneActivity(activityNo);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("activityVO", activityVO); // 含有輸入格式錯誤的rentVO物件,也存入req
					req.setAttribute("rentVO", rentVO); // 含有輸入格式錯誤的rentVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/activity/rentMarket.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				RentService rentSvc = new RentService();
				rentSvc.addRent(rentName, rentIntroduction, rentPic, status, rentPrice, reservation, activityNo,
						userNo);
				// todo 新增 狀態"r0"

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/activity/menberRent.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/activity/rentMarket.jsp");
				failureView.forward(req, res);
				throw e;
			}
		}
		if ("getOne_For_Update".equals(action)) { // 來自vendorcheackpageall.jsp的請求(更改狀態轉往menberRent.jsp)
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer activityNo = new Integer(req.getParameter("activityNo"));

				/*************************** 2.開始查詢資料 ****************************************/

				ActivityService activitySvc = new ActivityService();
				ActivityVO activityVO = activitySvc.getOneActivity(activityNo);
				RentService rentSvc = new RentService();
				List<RentVO> rentVOList = rentSvc.getRentByActivity(activityNo);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("activityVO", activityVO); // 資料庫取出的empVO物件,存入req
				req.setAttribute("rentVOList", rentVOList); // 資料庫取出的empVO物件,存入req
				String url = "/activity/vendorcheackpage1.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/activity/vendorcheackpageall.jsp");
				failureView.forward(req, res);
			}
		}
		if ("get_rent_deteail".equals(action)) { // 來自vendorcheackpageall.jsp的請求(更改狀態轉往menberRent.jsp)
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer rentNo = new Integer(req.getParameter("rentNo"));
				Integer activityNo = new Integer(req.getParameter("activityNo"));

				/*************************** 2.開始查詢資料 ****************************************/

				RentService rentSvc = new RentService();
				RentVO rentVO = rentSvc.getOneRent(rentNo);
				ActivityService activitySvc = new ActivityService();
				ActivityVO activityVO = activitySvc.getOneActivity(activityNo);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("rentVO", rentVO);
				req.setAttribute("activityVO", activityVO);
				String url = "/activity/vendorcheackpage2.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/activity/vendorcheackpageall.jsp");
				failureView.forward(req, res);
			}
		}
		if ("pass".equals(action)) { // 來自vendorcheackpage1.jsp的請求，更新狀態(通過)

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				Integer rentNo = new Integer(req.getParameter("rentNo"));
				String rentName = req.getParameter("rentName");
				String activityName = req.getParameter("activityName");

				String status = "r1"; // 通過

//				Integer userNo = new Integer(req.getParameter("userNo"));
//				Integer userNo = new Integer(1); // 目前先寫死

				RentVO rentVO = new RentVO();
				rentVO.setStatus(status);
				rentVO.setUserNo(rentNo);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("rentVO", rentVO); // 含有輸入格式錯誤的rentVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/activity/vendorcheackpage1.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				RentService rentSvc = new RentService();
				rentSvc.updateStatus(status, rentNo);
				// todo 新增 狀態"r1"

				String to = "apetfa102team07@gmail.com";
				String subject = "[艾普藝森審核結果通知]";
				String rent_Name = rentName;
				String activity_Name = activityName;
				String status_pass = "通過";
				String messageText = "親愛的會員您好 : " + "\n" + "您所提交的'" + activity_Name + "'已完成審核，結果如下:" + "\n" + rent_Name
						+ " : " + status_pass + "，歡迎持續關注本平台，謝謝。" + "\n" + "\n" + "\n" + "艾普藝森";

				ExecutorService ex = Executors.newSingleThreadExecutor();

				ex.execute(new Runnable() {
					@Override
					public void run() {
						MailService mailService = new MailService();
						mailService.sendMail(to, subject, messageText);

					}
				});

				ex.shutdown();

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/activity/vendorcheackpageall.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/activity/vendorcheackpage1.jsp");
				failureView.forward(req, res);
				throw e;
			}
		}
		if ("not_pass".equals(action)) { // 來自vendorcheackpage1.jsp的請求，更新狀態(通過)

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				Integer rentNo = new Integer(req.getParameter("rentNo"));
				String rentName = req.getParameter("rentName");
				String activityName = req.getParameter("activityName");
				String reservation = req.getParameter("reservation");
				Integer activityNo = new Integer(req.getParameter("activityNo"));

				String status = "r2"; // 未通過

//				Integer userNo = new Integer(req.getParameter("userNo"));
//				Integer userNo = new Integer(1); // 目前先寫死

				RentVO rentVO = new RentVO();
				rentVO.setStatus(status);
				rentVO.setUserNo(rentNo);
				rentVO.setActivityNo(activityNo);
				rentVO.setReservation(reservation);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("rentVO", rentVO); // 含有輸入格式錯誤的rentVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/activity/vendorcheackpage1.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				RentService rentSvc = new RentService();
				rentSvc.updateStatusRemove(status, rentNo, activityNo, reservation);
				// todo 新增 狀態"r1"

				String to = "apetfa102team07@gmail.com";
				String subject = "[艾普藝森審核結果通知]";
				String rent_Name = rentName;
				String activity_Name = activityName;
				String status_pass = "未通過";
				String messageText = "親愛的會員您好 : " + "\n" + "您所提交的'" + activity_Name + "'已完成審核，結果如下:" + "\n" + rent_Name
						+ " : " + status_pass + "\n" + "請您參考其他活動，謝謝。" + "\n" + "\n" + "\n" + "艾普藝森";

				ExecutorService ex = Executors.newSingleThreadExecutor();

				ex.execute(new Runnable() {
					@Override
					public void run() {
						MailService mailService = new MailService();
						mailService.sendMail(to, subject, messageText);

					}
				});

				ex.shutdown();

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/activity/vendorcheackpageall.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/activity/vendorcheackpage1.jsp");
				failureView.forward(req, res);
				throw e;
			}
		}
	}

}
