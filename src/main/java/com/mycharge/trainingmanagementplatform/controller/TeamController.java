package com.mycharge.trainingmanagementplatform.controller;


import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.global.Role;
import com.mycharge.trainingmanagementplatform.model.Result;
import com.mycharge.trainingmanagementplatform.service.TeamService;
import com.mycharge.trainingmanagementplatform.utility.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamService service;


    @RequestMapping("/insert")
    public Result insert(@RequestBody JSONObject object, HttpServletRequest request){
        if(Validate.valiToken(request)&&Validate.valiRole(request, Role.role_group.get("admin")))
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
        if(Validate.valiToken(request)&&Validate.valiRole(request, Role.role_group.get("admin")))
            return service.delete(object);
        else
            return Result.getResult(0).put("msg","未登录");
    }

    @RequestMapping("/findPlanProject")
    public Result findPlanProject(@RequestBody JSONObject object, HttpServletRequest request){
        if(Validate.valiToken(request))
            return service.findPlanProject(object);
        else
            return Result.getResult(0).put("msg","未登录");
    }

    @RequestMapping("/findStudent")
    public Result findStudent(@RequestBody JSONObject object, HttpServletRequest request){
        if(Validate.valiToken(request))
            return service.findStudent(object);
        else
            return Result.getResult(0).put("msg","未登录");
    }

    @RequestMapping("/findTeamDetail")
    public Result findTeamDetail(@RequestBody JSONObject object, HttpServletRequest request){
        if(Validate.valiToken(request))
            return service.findTeamDetail(object);
        else
            return Result.getResult(0).put("msg","未登录");
    }

    @RequestMapping("/findByTeacher")
    public Result findByTeacher(@RequestBody JSONObject object, HttpServletRequest request){
        if(Validate.valiToken(request))
            return service.findByTeacher(object);
        else
            return Result.getResult(0).put("msg","未登录");
    }

    @RequestMapping("/findByStudent")
    public Result findByStudent(@RequestBody JSONObject object, HttpServletRequest request){
        if(Validate.valiToken(request))
            return service.findByStudent(object);
        else
            return Result.getResult(0).put("msg","未登录");
    }

    @RequestMapping("/scoreForTeam")
    public Result scoreForTeam(@RequestBody JSONObject object, HttpServletRequest request){
        if(Validate.valiToken(request)&&Validate.valiRole(request, Role.role_group.get("all_but_stu")))
            return service.scoreForTeam(object);
        else
            return Result.getResult(0).put("msg","未登录");
    }
}
