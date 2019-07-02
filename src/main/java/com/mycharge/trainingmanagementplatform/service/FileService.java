package com.mycharge.trainingmanagementplatform.service;

import com.mycharge.trainingmanagementplatform.model.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface FileService {
    public Result upload(MultipartFile file, HttpServletRequest request,HttpServletResponse response);

    public void download(HttpServletRequest request, HttpServletResponse response);
}
