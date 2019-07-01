package com.mycharge.trainingmanagementplatform.service;

import com.mycharge.trainingmanagementplatform.model.Result;
import com.mycharge.trainingmanagementplatform.model.MyObject;

public interface Stu_SigninService {
    //插入一条签到记录
    public Result signin(MyObject myObject);

    //查询某学生某时间段的签到记录
    public Result findBySid_Date (MyObject myObject);
}
