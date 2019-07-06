package com.mycharge.trainingmanagementplatform.mapper;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface ResourceMapper {

    public int upload(JSONObject object);

    public List<JSONObject> find(JSONObject object);

    public int delete(JSONObject object);
}
