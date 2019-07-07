package com.mycharge.trainingmanagementplatform.service;

import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.model.Result;
import com.mycharge.trainingmanagementplatform.service.proService;

public interface TeamService extends proService {

    public Result findPlanProject();

    public Result findStudent(JSONObject jsonObject);
}
