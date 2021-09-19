package com.mem.model;

import java.util.*;

public interface MemDAO_interface {
	
	public void insert(MemVO memVO);
	public void update(MemVO memVO);
	public void delete(String userId);
	public MemVO findByUserId(String userId, String userPassword);
	public MemVO findByUesr(String userId);
	public String pwdhash(String password);
	
	public List<MemVO> getAll();
//	public List<MemVO> getAll(Map<String, String[]> map);
	
}
