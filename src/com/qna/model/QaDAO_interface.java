package com.qna.model;

import java.util.List;

public interface QaDAO_interface {

	public void insert(QaVO qaVO);
	public void update(QaVO qaVO);
	public void delete(Integer qaNo);
	public QaVO findByQaNo(Integer qaNo);
	
	public List<QaVO> getAll();
}
