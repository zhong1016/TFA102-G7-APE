package com.orderViewBuyer.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.orderView.model.OrderViewVO;

import util.Util;

public class OrderViewBuyerDAOImpl implements OrderViewBuyerDAO {
	public static final String GET_ALL=
			"select ORDER_DETAIL_NO, PRODUCT_NO, PRODUCT_PRICE, PRODUCT_AMOUNT, PRODUCT_TOTAL, orc.ORDER_SERIAL_NO, RECEIVE_NAME, RECEIVE_PHONE_NUM, RECEIVE_ADDRESS, `STATUS`, USER_NO from order_content orc join `ORDER` ord on orc.ORDER_SERIAL_NO = ord.ORDER_SERIAL_NO";
	
	@Override
	public List<OrderViewBuyerVO> getAll() {
		List<OrderViewBuyerVO> list = new ArrayList<OrderViewBuyerVO>();

		OrderViewBuyerVO orderViewBuyerVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				orderViewBuyerVO = new OrderViewBuyerVO();
				orderViewBuyerVO.setOrder_detail_no(rs.getInt("order_detail_no"));
				orderViewBuyerVO.setProduct_no(rs.getInt("product_no"));
				orderViewBuyerVO.setProduct_price(rs.getInt("product_price"));
				orderViewBuyerVO.setProduct_amount(rs.getInt("product_amount"));
				orderViewBuyerVO.setProduct_total(rs.getInt("product_total"));
				orderViewBuyerVO.setOrder_serial_no(rs.getInt("ORDER_SERIAL_NO"));
				orderViewBuyerVO.setReceive_name(rs.getString("RECEIVE_NAME"));
				orderViewBuyerVO.setReceive_address(rs.getString("RECEIVE_ADDRESS"));
				orderViewBuyerVO.setReceive_phone_num(rs.getString("RECEIVE_PHONE_NUM"));
				orderViewBuyerVO.setStatus(rs.getString("STATUS"));
				orderViewBuyerVO.setUserNo(rs.getInt("USER_NO"));
				list.add(orderViewBuyerVO);
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
