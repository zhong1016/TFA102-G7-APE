package com.orderViewBuyer.model;

import java.util.List;

import com.orderView.model.OrderViewVO;

public interface OrderViewBuyerDAO {
	//依照會員編號搜尋訂單明細
	public List<OrderViewBuyerVO> getAll();
}
