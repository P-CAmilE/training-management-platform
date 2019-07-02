package com.mycharge.trainingmanagementplatform.service;


import com.mycharge.trainingmanagementplatform.model.MyObject;

import javax.servlet.http.HttpServletResponse;

public interface LogService {

    public int login(MyObject myObject);

    public int register(MyObject myObject);


}
