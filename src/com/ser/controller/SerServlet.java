package com.ser.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.json.JSONObject;

import com.activity.model.ActivityVO;
import com.activitytype.model.ActivitytypeVO;
import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.ser.model.SerService;
import com.ser.model.SerVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class SerServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		HttpSession session = req.getSession();
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);
		String serSearch = req.getParameter("serSearch");

		System.out.println("######  into SerServlet  ######. serSearch is " + serSearch);

		if ("searchAcitivityType".equals(serSearch)) {
			HashMap<Integer, String> userInfoMap = new HashMap<>();
			try {
				SerService serService = new SerService();
				List<ActivitytypeVO> activitytypeVO = new ArrayList<>();

				activitytypeVO = serService.getAll();
				for (ActivitytypeVO str : activitytypeVO) {
					userInfoMap.put(str.getActivityTypeNo(), str.getActivityTypeName());
				}
				JSONObject obj = new JSONObject(userInfoMap);
				PrintWriter out = res.getWriter();
				out.println(obj);
				System.out.println(obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if ("searchAcitivity".equals(serSearch)) {
			System.out.println("###" + serSearch);

			HashMap<String, Object> userInfoMap = new LinkedHashMap<>();
			String typeNo = req.getParameter("type");
			System.out.println(typeNo);

			Integer activityTypeNo = null;
			try {
				activityTypeNo = new Integer(typeNo);
			} catch (Exception e) {
				errorMsgs.add("客服系統正在維修中 : 活動編號");
			}
			try {

				SerService serService = new SerService();
				List<ActivityVO> activityVO = new ArrayList<>();
				activityVO = serService.getActivity(activityTypeNo);
				
				if(activityVO == null) {
					errorMsgs.add("客服系統正在維修中 : 資料庫");
					return;
				}
				
				if (activityVO.size() == 0) {
					userInfoMap.put("活動查詢", "目前沒有活動"+"</br><p class='"+"serleaf'></p>");
				} else {
					for (ActivityVO str : activityVO) {
						userInfoMap.put(str.getActivityNo()+"1", "活動編號：　"+(Integer) str.getActivityNo());
						userInfoMap.put(str.getActivityNo()+"2", "活動名稱：　"+(String) str.getActivityName());
						userInfoMap.put(str.getActivityNo()+"3", "開始日期：　"+(java.sql.Date) str.getStartDate());
						userInfoMap.put(str.getActivityNo()+"4", "結束日期：　"+(java.sql.Date) str.getCloseDate());
						userInfoMap.put(str.getActivityNo()+"5", "活動天數：　"+(Integer) str.getDayCount());
						userInfoMap.put(str.getActivityNo()+"6", "活動時間：　"+(String) str.getActivityHours());
						userInfoMap.put(str.getActivityNo()+"7", "活動地址：　"+(String) str.getAddress());
						userInfoMap.put(str.getActivityNo()+"8", "活動價格：　"+(Integer) str.getPrice());
						userInfoMap.put(str.getActivityNo()+"9", "活動內容：　"+(String) str.getIntroduction()+"</br><p class='"+"serleaf'></p>");
					}
				}
				
				session.setAttribute("activityTypeNo", activityVO);
				JSONObject obj = new JSONObject(userInfoMap);
				PrintWriter out = res.getWriter();
				out.println(obj);
				System.out.println(obj);
			} catch (Exception e) {
				e.printStackTrace();
//			}
			}

		}
		if ("insertSer".equals(serSearch)) {
			System.out.println("### into collection SingupMem ###");
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				SerService serService = new SerService();

				String servName = req.getParameter("servName").trim();
				if (servName == null || servName.trim().length() == 0) {
					errorMsgs.add("Please confirm your UserId!");

				String servMain = req.getParameter("servMain").trim();
				if (servMain == null || servMain.trim().length() == 0) {
					errorMsgs.add("Please confirm your password!");
				}
				
				String servMsg = req.getParameter("servMsg").trim();
				String memEmailReg = "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$";
				if (servMsg == null || servMsg.trim().length() == 0) {
					System.out.println("try2 userEmail == null");
					errorMsgs.add("Please confirm your userEmail!");
				} else if (!servMsg.trim().matches(memEmailReg)) {
					System.out.println("try2 memEmailReg == memEmailReg");
					errorMsgs.add("Please confirm your memEmailReg !");
				}
				
				SerVO serVO = new SerVO();
				Part part = req.getPart("servPic");
				byte[] servPic = null;
				servPic = serVO.getBytesByPart(part);
				int userPiclength = servPic.length;
				if (userPiclength != 0) {
					System.out.println("PIC");
					serVO.setServPic(servPic);
				}

				serVO.setServName(servName);
				serVO.setServMain(servMain);
				serVO.setServMsg(servMsg);
				if (!errorMsgs.isEmpty()) {
					System.out.println("!errorMsgs.isEmpty()");
					session.setAttribute("serVO", serVO);
					RequestDispatcher successView = req.getRequestDispatcher("/service/service.jsp");
					successView.forward(req, res);
					return;
				}

				serVO = serService.addSer(serVO);
				RequestDispatcher successView = req.getRequestDispatcher("/service/service.jsp");
				successView.forward(req, res);
				}
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("test!!!!:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/service/service.jsp");
				failureView.forward(req, res);
			}
		}
	}
	
}

