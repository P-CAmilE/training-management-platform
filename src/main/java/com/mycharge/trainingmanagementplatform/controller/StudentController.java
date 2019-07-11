package com.mycharge.trainingmanagementplatform.controller;


import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.global.Role;
import com.mycharge.trainingmanagementplatform.model.Result;
import com.mycharge.trainingmanagementplatform.service.LogRegisterService;
import com.mycharge.trainingmanagementplatform.service.StudentService;
import com.mycharge.trainingmanagementplatform.utility.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService service;
    @Autowired
    private LogRegisterService logRegisterService;

    @RequestMapping("/insert")
    public Result insert(@RequestBody JSONObject object, HttpServletRequest request){
        if(Validate.valiToken(request))
            return logRegisterService.register(object);
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
        if(Validate.valiToken(request)&&Validate.valiRole(request, Role.role_group.get("admin")))
            return service.delete(object);
        else
            return Result.getResult(0).put("msg","未登录");
    }


    @RequestMapping("/findSchool")
    public Result findSchool(HttpServletRequest request){
        if(Validate.valiToken(request))
            return service.findSchool();
        else
            return Result.getResult(0).put("msg","未登录");
    }

    @RequestMapping("/scoreForStudent")
    public Result scoreForStudent(@RequestBody JSONObject object, HttpServletRequest request){
        if(Validate.valiToken(request)&&Validate.valiRole(request, Role.role_group.get("all_but_stu")))
            return service.scoreForStudent(object);
        else
            return Result.getResult(0).put("msg","未登录");
    }

    @RequestMapping("/findByTeacher")
    public Result findByTeacher(@RequestBody JSONObject object, HttpServletRequest request){
        if(Validate.valiToken(request)&&Validate.valiRole(request, Role.role_group.get("all_but_stu")))
            return service.findByTeacher(object);
        else
            return Result.getResult(0).put("msg","未登录");
    }



}
