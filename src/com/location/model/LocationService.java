package com.location.model;

import java.util.List;
import java.util.Set;
import com.restaurant.model.RestaurantVO;

public class LocationService {

	private LocationDAO_interface dao;

	public LocationService() {
		dao = new LocationDAO();
	}

	public LocationVO addLocation(String locationname, 
			String locationadd) {

		LocationVO locationVO = new LocationVO();

		locationVO.setLocationname(locationname);
		locationVO.setLocationadd(locationadd);

		dao.insert(locationVO);

		return locationVO;
	}
	
	public LocationVO updateLocation(Integer locationno,String locationname, 
			String locationadd) {

		LocationVO locationVO = new LocationVO();
		locationVO.setLocationno(locationno);
		locationVO.setLocationname(locationname);
		locationVO.setLocationadd(locationadd);
		dao.update(locationVO);

		return locationVO;
	}

	public void deleteLocation(Integer locationno) {
		dao.delete(locationno);
	}
	
	public LocationVO getOneLocation(Integer locationno) {
		return dao.findByPrimaryKey(locationno);
	}

	public List<LocationVO> getAll() {
		return dao.getAll();
	}
	public Set<RestaurantVO> getRestaurantsByLocationno(Integer locationno) {
		return dao.getRestaurantsByLocationno(locationno);
	}

	
	
	
	
}
