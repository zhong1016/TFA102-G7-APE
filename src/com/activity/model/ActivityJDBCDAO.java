package com.activity.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ActivityJDBCDAO implements ActivityDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/db01?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";
//	                                                                     
	private static final String INSERT_STMT = "INSERT INTO APE.ACTIVITY(ACTIVITY_NAME,START_DATE,CLOSE_DATE,DAY_COUNT,ACTIVITY_HOURS,RENT_COUNT,CREATE_DATE,ADDRESS,PRICE,INTRODUCTION,ACTIVITY_PIC,STATUS,RENT_STRING,ROW_NUM,COLUMNS_NUM,AVAILABLE,RESERVATION_ALL,COMPANY_NO,ACT_TYPE_NO) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String INSERT_STMT_NO_PIC = "INSERT INTO APE.ACTIVITY(ACTIVITY_NAME,START_DATE,CLOSE_DATE,DAY_COUNT,ACTIVITY_HOURS,RENT_COUNT,CREATE_DATE,ADDRESS,PRICE,INTRODUCTION,STATUS,RENT_STRING,ROW_NUM,COLUMNS_NUM,AVAILABLE,RESERVATION_ALL,COMPANY_NO,ACT_TYPE_NO) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT ACTIVITY_NO,ACTIVITY_NAME,START_DATE,CLOSE_DATE,DAY_COUNT,ACTIVITY_HOURS,RENT_COUNT,CREATE_DATE,ADDRESS,PRICE,INTRODUCTION,ACTIVITY_PIC,STATUS,RENT_STRING,ROW_NUM,COLUMNS_NUM,AVAILABLE,RESERVATION_ALL,COMPANY_NO,ACT_TYPE_NO FROM APE.ACTIVITY ORDER BY ACTIVITY_NO";
	private static final String GET_ONE_STMT = "SELECT ACTIVITY_NO,ACTIVITY_NAME,START_DATE,CLOSE_DATE,DAY_COUNT,ACTIVITY_HOURS,RENT_COUNT,CREATE_DATE,ADDRESS,PRICE,INTRODUCTION,ACTIVITY_PIC,STATUS,RENT_STRING,ROW_NUM,COLUMNS_NUM,AVAILABLE,RESERVATION_ALL,COMPANY_NO,ACT_TYPE_NO FROM APE.ACTIVITY WHERE ACTIVITY_NO = ?";
	private static final String GET_ACTIVITY_BY_STATUS = "SELECT ACTIVITY_NO,ACTIVITY_NAME,START_DATE,CLOSE_DATE,DAY_COUNT,ACTIVITY_HOURS,RENT_COUNT,CREATE_DATE,ADDRESS,PRICE,INTRODUCTION,ACTIVITY_PIC,STATUS,RENT_STRING,ROW_NUM,COLUMNS_NUM,AVAILABLE,RESERVATION_ALL,COMPANY_NO,ACT_TYPE_NO FROM APE.ACTIVITY WHERE STATUS = ?";
	private static final String UPDATE = "UPDATE APE.ACTIVITY SET ACTIVITY_NAME =? ,START_DATE = ?,CLOSE_DATE = ?,DAY_COUNT = ?,ACTIVITY_HOURS = ?,RENT_COUNT = ?,ADDRESS = ?,PRICE = ?,INTRODUCTION = ?,ACTIVITY_PIC = ?,STATUS = ?,RENT_STRING = ?,ROW_NUM = ?,COLUMNS_NUM = ?,AVAILABLE = ?,RESERVATION_ALL = ?,COMPANY_NO = ?,ACT_TYPE_NO = ? WHERE ACTIVITY_NO = ?";
	private static final String UPDATE_NO_PIC = "UPDATE APE.ACTIVITY SET ACTIVITY_NAME =? ,START_DATE = ?,CLOSE_DATE = ?,DAY_COUNT = ?,ACTIVITY_HOURS = ?,RENT_COUNT = ?,ADDRESS = ?,PRICE = ?,INTRODUCTION = ?,STATUS = ?,RENT_STRING = ?,ROW_NUM = ?,COLUMNS_NUM = ?,AVAILABLE = ?,RESERVATION_ALL = ?,COMPANY_NO = ?,ACT_TYPE_NO = ? WHERE ACTIVITY_NO = ?";
	private static final String UPDATE_RESERVATION = "UPDATE APE.ACTIVITY SET RESERVATION_ALL = ? WHERE ACTIVITY_NO = ?";
	private static final String GET_MY_ACTIVITY = "SELECT ACTIVITY_NO,ACTIVITY_NAME,START_DATE,CLOSE_DATE,DAY_COUNT,ACTIVITY_HOURS,RENT_COUNT,CREATE_DATE,ADDRESS,PRICE,INTRODUCTION,ACTIVITY_PIC,STATUS,RENT_STRING,ROW_NUM,COLUMNS_NUM,AVAILABLE,RESERVATION_ALL,ACT_TYPE_NO FROM APE.ACTIVITY WHERE COMPANY_NO = ?";
	private static final String UPDATE_STATUS = "UPDATE ACTIVITY SET STATUS = ? WHERE ACTIVITY_NO =?";
	private static final String GET_ONE_BY_SERVICE = "SELECT ACTIVITY_NO,ACTIVITY_NAME,START_DATE,CLOSE_DATE,DAY_COUNT,ACTIVITY_HOURS,RENT_COUNT,CREATE_DATE,ADDRESS,PRICE,INTRODUCTION,ACTIVITY_PIC,STATUS,RENT_STRING,ROW_NUM,COLUMNS_NUM,AVAILABLE,RESERVATION_ALL,ACT_TYPE_NO FROM APE.ACTIVITY WHERE  ACT_TYPE_NO = ? and STATUS='a1'";
	
	@Override
	public int insert(ActivityVO activityVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		int nextActivityNo = 0;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String cols[] = { "ACTIVITY_NO" };
			pstmt = con.prepareStatement(INSERT_STMT, cols);

			pstmt.setString(1, activityVO.getActivityName());
			pstmt.setDate(2, activityVO.getStartDate());
			pstmt.setDate(3, activityVO.getCloseDate());
			pstmt.setInt(4, activityVO.getDayCount());
			pstmt.setString(5, activityVO.getActivityHours());
			pstmt.setInt(6, activityVO.getRentCount());
			pstmt.setTimestamp(7, activityVO.getCreateDate());
			pstmt.setString(8, activityVO.getAddress());
			pstmt.setInt(9, activityVO.getPrice());
			pstmt.setString(10, activityVO.getIntroduction());
			pstmt.setBytes(11, activityVO.getActivityPic());
			pstmt.setString(12, activityVO.getStatus());
			pstmt.setString(13, activityVO.getRentString());
			pstmt.setString(14, activityVO.getRowNum());
			pstmt.setString(15, activityVO.getColumnsNum());
			pstmt.setString(16, activityVO.getAvailable());
			pstmt.setString(17, activityVO.getReservationAll());
			pstmt.setInt(18, activityVO.getCompanyNo());
			pstmt.setInt(19, activityVO.getActivityTypeNo());

			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				nextActivityNo = rs.getInt(1);
				System.out.println("自增主鍵值 = " + nextActivityNo + "(剛新增成功的訂單編號)");
				return nextActivityNo;
			} else {
				System.out.println("未取得自增主鍵值");
			}

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
		return nextActivityNo;

	}

	@Override
	public void update(ActivityVO activityVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, activityVO.getActivityName());
			pstmt.setDate(2, activityVO.getStartDate());
			pstmt.setDate(3, activityVO.getCloseDate());
			pstmt.setInt(4, activityVO.getDayCount());
			pstmt.setString(5, activityVO.getActivityHours());
			pstmt.setInt(6, activityVO.getRentCount());
			pstmt.setString(7, activityVO.getAddress());
			pstmt.setInt(8, activityVO.getPrice());
			pstmt.setString(9, activityVO.getIntroduction());
			pstmt.setBytes(10, activityVO.getActivityPic());
			pstmt.setString(11, activityVO.getStatus());
			pstmt.setString(12, activityVO.getRentString());
			pstmt.setString(13, activityVO.getRowNum());
			pstmt.setString(14, activityVO.getColumnsNum());
			pstmt.setString(15, activityVO.getAvailable());
			pstmt.setString(16, activityVO.getReservationAll());
			pstmt.setInt(17, activityVO.getCompanyNo());
			pstmt.setInt(18, activityVO.getActivityTypeNo());
			pstmt.setInt(19, activityVO.getActivityNo());

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

	public void updateNoPic(ActivityVO activityVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_NO_PIC);

			pstmt.setString(1, activityVO.getActivityName());
			pstmt.setDate(2, activityVO.getStartDate());
			pstmt.setDate(3, activityVO.getCloseDate());
			pstmt.setInt(4, activityVO.getDayCount());
			pstmt.setString(5, activityVO.getActivityHours());
			pstmt.setInt(6, activityVO.getRentCount());
			pstmt.setString(7, activityVO.getAddress());
			pstmt.setInt(8, activityVO.getPrice());
			pstmt.setString(9, activityVO.getIntroduction());
			pstmt.setString(10, activityVO.getStatus());
			pstmt.setString(11, activityVO.getRentString());
			pstmt.setString(12, activityVO.getRowNum());
			pstmt.setString(13, activityVO.getColumnsNum());
			pstmt.setString(14, activityVO.getAvailable());
			pstmt.setString(15, activityVO.getReservationAll());
			pstmt.setInt(16, activityVO.getCompanyNo());
			pstmt.setInt(17, activityVO.getActivityTypeNo());
			pstmt.setInt(18, activityVO.getActivityNo());

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
	public ActivityVO findByPrimaryKey(Integer activityNo) {

		ActivityVO activityVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, activityNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				activityVO = new ActivityVO();
				activityVO.setActivityNo(rs.getInt("ACTIVITY_NO"));
				activityVO.setActivityName(rs.getString("ACTIVITY_NAME"));
				activityVO.setStartDate(rs.getDate("START_DATE"));
				activityVO.setCloseDate(rs.getDate("CLOSE_DATE"));
				activityVO.setDayCount(rs.getInt("DAY_COUNT"));
				activityVO.setActivityHours(rs.getString("ACTIVITY_HOURS"));
				activityVO.setRentCount(rs.getInt("RENT_COUNT"));
				activityVO.setCreateDate(rs.getTimestamp("CREATE_DATE"));
				activityVO.setAddress(rs.getString("ADDRESS"));
				activityVO.setPrice(rs.getInt("PRICE"));
				activityVO.setIntroduction(rs.getString("INTRODUCTION"));
				activityVO.setActivityPic(rs.getBytes("ACTIVITY_PIC"));
				activityVO.setStatus(rs.getString("STATUS"));
				activityVO.setRentString(rs.getString("RENT_STRING"));
				activityVO.setRowNum(rs.getString("ROW_NUM"));
				activityVO.setColumnsNum(rs.getString("COLUMNS_NUM"));
				activityVO.setAvailable(rs.getString("AVAILABLE"));
				activityVO.setReservationAll(rs.getString("RESERVATION_ALL"));
				activityVO.setCompanyNo(rs.getInt("COMPANY_NO"));
				activityVO.setActivityTypeNo(rs.getInt("ACT_TYPE_NO"));
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
		return activityVO;
	}

	@Override
	public List<ActivityVO> getAll() {
		List<ActivityVO> list = new ArrayList<ActivityVO>();
		ActivityVO activityVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				activityVO = new ActivityVO();
				activityVO.setActivityNo(rs.getInt("ACTIVITY_NO"));
				activityVO.setActivityName(rs.getString("ACTIVITY_NAME"));
				activityVO.setStartDate(rs.getDate("START_DATE"));
				activityVO.setCloseDate(rs.getDate("CLOSE_DATE"));
				activityVO.setDayCount(rs.getInt("DAY_COUNT"));
				activityVO.setActivityHours(rs.getString("ACTIVITY_HOURS"));
				activityVO.setRentCount(rs.getInt("RENT_COUNT"));
				activityVO.setCreateDate(rs.getTimestamp("CREATE_DATE"));
				activityVO.setAddress(rs.getString("ADDRESS"));
				activityVO.setPrice(rs.getInt("PRICE"));
				activityVO.setIntroduction(rs.getString("INTRODUCTION"));
				activityVO.setActivityPic(rs.getBytes("ACTIVITY_PIC"));
				activityVO.setStatus(rs.getString("STATUS"));
				activityVO.setRentString(rs.getString("RENT_STRING"));
				activityVO.setRowNum(rs.getString("ROW_NUM"));
				activityVO.setColumnsNum(rs.getString("COLUMNS_NUM"));
				activityVO.setAvailable(rs.getString("AVAILABLE"));
				activityVO.setAvailable(rs.getString("RESERVATION_ALL"));
				activityVO.setCompanyNo(rs.getInt("COMPANY_NO"));
				activityVO.setActivityTypeNo(rs.getInt("ACT_TYPE_NO"));
				list.add(activityVO); // Store the row in the list
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

		ActivityJDBCDAO dao = new ActivityJDBCDAO();
		Date date = new Date(System.currentTimeMillis());
		Timestamp time = new Timestamp(date.getTime());

		// 新增
//		ActivityVO activityVO1 = new ActivityVO();
//		activityVO1.setActivityName("一起喝咖啡2");
//		activityVO1.setStartDate(java.sql.Date.valueOf("2021-12-01"));
//		activityVO1.setCloseDate(java.sql.Date.valueOf("2021-12-02"));
//		activityVO1.setDayCount(10);
//		activityVO1.setActivityHours("9am-16pm");
//		activityVO1.setRentCount(10);
//		activityVO1.setCreateDate(time);
//		activityVO1.setAddress("台東縣台東市博愛路478號");
//		activityVO1.setPrice(600);
//		activityVO1.setIntroduction("市集不單只有買賣，更是一個生活理念的實踐場所。");
//		activityVO1.setActivityPic(null);
//		activityVO1.setStatus("0");
//		activityVO1.setRentString(null);
//		activityVO1.setRowNum(null);
//		activityVO1.setColumnsNum(null);
//		activityVO1.setAvailable(null);
//		activityVO1.setReservationAll(null);
//		activityVO1.setCompanyNo(2);
//		activityVO1.setActivityTypeNo(2);
//		dao.insert(activityVO1);

		// 修改
//		ActivityVO activityVO2 = new ActivityVO();
//		activityVO2.setActivityNo(12);
//		activityVO2.setActivityName("一起喝咖啡");
//		activityVO2.setStartDate(java.sql.Date.valueOf("2021-11-01"));
//		activityVO2.setCloseDate(java.sql.Date.valueOf("2021-11-02"));
//		activityVO2.setDayCount(2);
//		activityVO2.setActivityHours("9am-16pm");
//		activityVO2.setRentCount(10);
//		activityVO2.setCreateDate(time);
//		activityVO2.setAddress("台東縣台東市博愛路471號");
//		activityVO2.setPrice(500);
//		activityVO2.setIntroduction("市集不單只有買賣，更是一個生活理念的實踐場所。");
//		activityVO2.setActivityPic(null);
//		activityVO2.setStatus("0");
//		activityVO2.setRentString(null);
//		activityVO2.setRowNum(null);
//		activityVO2.setColumnsNum(null);
//		activityVO2.setAvailable(null);
//		activityVO2.setReservationAll(null);
//		activityVO2.setCompanyNo(2);
//		activityVO2.setActivityTypeNo(8);
//		dao.update(activityVO2);
//		

		// 刪除
//		dao.delete();

		// 查詢
//		ActivityVO activityVO3 = dao.findByPrimaryKey(1);
//		System.out.print(activityVO3.getActivityNo() + ",");
//		System.out.print(activityVO3.getActivityName() + ",");
//		System.out.print(activityVO3.getStartDate() + ",");
//		System.out.print(activityVO3.getCloseDate() + ",");
//		System.out.print(activityVO3.getDayCount() + ",");
//		System.out.print(activityVO3.getActivityHours() + ",");
//		System.out.print(activityVO3.getRentCount() + ",");
//		System.out.print(activityVO3.getCreateDate() + ",");
//		System.out.print(activityVO3.getAddress() + ",");
//		System.out.print(activityVO3.getPrice() + ",");
//		System.out.print(activityVO3.getIntroduction() + ",");
//		System.out.print(activityVO3.getActivityPic() + ",");
//		System.out.print(activityVO3.getStatus()+ ",");
//		System.out.print(activityVO3.getRentString()+ ",");
//		System.out.print(activityVO3.getRowNum()+ ",");
//		System.out.print(activityVO3.getColumnsNum()+ ",");
//		System.out.print(activityVO3.getAvailable()+ ",");
//		System.out.print(activityVO3.getReservationAll()+ ",");
//		System.out.print(activityVO3.getCompanyNo() + ",");
//		System.out.println(activityVO3.getActivityTypeNo());
//		System.out.println("---------------------");

		// 查詢
//		List<ActivityVO> list = dao.getAll();
//		for (ActivityVO aActivityVO : list) {
//			System.out.print(aActivityVO.getActivityNo() + ",");
//			System.out.print(aActivityVO.getActivityName() + ",");
//			System.out.print(aActivityVO.getStartDate() + ",");
//			System.out.print(aActivityVO.getCloseDate() + ",");
//			System.out.print(aActivityVO.getDayCount() + ",");
//			System.out.print(aActivityVO.getActivityHours() + ",");
//			System.out.print(aActivityVO.getRentCount() + ",");
//			System.out.print(aActivityVO.getCreateDate() + ",");
//			System.out.print(aActivityVO.getAddress() + ",");
//			System.out.print(aActivityVO.getPrice() + ",");
//			System.out.print(aActivityVO.getintroduction() + ",");
//			System.out.print(aActivityVO.getActivityPic() + ",");
//			System.out.print(aActivityVO.getStatus()+ ",");
//			System.out.print(aActivityVO.getRentString()+ ",");
//			System.out.print(aActivityVO.getRowNum()+ ",");
//			System.out.print(aActivityVO.getColumnsNum()+ ",");
//			System.out.print(aActivityVO.getAvailable()+ ",");
//			System.out.print(aActivityVO.getReservationAll()+ ",");
//			System.out.print(aActivityVO.getCompanyNo() + ",");
//			System.out.print(aActivityVO.getActivityTypeNo());
//			System.out.println();
//		}
	}

	@Override
	public int insertNoPic(ActivityVO activityVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int nextActivityNo = 0;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String cols[] = { "ACTIVITY_NO" };
			pstmt = con.prepareStatement(INSERT_STMT_NO_PIC, cols);

			pstmt.setString(1, activityVO.getActivityName());
			pstmt.setDate(2, activityVO.getStartDate());
			pstmt.setDate(3, activityVO.getCloseDate());
			pstmt.setInt(4, activityVO.getDayCount());
			pstmt.setString(5, activityVO.getActivityHours());
			pstmt.setInt(6, activityVO.getRentCount());
			pstmt.setTimestamp(7, activityVO.getCreateDate());
			pstmt.setString(8, activityVO.getAddress());
			pstmt.setInt(9, activityVO.getPrice());
			pstmt.setString(10, activityVO.getIntroduction());
			pstmt.setString(11, activityVO.getStatus());
			pstmt.setString(12, activityVO.getRentString());
			pstmt.setString(13, activityVO.getRowNum());
			pstmt.setString(14, activityVO.getColumnsNum());
			pstmt.setString(15, activityVO.getAvailable());
			pstmt.setString(16, activityVO.getReservationAll());
			pstmt.setInt(17, activityVO.getCompanyNo());
			pstmt.setInt(18, activityVO.getActivityTypeNo());

			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				nextActivityNo = rs.getInt(1);
				System.out.println("自增主鍵值 = " + nextActivityNo + "(剛新增成功的訂單編號)");
				return nextActivityNo;
			} else {
				System.out.println("未取得自增主鍵值");
			}

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
		return nextActivityNo;
	}

	public List<ActivityVO> getMyActivity(Integer companyNo) {
		List<ActivityVO> list = new ArrayList<ActivityVO>();
		ActivityVO activityVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_MY_ACTIVITY);

			pstmt.setInt(1, companyNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				activityVO = new ActivityVO();
				activityVO.setActivityNo(rs.getInt("ACTIVITY_NO"));
				activityVO.setActivityName(rs.getString("ACTIVITY_NAME"));
				activityVO.setStartDate(rs.getDate("START_DATE"));
				activityVO.setCloseDate(rs.getDate("CLOSE_DATE"));
				activityVO.setDayCount(rs.getInt("DAY_COUNT"));
				activityVO.setActivityHours(rs.getString("ACTIVITY_HOURS"));
				activityVO.setRentCount(rs.getInt("RENT_COUNT"));
				activityVO.setCreateDate(rs.getTimestamp("CREATE_DATE"));
				activityVO.setAddress(rs.getString("ADDRESS"));
				activityVO.setPrice(rs.getInt("PRICE"));
				activityVO.setIntroduction(rs.getString("INTRODUCTION"));
				activityVO.setActivityPic(rs.getBytes("ACTIVITY_PIC"));
				activityVO.setStatus(rs.getString("STATUS"));
				activityVO.setRentString(rs.getString("RENT_STRING"));
				activityVO.setRowNum(rs.getString("ROW_NUM"));
				activityVO.setColumnsNum(rs.getString("COLUMNS_NUM"));
				activityVO.setAvailable(rs.getString("AVAILABLE"));
				activityVO.setAvailable(rs.getString("RESERVATION_ALL"));
//				activityVO.setCompanyNo(rs.getInt("COMPANY_NO"));
				activityVO.setActivityTypeNo(rs.getInt("ACT_TYPE_NO"));
				list.add(activityVO); // Store the row in the list
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
	public List<ActivityVO> getActivityByStatus(String status) {
		List<ActivityVO> list = new ArrayList<ActivityVO>();
		ActivityVO activityVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ACTIVITY_BY_STATUS);
			pstmt.setString(1, status);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				activityVO = new ActivityVO();
				activityVO.setActivityNo(rs.getInt("ACTIVITY_NO"));
				activityVO.setActivityName(rs.getString("ACTIVITY_NAME"));
				activityVO.setStartDate(rs.getDate("START_DATE"));
				activityVO.setCloseDate(rs.getDate("CLOSE_DATE"));
				activityVO.setDayCount(rs.getInt("DAY_COUNT"));
				activityVO.setActivityHours(rs.getString("ACTIVITY_HOURS"));
				activityVO.setRentCount(rs.getInt("RENT_COUNT"));
				activityVO.setCreateDate(rs.getTimestamp("CREATE_DATE"));
				activityVO.setAddress(rs.getString("ADDRESS"));
				activityVO.setPrice(rs.getInt("PRICE"));
				activityVO.setIntroduction(rs.getString("INTRODUCTION"));
				activityVO.setActivityPic(rs.getBytes("ACTIVITY_PIC"));
				activityVO.setStatus(rs.getString("STATUS"));
				activityVO.setRentString(rs.getString("RENT_STRING"));
				activityVO.setRowNum(rs.getString("ROW_NUM"));
				activityVO.setColumnsNum(rs.getString("COLUMNS_NUM"));
				activityVO.setAvailable(rs.getString("AVAILABLE"));
				activityVO.setAvailable(rs.getString("RESERVATION_ALL"));
				activityVO.setCompanyNo(rs.getInt("COMPANY_NO"));
				activityVO.setActivityTypeNo(rs.getInt("ACT_TYPE_NO"));
				activityVO.setStatus(rs.getString("STATUS"));
				list.add(activityVO); // Store the row in the list
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
	public void updateReservationAll(String reservationAll, Integer activityNo, Connection con) {
		PreparedStatement pstmt = null;

		try {


			pstmt = con.prepareStatement(UPDATE_RESERVATION);

			pstmt.setString(1, reservationAll);
			pstmt.setInt(2, activityNo);

			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			// Handle any driver errors
	
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3.設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-ActivityJDBCDAO");
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
	public void updateStatus(ActivityVO activityVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STATUS);
			
			pstmt.setString(1, activityVO.getStatus());
			pstmt.setInt(2, activityVO.getActivityNo());
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<ActivityVO> getActivity(Integer activityTypeNo) {
		List<ActivityVO> list = new ArrayList<ActivityVO>();
		ActivityVO activityVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_BY_SERVICE);
	
				pstmt.setInt(1, activityTypeNo);
	
				rs = pstmt.executeQuery();
	
				while (rs.next()) {
	
					activityVO = new ActivityVO();
					activityVO.setActivityNo(rs.getInt("ACTIVITY_NO"));
					activityVO.setActivityName(rs.getString("ACTIVITY_NAME"));
					activityVO.setStartDate(rs.getDate("START_DATE"));
					activityVO.setCloseDate(rs.getDate("CLOSE_DATE"));
					activityVO.setDayCount(rs.getInt("DAY_COUNT"));
					activityVO.setActivityHours(rs.getString("ACTIVITY_HOURS"));
					activityVO.setRentCount(rs.getInt("RENT_COUNT"));
					activityVO.setCreateDate(rs.getTimestamp("CREATE_DATE"));
					activityVO.setAddress(rs.getString("ADDRESS"));
					activityVO.setPrice(rs.getInt("PRICE"));
					activityVO.setIntroduction(rs.getString("INTRODUCTION"));
					activityVO.setActivityPic(rs.getBytes("ACTIVITY_PIC"));
					activityVO.setStatus(rs.getString("STATUS"));
					activityVO.setRentString(rs.getString("RENT_STRING"));
					activityVO.setRowNum(rs.getString("ROW_NUM"));
					activityVO.setColumnsNum(rs.getString("COLUMNS_NUM"));
					activityVO.setAvailable(rs.getString("AVAILABLE"));
					activityVO.setAvailable(rs.getString("RESERVATION_ALL"));
					activityVO.setCompanyNo(rs.getInt("COMPANY_NO"));
//					activityVO.setActivityTypeNo(rs.getInt("ACT_TYPE_NO"));
					list.add(activityVO); // Store the row in the list
				}

		
		} catch (Exception se) {
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
}
