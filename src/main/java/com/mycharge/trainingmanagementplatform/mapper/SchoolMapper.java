package com.mycharge.trainingmanagementplatform.mapper;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface SchoolMapper extends Mapper {
    public List<JSONObject> findAll();
}
