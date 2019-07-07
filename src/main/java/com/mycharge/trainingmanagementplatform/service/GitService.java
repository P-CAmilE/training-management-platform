package com.mycharge.trainingmanagementplatform.service;

import com.mycharge.trainingmanagementplatform.mapper.TeamMapper;
import com.mycharge.trainingmanagementplatform.model.Result;
import org.springframework.beans.factory.annotation.Autowired;

public interface GitService {
    Result sum(int flag);
}
