package com.mycharge.trainingmanagementplatform.mapper;

import com.alibaba.fastjson.JSONObject;

public interface ResourceMapper {

    public int upload(JSONObject object);

    public JSONObject find(JSONObject object);

    public int delete(JSONObject object);
}
