package com.manager.model;

import java.util.List;

public interface ManagerDAO_interface {
	
	public void insert(ManagerVO managerVO);
	public void update(ManagerVO managerVO);
	public void delete(Integer  manager_no);
	public ManagerVO findByPrimaryKey(Integer manager_no);
	public ManagerVO findByAccount(String account);
	public List<ManagerVO > getAll();
	
	public void updateIncludePic(ManagerVO managerVO);
	public void updateExcludePic(ManagerVO managerVO);
}
