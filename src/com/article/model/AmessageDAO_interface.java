package com.article.model;

import java.util.List;

public interface AmessageDAO_interface {
	
	public void insert(AmessageVO amessageVO);
	public void update(AmessageVO amessageVO);
	public void delete(AmessageVO amessageVO);
	public AmessageVO findPrimaryKey(Integer MSG_NO);
	public List <AmessageVO> getALL();
	public List <AmessageVO> getAllByNo(Integer i );
	
}
