package com.mycharge.trainingmanagementplatform.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.mapper.CompanyMapper;
import com.mycharge.trainingmanagementplatform.model.Result;
import com.mycharge.trainingmanagementplatform.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServicelmpl implements CompanyService  {

    @Autowired
    private CompanyMapper mapper;

    @Override
    public Result insert(JSONObject jsonObject) {
        try{
            Result res = Result.getResult(1);
            res.put("data",mapper.insert(jsonObject));
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return Result.getResult(0);
        }
    }

    @Override
    public Result find(JSONObject jsonObject) {
        try{
            Result res = Result.getResult(1);
            res.put("data",mapper.find(jsonObject));
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return Result.getResult(0);
        }
    }

    @Override
    public Result update(JSONObject jsonObject) {
        try{
            Result res = Result.getResult(1);
            res.put("data",mapper.update(jsonObject));
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return Result.getResult(0);
        }
    }

    @Override
    public Result delete(JSONObject jsonObject) {
        try{
            Result res = Result.getResult(1);
            res.put("data",mapper.delete(jsonObject));
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return Result.getResult(0);
        }
    }

}
