package com.restaurant.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.location.model.LocationService;
import com.restaurant.model.*;

public class RestaurantServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String str = req.getParameter("restaurantno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入餐廳編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/map/listOneRestaurant.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				Integer restaurantno = null;
				try {
					restaurantno = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("餐廳編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/map/Map.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				RestaurantService restaurantSvc = new RestaurantService();
				RestaurantVO restaurantVO = restaurantSvc.getOneRestaurant(restaurantno);
				if (restaurantVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/map/Map.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("restaurantVO", restaurantVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/map/restaurant/listOneRestaurant.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/map/Map.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
			
			try {
				Integer restaurantno = new Integer(req.getParameter("restaurantno"));
				
				RestaurantService restaurantSvc = new RestaurantService();
				RestaurantVO restaurantVO = restaurantSvc.getOneRestaurant(restaurantno);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("restaurantVO", restaurantVO);        
				String url = "/map/restaurant/update_restaurant_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			String requestURL = req.getParameter("requestURL");
					
			try {
				Integer restaurantno = new Integer(req.getParameter("restaurantno").trim());
				
				String restaurantname = req.getParameter("restaurantname");
				String restaurantnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,50}$";
				if (restaurantname == null || restaurantname.trim().length() == 0) {
					errorMsgs.add("餐廳名稱: 請勿空白");
				} else if(!restaurantname.trim().matches(restaurantnameReg)) {
					errorMsgs.add("餐廳名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到50之間");
	            }
				
				String restaurantlevel = req.getParameter("restaurantlevel").trim();
				if (restaurantlevel == null || restaurantlevel.trim().length() == 0) {
					errorMsgs.add("評價請勿空白");
				}	
				
				String restaurantadd = req.getParameter("restaurantadd");
				String restaurantaddReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,50}$";
				if (restaurantadd == null || restaurantadd.trim().length() == 0) {
					errorMsgs.add("餐廳地址: 請勿空白");
				} else if(!restaurantadd.trim().matches(restaurantaddReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("餐廳地址: 只能是中、英文字母、數字和_ , 且長度必需在2到50之間");
	            }

				String restaurantnum = req.getParameter("restaurantnum");
				String restaurantnumReg = "^[(0-9)]{9,12}$";
				if (restaurantnum == null || restaurantnum.trim().length() == 0) {
					errorMsgs.add("餐廳電話: 請勿空白");
				} else if(!restaurantnum.trim().matches(restaurantnumReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("餐廳電話: 只能是數字, 且長度最多是12");
	            }

				String restauranttype = req.getParameter("restauranttype");
				String restauranttypeReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,50}$";
				if (restauranttype == null || restauranttype.trim().length() == 0) {
					errorMsgs.add("餐廳種類: 請勿空白");
				} else if(!restauranttype.trim().matches(restauranttypeReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("餐廳種類: 只能是中、英文字母、數字和_ , 且長度必需在2到50之間");
	            }

				Integer locationno = new Integer(req.getParameter("locationno").trim());
				
				RestaurantVO restaurantVO = new RestaurantVO();
				restaurantVO.setRestaurantno(restaurantno);
				restaurantVO.setRestaurantname(restaurantname);
				restaurantVO.setRestaurantlevel(restaurantlevel);
				restaurantVO.setRestaurantadd(restaurantadd);
				restaurantVO.setRestaurantnum(restaurantnum);
				restaurantVO.setRestauranttype(restauranttype);
				restaurantVO.setLocationno(locationno);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("restaurantVO", restaurantVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/map/restaurant/update_restaurant_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				RestaurantService restaurantSvc = new RestaurantService();
				restaurantVO = restaurantSvc.updateRestaurant(restaurantno, restaurantname, restaurantlevel, restaurantadd, restaurantnum, restauranttype, locationno);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				LocationService locationSvc = new LocationService();
				if(requestURL.equals("/map/location/listRestaurants_ByLocationno.jsp") || requestURL.equals("/map/location/listAllLocation.jsp"))
					req.setAttribute("listRestaurants_ByLocationno",locationSvc.getRestaurantsByLocationno(locationno)); // ��Ʈw���X��list����,�s�Jrequest
				
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/map/restaurant/update_restaurant_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String restaurantname = req.getParameter("restaurantname");
				
				if (restaurantname == null) {
					errorMsgs.add("餐廳名稱: 請勿空白");
				}
				
				String restaurantlevel = req.getParameter("restaurantlevel").trim();
				if (restaurantlevel == null || restaurantlevel.trim().length() == 0) {
					errorMsgs.add("評價請勿空白");
				}
				
				String restaurantadd = req.getParameter("restaurantadd");
				
				if (restaurantadd == null) {
					errorMsgs.add("餐廳地址: 請勿空白");
				}
				
				String restaurantnum = req.getParameter("restaurantnum");
				if (restaurantnum == null || restaurantnum.trim().length() == 0) {
					errorMsgs.add("餐廳電話: 請勿空白");
				}

				String restauranttype = req.getParameter("restauranttype");
				
				if (restauranttype == null) {
					errorMsgs.add("餐廳種類: 請勿空白");
				}
				
				Integer locationno = new Integer(req.getParameter("locationno").trim());

				RestaurantVO restaurantVO = new RestaurantVO();
				restaurantVO.setRestaurantname(restaurantname);
				restaurantVO.setRestaurantlevel(restaurantlevel);
				restaurantVO.setRestaurantadd(restaurantadd);
				restaurantVO.setRestaurantnum(restaurantnum);
				restaurantVO.setRestauranttype(restauranttype);
				restaurantVO.setLocationno(locationno);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("restaurantVO", restaurantVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/map/restaurant/addRestaurant.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				RestaurantService restaurantSvc = new RestaurantService();
				restaurantVO = restaurantSvc.addRestaurant(restaurantname, restaurantlevel, restaurantadd, restaurantnum, restauranttype, locationno);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/map/restaurant/listAllRestaurant.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/map/restaurant/addRestaurant.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			String requestURL = req.getParameter("requestURL");
			
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				Integer restaurantno = new Integer(req.getParameter("restaurantno"));
				
				/***************************2.�}�l�R�����***************************************/
				RestaurantService restaurantSvc = new RestaurantService();
				RestaurantVO restaurantVO = restaurantSvc.getOneRestaurant(restaurantno);
				restaurantSvc.deleteRestaurant(restaurantno);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				LocationService locationSvc = new LocationService();
				if(requestURL.equals("/map/restaurant/listAllRestaurant.jsp") || requestURL.equals("/map/location/listAllLocation.jsp"))
					req.setAttribute("listRestaurants_ByLocationno",locationSvc.getRestaurantsByLocationno(restaurantVO.getLocationno())); // ��Ʈw���X��list����,�s�Jrequest
				
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
//		if ("listRestaurants_ByCompositeQuery".equals(action)) { // �Ӧ�select_page.jsp���ƦX�d�߽ШD
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				
//				/***************************1.�N��J����ରMap**********************************/ 
//				//�ĥ�Map<String,String[]> getParameterMap()����k 
//				//�`�N:an immutable java.util.Map 
//				Map<String, String[]> map = req.getParameterMap();
//				
//				/***************************2.�}�l�ƦX�d��***************************************/
//				RestaurantService restaurantSvc = new RestaurantService();
//				List<RestaurantVO> list  = restaurantSvc.getAll(map);
//				
//				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
//				req.setAttribute("listRestaurants_ByCompositeQuery", list); // ��Ʈw���X��list����,�s�Jrequest
//				RequestDispatcher successView = req.getRequestDispatcher("/restaurant/listRestaurants_ByCompositeQuery.jsp"); // ���\���listEmps_ByCompositeQuery.jsp
//				successView.forward(req, res);
//				
//				/***************************��L�i�઺���~�B�z**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}
	}
}
