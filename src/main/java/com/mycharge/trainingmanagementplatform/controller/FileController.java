package com.mycharge.trainingmanagementplatform.controller;

import com.mycharge.trainingmanagementplatform.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class FileController {

    @Autowired
    FileService service;

    @RequestMapping(value="/upload",method= RequestMethod.POST)
    @ResponseBody
    public String upload(MultipartFile file, HttpServletRequest request){
        String s;
        try {
            s = service.upload(file,request);
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败";
        }
        return s;
    }

    @RequestMapping(value = "/download",method = RequestMethod.GET)
    public void download(HttpServletRequest request, HttpServletResponse response){
        try {
            service.download(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

