package com.mycharge.trainingmanagementplatform.controller;

import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.model.Result;
import com.mycharge.trainingmanagementplatform.service.LogService;
import com.mycharge.trainingmanagementplatform.service.MessageService;
import com.mycharge.trainingmanagementplatform.utility.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    LogService service;

    //todo:权限控制

    @RequestMapping("/insert")
    public Result insert(@RequestBody JSONObject object, HttpServletRequest request){
        if(Validate.valiToken(request)&&Validate.valiRole(request,7))
            return service.insert(object);
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

    @RequestMapping("/update")
    public Result update(@RequestBody JSONObject object, HttpServletRequest request){
        if(Validate.valiToken(request))
            return service.update(object);
        else
            return Result.getResult(0).put("msg","未登录");
    }

    @RequestMapping("/delete")
    public Result delete(@RequestBody JSONObject object, HttpServletRequest request){
        if(Validate.valiToken(request))
            return service.delete(object);
        else
            return Result.getResult(0).put("msg","未登录");
    }
}