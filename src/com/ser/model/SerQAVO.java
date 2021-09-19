package com.ser.model;

public class SerQAVO implements java.io.Serializable {
	
	private Integer serQANo;	
	private String serAns;	
	private String serQus;
	
	public Integer getSerQANo() {
		return serQANo;
	}
	public void setSerQANo(Integer serQANo) {
		this.serQANo = serQANo;
	}
	public String getSerQus() {
		return serQus;
	}
	public void setSerQus(String serQus) {
		this.serQus = serQus;
	}
	public String getSerAns() {
		return serAns;
	}
	public void setSerAns(String serAns) {
		this.serAns = serAns;
	}

}
