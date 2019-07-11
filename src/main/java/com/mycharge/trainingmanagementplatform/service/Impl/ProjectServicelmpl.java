package com.mycharge.trainingmanagementplatform.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.mapper.PlanMapper;
import com.mycharge.trainingmanagementplatform.mapper.ProjectMapper;
import com.mycharge.trainingmanagementplatform.mapper.TeacherMapper;
import com.mycharge.trainingmanagementplatform.model.Result;
import com.mycharge.trainingmanagementplatform.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServicelmpl implements ProjectService {

    @Autowired
    private ProjectMapper mapper;
    @Autowired
    private PlanMapper planMapper;
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public Result insert(JSONObject object) {
        try{
            Result res = Result.getResult(1);
            mapper.insert(object);
            List<JSONObject> jsonObjectList = mapper.find(object);
            int proID = jsonObjectList.get(0).getInteger("pro_id");
            JSONArray jsonArray = object.getJSONArray("tea_id");
            if(jsonArray != null){
//              mapper.deletePlanTeacher(object);
                for(int i = 0;i < jsonArray.size(); i ++){
                    JSONObject jsonObject  = new JSONObject();
                    jsonObject.put("tea_id",jsonArray.get(i));
                    jsonObject.put("pro_id",proID);
                    mapper.insertProjectTeacher(jsonObject);
                }
            }
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
            if(object.getInteger("pro_id") != null) {
                Result res = Result.getResult(1);
                JSONArray jsonArray = object.getJSONArray("tea_id");
                int  proID = object.getInteger("pro_id");
                if (jsonArray != null) {
                    mapper.deleteProjectTeacher(object);
                    for (int i = 0; i < jsonArray.size(); i++) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("tea_id", jsonArray.get(i));
                        jsonObject.put("pro_id", proID);
                        mapper.insertProjectTeacher(jsonObject);
                    }
                }
                res.put("data", mapper.update(object));
                return res;
            }else {
                return Result.getResult(0);
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.getResult(0);
        }
    }

    @Override
    public Result delete(JSONObject object) {
        try{
            //todo:权限控制
            if(object.getInteger("pro_id") != null) {
                Result res = Result.getResult(1);
                res.put("data", mapper.delete(object));
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
    public Result findPlanName() {
        try{
            Result res = Result.getResult(1);
            res.put("data",planMapper.findName());
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return Result.getResult(0);
        }
    }

    @Override
    public Result findByStudent(JSONObject jsonObject) {
        try{
            if(jsonObject.getInteger("stu_id") != null) {
                Result res = Result.getResult(1);
                res.put("data", mapper.findByStudent(jsonObject));
                //res.put("allproject",mapper.find(jsonObject));
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
    public Result findProjectDetail(JSONObject jsonObject) {
        try{
            if(jsonObject.getInteger("pro_id") != null) {
                Result res = Result.getResult(1);
                res.put("data", mapper.find(jsonObject));
                res.put("teacher",teacherMapper.findByProject(jsonObject));
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
    public Result findTeacherByPlan(JSONObject jsonObject) {
        try{
            if(jsonObject.getInteger("plan_id") != null) {
                Result res = Result.getResult(1);
                res.put("data", teacherMapper.findByPlan(jsonObject));
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
