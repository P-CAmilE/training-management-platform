package com.mycharge.trainingmanagementplatform.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.mapper.ResourceMapper;
import com.mycharge.trainingmanagementplatform.model.Result;
import com.mycharge.trainingmanagementplatform.service.ResourceService;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Service
public class ResourceServicelmpl implements ResourceService {
    @Autowired
    ResourceMapper mapper;

    private String path="d:/upload/";//下载路径
    //上传
    @Override
    public Result upload(MultipartFile file, HttpServletRequest request ,HttpServletResponse response){
        FileWriter fileWriter=null;
        try {
            //String path = request.getSession().getServletContext().getRealPath("upload");
            String fileName = file.getOriginalFilename();
            String owner = request.getParameter("owner_id");//一个用户有一个目录
            String type = request.getParameter("type");//一个用户有一个目录
            //如果指定储存文件名
            String storage_name = request.getParameter("storage_name");
            if(storage_name!=null){
                fileName=storage_name;
            }

            File dir = new File(path + owner + "/"+ type+ "/", fileName);
            //目录不存在
            if (!dir.getParentFile().exists()) {
                dir.getParentFile().mkdirs();
            }

            if (!dir.exists()) {
                //插入数据库
                JSONObject object = new JSONObject();
                object.put("resource_path",dir.getPath());
                object.put("resource_name",fileName);
                object.put("owner_id",owner);
                mapper.upload(object);
            }else {
                dir.delete();
            }


            dir.createNewFile();
//            fileWriter =new FileWriter(dir);
//            fileWriter.write("");
//            fileWriter.flush();
//            fileWriter.close();
            file.transferTo(dir);

            return  Result.getResult(1);

        }catch (IOException e){
            e.printStackTrace();
            return  Result.getResult(0);
        }
        //关闭流
//      finally {
//            try {
//                fileWriter.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

    //下载
    @Override
    public void download(HttpServletRequest request, HttpServletResponse response){
        InputStream bis = null;
        BufferedOutputStream out = null;
        try {
            String owner = request.getParameter("owner_id");
            String type = request.getParameter("type");
            String fileName = request.getParameter("filename");
            String filePath = path+owner+"/"+type+"/"+fileName;
            bis = new BufferedInputStream(new FileInputStream(new File(filePath)));
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.setContentType("multipart/form-data");
            out = new BufferedOutputStream(response.getOutputStream());
            int len = 0;
            while ((len = bis.read()) != -1) {
                out.write(len);
                out.flush();
            }
            //out.close();
            //bis.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        //关闭流
        finally{
            try {
                bis.close();
                out.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public Result find(JSONObject object) {
        try{
            Result res=Result.getResult(1);
            res.put("data",mapper.find(object));
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return  Result.getResult(0);
        }
    }

    @Override
    public Result delete(JSONObject object) {
        int del_num=0;//成功删除的数目
        try{
            List<JSONObject> list = mapper.find(object);

            for(JSONObject oj:list){
                File file = new File(oj.getString("resource_path"));
                file.delete();
                mapper.delete(object);
                del_num++;
            }

            Result res=Result.getResult(1);
            res.put("data",del_num);
            return res;
        }catch (Exception e){
            e.printStackTrace();
            Result res=Result.getResult(0);
            res.put("data",del_num);
            return res;
        }
    }

    @Override
    public Result findTeacherResource(JSONObject object) {
        try{
            Result res=Result.getResult(1);
            res.put("data",mapper.findTeacherResource(object));
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return  Result.getResult(0);
        }
    }
}
