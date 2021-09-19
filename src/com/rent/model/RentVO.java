package com.rent.model;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Timestamp;

import javax.servlet.http.Part;

public class RentVO implements java.io.Serializable {
	private Integer rentNo;
	private String rentName;
	private String rentIntroduction;
	private byte[] rentPic;
	private String status;
	private Integer rentPrice;
	private String reservation;
	private Integer activityNo;
	private Integer userNo;

	public RentVO(Integer rentNo, String rentName, String rentIntroduction, byte[] rentPic, String status,
			Integer rentPrice, String reservation, Integer activityNo, Integer userNo) {
		super();
		this.rentNo = rentNo;
		this.rentName = rentName;
		this.rentIntroduction = rentIntroduction;
		this.rentPic = rentPic;
		this.status = status;
		this.rentPrice = rentPrice;
		this.reservation = reservation;
		this.activityNo = activityNo;
		this.userNo = userNo;
	}

	public Integer getRentNo() {
		return rentNo;
	}

	public void setRentNo(Integer rentNo) {
		this.rentNo = rentNo;
	}

	public String getRentName() {
		return rentName;
	}

	public void setRentName(String rentName) {
		this.rentName = rentName;
	}

	public String getRentIntroduction() {
		return rentIntroduction;
	}

	public void setRentIntroduction(String rentIntroduction) {
		this.rentIntroduction = rentIntroduction;
	}

	public byte[] getRentPic() {
		return rentPic;
	}

	public void setRentPic(byte[] rentPic) {
		this.rentPic = rentPic;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getRentPrice() {
		return rentPrice;
	}

	public void setRentPrice(Integer rentPrice) {
		this.rentPrice = rentPrice;
	}

	public String getReservation() {
		return reservation;
	}

	public void setReservation(String reservation) {
		this.reservation = reservation;
	}

	public Integer getActivityNo() {
		return activityNo;
	}

	public void setActivityNo(Integer activityNo) {
		this.activityNo = activityNo;
	}

	public Integer getUserNo() {
		return userNo;
	}

	public void setUserNo(Integer userNo) {
		this.userNo = userNo;
	}

	public RentVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public byte[] getBytesByPart(Part part) {
		InputStream in = null;
		byte[] buf = null;
		try {
			in = part.getInputStream();
			buf = new byte[in.available()];
			in.read(buf);
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return buf;
	}

}
