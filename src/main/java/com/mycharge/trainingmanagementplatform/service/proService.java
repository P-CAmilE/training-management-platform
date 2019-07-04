package com.mycharge.trainingmanagementplatform.service;

import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.model.Result;

public interface proService {
    public Result insert(JSONObject object);

    public Result find(JSONObject object);

    public Result update(JSONObject object);

    public Result delete(JSONObject object);
}
