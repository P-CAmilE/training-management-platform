package com.mycharge.trainingmanagementplatform.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.mapper.AMapper;
import com.mycharge.trainingmanagementplatform.model.A;
import com.mycharge.trainingmanagementplatform.model.Result;
import com.mycharge.trainingmanagementplatform.service.AService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.List;

@Service
public class AServicelmpl implements AService {

    @Autowired
    AMapper aMapper;

    @Override
    public Result save(A a){
        try {
            aMapper.save(a);
        }catch (Exception  e){
            return Result.getResult(0);
        }

        return Result.getResult(1);
    }

    @Override
    public Result findByAname(String aname){
        List<A> la;
        try {
            la=aMapper.findByAname(aname);
        }catch (Exception e){
            return Result.getResult(0);
        }
        Result res = Result.getResult(1);
        res.put("data",la);
        return res;
    }
}
