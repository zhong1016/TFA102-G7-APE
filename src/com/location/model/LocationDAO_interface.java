package com.location.model;

import java.util.*;
import com.restaurant.model.RestaurantVO;

public interface LocationDAO_interface {
	      public void insert(LocationVO locationVO);
          public void update(LocationVO locationVO);
          public void delete(Integer locationno);
          public LocationVO findByPrimaryKey(Integer locationno);
	      public List<LocationVO> getAll();
	      //查詢某部門的員工(一對多)(回傳 Set)
	      public Set<RestaurantVO> getRestaurantsByLocationno(Integer locationno);
}
