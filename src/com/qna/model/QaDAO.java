package com.qna.model;

import java.sql.*;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/*
 * new context.xml
 * <Resource auth="Container" driverClassName="com.mysql.cj.jdbc.Driver" maxIdle="10" maxTotal="20" maxWaitMillis="-1" name="jdbc/APEDB" password="123456" type="javax.sql.DataSource" url="jdbc:mysql://localhost:3306/APE?serverTimezone=Asia/Taipei" username="David"/>
 */

public class QaDAO implements QaDAO_interface {

	private static DataSource ds = null;
	
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/APEDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://localhost:3306/APE?serverTimezone=Asia/Taipei";
//	String qaNo = "David";
//	String passwd = "123456";
	
	private static final String INSERT_QA = 
			"INSERT INTO QASESSION (QUESTIONS, ANSWERS) "
			+ "VALUES ( ?, ?)";
	private static final String UPDATE_QA = 
			"UPDATE QASESSION SET QUESTIONS = ?, ANSWERS = ? WHERE QA_NO = ?";
	private static final String DELETE_QA =
			"DELETE FROM QASESSION WHERE Qa_No = ?";
	private static final String GET_ONE_QA =
			"SELECT QUESTIONS, ANSWERS FROM QASESSION WHERE QA_NO = ?";
	private static final String GET_ALL_QA =
			"SELECT QUESTIONS, ANSWERS FROM QASESSION ORDER BY QA_NO";

	@Override
	public void insert(QaVO qaVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, qaNo, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_QA);
			
			pstmt.setString( 1, qaVO.getQuestions());
			pstmt.setString( 2, qaVO.getAnswers());
			
			pstmt.executeUpdate();
			
		} /* catch (ClassNotFoundException e) {
			e.printStackTrace();
		}*/ catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if ( pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if ( con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public void update(QaVO qaVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, qaNo, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_QA);
			
			pstmt.setString( 1, qaVO.getQuestions());
			pstmt.setString( 2, qaVO.getAnswers());
			
			pstmt.executeUpdate();
	
		}/* catch (ClassNotFoundException e) {
			e.printStackTrace();
		}*/ catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if ( pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if ( con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void delete(Integer qaNo) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, qaNo, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_QA);
			
			pstmt.setInt(1, qaNo);
			pstmt.executeUpdate();
				
		}/* catch (ClassNotFoundException e) {
			e.printStackTrace();
		} */catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if ( pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if ( con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public QaVO findByQaNo(Integer qaNo) {
		
		QaVO QaVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		
		try {
			
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, qaNo, passwd);
			con = ds.getConnection();
			System.out.println("Connecting to database successfully! ");
			pstmt = con.prepareStatement(GET_ONE_QA);
			
			pstmt.setInt(1, qaNo);		

			rs = pstmt.executeQuery();
			
			while( rs.next()) {
				QaVO = new QaVO();
				QaVO.setQaNo(rs.getInt("QA_NO"));
				QaVO.setQuestions(rs.getString("QUESTIONS"));
				QaVO.setAnswers(rs.getString("ANSWERS"));
			}
		}/* catch (ClassNotFoundException e) {
			e.printStackTrace();
		} */catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if ( rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if ( pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if ( con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return QaVO;
	}
	@Override
	public List<QaVO> getAll() {
		
		List<QaVO> list = new ArrayList<>();
		QaVO QaVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		
		try {
			
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, qaNo, passwd);
			con = ds.getConnection();
			System.out.println("Connecting to database successfully! ");
			pstmt = con.prepareStatement(GET_ALL_QA);
			rs = pstmt.executeQuery();
			
			while( rs.next()) {
				QaVO = new QaVO();
				QaVO.setQaNo(rs.getInt("QA_NO"));
				QaVO.setQuestions(rs.getString("QUESTIONS"));
				QaVO.setAnswers(rs.getString("ANSWERS"));
				list.add(QaVO);
			}
			
			
		} /*catch (ClassNotFoundException e) {
			e.printStackTrace();
		} */catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if ( rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if ( pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if ( con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
}
