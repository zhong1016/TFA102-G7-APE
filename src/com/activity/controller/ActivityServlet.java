package com.activity.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.activity.model.ActivityService;
import com.activity.model.ActivityVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ActivityServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
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
		if ("getOne_For_Display".equals(action)) { // 來自vendorIndex.jsp的請求(轉往viewOnly)

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("activityNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入活動編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/activity/vendorIndex.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer activityNo = null;
				try {
					activityNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("活動編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/activity/vendorIndex.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/activity/vendorIndex.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("activityVO", activityVO); // 資料庫取出的empVO物件,存入req
				String url = "/activity/createViewonly.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/activity/vendorIndex.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Update".equals(action)) { // 來自vendorIndex.jsp的請求(轉往createActivity(update))

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("activityNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("錯誤的活動編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/activity/vendorIndex.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer activityNo = null;
				try {
					activityNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("活動編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/activity/vendorIndex.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/activity/vendorIndex.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("activityVO", activityVO);
				String url = "/activity/createActivity.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/activity/vendorIndex.jsp");
				failureView.forward(req, res);
			}
		}

		if ("submit".equals(action)) { // 來自createActivity.jsp的請求(有活動就走更新，無活動則新增，狀態"a0"送出審核，轉回vendorIndex)

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String activityNoStr = req.getParameter("activityNo");
				boolean isInsert = activityNoStr == null || "".equals(activityNoStr);
				Integer activityNo = isInsert ? 0 : new Integer(activityNoStr);
				String activityName = req.getParameter("activityName");
				String activityNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (activityName == null || activityName.trim().length() == 0) {
					errorMsgs.add("活動名稱: 請勿空白");
				} else if (!activityName.trim().matches(activityNameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("活動名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				java.sql.Date startDate = null;
				try {
					startDate = java.sql.Date.valueOf(req.getParameter("startDate").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入活動開始日期!");
				}

				java.sql.Date closeDate = null;
				try {
					closeDate = java.sql.Date.valueOf(req.getParameter("closeDate").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入活動結束日期!");
				}

				Integer dayCount = null;
				try {
					dayCount = new Integer(req.getParameter("dayCount").trim());
				} catch (NumberFormatException e) {
					dayCount = 0;
					errorMsgs.add("請輸入活動天數");
				}

				String activityHours = req.getParameter("activityHours").trim();
				if (activityHours == null || activityHours.trim().length() == 0) {
					errorMsgs.add("請填寫活動時間，範例:10am-09pm");
				}

				Integer rentCount = null;
				try {
					rentCount = new Integer(req.getParameter("rentCount").trim());
				} catch (NumberFormatException e) {
					rentCount = 0;
					errorMsgs.add("請填寫攤位數量(數字).");
				}

				String address = req.getParameter("address").trim();
				if (address == null || address.trim().length() == 0) {
					errorMsgs.add("請填寫活動地址");
				}

				Integer price = null;
				try {
					price = new Integer(req.getParameter("price").trim());
				} catch (NumberFormatException e) {
					price = 0;
					errorMsgs.add("請填寫攤位價格(數字).");
				}

				String introduction = req.getParameter("introduction").trim();
				if (introduction == null || introduction.trim().length() == 0) {
					errorMsgs.add("請填寫活動簡介");
				}

				Part part = req.getPart("activityPic");
				String rentString = req.getParameter("rentString");
				String rowNum = req.getParameter("rowNum");
				String columnsNum = req.getParameter("columnsNum");
				String available = req.getParameter("available");
				String reservationAll = req.getParameter("reservationAll");

				// CompanyVO companyVO = (CompanyVO)session.getAttribute("companyVO");
				// String companyNo = companyVO.getCompanyNo();

				Integer companyNo = new Integer(1);
				String status = "a0";
				Integer activityTypeNo = new Integer(req.getParameter("activityTypeNo"));

				Date date = new Date(System.currentTimeMillis());
				Timestamp createDate = new Timestamp(date.getTime());

				ActivityVO activityVO = new ActivityVO();
				byte[] activityPic = null;
				activityPic = activityVO.getBytesByPart(part);

				activityVO.setActivityName(activityName);
				activityVO.setStartDate(startDate);
				activityVO.setCloseDate(closeDate);
				activityVO.setDayCount(dayCount);
				activityVO.setActivityHours(activityHours);
				activityVO.setRentCount(rentCount);
				activityVO.setCreateDate(createDate);
				activityVO.setAddress(address);
				activityVO.setPrice(price);
				activityVO.setIntroduction(introduction);
				activityVO.setActivityPic(activityPic);
				activityVO.setStatus(status);
				activityVO.setRentString(rentString);
				activityVO.setRowNum(rowNum);
				activityVO.setColumnsNum(columnsNum);
				activityVO.setAvailable(available);
				activityVO.setReservationAll(reservationAll);
				activityVO.setCompanyNo(companyNo);
				activityVO.setActivityTypeNo(activityTypeNo);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("activityVO", activityVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/activity/createActivity.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				ActivityService activitySvc = new ActivityService();
				if (isInsert) {
					if (activityPic.length == 0) {
						activitySvc.addActivityNoPic(activityName, startDate, closeDate, dayCount, activityHours,
								rentCount, createDate, address, price, introduction, status, rentString, rowNum,
								columnsNum, available, reservationAll, companyNo, activityTypeNo);
					} else {
						activitySvc.addActivity(activityName, startDate, closeDate, dayCount, activityHours, rentCount,
								createDate, address, price, introduction, activityPic, status, rentString, rowNum,
								columnsNum, available, reservationAll, companyNo, activityTypeNo);
					}
					// todo 新增 狀態"a0"
				} else {

					if (activityPic.length == 0) {
						activitySvc.updateActivityNoPic(activityName, startDate, closeDate, dayCount, activityHours,
								rentCount, address, price, introduction, status, rentString, rowNum, columnsNum,
								available, reservationAll, companyNo, activityTypeNo, activityNo);
					} else {
						activitySvc.updateActivity(activityName, startDate, closeDate, dayCount, activityHours,
								rentCount, address, price, introduction, activityPic, status, rentString, rowNum,
								columnsNum, available, reservationAll, companyNo, activityTypeNo, activityNo);
						// todo 更新 狀態"a3"

					}
					// todo 更新 狀態"a0"
				}

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/activity/vendorIndex.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/activity/createActivity.jsp");
				failureView.forward(req, res);
				throw e;
			}
		}
		if ("save".equals(action)) { // 來自createActivity.jsp的請求(create點選"儲存"有活動則更新，無活動則新增，狀態"a3"，轉回vendorIndex)

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String activityNoStr = req.getParameter("activityNo");
				boolean isInsert = activityNoStr == null || "".equals(activityNoStr); // 判斷有沒有活動編號(已存在活動)
				Integer activityNo = isInsert ? 0 : new Integer(activityNoStr); // 是:0(給一個值，但不會用到activityNo為AI)，否:將activityN轉型Integer
				String activityName = req.getParameter("activityName");
				String activityNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (activityName == null || activityName.trim().length() == 0) {
					errorMsgs.add("活動名稱: 請勿空白");
				} else if (!activityName.trim().matches(activityNameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("活動名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				java.sql.Date startDate = null;
				try {
					startDate = java.sql.Date.valueOf(req.getParameter("startDate").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入活動開始日期!");
				}

				java.sql.Date closeDate = null;
				try {
					closeDate = java.sql.Date.valueOf(req.getParameter("closeDate").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入活動結束日期!");
				}

				Integer dayCount = null;
				try {
					dayCount = new Integer(req.getParameter("dayCount").trim());
				} catch (NumberFormatException e) {
					dayCount = 0;
					errorMsgs.add("請填寫活動天數.");
				}

				String activityHours = req.getParameter("activityHours").trim();
				if (activityHours == null || activityHours.trim().length() == 0) {
					errorMsgs.add("請填寫活動時間，範例:10am-09pm");
				}

				Integer rentCount = null;
				try {
					rentCount = new Integer(req.getParameter("rentCount").trim());
				} catch (NumberFormatException e) {
					rentCount = 0;
					errorMsgs.add("請填寫攤位數量(數字).");
				}

				String address = req.getParameter("address").trim();
				if (address == null || address.trim().length() == 0) {
					errorMsgs.add("請填寫活動地址");
				}

				Integer price = null;
				try {
					price = new Integer(req.getParameter("price").trim());
				} catch (NumberFormatException e) {
					price = 0;
					errorMsgs.add("請填寫攤位價格(數字).");
				}

				String introduction = req.getParameter("introduction").trim();
				if (introduction == null || introduction.trim().length() == 0) {
					errorMsgs.add("請填寫活動簡介");
				}

				Part part = req.getPart("activityPic");
				String rentString = req.getParameter("rentString");
				String rowNum = req.getParameter("rowNum");
				String columnsNum = req.getParameter("columnsNum");
				String available = req.getParameter("available");
				String reservationAll = req.getParameter("reservationAll");
//				Integer companyNo = new Integer(req.getParameter("companyNo"));
				Integer companyNo = new Integer(1);
				String status = "a3";
				Integer activityTypeNo = new Integer(req.getParameter("activityTypeNo"));

				Date date = new Date(System.currentTimeMillis());
				Timestamp createDate = new Timestamp(date.getTime());

				ActivityVO activityVO = new ActivityVO();
				byte[] activityPic = null;
				activityPic = activityVO.getBytesByPart(part);

				activityVO.setActivityName(activityName);
				activityVO.setStartDate(startDate);
				activityVO.setCloseDate(closeDate);
				activityVO.setDayCount(dayCount);
				activityVO.setActivityHours(activityHours);
				activityVO.setRentCount(rentCount);
				activityVO.setCreateDate(createDate);
				activityVO.setAddress(address);
				activityVO.setPrice(price);
				activityVO.setIntroduction(introduction);
				activityVO.setActivityPic(activityPic);
				activityVO.setStatus(status);
				activityVO.setRentString(rentString);
				activityVO.setRowNum(rowNum);
				activityVO.setColumnsNum(columnsNum);
				activityVO.setAvailable(available);
				activityVO.setReservationAll(reservationAll);
				activityVO.setCompanyNo(companyNo);
				activityVO.setActivityTypeNo(activityTypeNo);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("activityVO", activityVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/activity/createActivity.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/

				// 先判是是否有以存在的活動(更新或新增)，在判別是否有圖片。
				ActivityService activitySvc = new ActivityService();
				if (isInsert) {
					if (activityPic.length == 0) {
						activitySvc.addActivityNoPic(activityName, startDate, closeDate, dayCount, activityHours,
								rentCount, createDate, address, price, introduction, status, rentString, rowNum,
								columnsNum, available, reservationAll, companyNo, activityTypeNo);
					} else {
						activitySvc.addActivity(activityName, startDate, closeDate, dayCount, activityHours, rentCount,
								createDate, address, price, introduction, activityPic, status, rentString, rowNum,
								columnsNum, available, reservationAll, companyNo, activityTypeNo);
					}
					// todo 新增 狀態"a3"
				} else {
					if (activityPic.length == 0) {
						activitySvc.updateActivityNoPic(activityName, startDate, closeDate, dayCount, activityHours,
								rentCount, address, price, introduction, status, rentString, rowNum, columnsNum,
								available, reservationAll, companyNo, activityTypeNo, activityNo);
					} else {
						activitySvc.updateActivity(activityName, startDate, closeDate, dayCount, activityHours,
								rentCount, address, price, introduction, activityPic, status, rentString, rowNum,
								columnsNum, available, reservationAll, companyNo, activityTypeNo, activityNo);
						// todo 更新 狀態"a3"

					}
				}

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/activity/vendorIndex.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {

				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/activity/createActivity.jsp");
				failureView.forward(req, res);
			}

		}

	}

}
