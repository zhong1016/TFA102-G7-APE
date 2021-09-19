package com.manager.model;

import java.util.*;
import com.manager_msg.model.*;

public class  ManagerVO implements java.io.Serializable{
	private Integer  manager_no;
	private  String account;
	private String password;
	private String nickname;
	private String phone;
	private String email;
	private byte[] headshot;

	/**
	 * @return the manager_no

	 */
	public Integer getManager_no() {
		return manager_no;
	}

	/**
	 * @param manager_no the manager_no to set
	 */
	public void setManager_no(Integer manager_no) {
		this.manager_no = manager_no;
	}
	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}
	/**
	 * @param account the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the headshot
	 */
	public byte[] getHeadshot() {
		return headshot;
	}
	/**
	 * @param headshot the headshot to set
	 */
	public void setHeadshot(byte[] headshot) {
		this.headshot = headshot;
	}
	public ManagerVO() {
		super();
	}
	public ManagerVO(Integer manager_no, String account, String password, String nickname, String phone, String email,
			byte[] headshot) {
		super();
		this.manager_no = manager_no;
		this.account = account;
		this.password = password;
		this.nickname = nickname;
		this.phone = phone;
		this.email = email;
		this.headshot = headshot;
	}
	
	
	
	
}
