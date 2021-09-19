package com.rent.model;

import java.util.*;

public interface RentDAO_interface {
          public void insert(RentVO rentVO);
          public void update(RentVO rentVO);
          public RentVO findByPrimaryKey(Integer rentVO);
          public List<RentVO> getAll();
          public List<RentVO> getRentByActivity(Integer activityNo);
          public List<RentVO> getMyRent(Integer userNo);
          public List<RentVO> getStatus(String status, Integer activityNo);
          public void updateStatus(RentVO rentVO);
          public void updateStatusRemove(RentVO rentVO);
		
}


