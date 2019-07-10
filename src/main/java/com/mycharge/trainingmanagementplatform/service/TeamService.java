package com.mycharge.trainingmanagementplatform.service;

import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.model.Result;
import com.mycharge.trainingmanagementplatform.service.proService;

public interface TeamService extends proService {

    public Result findPlanProject(JSONObject jsonObject);

    public Result findStudent(JSONObject jsonObject);

    public Result findTeamDetail(JSONObject jsonObject);

    public Result findByTeacher(JSONObject jsonObject);

    public Result findByStudent(JSONObject jsonObject);

    public Result scoreForTeam(JSONObject jsonObject);
}
