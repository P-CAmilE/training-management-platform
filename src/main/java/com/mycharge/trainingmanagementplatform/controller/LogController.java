package com.mycharge.trainingmanagementplatform.controller;


import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.model.Result;
import com.mycharge.trainingmanagementplatform.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LogController {

    @Autowired
    private LogService logService;

    @PostMapping("/login")
    public Result userLogIn(@RequestBody JSONObject object, HttpServletResponse response) {
        Cookie cookie1=new Cookie("username", object.getString("username"));
        cookie1.setPath("/");// 这个要设置
        cookie1.setMaxAge(365 * 24 * 60 * 60);
        response.addCookie(cookie1);
        return Result.getResult(1);
    }

    @PostMapping("/register")
    public void userRegister(@RequestBody JSONObject object){

        logService.register(object);

    }

}
