package com.order_content.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.Util;

public class OrderContentDAOImpl implements OrderContentDAO{

	public static final String INSERT_OrderContent = 
			"INSERT INTO ORDER_CONTENT (product_no ,product_price,product_amount,product_total,order_serial_no,receive_name,receive_phone_num,receive_address,status) VALUES (?,?,?,?,?,?,?,?,?)";
	
	public static final String UPDATE_OrderContent = 
			"UPDATE ORDER_CONTENT SET order_detail_no=? , product_no=? ,product_price=?, product_amount=?,ticket_credit=?,account_no=?";
	
	public static final String UPDATE_STATUS=
			"UPDATE ORDER_CONTENT SET STATUS=?  WHERE order_detail_no=?";
	
	
	
//	public static final String GET_OrderContent = 
//			"SELECT order_serial_no ,order_detail_no,user_no, company_no,delivery_condition,invoice,arrival from order_content where order_serial_no=? ";
	
	public static final String GET_ALL=
			"SELECT * FROM ORDER_CONTENT";
	
	
	
	
	
	
	
	
	@Override
	public void insert(OrderContentVO orderContentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
//			product_no ,product_price,product_amount,product_total,order_serial_no,receive_name,receive_phone_num,receive_address,status
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_OrderContent);

			pstmt.setInt(1,orderContentVO.getProduct_no());
			pstmt.setInt(2,orderContentVO.getProduct_price());
			pstmt.setInt(3,orderContentVO.getProduct_amount());
			pstmt.setInt(4,orderContentVO.getProduct_total());
			pstmt.setInt(5, orderContentVO.getOrder_serial_no());
			pstmt.setString(6,orderContentVO.getReceive_name());
			pstmt.setString(7,orderContentVO.getReceive_phone_num());
			pstmt.setString(8, orderContentVO.getReceive_address());
			pstmt.setString(9,"尚未處理");
			
			
			
			

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
	public void update(OrderContentVO orderContentVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
				pstmt = con.prepareStatement(UPDATE_STATUS);

				pstmt.setInt(2,orderContentVO.getOrder_detail_no());
				pstmt.setString(1, orderContentVO.getStatus());
				
				
				

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
//	public OrderContentVO findByOrder_detail_no(Integer oRDER_DETAIL_NO) {
//		OrderContentVO orderContentVO =null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
//			pstmt = con.prepareStatement(GET_OrderContent);
//
//			pstmt.setInt(1,oRDER_DETAIL_NO );
//			rs = pstmt.executeQuery();
//
//			
//			while(rs.next()) {
//				orderContentVO= new OrderContentVO();
//				orderContentVO.setOrder_detail_no(rs.getInt("order_detail_no"));
//				orderContentVO.setProduct_no(rs.getInt("product_no"));
//				orderContentVO.setProduct_price(rs.getInt("product_price"));
//				orderContentVO.setProduct_amount(rs.getInt("product_amount"));
//				
//			}
//		} catch (SQLException se) {
//			se.printStackTrace();
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace();
//				}
//			}
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
//			
//			
//			
//		return orderContentVO;
//	}

	@Override
	public List<OrderContentVO> getAll() {
		List<OrderContentVO> list=new ArrayList<OrderContentVO>();
		OrderContentVO orderContentVO =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);

			
			rs = pstmt.executeQuery();

			
			while(rs.next()) {
				orderContentVO= new OrderContentVO();
				orderContentVO.setOrder_detail_no(rs.getInt("order_detail_no"));
				orderContentVO.setProduct_no(rs.getInt("product_no"));
				orderContentVO.setOrder_serial_no(rs.getInt("order_serial_no"));
				orderContentVO.setProduct_price(rs.getInt("product_price"));
				orderContentVO.setProduct_amount(rs.getInt("product_amount"));
				orderContentVO.setReceive_name(rs.getString("receive_name"));
				orderContentVO.setReceive_phone_num(rs.getString("receive_phone_num"));
				orderContentVO.setReceive_address(rs.getString("receive_address"));
				orderContentVO.setStatus(rs.getString("status"));
				list.add(orderContentVO);
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
	public static void main(String[] args) {
		OrderContentDAOImpl dao = new OrderContentDAOImpl();
		System.out.println(dao.getAll());
		
	}
	
	
}
