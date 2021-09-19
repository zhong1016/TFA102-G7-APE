package com.restaurant.model;

import java.util.List;
import java.util.Map;


public class RestaurantService {

	private RestaurantDAO_interface dao;

	public RestaurantService() {
		dao = new RestaurantDAO();
	}

	public RestaurantVO addRestaurant(String restaurantname, 
			String restaurantlevel, String restaurantadd,
			String restaurantnum, String restauranttype, Integer locationno) {

		RestaurantVO restaurantVO = new RestaurantVO();

		restaurantVO.setRestaurantname(restaurantname);
		restaurantVO.setRestaurantlevel(restaurantlevel);
		restaurantVO.setRestaurantadd(restaurantadd);
		restaurantVO.setRestaurantnum(restaurantnum);
		restaurantVO.setRestauranttype(restauranttype);
		restaurantVO.setLocationno(locationno);
		dao.insert(restaurantVO);

		return restaurantVO;
	}

	public RestaurantVO updateRestaurant(Integer restaurantno, String restaurantname, 
			String restaurantlevel, String restaurantadd,
			String restaurantnum, String restauranttype, Integer locationno) {

		RestaurantVO restaurantVO = new RestaurantVO();

		restaurantVO.setRestaurantno(restaurantno);
		restaurantVO.setRestaurantname(restaurantname);
		restaurantVO.setRestaurantlevel(restaurantlevel);
		restaurantVO.setRestaurantadd(restaurantadd);
		restaurantVO.setRestaurantnum(restaurantnum);
		restaurantVO.setRestauranttype(restauranttype);
		restaurantVO.setLocationno(locationno);
		dao.update(restaurantVO);

		return restaurantVO;
	}

	public void deleteRestaurant(Integer restaurantno) {
		dao.delete(restaurantno);
	}

	public RestaurantVO getOneRestaurant(Integer restaurantno) {
		return dao.findByPrimaryKey(restaurantno);
	}

	public List<RestaurantVO> getAll() {
		return dao.getAll();
	}
//	public List<RestaurantVO> getAll(Map<String, String[]> map) {
//		return dao.getAll(map);
//	}

}
