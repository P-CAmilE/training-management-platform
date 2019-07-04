package com.mycharge.trainingmanagementplatform.mapper;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface AMapper {
    public int save(JSONObject object) throws Exception;

    public List<JSONObject> findByAname(JSONObject object) throws Exception;
}
