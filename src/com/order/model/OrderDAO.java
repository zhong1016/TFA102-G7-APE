package com.order.model;


import java.util.List;

import com.order_content.model.OrderContentVO;

public interface OrderDAO {
	void insert(OrderVO orderVO);
	void update(OrderVO orderVO);
	void delete(Integer oRDER_SERIAL_NO);
	OrderVO findByoRDER_SERIAL_NO(Integer oRDER_SERIAL_NO);
	List<OrderVO> getAll();
	
	//新增訂單及訂單明細
	void insert1(OrderVO orderVO, List<OrderContentVO> list);
	
}
