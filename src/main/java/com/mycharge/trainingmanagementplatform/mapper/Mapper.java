package com.mycharge.trainingmanagementplatform.mapper;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface Mapper {
    public int insert(JSONObject object);

    public List<JSONObject> find(JSONObject object);

    public int update(JSONObject object);

    public int delete(JSONObject object);
}
