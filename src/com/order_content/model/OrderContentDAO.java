package com.order_content.model;

import java.util.List;


public interface OrderContentDAO {
	void insert(OrderContentVO orderContentVO);
	void update(OrderContentVO orderContentVO);
	
//	OrderContentVO findByOrder_detail_no(Integer oRDER_DETAIL_NO);
	List<OrderContentVO> getAll();
        
       
}
