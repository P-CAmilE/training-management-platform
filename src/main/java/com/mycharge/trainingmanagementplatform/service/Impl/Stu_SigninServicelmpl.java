package com.mycharge.trainingmanagementplatform.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.mapper.Stu_SigninMapper;
import com.mycharge.trainingmanagementplatform.model.Result;
import com.mycharge.trainingmanagementplatform.service.Stu_SigninService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class Stu_SigninServicelmpl implements Stu_SigninService {
    static private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    @Autowired
    Stu_SigninMapper mapper;

    @Override
    public Result signin(JSONObject object) {
        Result res;
        try {
            object.put("time",dateFormat.format(new Date()));
            mapper.signin(object);
        }catch (Exception  e){
             res = Result.getResult(0);
             res.put("msg",e.getMessage());
            return res;
        }
        return Result.getResult(1);
    }

    @Override
    public Result find(JSONObject object) {
        Result res;
        List<JSONObject> list;
        try {
            list=mapper.find(object);
        }catch (Exception e){
            res=Result.getResult(0);
            res.put("msg",e.getMessage());
            return res;
        }
        res = Result.getResult(1);
        res.put("data",list);
        return res;
    }
}
