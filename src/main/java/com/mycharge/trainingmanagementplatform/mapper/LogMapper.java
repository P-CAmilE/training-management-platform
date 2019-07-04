package com.mycharge.trainingmanagementplatform.mapper;

import com.alibaba.fastjson.JSONObject;

public interface LogMapper {

    public int studentLogIn(JSONObject object);

    public int teacherLogIn(JSONObject object);

    public int companyLogIn(JSONObject object);

    public int studentRegister(JSONObject object);

    public int companyRegister(JSONObject object);
}