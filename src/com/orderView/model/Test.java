package com.orderView.model;

import java.util.List;

public class Test {

	public static void main(String[] args) {
		OrderViewDAOImpl dao = new OrderViewDAOImpl();
		
		List<OrderViewVO> list = dao.getAll();
		 for(OrderViewVO orderViewVO : list) {
			 System.out.println(orderViewVO);
		 }
	}

}
