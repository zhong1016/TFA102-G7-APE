package com.manager_msg.model;

import java.sql.Timestamp;

import com.manager.model.ManagerVO;

public class Test {

	public static void main(String[] args) {
		

		
		ManagerVO managerVO = new ManagerVO();
		managerVO.setManager_no(5);
		Manager_msgVO msgVO = new Manager_msgVO();
		Manager_msgJDBCDAO msgDAO = new Manager_msgJDBCDAO();
		
		
		msgVO.setManagerVO(managerVO);		
		java.util.Date du = new java.util.Date();
		Long long1 = du.getTime();
		Timestamp date = new Timestamp(long1);
		
		msgVO.setEstablished_time(date);
		
		msgVO.setWork_record("1123");
		
		msgDAO.insert(msgVO);
//		
		
		
		
	
	}

}
