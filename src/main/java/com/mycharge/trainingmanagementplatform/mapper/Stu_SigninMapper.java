package com.mycharge.trainingmanagementplatform.mapper;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface Stu_SigninMapper {

    public int signin(JSONObject object);

    public List<JSONObject> find(JSONObject object);
}
