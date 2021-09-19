package com.mem.model;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

import javax.servlet.http.Part;

public class MemVO implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	/*
	 * member's all section.
	 */
	private Integer userNo;
	private String userId;
	private byte[] userPic;
	private String userPassword;
	private String userfName;
	private String userlName;
	private String userPhone;
	private String userAddressCity;
	private String userAddressDistrict;
	private String userAddress;
	private String userEmail;
	private Date createTime;
	private String status;
	
	public Integer getUserNo() {
		return userNo;
	}
	public void setUserNo(Integer userNo) {
		this.userNo = userNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public byte[] getUserPic() {
		return userPic;
	}
	public void setUserPic(byte[] uesrPic) {
		this.userPic = uesrPic;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserfName() {
		return userfName;
	}
	public void setUserfName(String userfName) {
		this.userfName = userfName;
	}
	public String getUserlName() {
		return userlName;
	}
	public void setUserlName(String userlName) {
		this.userlName = userlName;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserAddressCity() {
		return userAddressCity;
	}
	public void setUserAddressCity(String userAddressCity) {
		this.userAddressCity = userAddressCity;
	}
	public String getUserAddressDistrict() {
		return userAddressDistrict;
	}
	public void setUserAddressDistrict(String userAddressDistrict) {
		this.userAddressDistrict = userAddressDistrict;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public byte[] getBytesByPart(Part part) {
		InputStream in = null;
		byte[] userPic = null;
		try {
			in = part.getInputStream();
			userPic = new byte[in.available()];
			in.read(userPic);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userPic;
	}
}
