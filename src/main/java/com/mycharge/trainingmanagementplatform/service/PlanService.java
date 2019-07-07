package com.mycharge.trainingmanagementplatform.service;

import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.model.Result;

public interface PlanService extends proService{
    public Result findTeacherByUnit(JSONObject jsonObject);
    public Result findUnit();
    public Result findPlanDetail(JSONObject jsonObject);
}
