package com.article.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.article.model.AmessageDAO;
import com.article.model.AmessageVO;
import com.mem.model.MemVO;
@WebServlet("/amessageAPI")
public class amessageAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	doPost(req, res);
	}

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		res.addHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out= res.getWriter();
		
		HttpSession session = req.getSession();
		System.out.println("session=" + session.getAttribute("memVO"));
		//session.setAttribute("member", "abc");  //set the memberID to Ajax
		if (session.getAttribute("memVO") == null) {
			out.println(false);
			return;
		}
		
		System.out.println("這邊有執行");
		String member;
		MemVO mvo = new MemVO();
		mvo = (MemVO)session.getAttribute("memVO"); //get the memVO object
		Integer mvoName = (Integer)mvo.getUserNo();
		System.out.println("movName=" + mvoName);
		JSONObject JS = new JSONObject();
		String k = req.getParameter("k1"); //文章編號
		String k2 = req.getParameter("k2");// 留言內容
		Integer K1 = Integer.parseInt(k);
		
		System.out.println(K1);
		System.out.println(k2);
		
		AmessageVO avo = new AmessageVO();
		AmessageDAO dao = new AmessageDAO();
		
		avo.setARTICLE_NO(K1); //輸入對應文章編號
		avo.setMSG(k2);
		avo.setUSER_NO(mvoName);
		dao.insert(avo);
		
		
		
	}

}
