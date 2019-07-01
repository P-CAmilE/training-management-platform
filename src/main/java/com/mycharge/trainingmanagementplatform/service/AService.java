package com.mycharge.trainingmanagementplatform.service;

import com.mycharge.trainingmanagementplatform.model.A;
import com.mycharge.trainingmanagementplatform.model.MyObject;
import com.mycharge.trainingmanagementplatform.model.Result;

import java.util.List;

public interface AService {
    public Result save(MyObject myObject);

    public Result findByAname(MyObject myObject);
}
