package com.mycharge.trainingmanagementplatform.controller;


import com.alibaba.fastjson.*;
import com.mycharge.trainingmanagementplatform.model.*;
import com.mycharge.trainingmanagementplatform.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@CrossOrigin
@Controller
public class LogController {

    @Autowired
    private LogService logService;

    @PostMapping("/login")
    @ResponseBody
    public MSG logIn(@RequestBody JSONObject jsonObject){
        MSG loginMSG = logService.login(jsonObject.getString("username"),
                jsonObject.getString("password"), jsonObject.getInteger("usertype"));
        return loginMSG;
    }

    @PostMapping(value = "/register")
    @ResponseBody
    public MSG userRegister(@RequestBody JSONObject jsonObject){
        MSG registerMSG = logService.register(jsonObject.getString("username"), jsonObject.getString("password"),
                jsonObject.getInteger("usertype"), jsonObject.getString("useremail"));
        return registerMSG;
    }

}
