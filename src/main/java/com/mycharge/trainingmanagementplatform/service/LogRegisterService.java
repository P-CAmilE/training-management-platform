package com.mycharge.trainingmanagementplatform.service;


import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.model.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LogRegisterService {

    public Result login(JSONObject jsonObject, HttpServletResponse respons, HttpServletRequest request);

    public Result register(JSONObject jsonObject);


}
