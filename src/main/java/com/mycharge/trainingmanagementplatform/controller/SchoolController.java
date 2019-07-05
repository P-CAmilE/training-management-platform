package com.mycharge.trainingmanagementplatform.controller;

import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.model.Result;
import com.mycharge.trainingmanagementplatform.service.SchoolSerivice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/school")
public class SchoolController {

    @Autowired
    SchoolSerivice service;

    //todo:权限控制

    @RequestMapping("/insert")
    public Result insert(@RequestBody JSONObject myObject){
        return service.insert(myObject);
    }

    @RequestMapping("/find")
    public Result find(@RequestBody JSONObject myObject){
        return service.find(myObject);

    }
    @RequestMapping("/update")
    public Result update(@RequestBody JSONObject myObject){
        return service.update(myObject);
    }

    @RequestMapping("/delete")
    public Result delete(@RequestBody JSONObject myObject){
        return service.delete(myObject);
    }
}
