package com.mycharge.trainingmanagementplatform.service;

import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.model.Result;

public interface Stu_SigninService {
    //插入一条签到记录
    public Result signin(JSONObject object);

    //查询某学生某时间段的签到记录
    public Result find(JSONObject object);
}
