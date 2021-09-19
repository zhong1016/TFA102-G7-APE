package com.article.model;

public class ReportVO {

	private Integer REPORT_NO; // ���|�渹
	private String REPORT; // ���|���e
	private String REPORT_MSG; // ���|��T
	private String REPORT_STATUS; // ���|���A
	private String MANAGER_NO; // �޲z���s��
	private Integer MSG_NO;

	public Integer getMSG_NO() {
		return MSG_NO;
	}

	public void setMSG_NO(Integer mSG_NO) {
		this.MSG_NO = mSG_NO;
	}

	

	@Override
	public String toString() {
		return "ReportVO [REPORT_NO=" + REPORT_NO + ", REPORT=" + REPORT + ", REPORT_MSG=" + REPORT_MSG
				+ ", REPORT_STATUS=" + REPORT_STATUS + ", MANAGER_NO=" + MANAGER_NO + ", MSG_NO=" + MSG_NO + "]";
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
