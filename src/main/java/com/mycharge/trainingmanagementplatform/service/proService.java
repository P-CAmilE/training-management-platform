package com.mycharge.trainingmanagementplatform.service;

import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.model.MyObject;
import com.mycharge.trainingmanagementplatform.model.Result;

public interface proService {
    public Result insert(JSONObject jsonObject);

    public Result find(JSONObject jsonObject);

    public Result update(JSONObject jsonObject);

    public Result delete(JSONObject jsonObject);
}
