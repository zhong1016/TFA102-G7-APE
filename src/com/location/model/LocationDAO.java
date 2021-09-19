package com.location.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.restaurant.model.RestaurantVO;

public class LocationDAO implements LocationDAO_interface {

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

	private static final String INSERT_STMT = "INSERT INTO location (location_name,location_add) VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT location_no , location_name,location_add FROM location";
	private static final String GET_ONE_STMT = "SELECT location_no , location_name,location_add FROM location where location_no = ?";
	private static final String GET_RESTAURANTS_BYLOCATIONNO_STMT = "SELECT restaurant_no,restaurant_name,restaurant_level,restaurant_add,restaurant_num,restaurant_type,location_no FROM restaurant where location_no = ? order by restaurant_no";
	
	private static final String DELETE_RESTAURANTS = "DELETE FROM restaurant where location_no = ?";
	private static final String DELETE_LOCATION = "DELETE FROM location where location_no = ?";	
	
	private static final String UPDATE = "UPDATE location set location_name=?, location_add=? where location_no = ?";

	@Override
	public void insert(LocationVO locationVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, locationVO.getLocationname());
			pstmt.setString(2, locationVO.getLocationadd());

pstmt.executeUpdate("set auto_increment_offset=10;");
pstmt.executeUpdate("set auto_increment_increment=10;");
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
	public void update(LocationVO locationVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, locationVO.getLocationname());
			pstmt.setString(2, locationVO.getLocationadd());
			pstmt.setInt(3, locationVO.getLocationno());

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
	public void delete(Integer locationno) {
		int updateCount_Restaurants = 0;

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();

			// 1���]�w�� pstm.executeUpdate()���e
			con.setAutoCommit(false);

			// ���R�����u
			pstmt = con.prepareStatement(DELETE_RESTAURANTS);
			pstmt.setInt(1, locationno);
			updateCount_Restaurants = pstmt.executeUpdate();
			// �A�R������
			pstmt = con.prepareStatement(DELETE_LOCATION);
			pstmt.setInt(1, locationno);
			pstmt.executeUpdate();

			// 2���]�w�� pstm.executeUpdate()����
			con.commit();
			con.setAutoCommit(true);
			
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public LocationVO findByPrimaryKey(Integer locationno) {

		LocationVO locationVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, locationno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// deptVO �]�٬� Domain objects
				locationVO = new LocationVO();
				locationVO.setLocationno(rs.getInt("location_no"));
				locationVO.setLocationname(rs.getString("location_name"));
				locationVO.setLocationadd(rs.getString("location_add"));
			}

			// Handle any SQL errors
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
		return locationVO;
	}

	@Override
	public List<LocationVO> getAll() {
		List<LocationVO> list = new ArrayList<LocationVO>();
		LocationVO locationVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				locationVO = new LocationVO();
				locationVO.setLocationno(rs.getInt("location_no"));
				locationVO.setLocationname(rs.getString("location_name"));
				locationVO.setLocationadd(rs.getString("location_add"));
				list.add(locationVO); // Store the row in the list
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public Set<RestaurantVO> getRestaurantsByLocationno(Integer locationno) {
		Set<RestaurantVO> set = new LinkedHashSet<RestaurantVO>();
		RestaurantVO restaurantVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_RESTAURANTS_BYLOCATIONNO_STMT);
			pstmt.setInt(1, locationno);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				restaurantVO = new RestaurantVO();
				restaurantVO.setRestaurantno(rs.getInt("restaurant_no"));
				restaurantVO.setRestaurantname(rs.getString("restaurant_name"));
				restaurantVO.setRestaurantlevel(rs.getString("restaurant_level"));
				restaurantVO.setRestaurantadd(rs.getString("restaurant_add"));
				restaurantVO.setRestaurantnum(rs.getString("restaurant_num"));
				restaurantVO.setRestauranttype(rs.getString("restaurant_type"));
				restaurantVO.setLocationno(rs.getInt("location_no"));
				set.add(restaurantVO); // Store the row in the vector
			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return set;
	}
}