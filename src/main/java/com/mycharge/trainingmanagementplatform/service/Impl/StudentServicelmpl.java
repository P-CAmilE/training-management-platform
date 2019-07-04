//package com.mycharge.trainingmanagementplatform.service.Impl;
//
//
//import com.alibaba.fastjson.JSONObject;
//import com.mycharge.trainingmanagementplatform.mapper.StudentMapper;
//import com.mycharge.trainingmanagementplatform.model.MyObject;
//import com.mycharge.trainingmanagementplatform.model.Result;
//import com.mycharge.trainingmanagementplatform.service.StudentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class StudentServicelmpl implements StudentService {
//
//    @Autowired
//    private StudentMapper mapper;
//
//    @Override
//    public Result insert(JSONObject jsonObject) {
//        try{
//            Result res = Result.getResult(1);
//            res.put("data",mapper.insert(jsonObject));
//            return res;
//        }catch (Exception e){
//            e.printStackTrace();
//            return Result.getResult(0);
//        }
//    }
//
//    @Override
//    public Result find(JSONObject jsonObject) {
//        try{
//            Result res = Result.getResult(1);
//            res.put("data",mapper.find(jsonObject));
//            return res;
//        }catch (Exception e){
//            e.printStackTrace();
//            return Result.getResult(0);
//        }
//    }
//
//    @Override
//    public Result update(JSONObject jsonObject) {
//        try{
//            Result res = Result.getResult(1);
//            res.put("data",mapper.update(jsonObject));
//            return res;
//        }catch (Exception e){
//            e.printStackTrace();
//            return Result.getResult(0);
//        }
//    }
//
//    @Override
//    public Result delete(JSONObject jsonObject) {
//        try{
//            Result res = Result.getResult(1);
//            res.put("data",mapper.delete(jsonObject));
//            return res;
//        }catch (Exception e){
//            e.printStackTrace();
//            return Result.getResult(0);
//        }
//    }
//}
