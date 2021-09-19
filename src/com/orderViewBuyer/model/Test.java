package com.orderViewBuyer.model;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		OrderViewBuyerDAOImpl dao = new OrderViewBuyerDAOImpl();
		List<OrderViewBuyerVO> list = dao.getAll();
		for(OrderViewBuyerVO vo : list) {
			
			System.out.println(vo);
		}
		
	}

}
