package com.mycharge.trainingmanagementplatform.mapper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.model.MyObject;

import java.util.List;

public interface Mapper {
    public int insert(JSONObject jsonObject);

    public List<JSONObject> find(JSONObject jsonObject);

    public int update(JSONObject jsonObject);

    public int delete(JSONObject jsonObject);
}
