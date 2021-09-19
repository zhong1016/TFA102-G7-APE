package com.product.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import com.mem.model.MemVO;
import com.product.*;
import com.product.model.ProductVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

/**
 * Servlet implementation class Product
 */
@WebServlet("/Product")
public class Product extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		
		
		
		
		
		
		
		HttpSession session = req.getSession();
		
		
		
		
		Integer productNo = Integer.parseInt(req.getParameter("productNo"));
		String productName = req.getParameter("productName");
//		System.out.println("productName"+productName);
//		System.out.println("productNo"+productNo);
		List<Integer> cartValue = (List<Integer>) session.getAttribute("cartValue");

		

		// 存放名稱
		TreeMap cart = (TreeMap) session.getAttribute("cart");

		if (cart == null) {
			// 初始化購物車商品名稱
			cart = new TreeMap();
			cart.put(new Integer(1), productName);
			session.setAttribute("cart", cart);

			// 初始化購物車商品編號 新增 List存放商品編號
			cartValue = new ArrayList<Integer>();
			cartValue.add(productNo);
			session.setAttribute("cartValue", cartValue);

		} else {
			int cartSize = cart.size();
			boolean repeat = false;
			// 比對購物車商品是否有重複
			for (int i = 1; i <= cartSize; i++) {
				String exist = (String) cart.get(i);

				if (exist.equals(productName)) {
					repeat = true;
				}
			}
			// 如果沒重複 就加入車裡
			if (!repeat) {
				int i = cartSize + 1;
				cart.put(new Integer(i), productName);
				// List存放商品編號
				cartValue.add(productNo);
			}
		}
		JSONObject obj = new JSONObject(cart);
		PrintWriter out = res.getWriter();
		out.print(obj);
		System.out.println(obj);
	}

}
