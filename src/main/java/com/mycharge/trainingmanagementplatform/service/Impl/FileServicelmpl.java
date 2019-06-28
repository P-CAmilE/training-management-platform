package com.mycharge.trainingmanagementplatform.service.Impl;

import com.mycharge.trainingmanagementplatform.mapper.FileMapper;
import com.mycharge.trainingmanagementplatform.model.MyFile;
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
    FileMapper fileMapper;

    private String path="d:/upload/";//下载路径

    //上传
    @Override
    public String upload(MultipartFile file, HttpServletRequest request) throws IOException {
        //String path = request.getSession().getServletContext().getRealPath("upload");
        String fileName = file.getOriginalFilename();
        File dir = new File(path,fileName);
        if(!dir.exists()){
            dir.createNewFile();
        }
        file.transferTo(dir);

        //插入数据库
        MyFile myFile = new MyFile();
        myFile.setFid(null);
        myFile.setPath(dir.getPath());
        fileMapper.upload(myFile);

        return  fileName;
    }

    //下载
    @Override
    public void download(HttpServletRequest request, HttpServletResponse response) throws Exception{
        try {
            String fileName = request.getParameter("filename");;
            String filePath = path+ fileName;;
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
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
