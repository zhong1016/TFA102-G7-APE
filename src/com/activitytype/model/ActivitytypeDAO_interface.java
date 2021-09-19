package com.activitytype.model;

import java.util.*;

public interface ActivitytypeDAO_interface {
          public void insert(ActivitytypeVO ActivitytypeVO);
          public void update(ActivitytypeVO ActivitytypeVO);
          public ActivitytypeVO findByPrimaryKey(Integer acivityTypeNo);
          public List<ActivitytypeVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
