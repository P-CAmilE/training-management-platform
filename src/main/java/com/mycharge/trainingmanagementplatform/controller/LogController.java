package com.mycharge.trainingmanagementplatform.controller;

import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.model.*;
import com.mycharge.trainingmanagementplatform.service.LogService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@CrossOrigin
@Controller
public class LogController {

    @Autowired
    private LogService logService;

    @PostMapping("/login")
    @ResponseBody
    public Result logIn(@RequestBody JSONObject jsonObject, HttpServletResponse response){
        Result loginResult = logService.login(jsonObject, response);
        return loginResult;
    }

    @PostMapping(value = "/register")
    @ResponseBody
    public Result userRegister(@RequestBody JSONObject jsonObject, HttpServletResponse response){
        Result registerResult = logService.register(jsonObject, response);
        return registerResult;
    }
}
