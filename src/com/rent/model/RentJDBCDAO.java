package com.rent.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.apache.commons.lang3.StringUtils;

import com.activity.model.ActivityJDBCDAO;
import com.activity.model.ActivityVO;

public class RentJDBCDAO implements RentDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/db01?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO APE.RENT(RENT_NAME,RENT_INTRODUCTION,RENT_PIC,STATUS,RENT_PRICE,RESERVATION,ACTIVITY_NO,USER_NO) VALUES (?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT RENT_NO,RENT_NAME,RENT_INTRODUCTION,RENT_PIC,STATUS,RENT_PRICE,RESERVATION,ACTIVITY_NO,USER_NO FROM APE.RENT ORDER BY RENT_NO";
	private static final String GET_MY_RENT_ACTIVITY = "SELECT RENT_NO,RENT_NAME,RENT_INTRODUCTION,RENT_PIC,STATUS,RENT_PRICE,RESERVATION,ACTIVITY_NO,USER_NO FROM APE.RENT WHERE ACTIVITY_NO = ? ORDER BY RENT_NO ";
	private static final String GET_ONE_STMT = "SELECT RENT_NO,RENT_NAME,RENT_INTRODUCTION,RENT_PIC,STATUS,RENT_PRICE,RESERVATION,ACTIVITY_NO,USER_NO FROM APE.RENT WHERE RENT_NO = ?";
	private static final String UPDATE = "UPDATE APE.RENT SET RENT_NAME =? ,RENT_INTRODUCTION = ?,RENT_PIC = ?,STATUS = ?,RENT_PRICE =?,RESERVATION = ?,ACTIVITY_NO = ?,USER_NO = ? WHERE RENT_NO = ?";
	private static final String UPDATE_STATUS = "UPDATE APE.RENT SET STATUS = ? WHERE RENT_NO = ?";
	private static final String GET_MY_RENT = "SELECT RENT_NO,RENT_NAME,RENT_INTRODUCTION,RENT_PIC,STATUS,RENT_PRICE,RESERVATION,ACTIVITY_NO, USER_NO FROM APE.RENT WHERE USER_NO = ?";
	private static final String GET_STATUS = "SELECT RENT_NO,RENT_NAME,RENT_INTRODUCTION,RENT_PIC,STATUS,RENT_PRICE,RESERVATION,ACTIVITY_NO FROM APE.RENT WHERE USER_NO = ? AND ACTIVITY_NO = ?";

	@Override
	public void insert(RentVO rentVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			// 1.設定於pstmt.executeUpdate()之前
			con.setAutoCommit(false);

			// 先新增攤位
			Integer activityNo = rentVO.getActivityNo();
			String reservation = rentVO.getReservation();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, rentVO.getRentName());
			pstmt.setString(2, rentVO.getRentIntroduction());
			pstmt.setBytes(3, rentVO.getRentPic());
			pstmt.setString(4, rentVO.getStatus());
			pstmt.setInt(5, rentVO.getRentPrice());
			pstmt.setString(6, reservation);
			pstmt.setInt(7, activityNo);
			pstmt.setInt(8, rentVO.getUserNo());

			pstmt.executeUpdate();

			// 在更新活動攤位租借字串
			ActivityJDBCDAO dao = new ActivityJDBCDAO();
			ActivityVO activityVO = dao.findByPrimaryKey(activityNo);
			String reservationAll = activityVO.getReservationAll();
			String[] reservationAllArr = null;
			if(StringUtils.isNotBlank(reservationAll)) {
				reservationAllArr = reservationAll.split(","); 
			}
			

			List<String> list = new ArrayList<>();
			if (reservationAll != null && reservationAll.trim().length() > 0) {
				list = new ArrayList(Arrays.asList(reservationAllArr));
				if (!list.contains(reservation)) {
					list.add(reservation);
					reservation = String.join(",", list);
				} else {
					throw new SQLException("重複訂位");
				}
			}
			dao.updateReservationAll(reservation, activityNo, con);
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3.設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-Rent");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
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
	public void update(RentVO rentVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, rentVO.getRentName());
			pstmt.setString(2, rentVO.getRentIntroduction());
			pstmt.setBytes(3, rentVO.getRentPic());
			pstmt.setString(4, rentVO.getStatus());
			pstmt.setInt(5, rentVO.getRentPrice());
			pstmt.setString(6, rentVO.getReservation());
			pstmt.setInt(7, rentVO.getActivityNo());
			pstmt.setInt(8, rentVO.getUserNo());
			pstmt.setInt(9, rentVO.getRentNo());

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
	public RentVO findByPrimaryKey(Integer rentNO) {

		RentVO rentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, rentNO);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				rentVO = new RentVO();
				rentVO.setRentNo(rs.getInt("RENT_NO"));
				rentVO.setRentName(rs.getString("RENT_NAME"));
				rentVO.setRentIntroduction(rs.getString("RENT_INTRODUCTION"));
				rentVO.setRentPic(rs.getBytes("RENT_PIC"));
				rentVO.setStatus(rs.getString("STATUS"));
				rentVO.setRentPrice(rs.getInt("RENT_PRICE"));
				rentVO.setReservation(rs.getString("RESERVATION"));
				rentVO.setActivityNo(rs.getInt("ACTIVITY_NO"));
				rentVO.setUserNo(rs.getInt("USER_NO"));

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
		return rentVO;
	}

	@Override
	public List<RentVO> getAll() {
		List<RentVO> list = new ArrayList<RentVO>();
		RentVO rentVO = null;

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
				rentVO = new RentVO();
				rentVO.setRentNo(rs.getInt("RENT_NO"));
				rentVO.setRentName(rs.getString("RENT_NAME"));
				rentVO.setRentIntroduction(rs.getString("RENT_INTRODUCTION"));
				rentVO.setRentPic(rs.getBytes("RENT_PIC"));
				rentVO.setStatus(rs.getString("STATUS"));
				rentVO.setRentPrice(rs.getInt("RENT_PRICE"));
				rentVO.setReservation(rs.getString("RESERVATION"));
				rentVO.setActivityNo(rs.getInt("ACTIVITY_NO"));
				rentVO.setUserNo(rs.getInt("USER_NO"));
				list.add(rentVO); // Store the row in the list
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

	@Override
	public List<RentVO> getRentByActivity(Integer activityNo) {
		List<RentVO> list = new ArrayList<RentVO>();
		RentVO rentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_MY_RENT_ACTIVITY);
			pstmt.setInt(1, activityNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				rentVO = new RentVO();
				rentVO.setRentNo(rs.getInt("RENT_NO"));
				rentVO.setRentName(rs.getString("RENT_NAME"));
				rentVO.setRentIntroduction(rs.getString("RENT_INTRODUCTION"));
				rentVO.setRentPic(rs.getBytes("RENT_PIC"));
				rentVO.setStatus(rs.getString("STATUS"));
				rentVO.setRentPrice(rs.getInt("RENT_PRICE"));
				rentVO.setReservation(rs.getString("RESERVATION"));
				rentVO.setActivityNo(rs.getInt("ACTIVITY_NO"));
				rentVO.setUserNo(rs.getInt("USER_NO"));
				list.add(rentVO); // Store the row in the list
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

		RentJDBCDAO dao = new RentJDBCDAO();

		// 新增
//		RentVO rentVO1 = new RentVO();
//		rentVO1.setRentNo(5);
//		rentVO1.setRentName("剛剛好，生活");
//		rentVO1.setRentIntroduction(
//				"不要為了消費而生活， 我們應當是為了生活上的需要而消費，在不知不覺中，早已忘了生活的本質與意義．“剛剛好”並非一個標準值，而是提醒我們要時時檢視生活週遭的一切，跟自己對話，追求一個真正適合自己的生活價值．");
//		rentVO1.setRentPic(null);
//		rentVO1.setStatus("0");
//		rentVO1.setRentPrice(200);
//		rentVO1.setReservation(null);
//		rentVO1.setActivityNo(2);
//		rentVO1.setUserNo(2);
//		dao.insert(rentVO1);

		// 修改
//		RentVO rentVO2 = new RentVO();
//		rentVO2.setRentName("剛剛好生活2");
//		rentVO2.setRentIntroduction(
//				"不要為了消費而生活， 我們應當是為了生活上的需要而消費，在不知不覺中，早已忘了生活的本質與意義．“剛剛好”並非一個標準值，而是提醒我們要時時檢視生活週遭的一切，跟自己對話，追求一個真正適合自己的生活價值．");
//		rentVO2.setRentPic(null);
//		rentVO2.setStatus("0");
//		rentVO2.setRentPrice(100);
//		rentVO2.setReservation("0");
//		rentVO2.setActivityNo(2);
//		rentVO2.setUserNo(2);
//		rentVO2.setRentNo(5);
//		dao.update(rentVO2);

		// 刪除
//		dao.delete();

		// 查詢
//		RentVO rentVO3 = dao.findByPrimaryKey(3);
//		System.out.print(rentVO3.getRentNo() + ",");
//		System.out.print(rentVO3.getRentName() + ",");
//		System.out.print(rentVO3.getRentIntroduction() + ",");
//		System.out.print(rentVO3.getRentPic() + ",");
//		System.out.print(rentVO3.getStatus()+ ",");
//		System.out.print(rentVO3.getRentPrice()+ ",");
//		System.out.print(rentVO3.getReservation()+ ",");
//		System.out.print(rentVO3.getActivityNo() + ",");
//		System.out.println(rentVO3.getUserNo() );
//		System.out.println("---------------------");

		// 查詢
//		List<RentVO> list = dao.getAll();
//		for (RentVO aRentVO : list) {
//			System.out.print(aRentVO.getRentNo() + ",");
//			System.out.print(aRentVO.getRentName() + ",");
//			System.out.print(aRentVO.getRentIntroduction() + ",");
//			System.out.print(aRentVO.getRentPic() + ",");
//			System.out.print(aRentVO.getStatus() + ",");
//			System.out.print(aRentVO.getRentPrice() + ",");
//			System.out.print(aRentVO.getReservation() + ",");
//			System.out.print(aRentVO.getActivityNo() + ",");
//			System.out.print(aRentVO.getUserNo());
//			System.out.println();
//		}
	}

	@Override
	public List<RentVO> getMyRent(Integer userNo) {

		List<RentVO> list = new ArrayList<RentVO>();
		RentVO rentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_MY_RENT);

			pstmt.setInt(1, userNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				rentVO = new RentVO();
				rentVO.setRentNo(rs.getInt("RENT_NO"));
				rentVO.setRentName(rs.getString("RENT_NAME"));
				rentVO.setRentIntroduction(rs.getString("RENT_INTRODUCTION"));
				rentVO.setRentPic(rs.getBytes("RENT_PIC"));
				rentVO.setStatus(rs.getString("STATUS"));
				rentVO.setRentPrice(rs.getInt("RENT_PRICE"));
				rentVO.setReservation(rs.getString("RESERVATION"));
				rentVO.setActivityNo(rs.getInt("ACTIVITY_NO"));
				rentVO.setUserNo(rs.getInt("USER_NO"));
				list.add(rentVO);
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

	@Override
	public List<RentVO> getStatus(String status, Integer activityNo) {
		List<RentVO> list = new ArrayList<RentVO>();
		RentVO rentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_STATUS);

			pstmt.setString(1, status);
			pstmt.setInt(2, activityNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				rentVO = new RentVO();
				rentVO.setRentNo(rs.getInt("RENT_NO"));
				rentVO.setRentName(rs.getString("RENT_NAME"));
				rentVO.setRentIntroduction(rs.getString("RENT_INTRODUCTION"));
				rentVO.setRentPic(rs.getBytes("RENT_PIC"));
				rentVO.setStatus(rs.getString("STATUS"));
				rentVO.setRentPrice(rs.getInt("RENT_PRICE"));
				rentVO.setReservation(rs.getString("RESERVATION"));
				rentVO.setActivityNo(rs.getInt("ACTIVITY_NO"));
				rentVO.setUserNo(rs.getInt("USER_NO"));
				list.add(rentVO);
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

	@Override
	public void updateStatus(RentVO rentVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STATUS);

			pstmt.setString(1, rentVO.getStatus());
			pstmt.setInt(2, rentVO.getRentNo());
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

	public void updateStatusRemove(RentVO rentVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			// 1.設定於pstmt.executeUpdate()之前
			con.setAutoCommit(false);

			Integer activityNo = rentVO.getActivityNo();
			String reservation = rentVO.getReservation();
			pstmt = con.prepareStatement(UPDATE_STATUS);

			pstmt.setString(1, rentVO.getStatus());
			pstmt.setInt(2, rentVO.getRentNo());
			pstmt.executeUpdate();

			// 在更新活動攤位租借字串
			ActivityJDBCDAO dao = new ActivityJDBCDAO();
			ActivityVO activityVO = dao.findByPrimaryKey(activityNo);
			String reservationAll = activityVO.getReservationAll();
			String[] reservationAllArr = reservationAll.split(",");
			List<String> list = new ArrayList<>();
			if (reservationAll != null && reservationAll.trim().length() > 0) {
				list = new ArrayList(Arrays.asList(reservationAllArr));
				if (list.contains(reservation)) {
					list.remove(reservation);
					reservation = String.join(",", list);
				} else {
					throw new SQLException("重複訂位");
				}
			}
			dao.updateReservationAll(reservation, activityNo, con);
			// Handle any driver errors

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3.設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-Rent");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
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

}