package com.report.model;

import java.util.List;

public class ReportService {
	
	
	private Report_interface dao;

	public ReportService() {		
		dao = new ReportDAO();
	}
	
	public List<ReportVO> getAll(){
		return dao.getAll();
	}
	
	
}
