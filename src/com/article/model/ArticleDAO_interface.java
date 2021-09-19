package com.article.model;

import java.util.List;

public interface ArticleDAO_interface {
	
	public void insert (ArticleVO aVo);
	public void update(ArticleVO aVo);
	public void delete(Integer aRTICLE_NO);
	public ArticleVO findPrimartKey(Integer ARTICLE_NO);
	public List<ArticleVO> getAll();
	public List<ArticleVO> getAllByCount();
	public List<ArticleVO> getAllByArea(String area);
	public void updateCount(Integer i);
	public List<ArticleVO> getAllByUSERNO(Integer userNO);
}
