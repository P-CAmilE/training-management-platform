package com.mycharge.trainingmanagementplatform.mapper;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface StudentMapper extends Mapper{

    public List<JSONObject> findBySchool(JSONObject jsonObject);

    public List<JSONObject> findByTeam(JSONObject jsonObject);

    public List<JSONObject> findByTeacher(JSONObject jsonObject);

    public List<JSONObject> findStudentScore(JSONObject jsonObject);

    public int scoreForStudent(JSONObject jsonObject);

    public List<JSONObject> findByTeacherDetail(JSONObject jsonObject);

    public List<JSONObject> updateScore(JSONObject jsonObject);

}
