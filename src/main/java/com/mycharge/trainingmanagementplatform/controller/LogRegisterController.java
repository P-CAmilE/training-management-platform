package com.mycharge.trainingmanagementplatform.controller;

import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.model.*;
import com.mycharge.trainingmanagementplatform.service.LogRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LogRegisterController {

    @Autowired
    private LogRegisterService logService;

    @RequestMapping("/login")
    public Result logIn(@RequestBody JSONObject jsonObject, HttpServletResponse response, HttpServletRequest request){
        Result loginResult = logService.login(jsonObject, response, request);
        return loginResult;
    }

    @RequestMapping("/register")
    public Result userRegister(@RequestBody JSONObject jsonObject){
        Result registerResult = logService.register(jsonObject);
        return registerResult;
    }
}
