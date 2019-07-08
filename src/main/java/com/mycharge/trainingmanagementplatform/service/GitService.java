package com.mycharge.trainingmanagementplatform.service;

import com.mycharge.trainingmanagementplatform.mapper.TeamMapper;
import com.mycharge.trainingmanagementplatform.model.Result;
import org.springframework.beans.factory.annotation.Autowired;

public interface GitService {
    Result sum(int week);

    Result team(int team_id,int week);

    Result language(int team_id);
}
