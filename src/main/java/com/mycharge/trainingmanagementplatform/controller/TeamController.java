package com.mycharge.trainingmanagementplatform.controller;


import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.model.Result;
import com.mycharge.trainingmanagementplatform.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamService teamService;


    @RequestMapping("/insert")
    public Result insert(@RequestBody JSONObject object){
        return teamService.insert(object);
    }
    @RequestMapping("/find")
    public Result find(@RequestBody JSONObject object){
        return teamService.find(object);
    }

    @RequestMapping("/update")
    public Result update(@RequestBody JSONObject object){
        return teamService.update(object);
    }

    @RequestMapping("/delete")
    public Result delete(@RequestBody JSONObject object){
        return teamService.delete(object);
    }

    @RequestMapping("/findPlanProject")
    public Result findPlanProject(){
        return teamService.findPlanProject();
    }

    @RequestMapping("/findStudent")
    public Result findStudent(@RequestBody JSONObject object){
        return teamService.findStudent(object);
    }
}
