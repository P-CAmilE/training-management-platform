package com.mycharge.trainingmanagementplatform.controller;

import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.model.A;
import com.mycharge.trainingmanagementplatform.model.MyObject;
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
    public Result save(@RequestBody MyObject myObject){ return service.save(myObject); }

    @RequestMapping("/Afind")
    @ResponseBody
    public Result findByAname(@RequestBody MyObject myObject){
        return service.findByAname(myObject);
    };

    @RequestMapping("/json")
    public Result getJson(@RequestBody JSONObject json){
        Result res=Result.getResult("成功");

        res.put("time",new Date());
        res.put("data",json);
        return  res;
    }
}
