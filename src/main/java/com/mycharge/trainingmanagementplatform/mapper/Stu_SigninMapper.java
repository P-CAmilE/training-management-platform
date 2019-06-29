package com.mycharge.trainingmanagementplatform.mapper;

import com.mycharge.trainingmanagementplatform.model.MyObject;

import java.util.List;

public interface Stu_SigninMapper {

    public int signin(MyObject myObject);

    public List<MyObject> findBySid_Date (MyObject myObject);
}
