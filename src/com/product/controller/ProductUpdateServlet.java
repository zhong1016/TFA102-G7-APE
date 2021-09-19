package com.product.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.mem.model.MemVO;
import com.product.model.ProductDAOImpl;
import com.product.model.ProductVO;

/**
 * Servlet implementation class Product
 */
@WebServlet("/ProductUpdateServlet")
public class ProductUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		
		HttpSession session = req.getSession();
		
		if(session.getAttribute("memVO")== null) {
			System.out.println("有到這");
			RequestDispatcher dispatcher =req.getRequestDispatcher("/members/Member.jsp");
			dispatcher.forward(req, res);
			return; //程式中斷
		
		}
		MemVO memVO =(MemVO) session.getAttribute("memVO");
		Integer userNo =memVO.getUserNo();
		System.out.println("管理的userno="+userNo);
		session.setAttribute("userNo", userNo);
		
		//---------------------更新商品---------------------------------
		Integer pro_no = Integer.parseInt(req.getParameter("pro_no"));
		String image = req.getParameter("image");
		String name =req.getParameter("pro_name");
		Integer price = Integer.parseInt(req.getParameter("price"));
		Integer stock = Integer.parseInt(req.getParameter("stock"));
		Integer category = Integer.parseInt(req.getParameter("category"));
		String imageChange = (String)req.getParameter("imagechange");
		System.out.println(imageChange);
		
		System.out.println(category);
		
		if("uncaught".equals(imageChange)) {
			image=req.getParameter("");
			ProductDAOImpl dao =new ProductDAOImpl();
			ProductVO productVO = new ProductVO(); 
			productVO.setProduct_name(name);
			productVO.setProduct_price(price);
			productVO.setStock(stock);
			productVO.setProduct_category_no(category);
			productVO.setProduct_no(pro_no);
			dao.updateNoPic(productVO);
		}
		
		else if("change".equals(imageChange)) {
			String myString = image.substring(image.lastIndexOf(',') + 1);
			byte[] pic = Base64.getDecoder().decode(myString.getBytes());
			
			
			ProductDAOImpl dao =new ProductDAOImpl();
			ProductVO productVO = new ProductVO();
			productVO.setProduct_pic(pic);
			productVO.setProduct_name(name);
			productVO.setProduct_price(price);
			productVO.setStock(stock);
			productVO.setProduct_category_no(category);
			productVO.setProduct_no(pro_no);
			dao.update(productVO);
			
		
		}
		
		
		
		
			
		
		
		
		
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	doGet(req,res);
	}
}
