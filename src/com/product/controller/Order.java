package com.product.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mem.model.MemVO;
import com.product.model.ProductService;
import com.product.model.ProductVO;

/**
 * Servlet implementation class Order
 */
@WebServlet("/Order")
public class Order extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		
	if (session.getAttribute("memVO") == null) {
		session.setAttribute("location", req.getRequestURI());
		RequestDispatcher dispatcher = req.getRequestDispatcher("/members/Member.jsp");
		dispatcher.forward(req, res);
		return;
	}
		
		
		
		
		
		MemVO memVO = (MemVO) session.getAttribute("memVO");
		String name = memVO.getUserfName();
		String phone =memVO.getUserPhone();
		String add =memVO.getUserAddress();
		Integer userNo = memVO.getUserNo();
		System.out.println(memVO);
		System.out.println(name);
		
		
		//將不重複商品編號取出
		List<Integer> list = (List<Integer>)session.getAttribute("cartValue");
		
		//取得
		List<ProductVO> productList = new ArrayList();
		
		//如果list裡有商品編號才執行
		if(list != null) {
			for(Integer productNo: list) {
				ProductVO productVO = new ProductVO();
				ProductService productSvc = new ProductService();
				
				productVO = productSvc.findByPRODUCT(productNo);
				productList.add(productVO);
				session.setAttribute("productList", productList);
			}
			
		}
			session.setAttribute("userNo",userNo);
			session.setAttribute("name", name);
			session.setAttribute("phone", phone);
			session.setAttribute("add", add);
		
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/store/order.jsp");
		dispatcher.forward(req, res);
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doGet(req, res);
	}

}
