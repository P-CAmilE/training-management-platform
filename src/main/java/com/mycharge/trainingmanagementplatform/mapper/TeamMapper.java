package com.mycharge.trainingmanagementplatform.mapper;

import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.model.Result;

import java.util.List;

public interface TeamMapper extends Mapper {

    public int insertTeamStudent(JSONObject jsonObject);

    public int deleteTeamStudent(JSONObject jsonObject);

    public List<JSONObject> findByTeacher(JSONObject jsonObject);

    public List<JSONObject> findByStudent(JSONObject jsonObject);

    public int scoreForTeam(JSONObject jsonObject);

    public List<JSONObject> findTeamScore(JSONObject jsonObject);

    public List<JSONObject>  updateScore(JSONObject jsonObject);

}

