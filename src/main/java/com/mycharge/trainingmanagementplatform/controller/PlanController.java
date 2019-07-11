package com.mycharge.trainingmanagementplatform.controller;

import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.global.Role;
import com.mycharge.trainingmanagementplatform.model.Result;
import com.mycharge.trainingmanagementplatform.service.PlanService;
import com.mycharge.trainingmanagementplatform.utility.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/plan")
public class PlanController {

    @Autowired
    PlanService service;

    //todo:权限控制

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

    //在修改界面确认修改
    //传入;页面内所有信息
    @RequestMapping("/update")
    public Result update(@RequestBody JSONObject object, HttpServletRequest request){
        if(Validate.valiToken(request)&&Validate.valiRole(request, Role.role_group.get("admin")))
            return service.update(object);
        else
            return Result.getResult(0).put("msg","未登录");
    }

    // 点击删除
    //传入：plan_id
    @RequestMapping("/delete")
    public Result delete(@RequestBody JSONObject object, HttpServletRequest request){
        if(Validate.valiToken(request)&&Validate.valiRole(request, Role.role_group.get("admin")))
            return service.delete(object);
        else
            return Result.getResult(0).put("msg","未登录");
    }

//    在点击新建计划按钮与修改按钮时调用
//    传入：无
//    返回：school：所有学校的sch_id,sch_name; company：所有公司的com_id,com_name
    @RequestMapping("/findUnit")
    public Result findUnit(HttpServletRequest request){
        if(Validate.valiToken(request)&&Validate.valiRole(request, Role.role_group.get("admin")))
            return service.findUnit();
        else
            return Result.getResult(0).put("msg","未登录");
    }

//    在修改界面中点击老师按钮时调用
//    传入：sch_name,com_name
//    返回：tea_id,tea_name
    @RequestMapping("/findTeacherByUnit")
    public Result  findTeacherByUnit(@RequestBody JSONObject object, HttpServletRequest request){
        if(Validate.valiToken(request)&&Validate.valiRole(request, Role.role_group.get("admin")))
            return service.findTeacherByUnit(object);
        else
            return Result.getResult(0).put("msg","未登录");
    }

//    在计划页面中点击查看按钮时调用
//    传入：plan-id
//    返回：data：该plan_id对应plan中所有属; teacher：tea_id,tea_name
    @RequestMapping("/findPlanDetail")
    public Result  findPlanDetail(@RequestBody JSONObject object, HttpServletRequest request){
        if(Validate.valiToken(request))
            return service.findPlanDetail(object);
        else
            return Result.getResult(0).put("msg","未登录");
    }

}
