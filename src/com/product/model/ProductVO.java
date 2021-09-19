package com.product.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;

import oracle.sql.BLOB;

public class ProductVO implements Serializable  {
	private Integer product_no ;
	private Integer product_category_no;
	private Integer product_price;
	private String product_name;
	private Integer stock;
	private byte[] product_pic;
	private String state;
	private Integer user_no;
	
	
	
	
	public Integer getUser_no() {
		return user_no;
	}
	public void setUser_no(Integer user_no) {
		this.user_no = user_no;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getProduct_no() {
		return product_no;
	}
	public void setProduct_no(Integer product_no) {
		this.product_no = product_no;
	}
	public Integer getProduct_category_no() {
		return product_category_no;
	}
	public void setProduct_category_no(Integer product_category_no) {
		this.product_category_no = product_category_no;
	}
	public Integer getProduct_price() {
		return product_price;
	}
	public void setProduct_price(Integer product_price) {
		this.product_price = product_price;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public byte[] getProduct_pic() {
		return product_pic;
	}
	public void setProduct_pic(byte[] product_pic) {
		this.product_pic = product_pic;
	}
	public ProductVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public static byte[] getPicture(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}



	
}
