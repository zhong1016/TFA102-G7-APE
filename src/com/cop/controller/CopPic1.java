package com.cop.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class CopPic
 */
@WebServlet("/CopPic1")
public class CopPic1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	Connection con;     
	public void init() throws ServletException {
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/APE");
			con = ds.getConnection();
		}  catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
    
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
				
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		
		try {
			Statement stmt = con.createStatement();
			
			Integer companyNo = Integer.parseInt(req.getParameter("companyNo"));	
			System.out.println(companyNo);
			ResultSet rs = stmt.executeQuery("SELECT COMPANY_PIC FROM COMPANY WHERE COMPANY_NO=" + companyNo);	

			if(rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("COMPANY_PIC"));
				byte[] buf = new byte[4 * 1024];
				int len;
				while((len =  in.read(buf)) != -1){
					out.write(buf, 0, len);
				}
				in.close();
			}else {
				InputStream in = getServletContext().getResourceAsStream("/NoData/null2.jpg");
				byte[] b = new byte[in.available()];
				in.read(b);
				out.write(b);
				in.close();	
			}
			
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			InputStream in = getServletContext().getResourceAsStream("/NoData/null2.jpg");
			byte[] b = new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();	
		}		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
