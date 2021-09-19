package com.manager.model;

import java.io.*;

import com.manager_msg.model.Manager_msgDAO_interface;
import com.manager_msg.model.Manager_msgJDBCDAO;
import com.manager_msg.model.Manager_msgJNDIDAO;
public class ManagerService {
	
	private ManagerDAO_interface dao;
	
	private Manager_msgDAO_interface msgdao;
	
	public  ManagerService() {
		dao = new ManagerJDBCDAO();
		msgdao = new Manager_msgJNDIDAO();
	}
	
	/*****************更新管理員資料(含大頭貼)******************/
	public void updateManagerIncludePic (byte[] headshot, String nickname , String phone, String email , Integer manager_no) {
		ManagerVO managerVO = new ManagerVO();
				
		managerVO.setHeadshot(headshot);
		managerVO.setNickname(nickname);
		managerVO.setPhone(phone);
		managerVO.setEmail(email);
		managerVO.setManager_no(manager_no);
		dao.updateIncludePic(managerVO);
		
	}

	/*****************更新管理員資料(不含大頭貼)******************/
	public void updateManagerExcludePic ( String nickname , String phone, String email, Integer manager_no ) {
		ManagerVO managerVO = new ManagerVO();
				
		managerVO.setNickname(nickname);
		managerVO.setPhone(phone);
		managerVO.setEmail(email);
		managerVO.setManager_no(manager_no);
		dao.updateExcludePic(managerVO);	
	}
	
	
	
	public ManagerVO getOneManager(Integer empno) {
		return dao.findByPrimaryKey(empno);
	}
	
	public ManagerVO update(Integer manager_no, String account, String password, String nickname, String phone, String email ) {
		ManagerVO managerVO = new ManagerVO();
		managerVO.setManager_no(manager_no);
		managerVO.setAccount(account);
		managerVO.setPassword(password);
		managerVO.setNickname(nickname);
		managerVO.setPhone(phone);
		managerVO.setEmail(email);
		dao.update(managerVO);
		return managerVO;
	}
	
	public void delete(Integer manager_no){
		//刪除留言
		msgdao.delete(manager_no);
		//再刪除帳號
		dao.delete(manager_no);
		
	}
	
	public ManagerVO addManager(String account, String password, String nickname, String phone, String email) {
		ManagerVO managerVO = new ManagerVO();
		managerVO.setAccount(account);
		managerVO.setPassword(password);
		managerVO.setNickname(nickname);
		managerVO.setPhone(phone);
		managerVO.setEmail(email);
		dao.insert(managerVO);
		return managerVO;		
	}
	
	
	public ManagerVO findManagerByAccount(String account) {
		return dao.findByAccount(account);
	}
	
	
}
