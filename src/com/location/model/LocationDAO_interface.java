package com.location.model;

import java.util.*;
import com.restaurant.model.RestaurantVO;

public interface LocationDAO_interface {
	      public void insert(LocationVO locationVO);
          public void update(LocationVO locationVO);
          public void delete(Integer locationno);
          public LocationVO findByPrimaryKey(Integer locationno);
	      public List<LocationVO> getAll();
	      //�d�߬Y���������u(�@��h)(�^�� Set)
	      public Set<RestaurantVO> getRestaurantsByLocationno(Integer locationno);
}
