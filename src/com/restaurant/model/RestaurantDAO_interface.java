package com.restaurant.model;

import java.util.*;

public interface RestaurantDAO_interface {
	public void insert(RestaurantVO restaurantVO);
    public void update(RestaurantVO restaurantVO);
    public void delete(Integer restaurantno);
    public RestaurantVO findByPrimaryKey(Integer restaurantno);
    public List<RestaurantVO> getAll();

    //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//  public List<RestaurantVO> getAll(Map<String, String[]> map); 

}
