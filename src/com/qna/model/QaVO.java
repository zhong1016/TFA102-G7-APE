package com.qna.model;

import java.sql.*;

public class QaVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer qaNo;
	private String questions;
	private String answers;
	
	public Integer getQaNo() {
		return qaNo;
	}
	public void setQaNo(Integer qaNo) {
		this.qaNo = qaNo;
	}
	public String getQuestions() {
		return questions;
	}
	public void setQuestions(String questions) {
		this.questions = questions;
	}
	public String getAnswers() {
		return answers;
	}
	public void setAnswers(String answers) {
		this.answers = answers;
	}
}
