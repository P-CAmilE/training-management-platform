package com.mycharge.trainingmanagementplatform.controller;


import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.model.Result;
import com.mycharge.trainingmanagementplatform.service.LogRegisterService;
import com.mycharge.trainingmanagementplatform.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService service;
    @Autowired
    private LogRegisterService logRegisterService;

    @RequestMapping("/insert")
    public Result insert(@RequestBody JSONObject object){
        return logRegisterService.register(object);
    }
    @RequestMapping("/find")
    public Result find(@RequestBody JSONObject object){
        return service.find(object);
    }

    @RequestMapping("/update")
    public Result update(@RequestBody JSONObject object){
        return service.update(object);
    }

    @RequestMapping("/delete")
    public Result delete(@RequestBody JSONObject object){
        return service.delete(object);
    }

    @RequestMapping("/findUnit")
    public Result findUnit(){
        return service.findUnit();
    }




}