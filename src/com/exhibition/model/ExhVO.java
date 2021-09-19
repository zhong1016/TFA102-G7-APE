package com.exhibition.model;


public class ExhVO {

//	EXHIBITION_NO, EXHIBITION_PIC, EXHIBITION_DATE, EXHIBITION_TOPIC, EXHIBITION_CONTEUT, EXHIBITION_AREA
	private Integer exhibitionNo;
	private byte[] exhibitionPic;
	private java.sql.Date exhibitionDate;
	private String exhibitionTopic;
	private String exhibitionContent;
	private String exhibitionArea;

	public Integer getExhibitionNo() {
		return exhibitionNo;
	}

	public void setExhibitionNo(Integer exhibitionNo) {
		this.exhibitionNo = exhibitionNo;
	}

	public byte[] getExhibitionPic() {
		return exhibitionPic;
	}

	public void setExhibitionPic(byte[] exhibitionPic) {
		this.exhibitionPic = exhibitionPic;
	}

	public java.sql.Date getExhibitionDate() {
		return exhibitionDate;
	}

	public void setExhibitionDate(java.sql.Date exhibitionDate) {
		this.exhibitionDate = exhibitionDate;
	}

	public String getExhibitionTopic() {
		return exhibitionTopic;
	}

	public void setExhibitionTopic(String exhibitionTopic) {
		this.exhibitionTopic = exhibitionTopic;
	}


	public String getExhibitionArea() {
		return exhibitionArea;
	}

	public void setExhibitionArea(String exhibitionArea) {
		this.exhibitionArea = exhibitionArea;
	}

	public String getExhibitionContent() {
		return exhibitionContent;
	}

	public void setExhibitionContent(String exhibitionContent) {
		this.exhibitionContent = exhibitionContent;
	}

}
