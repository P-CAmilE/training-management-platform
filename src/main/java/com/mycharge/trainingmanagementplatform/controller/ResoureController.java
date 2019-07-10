package com.mycharge.trainingmanagementplatform.controller;

import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.model.Result;
import com.mycharge.trainingmanagementplatform.service.ResourceService;
import com.mycharge.trainingmanagementplatform.utility.Validate;
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
        if(Validate.valiToken(request)){
            service.upload(file,request,response);
            return Result.getResult(1);
        }
        else
            return Result.getResult(0).put("msg","未登录");
    }

    @RequestMapping(value = "/download",method = RequestMethod.GET)
    public void download(HttpServletRequest request, HttpServletResponse response){
        service.download(request,response);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Result delete(@RequestBody JSONObject object,HttpServletRequest request){
        if(Validate.valiToken(request))
            return service.delete(object);
        else
            return Result.getResult(0).put("msg","未登录");
    }

    @RequestMapping("/find")
    public Result find(@RequestBody JSONObject object,HttpServletRequest request){
        if(Validate.valiToken(request))
            return service.find(object);
        else
            return Result.getResult(0).put("msg","未登录");
    }

    @RequestMapping("/findTeacherResource")
    public Result findTeacherResource(@RequestBody JSONObject object,HttpServletRequest request){
        if(Validate.valiToken(request))
            return service.findTeacherResource(object);
        else
            return Result.getResult(0).put("msg","未登录");
    }
}

