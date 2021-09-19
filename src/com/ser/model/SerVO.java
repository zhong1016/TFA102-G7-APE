package com.ser.model;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

import javax.servlet.http.Part;

public class SerVO implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer servNo;
	private String servName;
	private String servMsg;
	private String servMain;
	private byte[] servPic;
	private String servAns;
	private String status;
	
	public Integer getServNo() {
		return servNo;
	}
	public void setServNo(Integer servNo) {
		this.servNo = servNo;
	}
	public String getServName() {
		return servName;
	}
	public void setServName(String servName) {
		this.servName = servName;
	}
	public String getServMsg() {
		return servMsg;
	}
	public void setServMsg(String servMsg) {
		this.servMsg = servMsg;
	}
	public String getServMain() {
		return servMain;
	}
	public void setServMain(String servMain) {
		this.servMain = servMain;
	}
	public byte[] getServPic() {
		return servPic;
	}
	public void setServPic(byte[] servPic) {
		this.servPic = servPic;
	}
	public String getServAns() {
		return servAns;
	}
	public void setServAns(String servAns) {
		this.servAns = servAns;
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
