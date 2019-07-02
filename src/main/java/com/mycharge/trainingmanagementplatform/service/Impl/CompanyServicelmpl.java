package com.mycharge.trainingmanagementplatform.service.Impl;

import com.mycharge.trainingmanagementplatform.mapper.CompanyMapper;
import com.mycharge.trainingmanagementplatform.model.MyObject;
import com.mycharge.trainingmanagementplatform.model.Result;
import com.mycharge.trainingmanagementplatform.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServicelmpl implements CompanyService  {

    @Autowired
    CompanyMapper mapper;

    @Override
    public Result insert(MyObject myObject) {
        try{
            Result res = Result.getResult(1);
            res.put("data",mapper.insert(myObject));
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return Result.getResult(0);
        }
    }

    @Override
    public Result find(MyObject myObject) {
        try{
            Result res = Result.getResult(1);
            res.put("data",mapper.find(myObject));
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return Result.getResult(0);
        }
    }

    @Override
    public Result update(MyObject myObject) {
        try{
            Result res = Result.getResult(1);
            res.put("data",mapper.update(myObject));
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return Result.getResult(0);
        }
    }

    @Override
    public Result delete(MyObject myObject) {
        try{
            Result res = Result.getResult(1);
            res.put("data",mapper.delete(myObject));
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return Result.getResult(0);
        }
    }

}
