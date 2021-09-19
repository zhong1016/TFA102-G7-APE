package com.orderView.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.order.model.OrderVO;

import util.Util;

public class OrderViewDAOImpl implements OrderViewDAO {

	public static final String GET_ALL = "select ORDER_DETAIL_NO, ord.PRODUCT_NO, ord.PRODUCT_PRICE, PRODUCT_AMOUNT, PRODUCT_TOTAL, ORDER_SERIAL_NO, RECEIVE_NAME ,RECEIVE_PHONE_NUM ,\r\n" + 
			"RECEIVE_ADDRESS,  `STATUS` , pr.PRODUCT_NAME , pr.USER_NO from order_content ord join product pr on ord.product_no = pr.product_no order by ORDER_DETAIL_NO ";

	
	
	
	@Override
	public List<OrderViewVO> getAll() {
		List<OrderViewVO> list = new ArrayList<OrderViewVO>();

		OrderViewVO orderViewVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				orderViewVO = new OrderViewVO();
				orderViewVO.setOrder_detail_no(rs.getInt("order_detail_no"));
				orderViewVO.setProduct_no(rs.getInt("product_no"));
				orderViewVO.setProduct_price(rs.getInt("product_price"));
				orderViewVO.setProduct_amount(rs.getInt("product_amount"));
				orderViewVO.setProduct_total(rs.getInt("product_total"));
				orderViewVO.setOrder_serial_no(rs.getInt("ORDER_SERIAL_NO"));
				orderViewVO.setReceive_name(rs.getString("RECEIVE_NAME"));
				orderViewVO.setReceive_address(rs.getString("RECEIVE_ADDRESS"));
				orderViewVO.setReceive_phone_num(rs.getString("RECEIVE_PHONE_NUM"));
				orderViewVO.setStatus(rs.getString("STATUS"));
				orderViewVO.setProduct_name(rs.getString("PRODUCT_NAME"));
				orderViewVO.setUser_no(rs.getInt("USER_NO"));
				list.add(orderViewVO);
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

}
