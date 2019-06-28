package com.mycharge.trainingmanagementplatform.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface FileService {
    public String upload(MultipartFile file, HttpServletRequest request) throws IOException;

    public void download(HttpServletRequest request, HttpServletResponse response)throws Exception;
}
