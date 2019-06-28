package com.mycharge.trainingmanagementplatform.controller;

import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.model.A;
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
    public Result save(@RequestBody A a){ return service.save(a); }

    @RequestMapping("/Afind")
    @ResponseBody
    public Result findByAname(@RequestParam String aname){
        return service.findByAname(aname);
    };

    @RequestMapping("/json")
    public Result getJson(@RequestBody JSONObject json){
        Result res=Result.getResult("成功");
        //json.getString("name");
        res.put("data",json);
        return  res;
    }


}
