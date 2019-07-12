package com.mycharge.trainingmanagementplatform.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.mapper.AccountMapper;
import com.mycharge.trainingmanagementplatform.mapper.AdministratorMapper;
import com.mycharge.trainingmanagementplatform.model.Result;
import com.mycharge.trainingmanagementplatform.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminstratorServiceImpl implements AdministratorService {

    @Autowired
    AdministratorMapper mapper;
    @Autowired
    AccountMapper accountMapper;

    @Override
    public Result find(JSONObject jsonObject) {
        try{
//            if(jsonObject.getInteger("admin_id") != null) {
                Result res = Result.getResult(1);
                res.put("data", mapper.find(jsonObject));
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
            if(jsonObject.getInteger("admin_id") != null) {
                if(!mapper.find(jsonObject).isEmpty()) {
                    Result res = Result.getResult(1);
                    res.put("data", mapper.update(jsonObject));
                    if(jsonObject.getString("new_password") != null && jsonObject.getString("new_password") != "") {
                        jsonObject.put("acc_id", jsonObject.getInteger("admin_id"));
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

    //管理在注册服务中调用插入
    @Override
    public Result insert(JSONObject jsonObject) {
        return null;
    }

    @Override
    public Result delete(JSONObject jsonObject) {
        try{
            if(jsonObject.getInteger("admin_id") != null ){
                if(!mapper.find(jsonObject).isEmpty()) {
                    Result res = Result.getResult(1);
                    res.put("data", mapper.delete(jsonObject));
                    jsonObject.put("acc_id", jsonObject.getInteger("admin_id"));
                    accountMapper.delete(jsonObject);
                    return res;
                }else{
                    return Result.getResult(0);
                }
            }else {
                return Result.getResult(0);
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.getResult(0);
        }
    }
}
