package com.manager_msg.model;

import java.util.List;

public  interface Manager_msgDAO_interface {
			
		public void insert(Manager_msgVO manager_msgVO);
		public void update(Manager_msgVO manager_msgVO);
		public void delete(Integer  manager_no);
		public Manager_msgVO findByPrimaryKey(Integer manager_no);
		public List<Manager_msgVO> getAll();
	
	
}
