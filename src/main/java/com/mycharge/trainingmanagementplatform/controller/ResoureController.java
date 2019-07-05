package com.mycharge.trainingmanagementplatform.controller;

import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.model.Result;
import com.mycharge.trainingmanagementplatform.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/resource")
public class ResoureController {

    @Autowired
    ResourceService service;

    @RequestMapping(value="/upload",method= RequestMethod.POST)
    public Result upload(MultipartFile file, HttpServletRequest request,HttpServletResponse response){
         service.upload(file,request,response);
         return Result.getResult(1);
    }

    @RequestMapping(value = "/download",method = RequestMethod.GET)
    public void download(HttpServletRequest request, HttpServletResponse response){
            service.download(request,response);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Result delete(@RequestBody JSONObject object){
        return service.delete(object);
    }

    @RequestMapping("/find")
    public Result find(@RequestBody JSONObject object){
        return service.find(object);
    }
}

