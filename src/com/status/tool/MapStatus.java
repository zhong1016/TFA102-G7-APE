package com.status.tool;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class MapStatus extends HttpServlet {
       
	@Override
	public void init() throws ServletException {
		ServletContext context = this.getServletContext();
		
		Map<String, String> actMap = new HashMap<String, String>();
		actMap.put("a0", "待審核");
		actMap.put("a1", "通過");
		actMap.put("a2", "未通過");
		actMap.put("a3", "編輯中");
		
		context.setAttribute("actMap", actMap);
//		for(Entry<String, String> xx :actMap.entrySet()) {
//			xx.getKey();
//			xx.getValue();
//		}
		
		Map<String, String> rentMap = new HashMap<String, String>();
		rentMap.put("r0", "待審核");
		rentMap.put("r1", "通過");
		rentMap.put("r2", "未通過");
		
		context.setAttribute("rentMap", rentMap);
		
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
