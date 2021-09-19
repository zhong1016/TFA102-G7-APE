	package com.order_content.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.order.model.OrderDAOImpl;
import com.order.model.OrderVO;
import com.order_content.model.OrderContentDAOImpl;
import com.order_content.model.OrderContentVO;
import com.product.model.ProductDAOImpl;
import com.product.model.ProductVO;

/**
 * Servlet implementation class CheckOut
 */
@WebServlet("/CheckOut")
public class CheckOut extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		
		
		
		Gson gson = new Gson();
		try (BufferedReader br = req.getReader()) {
			Map map = gson.fromJson(br, Map.class);
			   System.out.println(map);
			   Map information = (LinkedTreeMap) map.get("information");
			   System.out.println(information);

			   // 購買數量list
			   List<String> list = (ArrayList) map.get("count");

			   //取的商品list
			   HttpSession session = req.getSession();
			   List<ProductVO> productList = (List<ProductVO>) session.getAttribute("productList");

			OrderDAOImpl dao = new OrderDAOImpl();
						
			List<OrderContentVO> orderList = new ArrayList();
			for (int i = 0; i < productList.size(); i++) {
				OrderContentVO orderContentVO = new OrderContentVO();
				orderContentVO.setProduct_amount(Integer.parseInt(list.get(i)));
				orderContentVO.setProduct_no(productList.get(i).getProduct_no());
				orderContentVO.setProduct_price(productList.get(i).getProduct_price());
				orderContentVO.setProduct_total(Integer.parseInt(list.get(i)) * productList.get(i).getProduct_price());
				orderContentVO.setReceive_address((String)information.get("address"));
			    orderContentVO.setReceive_phone_num((String)information.get("phone"));
			    orderContentVO.setReceive_name((String)information.get("name"));
				orderList.add(orderContentVO);
				
				
				//扣掉購買商品數量
				ProductDAOImpl dao1 = new ProductDAOImpl();
				dao1.decreaseProductAmount(productList.get(i).getProduct_no(), Integer.parseInt(list.get(i)));
			}
			
			OrderVO orderVO = new OrderVO();
			
			orderVO.setUser_no(new Integer(1));
			dao.insert1(orderVO, orderList);
			
			session.removeAttribute("productList");
			session.removeAttribute("cart");
			session.removeAttribute("cartValue");
			
//			RequestDispatcher dispatcher = req.getRequestDispatcher("/store/buyer_manage.jsp");
//			dispatcher.forward(req, res);
//			res.sendRedirect("http://localhost:8081/APE/store/buyer_manage.jsp");

		}

	}

}
