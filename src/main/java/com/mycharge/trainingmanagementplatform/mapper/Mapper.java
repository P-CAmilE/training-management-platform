package com.mycharge.trainingmanagementplatform.mapper;

import com.mycharge.trainingmanagementplatform.model.MyObject;

import java.util.List;

public interface Mapper {
    public int insert(MyObject myObject);

    public List<MyObject> find(MyObject myObject);

    public int update(MyObject myObject);

    public int delete(MyObject myObject);
}
