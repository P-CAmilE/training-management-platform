package com.mycharge.trainingmanagementplatform.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.mapper.AMapper;
import com.mycharge.trainingmanagementplatform.model.Result;
import com.mycharge.trainingmanagementplatform.service.AService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AServicelmpl implements AService {

    @Autowired
    AMapper mapper;

    @Override
    public Result save(JSONObject object){
        try {
            mapper.save(object);
        }catch (Exception  e){
            return Result.getResult(0);
        }

        return Result.getResult(1);
    }

    @Override
    public Result findByAname(JSONObject object){
        List<JSONObject> list;
        try {
            list=mapper.findByAname(object);
        }catch (Exception e){
            return Result.getResult(0);
        }
        Result res = Result.getResult(1);
        res.put("data",list);
        return res;
    }
}
