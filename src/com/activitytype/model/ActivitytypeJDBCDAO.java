package com.activitytype.model;

import java.util.*;
import java.sql.*;

public class ActivitytypeJDBCDAO implements ActivitytypeDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/db01?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO APE.ACTIVITYTYPE(ACT_TYPE_NO,ACT_TYPE_NAME) VALUES (?,?)";
	private static final String GET_ALL_STMT = "SELECT ACT_TYPE_NO,ACT_TYPE_NAME FROM APE.ACTIVITYTYPE ORDER BY ACT_TYPE_NO";
	private static final String GET_ONE_STMT = "SELECT ACT_TYPE_NO,ACT_TYPE_NAME FROM APE.ACTIVITYTYPE WHERE ACT_TYPE_NO = ?";
	private static final String UPDATE = "UPDATE APE.ACTIVITYTYPE SET ACT_TYPE_NAME = ? WHERE ACT_TYPE_NO = ?";

	@Override
	public void insert(ActivitytypeVO activitytypeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, activitytypeVO.getActivityTypeNo());
			pstmt.setString(2, activitytypeVO.getActivityTypeName());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(ActivitytypeVO activitytypeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, activitytypeVO.getActivityTypeName());
			pstmt.setInt(2, activitytypeVO.getActivityTypeNo());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public ActivitytypeVO findByPrimaryKey(Integer acivityTypeNo) {

		ActivitytypeVO activitytypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, acivityTypeNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				activitytypeVO = new ActivitytypeVO();
				activitytypeVO.setActivityTypeNo(rs.getInt("ACT_TYPE_NO"));
				activitytypeVO.setActivityTypeName(rs.getString("ACT_TYPE_NAME"));

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return activitytypeVO;
	}

	@Override
	public List<ActivitytypeVO> getAll() {
		List<ActivitytypeVO> list = new ArrayList<ActivitytypeVO>();
		ActivitytypeVO activitytypeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				activitytypeVO = new ActivitytypeVO();
				activitytypeVO.setActivityTypeNo(rs.getInt("ACT_TYPE_NO"));
				activitytypeVO.setActivityTypeName(rs.getString("ACT_TYPE_NAME"));

				list.add(activitytypeVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {

		ActivitytypeJDBCDAO dao = new ActivitytypeJDBCDAO();

		// 新增
//		ActivitytypeVO activitytypeVO1 = new ActivitytypeVO();
//		activitytypeVO1.setActivityTypeNo(2);
//		activitytypeVO1.setActivityTypeName("生活雜貨");
//		dao.insert(activitytypeVO1);

		// 修改
//		ActivitytypeVO activitytypeVO2 = new ActivitytypeVO();
//		activitytypeVO2.setActivityTypeNo(7);
//		activitytypeVO2.setActivityTypeName("二手市集");
//		dao.update(activitytypeVO2);
		

		// 刪除
//		dao.delete();

		// 查詢
//		ActivitytypeVO activitytypeVO3 = dao.findByPrimaryKey(1);
//		System.out.print(activitytypeVO3.getActivityTypeNo()+ ",");
//		System.out.print(activitytypeVO3.getActivityTypeName());
//		System.out.println("---------------------");

		// 查詢
		List<ActivitytypeVO> list = dao.getAll();
		for (ActivitytypeVO aActivitytypeVO : list) {
			System.out.print(aActivitytypeVO.getActivityTypeNo() + ",");
			System.out.print(aActivitytypeVO.getActivityTypeName());
			System.out.println();
		}
	}

}