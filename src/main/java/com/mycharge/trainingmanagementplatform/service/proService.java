package com.mycharge.trainingmanagementplatform.service;

import com.mycharge.trainingmanagementplatform.model.MyObject;
import com.mycharge.trainingmanagementplatform.model.Result;

public interface proService {
    public Result insert(MyObject myObject);

    public Result find(MyObject myObject);

    public Result update(MyObject myObject);

    public Result delete(MyObject myObject);
}
