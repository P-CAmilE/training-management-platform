package com.mycharge.trainingmanagementplatform.controller;


import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.mapper.StudentMapper;
import com.mycharge.trainingmanagementplatform.model.Result;
import com.mycharge.trainingmanagementplatform.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdministratorService service;
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


}
