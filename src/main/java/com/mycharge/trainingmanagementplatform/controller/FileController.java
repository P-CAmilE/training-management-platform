package com.mycharge.trainingmanagementplatform.controller;

import com.mycharge.trainingmanagementplatform.model.Result;
import com.mycharge.trainingmanagementplatform.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@CrossOrigin
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    FileService service;

    @RequestMapping(value="/upload",method= RequestMethod.POST)
    public Result upload(MultipartFile file, HttpServletRequest request,HttpServletResponse response){
         service.upload(file,request,response);
         return Result.getResult(1);
    }

    @RequestMapping(value = "/download",method = RequestMethod.GET)
    public void download(HttpServletRequest request, HttpServletResponse response){
            service.download(request,response);
    }



}

