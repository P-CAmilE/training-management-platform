package com.mycharge.trainingmanagementplatform.controller;

import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.model.Result;
import com.mycharge.trainingmanagementplatform.service.AService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin//测试用
@RestController
public class AController {
    @Autowired
    AService service;

    @RequestMapping("/Asave")
    public Result save(@RequestBody JSONObject object){ return service.save(object); }

    @RequestMapping("/Afind")
    public Result findByAname(@RequestBody JSONObject object){
        return service.findByAname(object);
    };

    @RequestMapping("/json")
    public Result getJson(@RequestBody JSONObject json){
        Result res=Result.getResult("成功");

        res.put("time",new Date());
        res.put("data",json);
        return  res;
    }
}
