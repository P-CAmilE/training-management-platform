package com.mycharge.trainingmanagementplatform.service;

import com.mycharge.trainingmanagementplatform.model.A;
import com.mycharge.trainingmanagementplatform.model.Result;

import java.util.List;

public interface AService {
    public Result save(A a);

    public Result findByAname(String aname);
}
