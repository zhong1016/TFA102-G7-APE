package com.article.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.article.model.ReportDAO;
import com.article.model.ReportVO;
@WebServlet("/reportAPI")
public class reportAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HashMap map = new HashMap(); //���|���O
	HashMap map_title = new HashMap(); //���|�D�D
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	doPost(req, res);
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
//		res.setContentType("application/json");
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
		

		System.out.println("=====================");
		System.out.println("a9=" +req.getParameter("a9") );
		System.out.println("這邊結束====================");
		
		//���o�e�����|���e(a1~a8)����	
		for (int i = 1; i <= 9; i++) {
				if(req.getParameter("a"+i).isEmpty()) {
					map.put("a"+i,null ); //req no value to null
				}else {
					map.put("a"+i,req.getParameter("a"+i) );	//��value��Jmap
				}
				
			}
		//���N������(���|���O)
		Set iSetset = map.keySet();
		Iterator it = iSetset.iterator();
		System.out.println("======================");
		while(it.hasNext()) {
			System.out.println(map.get(it.next()));
		}
	
		//�I�sModel
		
		System.out.println("a9=" +req.getParameter("a9") );
		
		ReportVO rvo = new ReportVO();
		ReportDAO dao = new ReportDAO();
		String msgnoString =  req.getParameter("a9");
		Integer msgno = Integer.parseInt(msgnoString);
		
		rvo.setMANAGER_NO(null);
		rvo.setREPORT(req.getParameter("a8"));
		rvo.setREPORT_MSG(req.getParameter("a1") +"" + req.getParameter("a2")+"" + req.getParameter("a3")+"" + req.getParameter("a4")+"" + req.getParameter("a5"));
//		rvo.setREPORT_STATUS("");
		rvo.setMSG_NO(msgno);
		dao.insert(rvo);
	
	}
}
