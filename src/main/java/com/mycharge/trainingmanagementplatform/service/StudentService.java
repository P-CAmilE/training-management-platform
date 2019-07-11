package com.mycharge.trainingmanagementplatform.service;

import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.model.Result;

public interface StudentService extends proService {

    public Result findSchool();

    public Result scoreForStudent(JSONObject jsonObject);

    public Result findByTeacher(JSONObject jsonObject);

}
