package com.activity.model;

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

public class ActivityJNDIDAO implements ActivityDAO_interface {
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/APE");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
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

			con = ds.getConnection();
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
				System.out.println("自增主鍵值 = " + nextActivityNo + "(剛新增成功的活動編號)");
				return nextActivityNo;
			} else {
				System.out.println("未取得自增主鍵值");
			}

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

			con = ds.getConnection();
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

			con = ds.getConnection();
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

			con = ds.getConnection();
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

			con = ds.getConnection();
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
	public int insertNoPic(ActivityVO activityVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int nextActivityNo = 0;
		try {

			con = ds.getConnection();
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

			con = ds.getConnection();
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

			con = ds.getConnection();
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STATUS);

			pstmt.setString(1, activityVO.getStatus());
			pstmt.setInt(2, activityVO.getActivityNo());
			pstmt.executeUpdate();
		
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
	public List<ActivityVO> getActivity(Integer activityTypeNo) {
		List<ActivityVO> list = new ArrayList<ActivityVO>();
		ActivityVO activityVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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
//				activityVO.setCompanyNo(rs.getInt("COMPANY_NO"));
//				activityVO.setActivityTypeNo(rs.getInt("ACT_TYPE_NO"));
				list.add(activityVO); // Store the row in the list
			}

		
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
}
