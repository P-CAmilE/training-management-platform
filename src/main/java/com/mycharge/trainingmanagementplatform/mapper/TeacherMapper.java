package com.mycharge.trainingmanagementplatform.mapper;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface TeacherMapper extends Mapper {

    public List<JSONObject> findByCompany(JSONObject jsonObject);
    public List<JSONObject> findBySchool(JSONObject jsonObject);
    public List<JSONObject> findByPlan(JSONObject jsonObject);
    public List<JSONObject> findByProject(JSONObject jsonObject);
    public List<JSONObject> findByTeam(JSONObject jsonObject);
}
