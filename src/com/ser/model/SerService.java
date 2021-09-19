package com.ser.model;

import java.util.List;

import com.activity.model.ActivityDAO_interface;
import com.activity.model.ActivityJNDIDAO;
import com.activity.model.ActivityVO;
import com.activitytype.model.ActivitytypeDAO_interface;
import com.activitytype.model.ActivitytypeJDBCDAO;
import com.activitytype.model.ActivitytypeVO;

public class SerService {
	
	private ActivitytypeDAO_interface daoType;
	private ActivityDAO_interface dao;
	private SerQADAO_interface daoQA;
	private SerDAO_interface daoSer;
	
	public SerService() {
		daoType = new ActivitytypeJDBCDAO();
		dao=  new ActivityJNDIDAO();
		daoQA = new SerQADAO();
	}
	
	public List<ActivitytypeVO> getAll() {
		return daoType.getAll();
	}
	
	public List<ActivityVO> getActivity(Integer activityTypeNo) {
		return dao.getActivity(activityTypeNo);
	}
	
	public List<SerQAVO> getAllQA(){
		return daoQA.getAll();
	}
	
	public SerVO addSer(SerVO serVO) {
		serVO.setStatus("0");
		daoSer.insert(serVO);
		
		return serVO;
	}
}
