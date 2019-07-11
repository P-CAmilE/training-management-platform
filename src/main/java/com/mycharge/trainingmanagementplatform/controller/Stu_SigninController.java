package com.mycharge.trainingmanagementplatform.controller;

import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.model.Result;
import com.mycharge.trainingmanagementplatform.service.Stu_SigninService;
import com.mycharge.trainingmanagementplatform.utility.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/stusignin")
public class Stu_SigninController {

    @Autowired
    Stu_SigninService service;

    @RequestMapping("/signin")
    public Result signin(@RequestBody JSONObject object, HttpServletRequest request){
        if(Validate.valiToken(request))
            return service.signin(object);
        else
            return Result.getResult(0).put("msg","未登录");
    }

    @RequestMapping("/find")
    public Result find(@RequestBody JSONObject object, HttpServletRequest request){
        if(Validate.valiToken(request))
            return service.find(object);
        else
            return Result.getResult(0).put("msg","未登录");
    }
}
