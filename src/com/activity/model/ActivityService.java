package com.activity.model;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.Part;

public class ActivityService {
	private ActivityDAO_interface dao;
			//使用介面來宣告，未來配合使用框架
	
	public ActivityService() {
		dao = new ActivityJNDIDAO();
	}

	public int addActivity(String activityName, Date startDate, Date closeDate, Integer dayCount, String activityHours,
			Integer rentCount, Timestamp createDate, String address, Integer price, String introduction,
			byte[] activityPic, String status, String rentString, String rowNum, String columnsNum, String available,
			String reservationAll, Integer companyNo, Integer activityTypeNo) {

		ActivityVO activityVO = new ActivityVO();
		activityVO.setActivityName(activityName);
		activityVO.setStartDate(startDate);
		activityVO.setCloseDate(closeDate);
		activityVO.setDayCount(dayCount);
		activityVO.setActivityHours(activityHours);
		activityVO.setRentCount(rentCount);
		activityVO.setCreateDate(createDate);
		activityVO.setAddress(address);
		activityVO.setPrice(price);
		activityVO.setIntroduction(introduction);
		activityVO.setActivityPic(activityPic);
		activityVO.setStatus(status);
		activityVO.setRentString(rentString);
		activityVO.setRowNum(rowNum);
		activityVO.setColumnsNum(columnsNum);
		activityVO.setAvailable(available);
		activityVO.setReservationAll(reservationAll);
		activityVO.setCompanyNo(companyNo);
		activityVO.setActivityTypeNo(activityTypeNo);

		return dao.insert(activityVO);
	}

	public int addActivityNoPic(String activityName, Date startDate, Date closeDate, Integer dayCount,
			String activityHours, Integer rentCount, Timestamp createDate, String address, Integer price,
			String introduction, String status, String rentString, String rowNum, String columnsNum, String available,
			String reservationAll, Integer companyNo, Integer activityTypeNo) {

		ActivityVO activityVO = new ActivityVO();
		activityVO.setActivityName(activityName);
		activityVO.setStartDate(startDate);
		activityVO.setCloseDate(closeDate);
		activityVO.setDayCount(dayCount);
		activityVO.setActivityHours(activityHours);
		activityVO.setRentCount(rentCount);
		activityVO.setCreateDate(createDate);
		activityVO.setAddress(address);
		activityVO.setPrice(price);
		activityVO.setIntroduction(introduction);
//		activityVO.setActivityPic(activityPic);
		activityVO.setStatus(status);
		activityVO.setRentString(rentString);
		activityVO.setRowNum(rowNum);
		activityVO.setColumnsNum(columnsNum);
		activityVO.setAvailable(available);
		activityVO.setReservationAll(reservationAll);
		activityVO.setCompanyNo(companyNo);
		activityVO.setActivityTypeNo(activityTypeNo);

		return dao.insertNoPic(activityVO);
	}

	public ActivityVO updateActivity(String activityName, Date startDate, Date closeDate, Integer dayCount,
			String activityHours, Integer rentCount, String address, Integer price,
			String introduction, byte[] activityPic, String status, String rentString, String rowNum, String columnsNum,
			String available, String reservationAll, Integer companyNo, Integer activityTypeNo, Integer activityNo) {

		ActivityVO activityVO = new ActivityVO();
		activityVO.setActivityName(activityName);
		activityVO.setStartDate(startDate);
		activityVO.setCloseDate(closeDate);
		activityVO.setDayCount(dayCount);
		activityVO.setActivityHours(activityHours);
		activityVO.setRentCount(rentCount);
		activityVO.setAddress(address);
		activityVO.setPrice(price);
		activityVO.setIntroduction(introduction);
		activityVO.setActivityPic(activityPic);
		activityVO.setStatus(status);
		activityVO.setRentString(rentString);
		activityVO.setRowNum(rowNum);
		activityVO.setColumnsNum(columnsNum);
		activityVO.setAvailable(available);
		activityVO.setReservationAll(reservationAll);
		activityVO.setCompanyNo(companyNo);
		activityVO.setActivityTypeNo(activityTypeNo);
		activityVO.setActivityNo(activityNo);
		dao.update(activityVO);

		return activityVO;
	}

	public ActivityVO updateActivityNoPic(String activityName, Date startDate, Date closeDate, Integer dayCount,
			String activityHours, Integer rentCount, String address, Integer price,
			String introduction, String status, String rentString, String rowNum, String columnsNum, String available,
			String reservationAll, Integer companyNo, Integer activityTypeNo, Integer activityNo) {

		ActivityVO activityVO = new ActivityVO();
		activityVO.setActivityName(activityName);
		activityVO.setStartDate(startDate);
		activityVO.setCloseDate(closeDate);
		activityVO.setDayCount(dayCount);
		activityVO.setActivityHours(activityHours);
		activityVO.setRentCount(rentCount);
		activityVO.setAddress(address);
		activityVO.setPrice(price);
		activityVO.setIntroduction(introduction);
//		activityVO.setActivityPic(activityPic);
		activityVO.setStatus(status);
		activityVO.setRentString(rentString);
		activityVO.setRowNum(rowNum);
		activityVO.setColumnsNum(columnsNum);
		activityVO.setAvailable(available);
		activityVO.setReservationAll(reservationAll);
		activityVO.setCompanyNo(companyNo);
		activityVO.setActivityTypeNo(activityTypeNo);
		activityVO.setActivityNo(activityNo);
		dao.updateNoPic(activityVO);

		return activityVO;
	}

	public ActivityVO getOneActivity(Integer activityNo) {
		return dao.findByPrimaryKey(activityNo);
	}

	public List<ActivityVO> getAll() {
		return dao.getAll();
	}
	public List<ActivityVO> getActivityByStatus(String status) {
		return dao.getActivityByStatus(status);
	}
	public List<ActivityVO> getMyActivity(Integer companyNo) {
		return dao.getMyActivity(companyNo);
	}
	public void updateStatus(ActivityVO activityVO) {
		dao.updateStatus(activityVO);
	}
	public byte[] getBytesByPart(Part part) {
		InputStream in = null;
		byte[] buf = null;
		try {
			in = part.getInputStream();
			buf = new byte[in.available()];
			in.read(buf);
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return buf;
	}

}
