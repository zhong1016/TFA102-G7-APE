package com.ser.model;

import java.util.List;

public interface SerQADAO_interface {
	
	public void insert(SerQAVO serQAVO);
	public void update(SerQAVO serQAVO);
	public void delete(Integer servQANo);
	public SerQAVO findByServNo(Integer serQANo);
	
	public List<SerQAVO> getAll();

}
