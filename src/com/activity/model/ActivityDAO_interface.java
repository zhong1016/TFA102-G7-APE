package com.activity.model;

import java.sql.Connection;
import java.util.*;

import javax.servlet.http.Part;

public interface ActivityDAO_interface {
          public int insert(ActivityVO activityVO);
          public int insertNoPic(ActivityVO activityVO);
          public void update(ActivityVO activityVO);
          public void updateNoPic(ActivityVO activityVO);
          public ActivityVO findByPrimaryKey(Integer activityNo);
          public List<ActivityVO> getAll();
          public List<ActivityVO> getMyActivity(Integer companyNo);
          public List<ActivityVO> getActivityByStatus(String status);
          public void updateReservationAll(String reservationAll, Integer activityNo, Connection con);
          public void updateStatus(ActivityVO activityVO);
          public List<ActivityVO> getActivity(Integer activityTypeNo);
}

