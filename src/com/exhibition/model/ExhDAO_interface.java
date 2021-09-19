package com.exhibition.model;

import java.util.List;

public interface ExhDAO_interface {

	public void insert(ExhVO exhVO);
	public void update(ExhVO exhVO);
	public void delete(Integer exhibitionNo);
	public ExhVO findByPrimaryKey(Integer exhibitionNo);
	public List<ExhVO> getAll();
	
	
}
