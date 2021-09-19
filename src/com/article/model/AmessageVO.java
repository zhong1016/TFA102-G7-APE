package com.article.model;

import java.sql.Date;

public class AmessageVO {
	
	private Integer MSG_NO ; //�d���s�� 
	private String MSG ; //�d�����e
	private String MSG_TM; // �d���ɶ�
	private Integer ARTICLE_NO; //�峹�s��
	private Integer USER_NO;
	public Integer getUSER_NO() {
		return USER_NO;
	}
	public void setUSER_NO(Integer uSER_NO) {
		this.USER_NO = uSER_NO;
	}
	public Integer getARTICLE_NO() {
		return ARTICLE_NO;
	}
	public void setARTICLE_NO(Integer aRTICLE_NO) {
		this.ARTICLE_NO = aRTICLE_NO;
	}
	public Integer getMSG_NO() {
		return MSG_NO;
	}
	public void setMSG_NO(Integer mSG_NO) {
		this.MSG_NO = mSG_NO;
	}
	public String getMSG() {
		return MSG;
	}
	public void setMSG(String mSG) {
		this.MSG = mSG;
	}
	public String getMSG_TM() {
		return MSG_TM;
	}
	public void setMSG_TM(String mSG_TM) {
		this.MSG_TM = mSG_TM;
	}

	
}
