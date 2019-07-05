package com.mycharge.trainingmanagementplatform.service.Impl;


import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.mapper.MessageMapper;
import com.mycharge.trainingmanagementplatform.model.Result;
import com.mycharge.trainingmanagementplatform.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServicelmpl implements MessageService {

    @Autowired
    MessageMapper mapper;

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
            Result res = Result.getResult(1);
            res.put("data",mapper.delete(object));
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return Result.getResult(0);
        }
    }
}