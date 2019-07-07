package com.mycharge.trainingmanagementplatform.controller;

import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.model.Result;
import com.mycharge.trainingmanagementplatform.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
public class  ProjectController {

    @Autowired
    ProjectService service;

    //todo:权限控制

    @RequestMapping("/insert")
    public Result insert(@RequestBody JSONObject object){
        return service.insert(object);
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

    @RequestMapping("/findPlanName")
    public Result findPlanName(){
        return service.findPlanName();
    }

    @RequestMapping("/findTeacherByPlan")
    public Result findTeacherByPlan(@RequestBody JSONObject object){
        return service.findTeacherByPlan(object);
    }

    @RequestMapping("/findProjectDetail")
    public Result findProjectDetail(@RequestBody JSONObject object){
        return service.findProjectDetail(object);
    }

    @RequestMapping("/findByStudent")
    public Result findByStudent(@RequestBody JSONObject object){
        return service.findByStudent(object);
    }
}