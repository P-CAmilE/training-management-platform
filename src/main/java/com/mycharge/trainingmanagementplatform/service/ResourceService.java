package com.mycharge.trainingmanagementplatform.service;

import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.model.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ResourceService {
    public Result upload(MultipartFile file, HttpServletRequest request,HttpServletResponse response);

    public void download(HttpServletRequest request, HttpServletResponse response);

    public Result find(JSONObject object);

    public Result delete(JSONObject object);

    public Result findTeacherResource(JSONObject object);
}
