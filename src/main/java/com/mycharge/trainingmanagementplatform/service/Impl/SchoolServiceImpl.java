package com.mycharge.trainingmanagementplatform.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.mapper.SchoolMapper;
import com.mycharge.trainingmanagementplatform.model.Result;
import com.mycharge.trainingmanagementplatform.service.SchoolSerivice;
import org.springframework.beans.factory.annotation.Autowired;

public class SchoolServiceImpl implements SchoolSerivice {

    @Autowired
    private SchoolMapper schoolMapper;


    @Override
    public Result insert(JSONObject jsonObject) {
        try{
            Result res = Result.getResult(1);
            res.put("data",schoolMapper.insert(jsonObject));
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
            res.put("data",schoolMapper.find(jsonObject));
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
            res.put("data",schoolMapper.update(jsonObject));
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
            res.put("data",schoolMapper.delete(jsonObject));
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return Result.getResult(0);
        }
    }
}
