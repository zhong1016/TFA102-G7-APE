package com.product.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.product.model.ProductDAOImpl;
import com.product.model.ProductVO;

/**
 * Servlet implementation class SingleProduct
 */
@WebServlet("/SingleProduct")
public class SingleProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	req.setCharacterEncoding("UTF-8");
	Integer pro_no = Integer.parseInt(req.getParameter("pro_no"));
	
	System.out.println("prono"+pro_no);
	
	ProductDAOImpl dao =new ProductDAOImpl();
	ProductVO productVO = new ProductVO(); 
	dao.findByPRODUCT(pro_no);
	productVO=dao.findByPRODUCT(pro_no);
//	System.out.println(productVO.getProduct_name());
//	System.out.println(productVO.getProduct_no());
//	System.out.println(productVO.getProduct_price());
	
	req.setAttribute("productVO", productVO);
	RequestDispatcher rd =req.getRequestDispatcher("/store/product.jsp");
	rd.forward(req, res);
	
	
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

}
