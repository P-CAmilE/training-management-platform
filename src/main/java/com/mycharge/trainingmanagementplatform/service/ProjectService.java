package com.mycharge.trainingmanagementplatform.service;

import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.model.Result;
import com.mycharge.trainingmanagementplatform.service.proService;

public interface ProjectService extends proService {
    public Result findPlanName();

    public Result findTeacherByPlan(JSONObject jsonObject);
}
