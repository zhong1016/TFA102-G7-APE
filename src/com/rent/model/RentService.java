package com.rent.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RentService {
	private RentDAO_interface dao;

	public RentService() {
		dao = new RentJNDIDAO();
	}

	public void addRent(String rentName, String rentIntroduction, byte[] rentPic, String status, Integer rentPrice,
			String reservation, Integer activityNo, Integer userNo) {
		RentVO rentVO = new RentVO();

		rentVO.setRentName(rentName);
		rentVO.setRentIntroduction(rentIntroduction);
		rentVO.setRentPic(rentPic);
		rentVO.setStatus(status);
		rentVO.setRentPrice(rentPrice);
		rentVO.setReservation(reservation);
		rentVO.setActivityNo(activityNo);
		rentVO.setUserNo(userNo);

		dao.insert(rentVO);

	}

	public void updateRent(String rentName, String rentIntroduction, byte[] rentPic, String status, Integer rentPrice,
			String reservation, Integer activityNo, Integer userNo, Integer rentNo) {
		RentVO rentVO = new RentVO();

		rentVO.setRentName(rentName);
		rentVO.setRentIntroduction(rentIntroduction);
		rentVO.setRentPic(rentPic);
		rentVO.setStatus(status);
		rentVO.setRentPrice(rentPrice);
		rentVO.setReservation(reservation);
		rentVO.setActivityNo(activityNo);
		rentVO.setUserNo(userNo);
		rentVO.setRentNo(rentNo);

		dao.update(rentVO);

	}

	public void updateStatus(String status, Integer rentNo) {
		RentVO rentVO = new RentVO();
		rentVO.setStatus(status);
		rentVO.setRentNo(rentNo);
		
		dao.updateStatus(rentVO);

	}
	public void updateStatusRemove(String status, Integer rentNo,Integer activityNo,String reservation) {
		RentVO rentVO = new RentVO();
		rentVO.setStatus(status);
		rentVO.setRentNo(rentNo);
		rentVO.setActivityNo(activityNo);
		rentVO.setReservation(reservation);
		
		dao.updateStatusRemove(rentVO);
		
	}

	public List<RentVO> getStatus(String status, Integer activityNo) {
		return dao.getStatus(status, activityNo);

	}

	public RentVO getOneRent(Integer rentNo) {
		return dao.findByPrimaryKey(rentNo);
	}

	public List<RentVO> getAll() {
		return dao.getAll();
	}
	public List<RentVO> getRentByActivity(Integer activityNo) {
		return dao.getRentByActivity(activityNo);
	}

	public List<RentVO> getMyRent(Integer userNo) {
		return dao.getMyRent(userNo);
	}

}
