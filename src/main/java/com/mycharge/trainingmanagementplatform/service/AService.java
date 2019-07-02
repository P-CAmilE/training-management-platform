package com.mycharge.trainingmanagementplatform.service;

import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.model.Result;

public interface AService {
    public Result save(JSONObject object);

    public Result findByAname(JSONObject object);
}
