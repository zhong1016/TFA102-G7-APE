package com.manager.model;

import java.sql.*;
import java.util.List;
public class Test {

	public static void main(String[] args) {
		
//		測試	
		//新增
//		ManagerJDBCDAO dao = new ManagerJDBCDAO();
//		ManagerVO managerVO = new ManagerVO();	
		
//		String account = "xx841995@gmail.cm";
//		ManagerJDBCDAO dao = new ManagerJDBCDAO();
//		ManagerVO managerVO = dao.findByAccounta(account);
//		System.out.println(managerVO);
		
//		managerVO.setAccount("xx841995@gmail.com");
//		managerVO.setPassword("mama1957");
//		managerVO.setNickname("金城武第一");
//		managerVO.setPhone("0920890077");
//		managerVO.setEmail("xx841995@gmail.com");	
//		dao.insert(managerVO);
		//更新
//		managerVO.setAccount("xx841995@gmail.com");
//		managerVO.setPassword("mama1957");
//		managerVO.setNickname("金城武第一");
//		managerVO.setPhone("0920890077");
//		managerVO.setEmail("xx841995@gmail.com");
//		managerVO.setManager_no(1);
//		dao.update(managerVO);	
		//刪除
//		dao.delete(4);		
		//查詢單筆
		
//		
//		managerVO = dao.findByPrimaryKey(1);
//	
//		System.out.println(managerVO.getManager_no());
//		System.out.println(managerVO.getAccount());
//		System.out.println(managerVO.getPassword());
//		System.out.println(managerVO.getNickname());
//		System.out.println(managerVO.getPhone());
//		System.out.println(managerVO.getEmail());
			
		
		//查詢全部
//		
//		List<ManagerVO> list = dao.getAll();
//		for(ManagerVO managerVO1: list) {		
//			System.out.println("MANAGER_NO="+managerVO1.getManager_no());
//			System.out.println("ACCOUNT="+managerVO1.getAccount());
//			System.out.println(managerVO1.getPassword());
//			System.out.println(managerVO1.getNickname());
//			System.out.println(managerVO1.getPhone());
//			System.out.println(managerVO1.getEmail());	
//			System.out.println("==================");
		
		
		try {
			
		ManagerService managerSvc = new ManagerService();
		managerSvc.updateManagerExcludePic("32", "1231", "1321", new Integer(1));
//		ManagerJDBCDAO dao = new ManagerJDBCDAO();
//		ManagerVO managerVO = new ManagerVO();
//		managerVO.setHeadshot();
//		managerVO.setNickname("123");
//		managerVO.setPhone("123");
//		managerVO.setEmail("123");
//		managerVO.setManager_no(new Integer(1));
//		dao.updateExcludePic(managerVO);
			
		}catch(Exception e) {
			e.printStackTrace(System.err);
		}
		
		
		
		}
			
	}
//
//}
