package com.mycharge.trainingmanagementplatform.service;

import com.mycharge.trainingmanagementplatform.model.MyObject;
import com.mycharge.trainingmanagementplatform.model.Result;

public interface AService {
    public Result save(MyObject myObject);

    public Result findByAname(MyObject myObject);
}
