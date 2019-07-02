package com.mycharge.trainingmanagementplatform.controller;

import com.mycharge.trainingmanagementplatform.model.Result;
import com.mycharge.trainingmanagementplatform.model.MyObject;
import com.mycharge.trainingmanagementplatform.service.Stu_SigninService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin//测试用
@RestController
@RequestMapping("/stusignin")
public class Stu_SigninController {

    @Autowired
    Stu_SigninService service;

    @RequestMapping("/signin")
    public Result signin(@RequestBody MyObject myObject){
        return  service.signin(myObject);
    }

    @RequestMapping("/find")
    public Result find(@RequestBody MyObject myObject){
        return service.find(myObject);
    }
}
