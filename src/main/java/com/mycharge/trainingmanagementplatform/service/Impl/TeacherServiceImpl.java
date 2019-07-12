package com.mycharge.trainingmanagementplatform.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.mapper.AccountMapper;
import com.mycharge.trainingmanagementplatform.mapper.CompanyMapper;
import com.mycharge.trainingmanagementplatform.mapper.SchoolMapper;
import com.mycharge.trainingmanagementplatform.mapper.TeacherMapper;
import com.mycharge.trainingmanagementplatform.model.Result;
import com.mycharge.trainingmanagementplatform.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private SchoolMapper schoolMapper;
    @Autowired
    private CompanyMapper companyMapper;

    //在注册服务中实现
    @Override
    public Result insert(JSONObject jsonObject) {
        return null;
    }

    @Override
    public Result find(JSONObject jsonObject) {
        try{
//            if(jsonObject.getInteger("tea_id") != null) {
                Result res = Result.getResult(1);
                res.put("data", teacherMapper.find(jsonObject));
                return res;
//            }else{
//                return Result.getResult(0);
//            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.getResult(0);
        }
    }

    @Override
    public Result update(JSONObject jsonObject) {
        try{
            if(jsonObject.getInteger("tea_id") != null) {
                if(!teacherMapper.find(jsonObject).isEmpty()) {
                    Result res = Result.getResult(1);
                    res.put("data", teacherMapper.update(jsonObject));
                    if(jsonObject.getString("new_password") != null && jsonObject.getString("new_password") != "") {
                        jsonObject.put("acc_id", jsonObject.get("tea_id"));
                        accountMapper.update(jsonObject);
                    }
                    return res;
                }else{
                    return Result.getResult(0);
                }
            }else{
                return Result.getResult(0);
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.getResult(0);
        }
    }

    @Override
    public Result delete(JSONObject jsonObject) {
        try{
            if(jsonObject.getInteger("tea_id") != null){
                if(!teacherMapper.find(jsonObject).isEmpty()) {
                    Result res = Result.getResult(1);
                    res.put("data", teacherMapper.delete(jsonObject));
                    jsonObject.put("acc_id", jsonObject.getInteger("tea_id"));
                    accountMapper.delete(jsonObject);
                    return res;
                }else{
                    return Result.getResult(0);
                }
            }
            else{
                return Result.getResult(0);
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.getResult(0);
        }
    }

    @Override
    public Result findUnit() {
        try{
            Result res = Result.getResult(1);
            res.put("school",schoolMapper.findAll());
            res.put("company",companyMapper.findAll());
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return Result.getResult(0);
        }
    }

}
