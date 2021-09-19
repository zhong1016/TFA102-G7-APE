package com.cop.model;

import java.util.List;

public interface CopDAO_interface {

	public void insert(CopVO copVO);
	public void update(CopVO copVO);
	public void delete(String companyId);
	public CopVO findByCompanyId(String companyId);
	public CopVO findByCompanyId(String companyId, String companyPassword);
	public String pwdhash(String password);
	
	public List<CopVO> getAll();
}
