package com.mycharge.trainingmanagementplatform.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.mapper.ProjectMapper;
import com.mycharge.trainingmanagementplatform.model.Result;
import com.mycharge.trainingmanagementplatform.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServicelmpl implements ProjectService {

    @Autowired
    ProjectMapper mapper;

    @Override
    public Result insert(JSONObject object) {
        try{
            Result res = Result.getResult(1);
            res.put("data",mapper.insert(object));
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return Result.getResult(0);
        }
    }

    @Override
    public Result find(JSONObject object) {
        try{
            Result res = Result.getResult(1);
            res.put("data",mapper.find(object));
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return Result.getResult(0);
        }
    }

    @Override
    public Result update(JSONObject object) {
        try{
            Result res = Result.getResult(1);
            res.put("data",mapper.update(object));
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return Result.getResult(0);
        }
    }

    @Override
    public Result delete(JSONObject object) {
        try{
            //todo:权限控制
            Result res = Result.getResult(1);
            res.put("data",mapper.delete(object));
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return Result.getResult(0);
        }
    }

}
