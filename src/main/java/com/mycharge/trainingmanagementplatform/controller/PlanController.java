package com.mycharge.trainingmanagementplatform.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.model.Result;
import com.mycharge.trainingmanagementplatform.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/plan")
public class PlanController {

    @Autowired
    PlanService service;

    //todo:权限控制

    @RequestMapping("/insert")
    public Result insert(@RequestBody JSONObject object){
        return service.insert(object);
    }

    @RequestMapping("/find")
    public Result find(@RequestBody JSONObject object){
        return service.find(object);
    }

    //在修改界面确认修改
    //传入;页面内所有信息
    @RequestMapping("/update")
    public Result update(@RequestBody JSONObject object){
        return service.update(object);
    }

    // 点击删除
    //传入：plan_id
    @RequestMapping("/delete")
    public Result delete(@RequestBody JSONObject object){
        return service.delete(object);
    }

//    在点击新建计划按钮与修改按钮时调用
//    传入：无
//    返回：school：所有学校的sch_id,sch_name; company：所有公司的com_id,com_name
    @RequestMapping("/findUnit")
    public Result findUnit(){
        return service.findUnit();
    }

//    在修改界面中点击老师按钮时调用
//    传入：sch_id,com_id
//    返回：tea_id,tea_name
    @RequestMapping("/findTeacherByUnit")
    public Result  findTeacherByUnit(@RequestBody JSONObject object){
        return service.findTeacherByUnit(object);
    }

//    在计划页面中点击查看按钮时调用
//    传入：plan-id
//    返回：data：该plan_id对应plan中所有属; teacher：tea_id,tea_name
    @RequestMapping("/findPlanDetail")
    public Result  findPlanDetail(@RequestBody JSONObject object){
        return service.findPlanDetail(object);
    }

}
