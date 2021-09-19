package com.report.model;

import java.util.List;

public interface Report_interface {
	
	public void insert(ReportVO reportVO);
	public void update(ReportVO reportVO);
	public void delete(Integer REPORT_NO);
	public ReportVO findPrimaryKey(Integer REPORT_NO);
	public List<ReportVO> getAll();
	
}
