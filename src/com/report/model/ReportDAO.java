package com.report.model;

import java.util.*;


import java.sql.*;

public class ReportDAO implements Report_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/APE?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";
	
	//SQL���O
	private static final String INSERT_STMT = 
			"INSERT INTO REPORT (REPORT,REPORT_MSG,REPORT_STATUS, MANAGER_NO) VALUES ( ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT REPORT_NO,REPORT,REPORT_MSG,REPORT_STATUS,MANAGER_NO FROM REPORT order by REPORT_NO";
	private static final String GET_ONE_STMT = 
			"SELECT REPORT_NO,REPORT,REPORT_MSG,REPORT_STATUS,MANAGER_NO FROM REPORT where REPORT_NO = ?";
	private static final String DELETE = 
			"DELETE FROM REPORT where REPORT_NO = ?";
	private static final String UPDATE = 
			"UPDATE REPORT set REPORT=?, REPORT_MSG=?, REPORT_STATUS=?, MANAGER_NO=? where REPORT_NO = ?";
	
	
	@Override
	public void insert(ReportVO reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = (Connection) DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1,reportVO.getREPORT());
			pstmt.setString(2 , reportVO.getREPORT_MSG());
			pstmt.setString(3, "a0");
			pstmt.setString(4, reportVO.getMANAGER_NO());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}
		
	}

	@Override
	public void update(ReportVO reportVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, reportVO.getREPORT());
			pstmt.setString(2, reportVO.getREPORT_MSG());
			pstmt.setString(3, reportVO.getREPORT_STATUS());
			pstmt.setString(4, reportVO.getMANAGER_NO());
			pstmt.setInt(5, reportVO.getREPORT_NO());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public void delete(Integer REPORT_NO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, REPORT_NO);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}
		}
		
	}

	@Override
	public ReportVO findPrimaryKey(Integer REPORT_NO) {
		ReportVO reportVO = null;
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, REPORT_NO);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				reportVO = new ReportVO();
				reportVO.setREPORT_NO(REPORT_NO);
				reportVO.setREPORT(rs.getString("REPORT"));
				reportVO.setREPORT_MSG(rs.getString("REPORT_MSG"));
				reportVO.setREPORT_STATUS(rs.getString("REPORT_STATUS"));
				reportVO.setMANAGER_NO(rs.getString("MANAGER_NO"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}
		}
		
		
		return reportVO;
	}

	@Override
	public List<ReportVO> getAll() {
		List<ReportVO> list = new ArrayList<ReportVO>();
		ReportVO reportVO = null;
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				reportVO = new ReportVO();
				reportVO.setREPORT_NO(rs.getInt("REPORT_NO"));
				reportVO.setREPORT(rs.getString("REPORT"));
				reportVO.setREPORT_MSG(rs.getString("REPORT_MSG"));
				reportVO.setREPORT_STATUS(rs.getString("REPORT_STATUS"));
				reportVO.setMANAGER_NO(rs.getString("MANAGER_NO"));
				list.add(reportVO); // Store the row in the list
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return list;
	}
	
	public static void main(String[] args) {
		ReportDAO dao = new ReportDAO();
		//�浧�d��
		
		ReportVO reportVO = dao.findPrimaryKey(1);
		System.out.println(reportVO);
		System.out.println("========================");
		
		//�h���d��
		
		List <ReportVO> list = dao.getAll();
		for (ReportVO reportVO2 : list) {
			System.out.println(reportVO2);
		}
		System.out.println("========================");
		
		//�s�W
		ReportVO reportVO3 = new ReportVO();
		reportVO3.setREPORT("����");
		reportVO3.setREPORT_MSG("����");
		reportVO3.setREPORT_STATUS("a0");
		reportVO3.setMANAGER_NO("����");
		
		dao.insert(reportVO3);
		
		//�R��
		
		dao.delete(1);
		
		//�ק�
		ReportVO reportVO4 = new ReportVO();
		reportVO4.setREPORT("���խק�");
		reportVO4.setREPORT_MSG("���խק�");
		reportVO4.setREPORT_STATUS("a0");
		reportVO4.setMANAGER_NO("���խק�");
		reportVO4.setREPORT_NO(1);
		dao.update(reportVO4);
		
		
	}

}

