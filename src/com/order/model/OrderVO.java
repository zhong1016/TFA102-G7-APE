package com.order.model;

import java.io.Serializable;
import java.sql.Date;

public class OrderVO implements Serializable {

	private Integer order_serial_no;

	private Integer user_no;


	Integer getOrder_serial_no() {
		return order_serial_no;
	}

	public void setOrder_serial_no(Integer order_serial_no) {
		this.order_serial_no = order_serial_no;
	}

	public Integer getUser_no() {
		return user_no;
	}

	public void setUser_no(Integer user_no) {
		this.user_no = user_no;
	}


};
