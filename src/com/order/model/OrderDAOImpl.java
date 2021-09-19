package com.order.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.order_content.model.OrderContentDAOImpl;
import com.order_content.model.OrderContentVO;

import util.Util;

public class OrderDAOImpl implements OrderDAO {
	public static final String INSERT_Order = "INSERT INTO `ORDER`( USER_NO) VALUES (?)";
	public static final String DELETE_Order = "DELETE FROM ORDER WHERE oRDER_SERIAL_NO= ? ";
//	public static final String UPDATE_Order = "UPDATE ORDER SET oRDER_SERIAL_NO=? ,oRDER_DETAIL_NO= ?,uSER_NO=?, cOMPANY_NO=?,dELIVERY_CONDITION=?,iNVOICE=?,aRRIVAL=?";
	public static final String GET_Order = "SELECT oRDER_SERIAL_NO ,oRDER_DETAIL_NO,uSER_NO, cOMPANY_NO,dELIVERY_CONDITION,iNVOICE,aRRIVAL FROM ORDER WHERE oRDER_SERIAL_NO=? ";
	public static final String GET_ALL = "SELECT * FROM ORDER";

	@Override
	public void insert(OrderVO orderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_Order);

			pstmt.setInt(1, orderVO.getOrder_serial_no());

			pstmt.setInt(2, orderVO.getUser_no());


			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}

		}

	}

//	@Override
//	public void update(OrderVO orderVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
//			pstmt = con.prepareStatement(UPDATE_Order);
//
//			pstmt.setInt(1, orderVO.getOrder_serial_no());
//
//			pstmt.setInt(3, orderVO.getUser_no());
//
//
//			pstmt.executeUpdate();
//
//		} catch (SQLException se) {
//			se.printStackTrace();
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace();
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (SQLException se) {
//					se.printStackTrace();
//				}
//			}
//
//		}
//
//	}

	@Override
	public void delete(Integer oRDER_SERIAL_NO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE_Order);

			pstmt.setInt(1, oRDER_SERIAL_NO);

			pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}

		}

	}

	@Override
	public OrderVO findByoRDER_SERIAL_NO(Integer oRDER_SERIAL_NO) {
		OrderVO orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_Order);

			pstmt.setInt(1, oRDER_SERIAL_NO);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				orderVO = new OrderVO();
				orderVO.setOrder_serial_no(rs.getInt("order_serial_no"));

				orderVO.setUser_no(rs.getInt("user_no"));


			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}

		}
		return orderVO;
	}

	@Override
	public List<OrderVO> getAll() {
		List<OrderVO> list = new ArrayList<OrderVO>();

		OrderVO orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				orderVO = new OrderVO();
				orderVO.setOrder_serial_no(rs.getInt("order_serial_no"));

				orderVO.setUser_no(rs.getInt("user_no"));


				list.add(orderVO);

			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}

		}

		return list;
	}

	// 新增訂單及訂單明細
	@Override
	public void insert1(OrderVO orderVO, List<OrderContentVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			String cols[] = {"ORDER_SERIAL_NO"};
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);		
			pstmt = con.prepareStatement(INSERT_Order, cols);
		
//			pstmt.setInt(1,orderVO.getOrder_serial_no());
			pstmt.setInt(1, orderVO.getUser_no());	
						
			pstmt.executeUpdate();
			
			//取得自增主鍵
			
			ResultSet rs = pstmt.getGeneratedKeys();
			System.out.println(rs);
			
			//新增訂單明細
			if (rs.next()) {
				int order_serial_no = rs.getInt(1);
				System.out.println(order_serial_no);
				OrderContentDAOImpl dao = new OrderContentDAOImpl();
				
				for(OrderContentVO orderContentVO: list) {
					orderContentVO.setOrder_serial_no(new Integer(order_serial_no));
					dao.insert(orderContentVO);
				}
				
			}
			
			
			
			
		} catch (SQLException se) {
			
			se.printStackTrace();
		} finally {
			
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}

		}
		
		
	}

	@Override
	public void update(OrderVO orderVO) {
		// TODO Auto-generated method stub
		
	}

}
