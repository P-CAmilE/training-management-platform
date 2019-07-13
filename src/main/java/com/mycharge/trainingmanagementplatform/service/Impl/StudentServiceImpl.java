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

import java.util.List;


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
//            if(jsonObject.getInteger("stu_id") != null) {
                Result res = Result.getResult(1);
                res.put("data", studentMapper.find(jsonObject));
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
    public Result findByTeacher(JSONObject jsonObject) {
        try{
            if(jsonObject.getInteger("tea_id") != null) {
                Result res = Result.getResult(1);
                res.put("data", studentMapper.findByTeacher(jsonObject));
                return res;
            }
            else if(jsonObject.getInteger("stu_id") != null){
                Result res = Result.getResult(1);
                List<JSONObject> list = studentMapper.findByTeacherDetail(jsonObject);
                List<JSONObject> list2 =studentMapper.findStudentScore(jsonObject);
                if(list2.size() > 0) {
                    float allStudentScore = 0;
                    for (int i = 0; i < list2.size(); i++) {
                        allStudentScore += list2.get(i).getFloat("stu_score");
                    }
                    list.get(0).put("stu_score", allStudentScore / list2.size());
                }else{
                    list.get(0).put("stu_score","目前没有老师打分");
                }
                res.put("data",list);
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
    public Result update(JSONObject jsonObject) {
        try{
            if(jsonObject.getInteger("stu_id") != null) {
                    if (!studentMapper.find(jsonObject).isEmpty()) {
                        Result res = Result.getResult(1);
                        res.put("data", studentMapper.update(jsonObject));
                        if (jsonObject.getString("new_password") != "") {
                            jsonObject.put("acc_id", jsonObject.get("stu_id"));
                            accountMapper.update(jsonObject);
                        }
                        return res;
                    } else {
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
            if(jsonObject.getInteger("stu_id") != null){
                if(!studentMapper.find(jsonObject).isEmpty()) {
                    Result res = Result.getResult(1);
                    res.put("data", studentMapper.delete(jsonObject));
                    jsonObject.put("acc_id", jsonObject.getInteger("stu_id"));
                    accountMapper.delete(jsonObject);
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

    @Override
    public Result scoreForStudent(JSONObject jsonObject) {
        try{
            if(jsonObject.getInteger("tea_id") != null && jsonObject.getInteger("stu_id") != null
                    && jsonObject.getFloat("stu_score") != null) {
                Result res = Result.getResult(1);
                if(studentMapper.findStudentScore(jsonObject).isEmpty()) {
                    res.put("data", studentMapper.scoreForStudent(jsonObject));
                }else{
                    studentMapper.updateScore(jsonObject);
                }
                return res;
            }else{
                return Result.getResult(0);
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.getResult(0);
        }
    }
}
