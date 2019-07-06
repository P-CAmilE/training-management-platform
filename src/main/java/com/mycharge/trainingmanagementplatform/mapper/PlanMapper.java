package com.mycharge.trainingmanagementplatform.mapper;

import com.alibaba.fastjson.JSONObject;

import java.util.List;


public interface PlanMapper extends Mapper {

    public int insertPlanTeacher(JSONObject jsonObject);

    public int deletePlanTeacher(JSONObject jsonObject);

    public List<JSONObject> findName();

}
