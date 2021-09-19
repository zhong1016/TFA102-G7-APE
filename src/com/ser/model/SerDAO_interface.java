package com.ser.model;

import java.util.List;

public interface SerDAO_interface {

	public void insert(SerVO SerVO);
	public void update(SerVO SerVO);
	public void delete(Integer servNo);
	public SerVO findByServNo(Integer servNo);
	
	public List<SerVO> getAll();
}
