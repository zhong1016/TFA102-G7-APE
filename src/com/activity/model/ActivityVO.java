package com.activity.model;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Timestamp;

import javax.servlet.http.Part;

public class ActivityVO implements java.io.Serializable {
	private Integer activityNo;
	private String activityName;
	private Date startDate;
	private Date closeDate;
	private Integer dayCount;
	private String activityHours;
	private Integer rentCount;
	private Timestamp createDate;
	private String address;
	private Integer price;
	private String introduction;
	private byte[] activityPic;
	private String status;
	private String rentString;
	private String rowNum;
	private String columnsNum;
	private String available;
	private String reservationAll;
	private Integer companyNo;
	private Integer activityTypeNo;

	public ActivityVO(Integer activityNo, String activityName, Date startDate, Date closeDate, Integer dayCount,
			String activityHours, Integer rentCount, Timestamp createDate, String address, Integer price,
			String introduction, byte[] activityPic, String status, String rentString, String rowNum, String columnsNum,
			String available, String reservationAll, Integer companyNo, Integer activityTypeNo) {
		super();
		this.activityNo = activityNo;
		this.activityName = activityName;
		this.startDate = startDate;
		this.closeDate = closeDate;
		this.dayCount = dayCount;
		this.activityHours = activityHours;
		this.rentCount = rentCount;
		this.createDate = createDate;
		this.address = address;
		this.price = price;
		this.introduction = introduction;
		this.activityPic = activityPic;
		this.status = status;
		this.rentString = rentString;
		this.rowNum = rowNum;
		this.columnsNum = columnsNum;
		this.available = available;
		this.reservationAll = reservationAll;
		this.companyNo = companyNo;
		this.activityTypeNo = activityTypeNo;
	}

	public ActivityVO() {
		// TODO Auto-generated constructor stub
	}



	public Integer getActivityNo() {
		return activityNo;
	}

	public void setActivityNo(Integer activityNo) {
		this.activityNo = activityNo;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public Integer getDayCount() {
		return dayCount;
	}

	public void setDayCount(Integer dayCount) {
		this.dayCount = dayCount;
	}

	public String getActivityHours() {
		return activityHours;
	}

	public void setActivityHours(String activityHours) {
		this.activityHours = activityHours;
	}

	public Integer getRentCount() {
		return rentCount;
	}

	public void setRentCount(Integer rentCount) {
		this.rentCount = rentCount;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public byte[] getActivityPic() {
		return activityPic;
	}

	public void setActivityPic(byte[] activityPic) {
		this.activityPic = activityPic;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRentString() {
		return rentString;
	}

	public void setRentString(String rentString) {
		this.rentString = rentString;
	}

	public String getRowNum() {
		return rowNum;
	}

	public void setRowNum(String rowNum) {
		this.rowNum = rowNum;
	}

	public String getColumnsNum() {
		return columnsNum;
	}

	public void setColumnsNum(String columnsNum) {
		this.columnsNum = columnsNum;
	}

	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	public String getReservationAll() {
		return reservationAll;
	}

	public void setReservationAll(String reservationAll) {
		this.reservationAll = reservationAll;
	}

	public Integer getCompanyNo() {
		return companyNo;
	}

	public void setCompanyNo(Integer companyNo) {
		this.companyNo = companyNo;
	}

	public Integer getActivityTypeNo() {
		return activityTypeNo;
	}

	public void setActivityTypeNo(Integer activityTypeNo) {
		this.activityTypeNo = activityTypeNo;
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
