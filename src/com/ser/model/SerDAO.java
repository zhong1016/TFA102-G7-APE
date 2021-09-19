package com.ser.model;

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

public class SerDAO implements SerDAO_interface {

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
//	String userid = "David";
//	String passwd = "123456";

	private static final String INSERT_SER = "INSERT INTO SERVICE (SERV_NAME, SERV_MSG, SERV_MAIN, SERV_PIC, SERV_ANS, STATUS) "
			+ "VALUES ( ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_SER = "UPDATE SERVICE SET SERV_NAME = ?,SERV_MSG = ?,SERV_MAIN = ?,SERV_PIC = ?,SERV_ANS = ?,STATUS = ? WHERE user_Id = ?";
	private static final String DELETE_SER = "DELETE FROM SERVICE WHERE SERV_NO = ?";
	private static final String GET_ONE_SER = "SELECT SERV_NO, SERV_NAME, SERV_MSG, SERV_MAIN, SERV_PIC, SERV_ANS, STATUS FROM SERVICE WHERE SERV_NO = ?";
	private static final String GET_ALL_SER = "SELECT SERV_NO, SERV_NAME, SERV_MSG, SERV_MAIN, SERV_PIC, SERV_ANS, STATUS FROM SERVICE ORDER BY SERV_NO";

	@Override
	public void insert(SerVO serVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_SER);

			pstmt.setString(1, serVO.getServName());
			pstmt.setString(2, serVO.getServMsg());
			pstmt.setString(3, serVO.getServMain());
			pstmt.setBytes(4, serVO.getServPic());
			pstmt.setString(5, serVO.getServAns());
			pstmt.setString(6, serVO.getStatus());

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
	public void update(SerVO serVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_SER);

			pstmt.setString(1, serVO.getServName());
			pstmt.setString(2, serVO.getServMsg());
			pstmt.setString(3, serVO.getServMain());
			pstmt.setBytes(4, serVO.getServPic());
			pstmt.setString(5, serVO.getServAns());

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

	public void delete(Integer servNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_SER);

			pstmt.setInt(1, servNo);
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
	public SerVO findByServNo(Integer servNo) {

		SerVO serVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			System.out.println("Connecting to database successfully! ");
			pstmt = con.prepareStatement(GET_ONE_SER);

			pstmt.setInt(1, servNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				serVO = new SerVO();
				serVO.setServNo(rs.getInt("SERV_NO"));
				serVO.setServName(rs.getString("SERV_NAME"));
				serVO.setServMsg(rs.getString("SERV_MSG"));
				serVO.setServMain(rs.getString("SERV_MAIN"));
				serVO.setServPic(rs.getBytes("SERV_PIC"));
				serVO.setServAns(rs.getString("SERV_ANS"));
				serVO.setStatus(rs.getString("status"));
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
		return serVO;
	}

	@Override
	public List<SerVO> getAll() {

		List<SerVO> list = new ArrayList<>();
		SerVO serVO = null;
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
				serVO = new SerVO();
				serVO.setServNo(rs.getInt("SERV_NO"));
				serVO.setServName(rs.getString("SERV_NAME"));
				serVO.setServMsg(rs.getString("SERV_MSG"));
				serVO.setServMain(rs.getString("SERV_MAIN"));
				serVO.setServPic(rs.getBytes("SERV_PIC"));
				serVO.setServAns(rs.getString("SERV_ANS"));
				serVO.setStatus(rs.getString("status"));
				list.add(serVO);
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
//	@Override //�o�Ӥ��ᦳ�ŦA��g(����)
//	public List<MemVO> getAll(Map<String, String[]> map) {
//		return null;
}
