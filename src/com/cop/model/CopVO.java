package com.cop.model;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.Arrays;

import javax.servlet.http.Part;

public class CopVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * company's all section. 
	 */
	private Integer companyNo;
	private String companyId;
	private byte[] companyBRC;
	private byte[] companyPic;
	private String companyPassword;
	private String companyName;
	private String companyPhone;
	private String companyAddressCity;
	private String companyAddressDistrict;
	private String companyAddress;
	private String companyEmail;
	private Date createTime;
	private String status;
	
	
	@Override
	public String toString() {
		return "CopVO [companyNo=" + companyNo + ", companyId=" + companyId + ", companyBRC="
				+ Arrays.toString(companyBRC) + ", companyPic=" + Arrays.toString(companyPic) + ", companyPassword="
				+ companyPassword + ", companyName=" + companyName + ", companyPhone=" + companyPhone
				+ ", companyAddressCity=" + companyAddressCity + ", companyAddressDistrict=" + companyAddressDistrict
				+ ", companyAddress=" + companyAddress + ", companyEmail=" + companyEmail + ", createTime=" + createTime
				+ ", status=" + status + "]";
	}
	public Integer getCompanyNo() {
		return companyNo;
	}
	public void setCompanyNo(Integer companyNo) {
		this.companyNo = companyNo;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public byte[] getCompanyBRC() {
		return companyBRC;
	}
	public void setCompanyBRC(byte[] companyBRC) {
		this.companyBRC = companyBRC;
	}
	public byte[] getCompanyPic() {
		return companyPic;
	}
	public void setCompanyPic(byte[] companyPic) {
		this.companyPic = companyPic;
	}
	public String getCompanyPassword() {
		return companyPassword;
	}
	public void setCompanyPassword(String companyPassword) {
		this.companyPassword = companyPassword;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyPhone() {
		return companyPhone;
	}
	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}
	public String getCompanyAddressCity() {
		return companyAddressCity;
	}
	public void setCompanyAddressCity(String companyAddressCity) {
		this.companyAddressCity = companyAddressCity;
	}
	public String getCompanyAddressDistrict() {
		return companyAddressDistrict;
	}
	public void setCompanyAddressDistrict(String companyAddressDistrict) {
		this.companyAddressDistrict = companyAddressDistrict;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getCompanyEmail() {
		return companyEmail;
	}
	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
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
	
	public byte[] getBytesByPart(Part partBRC) {
		InputStream in = null;
		byte[] partPic = null;
		try {
			in = partBRC.getInputStream();
			partPic = new byte[in.available()];
			in.read(partPic);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return partPic;
	}
	
	
}
