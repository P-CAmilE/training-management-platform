package com.mycharge.trainingmanagementplatform.mapper;

import com.alibaba.fastjson.JSONObject;

public interface TeamMapper extends Mapper {

    public int insertTeamStudent(JSONObject jsonObject);

    public int deleteTeamStudent(JSONObject jsonObject);
}
