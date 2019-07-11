package com.mycharge.trainingmanagementplatform.mapper;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface ProjectMapper extends Mapper {

    public int insertProjectTeacher(JSONObject jsonObject);

    public int deleteProjectTeacher(JSONObject jsonObject);

    public List<JSONObject> findName(JSONObject jsonObject);

    public List<JSONObject> findByStudent(JSONObject jsonObject);

}
