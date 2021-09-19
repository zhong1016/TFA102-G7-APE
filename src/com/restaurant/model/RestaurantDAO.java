package com.restaurant.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.restaurant.model.RestaurantVO;


//import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_Restaurant;

public class RestaurantDAO implements RestaurantDAO_interface {

	// �@�����ε{����,�w��@�Ӹ�Ʈw ,�@�Τ@��DataSource�Y�i
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/APE");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = 
		"INSERT INTO restaurant (restaurant_name,restaurant_level,restaurant_add,restaurant_num,restaurant_type, location_no) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT restaurant_no,restaurant_name,restaurant_level,restaurant_add,restaurant_num,restaurant_type, location_no FROM restaurant order by restaurant_no";
	private static final String GET_ONE_STMT = 
		"SELECT restaurant_no,restaurant_name,restaurant_level,restaurant_add,restaurant_num,restaurant_type, location_no FROM restaurant where restaurant_no = ?";
	private static final String DELETE = 
		"DELETE FROM restaurant where restaurant_no = ?";
	private static final String UPDATE = 
		"UPDATE restaurant set restaurant_name=?, restaurant_level=?, restaurant_add=?, restaurant_num=?, restaurant_type=? , location_no=? where restaurant_no = ?";
	
	@Override
	public void insert(RestaurantVO restaurantVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, restaurantVO.getRestaurantname());
			pstmt.setString(2, restaurantVO.getRestaurantlevel());
			pstmt.setString(3, restaurantVO.getRestaurantadd());
			pstmt.setString(4, restaurantVO.getRestaurantnum());
			pstmt.setString(5, restaurantVO.getRestauranttype());
			pstmt.setInt(6, restaurantVO.getLocationno());


			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void update(RestaurantVO restaurantVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, restaurantVO.getRestaurantname());
			pstmt.setString(2, restaurantVO.getRestaurantlevel());
			pstmt.setString(3, restaurantVO.getRestaurantadd());
			pstmt.setString(4, restaurantVO.getRestaurantnum());
			pstmt.setString(5, restaurantVO.getRestauranttype());
			pstmt.setInt(6, restaurantVO.getLocationno());
			pstmt.setInt(7, restaurantVO.getRestaurantno());
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void delete(Integer restaurantno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, restaurantno);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public RestaurantVO findByPrimaryKey(Integer restaurantno) {

		RestaurantVO restaurantVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, restaurantno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// restaurantVO �]�٬� Domain objects
				restaurantVO = new RestaurantVO();
				restaurantVO.setRestaurantno(rs.getInt("restaurant_no"));
				restaurantVO.setRestaurantname(rs.getString("restaurant_name"));
				restaurantVO.setRestaurantlevel(rs.getString("restaurant_level"));
				restaurantVO.setRestaurantadd(rs.getString("restaurant_add"));
				restaurantVO.setRestaurantnum(rs.getString("restaurant_num"));
				restaurantVO.setRestauranttype(rs.getString("restaurant_type"));
				restaurantVO.setLocationno(rs.getInt("location_no"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return restaurantVO;
	}

	@Override
	public List<RestaurantVO> getAll() {
		List<RestaurantVO> list = new ArrayList<RestaurantVO>();
		RestaurantVO restaurantVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// restaurantVO �]�٬� Domain objects
				restaurantVO = new RestaurantVO();
				restaurantVO.setRestaurantno(rs.getInt("restaurant_no"));
				restaurantVO.setRestaurantname(rs.getString("restaurant_name"));
				restaurantVO.setRestaurantlevel(rs.getString("restaurant_level"));
				restaurantVO.setRestaurantadd(rs.getString("restaurant_add"));
				restaurantVO.setRestaurantnum(rs.getString("restaurant_num"));
				restaurantVO.setRestauranttype(rs.getString("restaurant_type"));
				restaurantVO.setLocationno(rs.getInt("location_no"));
				list.add(restaurantVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	
//	public List<RestaurantVO> getAll(Map<String, String[]> map){
//		List<RestaurantVO> list = new ArrayList<RestaurantVO>();
//		RestaurantVO restaurantVO = null;
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = ds.getConnection();
//			String finalSQL = "select * from restaurant "
//			          + jdbcUtil_CompositeQuery_Restaurant.get_WhereCondition(map)
//			          + "order by restaurantno";
//				pstmt = con.prepareStatement(finalSQL);
//				System.out.println("����finalSQL(by DAO) = "+finalSQL);
//				rs = pstmt.executeQuery();
//		
//				while (rs.next()) {
//					restaurantVO = new RestaurantVO();
//					restaurantVO.setRestaurantno(rs.getInt("restaurant_no"));
//					restaurantVO.setRestaurantname(rs.getString("restaurant_name"));
//					restaurantVO.setRestaurantlevel(rs.getString("restaurant_level"));
//					restaurantVO.setRestaurantadd(rs.getString("restaurant_add"));
//					restaurantVO.setRestaurantnum(rs.getString("restaurant_num"));
//					restaurantVO.setRestauranttype(rs.getString("restaurant_type"));
//					restaurantVO.setLocationno(rs.getInt("location_no"));
//					list.add(restaurantVO); // Store the row in the List
//				}
//		
//				// Handle any SQL errors
//			} catch (SQLException se) {
//				throw new RuntimeException("A database error occured. "
//						+ se.getMessage());
//			} finally {
//				if (rs != null) {
//					try {
//						rs.close();
//					} catch (SQLException se) {
//						se.printStackTrace(System.err);
//					}
//				}
//				if (pstmt != null) {
//					try {
//						pstmt.close();
//					} catch (SQLException se) {
//						se.printStackTrace(System.err);
//					}
//				}
//				if (con != null) {
//					try {
//						con.close();
//					} catch (Exception e) {
//						e.printStackTrace(System.err);
//					}
//				}
//			}
//			return list;
//	}

}