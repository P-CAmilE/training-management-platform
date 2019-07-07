package com.mycharge.trainingmanagementplatform.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.mapper.AccountMapper;
import com.mycharge.trainingmanagementplatform.mapper.CompanyMapper;
import com.mycharge.trainingmanagementplatform.mapper.SchoolMapper;
import com.mycharge.trainingmanagementplatform.mapper.StudentMapper;
import com.mycharge.trainingmanagementplatform.model.Result;
import com.mycharge.trainingmanagementplatform.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private SchoolMapper schoolMapper;

    @Override
    public Result find(JSONObject jsonObject) {
        try{
            Result res = Result.getResult(1);
            res.put("data",studentMapper.find(jsonObject));
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return Result.getResult(0);
        }
    }

    @Override
    public Result update(JSONObject jsonObject) {
        try{
            if(jsonObject.getInteger("stu_id") != null) {
                Result res = Result.getResult(1);
                res.put("data", studentMapper.update(jsonObject));
                jsonObject.put("acc_id", jsonObject.get("stu_id"));
                accountMapper.update(jsonObject);
                return res;
            }else{
                return Result.getResult(0);
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.getResult(0);
        }
    }

    //管理在注册服务中调用插入
    @Override
    public Result insert(JSONObject jsonObject) {
        return null;
    }

    @Override
    public Result delete(JSONObject jsonObject) {
        try{
            if(jsonObject.getInteger("stu_id") != null){
                Result res = Result.getResult(1);
                res.put("data",studentMapper.delete(jsonObject));
                jsonObject.put("acc_id",jsonObject.getInteger("stu_id"));
                accountMapper.delete(jsonObject);
                return res;
            }else{
                return Result.getResult(0);
            }

        }catch (Exception e){
            e.printStackTrace();
            return Result.getResult(0);
        }
    }

    @Override
    public Result findSchool() {
        try{
            Result res = Result.getResult(1);
            res.put("data",schoolMapper.findAll());
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return Result.getResult(0);
        }
    }
}
