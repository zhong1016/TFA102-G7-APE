package com.ser.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class SerQADAO implements SerQADAO_interface {

	private static DataSource ds = null;

	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/APE");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_SER = "INSERT INTO QASESSION (QUESTIONS, ANSWERS) "
			+ "VALUES ( ?, ?)";
	private static final String UPDATE_SER = "UPDATE QASESSION SET QUESTIONS = ?,ANSWERS = ? WHERE QA_NO = ?";
	private static final String DELETE_SER = "DELETE FROM QASESSION WHERE QA_NO = ?";
	private static final String GET_ONE_SER = "SELECT QA_NO, QUESTIONS, ANSWERS FROM QASESSION WHERE QA_NO = ?";
	private static final String GET_ALL_SER = "SELECT QA_NO, QUESTIONS, ANSWERS FROM QASESSION ORDER BY QA_NO";

	@Override
	public void insert(SerQAVO serQAVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_SER);

			pstmt.setString(1, serQAVO.getSerQus());
			pstmt.setString(2, serQAVO.getSerAns());

			pstmt.executeUpdate();

		} /*
			 * catch (ClassNotFoundException e) { e.printStackTrace(); }
			 */ catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void update(SerQAVO serQAVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_SER);

			pstmt.setString(1, serQAVO.getSerQus());
			pstmt.setString(2, serQAVO.getSerAns());
			pstmt.setInt(3, serQAVO.getSerQANo());

			pstmt.executeUpdate();

		} /*
			 * catch (ClassNotFoundException e) { e.printStackTrace(); }
			 */ catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void delete(Integer servQANo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_SER);

			pstmt.setInt(1, servQANo);
			pstmt.executeUpdate();

		} /*
			 * catch (ClassNotFoundException e) { e.printStackTrace(); }
			 */catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public SerQAVO findByServNo(Integer serQANo) {
		SerQAVO serQAVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			System.out.println("Connecting to database successfully! ");
			pstmt = con.prepareStatement(GET_ONE_SER);

			pstmt.setInt(1, serQANo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				serQAVO = new SerQAVO();
				serQAVO.setSerQANo(rs.getInt("QA_NO"));
				serQAVO.setSerQus(rs.getString("QUESTIONS"));
				serQAVO.setSerAns(rs.getString("ANSWERS"));
			}
		} /*
			 * catch (ClassNotFoundException e) { e.printStackTrace(); }
			 */catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return serQAVO;
	}

	@Override
	public List<SerQAVO> getAll() {
		
		List<SerQAVO> list = new ArrayList<>();
		SerQAVO serQAVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			System.out.println("Connecting to database successfully! ");
			pstmt = con.prepareStatement(GET_ALL_SER);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				serQAVO = new SerQAVO();
				serQAVO.setSerQANo(rs.getInt("QA_NO"));
				serQAVO.setSerQus(rs.getString("QUESTIONS"));
				serQAVO.setSerAns(rs.getString("ANSWERS"));
				list.add(serQAVO);
			}

		} /*
			 * catch (ClassNotFoundException e) { e.printStackTrace(); }
			 */catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
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
