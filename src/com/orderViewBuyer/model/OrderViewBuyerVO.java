package com.orderViewBuyer.model;

import java.io.Serializable;

public class OrderViewBuyerVO implements Serializable {
	private Integer order_detail_no;
	private Integer product_no;
	private Integer product_price;
	private Integer product_amount;
	private Integer product_total;
	private Integer order_serial_no;
	private String  receive_name;
	private String  receive_phone_num;
	private String  receive_address;
	private String  status;
	private Integer userNo;
	
	
	@Override
	public String toString() {
		return "OrderViewBuyerVO [order_detail_no=" + order_detail_no + ", product_no=" + product_no
				+ ", product_price=" + product_price + ", product_amount=" + product_amount + ", product_total="
				+ product_total + ", order_serial_no=" + order_serial_no + ", receive_name=" + receive_name
				+ ", receive_phone_num=" + receive_phone_num + ", receive_address=" + receive_address + ", status="
				+ status + ", userNo=" + userNo + "]";
	}
	public Integer getOrder_detail_no() {
		return order_detail_no;
	}
	public void setOrder_detail_no(Integer order_detail_no) {
		this.order_detail_no = order_detail_no;
	}
	public Integer getProduct_no() {
		return product_no;
	}
	public void setProduct_no(Integer product_no) {
		this.product_no = product_no;
	}
	public Integer getProduct_price() {
		return product_price;
	}
	public void setProduct_price(Integer product_price) {
		this.product_price = product_price;
	}
	public Integer getProduct_amount() {
		return product_amount;
	}
	public void setProduct_amount(Integer product_amount) {
		this.product_amount = product_amount;
	}
	public Integer getProduct_total() {
		return product_total;
	}
	public void setProduct_total(Integer product_total) {
		this.product_total = product_total;
	}
	public Integer getOrder_serial_no() {
		return order_serial_no;
	}
	public void setOrder_serial_no(Integer order_serial_no) {
		this.order_serial_no = order_serial_no;
	}
	public String getReceive_name() {
		return receive_name;
	}
	public void setReceive_name(String receive_name) {
		this.receive_name = receive_name;
	}
	public String getReceive_phone_num() {
		return receive_phone_num;
	}
	public void setReceive_phone_num(String receive_phone_num) {
		this.receive_phone_num = receive_phone_num;
	}
	public String getReceive_address() {
		return receive_address;
	}
	public void setReceive_address(String receive_address) {
		this.receive_address = receive_address;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getUserNo() {
		return userNo;
	}
	public void setUserNo(Integer userNo) {
		this.userNo = userNo;
	}
	
	
}
