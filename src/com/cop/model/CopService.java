package com.cop.model;

import java.util.List;

public class CopService {
	
	private CopDAO_interface dao;
	
	public CopService() {
		dao = new CopDAO();
	}
	
	public CopVO addCop (CopVO copVO) {
		System.out.println("### addCop service");
		java.util.Date date = new java.util.Date();
		java.sql.Date dateSql = new java.sql.Date(date.getTime());
		copVO.setCompanyAddressCity("臺北市");
		copVO.setCompanyAddressDistrict("中山區");
		copVO.setCreateTime(dateSql);
		copVO.setStatus("0");
		dao.insert(copVO);

		return copVO;
	}
	
	public CopVO updateCop(CopVO copVO) {
		System.out.println("### updateCop service");
		dao.update(copVO);
		return copVO;
	}

	public void deleteMem(String companyId) {
		dao.delete(companyId);
	}

	public CopVO getOneCop(String companyId) {
		return dao.findByCompanyId(companyId);
	}
	
	public CopVO getOneCop(String userId, String userPassword) {
		return dao.findByCompanyId(userId, userPassword);
	}

	public List<CopVO> getAll() {
		return dao.getAll();
	}
	
	public String pwdhash(String password) {
		dao.pwdhash(password);
		return password;
	}

}

