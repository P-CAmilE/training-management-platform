package com.mycharge.trainingmanagementplatform.service;


import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.model.*;

import javax.servlet.http.HttpServletResponse;

public interface LogService {

    public Result login(JSONObject jsonObject, HttpServletResponse response);

    public Result register(JSONObject jsonObject, HttpServletResponse response);


}
