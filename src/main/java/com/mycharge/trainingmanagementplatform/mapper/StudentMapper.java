package com.mycharge.trainingmanagementplatform.mapper;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface StudentMapper extends Mapper{

    public List<JSONObject> findBySchool(JSONObject jsonObject);


}
