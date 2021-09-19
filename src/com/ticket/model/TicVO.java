package com.ticket.model;

import java.sql.*;

public class TicVO{
//	TICKET_NO, TICKET_NAME, TICKET_ID_CARD, TICKET_EMAIL, TICKET_QRCODE, EXHIBITION_NO
	private Integer ticketNo;
	private String ticketName;
	private Integer ticketIdCard;
	private String ticketEmail;
	private String ticketQrcode;
	private Integer exhibitionNo;
	private Integer userId;
	public Integer getTicketNo() {
		return ticketNo;
	}
	public void setTicketNo(Integer ticketNo) {
		this.ticketNo = ticketNo;
	}
	public String getTicketName() {
		return ticketName;
	}
	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}
	public Integer getTicketIdCard() {
		return ticketIdCard;
	}
	public void setTicketIdCard(Integer ticketIdCard) {
		this.ticketIdCard = ticketIdCard;
	}
	public String getTicketEmail() {
		return ticketEmail;
	}
	public void setTicketEmail(String ticketEmail) {
		this.ticketEmail = ticketEmail;
	}
	public String getTicketQrcode() {
		return ticketQrcode;
	}
	public void setTicketQrcode(String ticketQrcode) {
		this.ticketQrcode = ticketQrcode;
	}
	public Integer getExhibitionNo() {
		return exhibitionNo;
	}
	public void setExhibitionNo(Integer exhibitionNo) {
		this.exhibitionNo = exhibitionNo;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}
