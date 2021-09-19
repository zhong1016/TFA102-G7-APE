package com.location.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.location.model.*;
import com.location.controller.*;

import com.restaurant.model.*;
import com.restaurant.controller.*;

public class LocationServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");

		// �Ӧ�select_page.jsp���ШD // �Ӧ� dept/listAllDept.jsp���ШD
		if ("listRestaurants_ByLocationno_A".equals(action) || "listRestaurants_ByLocationno_B".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				Integer locationno = new Integer(req.getParameter("locationno"));

				/*************************** 2.�}�l�d�߸�� ****************************************/
				LocationService locationSvc = new LocationService();
				Set<RestaurantVO> set = locationSvc.getRestaurantsByLocationno(locationno);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("listRestaurants_ByLocationno", set); // ��Ʈw���X��list����,�s�Jrequest

				String url = null;
				if ("listRestaurants_ByLocationno_A".equals(action))
					url = "/map/location/listRestaurants_ByLocationno.jsp"; // ���\��� dept/listEmps_ByDeptno.jsp
				else if ("listRestaurants_ByLocationno_B".equals(action))
					url = "/map/location/listAllLocation.jsp"; // ���\��� dept/listAllDept.jsp

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}

		if ("delete_Location".equals(action)) { // �Ӧ�/dept/listAllDept.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				Integer locationno = new Integer(req.getParameter("locationno"));

				/*************************** 2.�}�l�R����� ***************************************/
				LocationService locationSvc = new LocationService();
				locationSvc.deleteLocation(locationno);

				/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
				String url = "/map/location/listAllLocation.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��, ���\��� �^�� /dept/listAllDept.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z ***********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/map/location/listAllLocation.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update_Location".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				Integer locationno = new Integer(req.getParameter("locationno").trim());

				String locationname = req.getParameter("locationname");

				if (locationname == null) {
					errorMsgs.add("餐廳名稱: 請勿空白");
				}

				String locationadd = req.getParameter("locationadd");

				if (locationadd == null) {
					errorMsgs.add("餐廳地址: 請勿空白");
				}
				LocationVO locationVO = new LocationVO();
				locationVO.setLocationname(locationname);
				locationVO.setLocationadd(locationadd);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("locationVO", locationVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/map/location/update_location_input.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				LocationService locationSvc = new LocationService();
				locationVO = locationSvc.updateLocation(locationno, locationname, locationadd);

				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/

				if (requestURL.equals("/map/location/listAllLocation.jsp"))
					req.setAttribute("listRestaurants_ByLocationno",
							locationSvc.getRestaurantsByLocationno(locationno)); // ��Ʈw���X��list����,�s�Jrequest

				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/map/location/update_location_input.jsp");
				failureView.forward(req, res);
			}
		}
		if ("insert".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/

				String locationname = req.getParameter("locationname");
				if (locationname == null || locationname.trim().length() == 0) {
					errorMsgs.add("展區名稱: 請勿空白");
				}

				String locationadd = req.getParameter("locationadd");
				if (locationadd == null || locationadd.trim().length() == 0) {
					errorMsgs.add("展區地址: 請勿空白");
				}
				LocationVO locationVO = new LocationVO();

				locationVO.setLocationname(locationname);
				locationVO.setLocationadd(locationadd);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("locationVO", locationVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/map/location/update_location_input.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				LocationService locationSvc = new LocationService();
				locationVO = locationSvc.addLocation(locationname, locationadd);

				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				String url = "/map/location/listAllLocation.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/map/location/update_location_input.jsp");
				failureView.forward(req, res);
			}
		}
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
				/***************************1.�����ШD�Ѽ�****************************************/
				Integer locationno = new Integer(req.getParameter("locationno"));
				
				/***************************2.�}�l�d�߸��****************************************/
				LocationService locationSvc = new LocationService();
				LocationVO locationVO = locationSvc.getOneLocation(locationno);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("locationVO", locationVO);         // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/map/location/update_location_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.

			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");
			

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				Integer locationno = new Integer(req.getParameter("locationno").trim());

				String locationname = req.getParameter("locationname");
				if (locationname == null) {
					errorMsgs.add("展區名稱: 請勿空白");
				}

				String locationadd = req.getParameter("locationadd");
				if (locationadd == null) {
					errorMsgs.add("展區地址: 請勿空白");
				}
				
				LocationVO locationVO = new LocationVO();
				locationVO.setLocationno(locationno);
				locationVO.setLocationname(locationname);
				locationVO.setLocationadd(locationadd);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {

					req.setAttribute("locationVO", locationVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/map/location/update_location_input.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				LocationService locationSvc = new LocationService();
				locationVO = locationSvc.updateLocation(locationno, locationname, locationadd);

				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				if (requestURL.equals("/map/location/listAllLocation.jsp"))
					req.setAttribute("listRestaurants_ByLocationno",
							locationSvc.getRestaurantsByLocationno(locationno)); // ��Ʈw���X��list����,�s�Jrequest

				String url = "/map/location/listAllLocation.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/map/location/update_location_input.jsp");
				failureView.forward(req, res);
			}
		}
	}
}