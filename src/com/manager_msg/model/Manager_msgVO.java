package com.manager_msg.model;


import java.sql.Date;
import java.sql.Timestamp;
import com.manager.model.*;
public class Manager_msgVO {
	private Integer message_no;
	private  Timestamp  established_time;
	private String work_record;
	private ManagerVO managerVO;
	
	public ManagerVO getManagerVO() {
		return managerVO;
	}
	public void setManagerVO(ManagerVO managerVO) {
		this.managerVO = managerVO;
	}
	/**
	 * @return the message_no
	 */
	public Integer getMessage_no() {
		return message_no;
	}
	/**
	 * @param message_no the message_no to set
	 */
	public void setMessage_no(Integer message_no) {
		this.message_no = message_no;
	}
	/**
	 * @return the established_time
	 */
	public Timestamp getEstablished_time() {
		return established_time;
	}
	/**
	 * @param established_time the established_time to set
	 */
	public void setEstablished_time(Timestamp established_time) {
		this.established_time = established_time;
	}
	/**
	 * @return the work_record
	 */
	public String getWork_record() {
		return work_record;
	}
	/**
	 * @param work_record the work_record to set
	 */
	public void setWork_record(String work_record) {
		this.work_record = work_record;
	}
	/**
	 * @return the manager_no
	 */
	
	public Manager_msgVO() {
		
	}
	
	
}
