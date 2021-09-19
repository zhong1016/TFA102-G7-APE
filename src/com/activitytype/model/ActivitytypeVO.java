package com.activitytype.model;

import java.sql.Date;
import java.sql.Timestamp;

public class ActivitytypeVO implements java.io.Serializable {
	private Integer activityTypeNo;
	private String activityTypeName;
	

	public ActivitytypeVO(Integer activityTypeNo, String activityTypeName) {
		super();
		this.activityTypeNo = activityTypeNo;
		this.activityTypeName = activityTypeName;

	}


	public ActivitytypeVO() {
		// TODO Auto-generated constructor stub
	}


	public Integer getActivityTypeNo() {
		return activityTypeNo;
	}


	public void setActivityTypeNo(Integer activityTypeNo) {
		this.activityTypeNo = activityTypeNo;
	}


	public String getActivityTypeName() {
		return activityTypeName;
	}


	public void setActivityTypeName(String activityTypeName) {
		this.activityTypeName = activityTypeName;
	}


	
	
}
