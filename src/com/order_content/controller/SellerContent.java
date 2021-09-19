package com.order_content.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.orderView.model.OrderViewDAOImpl;
import com.orderView.model.OrderViewVO;
import com.order_content.model.OrderContentDAOImpl;
import com.order_content.model.OrderContentVO;

/**
 * Servlet implementation class SellerContent
 */
@WebServlet("/SellerContent")
public class SellerContent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 將product 和 ordercontent join 產生 view 並以一個新類別OrderViewVO對應
//		OrderViewDAOImpl dao = new OrderViewDAOImpl();
//		List<OrderViewVO> list = dao.getAll();
//		
//		
//		req.setAttribute("sellList", list);

		req.setCharacterEncoding("UTF-8");
		Integer detail_no =Integer.parseInt(req.getParameter("detail_no"));

			System.out.println("detail_no=" + detail_no);
			
			OrderContentDAOImpl dao =new OrderContentDAOImpl();
			OrderContentVO ordercontentVO =new OrderContentVO();
			ordercontentVO.setOrder_detail_no(detail_no);
			ordercontentVO.setStatus("已處理");;
			dao.update(ordercontentVO);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/store/seller_manage.jsp");

			dispatcher.forward(req, res);


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
