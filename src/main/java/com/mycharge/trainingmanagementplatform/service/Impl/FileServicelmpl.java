package com.mycharge.trainingmanagementplatform.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.mapper.FileMapper;
import com.mycharge.trainingmanagementplatform.model.Result;
import com.mycharge.trainingmanagementplatform.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Service
public class FileServicelmpl implements FileService {
    @Autowired
    FileMapper mapper;

    private String path="d:/upload/";//下载路径
    //上传
    @Override
    public Result upload(MultipartFile file, HttpServletRequest request ,HttpServletResponse response){
        try {
            //String path = request.getSession().getServletContext().getRealPath("upload");
            String fileName = file.getOriginalFilename();
            String username = request.getParameter("username");

            //如果指定储存文件名
            String storage_Name = request.getParameter("storage_Name");
            if(storage_Name!=null){
                fileName=storage_Name;
            }

            File dir = new File(path + username + "/", fileName);
            if (!dir.getParentFile().exists()) {
                dir.getParentFile().mkdir();
            }
            if (!dir.exists()) {
                dir.createNewFile();
            }
            file.transferTo(dir);
            //插入数据库

            JSONObject object = new JSONObject();
            object.put("path",dir.getPath());
            mapper.upload(object);
            return  Result.getResult(1);
        }catch (IOException e){
            e.printStackTrace();
            return  Result.getResult(0);
        }
    }

    //下载
    @Override
    public void download(HttpServletRequest request, HttpServletResponse response){
        try {
            String username = request.getParameter("username");;
            String fileName = request.getParameter("filename");;
            String filePath = path+username+"/"+fileName;
            InputStream bis = new BufferedInputStream(new FileInputStream(new File(filePath)));
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.setContentType("multipart/form-data");
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
            int len = 0;
            while ((len = bis.read()) != -1) {
                out.write(len);
                out.flush();
            }
            out.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
