package com.report.model;

public class ReportVO {
	
	private	Integer REPORT_NO; 
	private 	String REPORT; 
	private 	String REPORT_MSG; 
	private 	String REPORT_STATUS; 
	private 	String MANAGER_NO; 
	
	@Override
	public String toString() {
		return "ReportVO [REPORT_NO=" + REPORT_NO + ", REPORT=" + REPORT + ", REPORT_MSG=" + REPORT_MSG
				+ ", REPORT_STATUS=" + REPORT_STATUS + ", MANAGER_NO=" + MANAGER_NO + "]";
	}
	public Integer getREPORT_NO() {
		return REPORT_NO;
	}
	public void setREPORT_NO(Integer rEPORT_NO) {
		this.REPORT_NO = rEPORT_NO;
	}
	public String getREPORT() {
		return REPORT;
	}
	public void setREPORT(String rEPORT) {
		this.REPORT = rEPORT;
	}
	public String getREPORT_MSG() {
		return REPORT_MSG;
	}
	public void setREPORT_MSG(String rEPORT_MSG) {
		this.REPORT_MSG = rEPORT_MSG;
	}
	public String getREPORT_STATUS() {
		return REPORT_STATUS;
	}
	public void setREPORT_STATUS(String rEPORT_STATUS) {
		this.REPORT_STATUS = rEPORT_STATUS;
	}
	public String getMANAGER_NO() {
		return MANAGER_NO;
	}
	public void setMANAGER_NO(String mANAGER_NO) {
		this.MANAGER_NO = mANAGER_NO;
	}
	
	
	
}
