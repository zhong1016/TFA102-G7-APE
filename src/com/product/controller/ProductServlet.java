package com.product.controller;

import java.io.*;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.cop.model.CopVO;
import com.mem.model.MemVO;
import com.product.model.ProductDAOImpl;
import com.product.model.ProductService;
import com.product.model.ProductVO;

/**
 * Servlet implementation class Product
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		HttpSession session = req.getSession(false);

		MemVO memVO = (MemVO) session.getAttribute("memVO");
		System.out.println("memVO="+memVO);
		
		
		String image = req.getParameter("image");
		String name = req.getParameter("name");
		Integer price = Integer.parseInt(req.getParameter("price"));
		Integer stock = Integer.parseInt(req.getParameter("stock"));
		Integer category = Integer.parseInt(req.getParameter("category"));
		Integer userNo= memVO.getUserNo();
		String myString = image.substring(image.lastIndexOf(',') + 1);
		byte[] pic = Base64.getDecoder().decode(myString.getBytes());
		System.out.println(userNo);
		ProductDAOImpl dao = new ProductDAOImpl();
		ProductVO productVO = new ProductVO();
		productVO.setProduct_pic(pic);
		productVO.setProduct_name(name);
		productVO.setProduct_price(price);
		productVO.setStock(stock);
		productVO.setProduct_category_no(category);
		productVO.setUser_no(userNo);

		dao.insert(productVO);

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

}
