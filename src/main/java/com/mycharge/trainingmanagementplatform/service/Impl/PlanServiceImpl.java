package com.mycharge.trainingmanagementplatform.service.Impl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.mapper.CompanyMapper;
import com.mycharge.trainingmanagementplatform.mapper.PlanMapper;
import com.mycharge.trainingmanagementplatform.mapper.SchoolMapper;
import com.mycharge.trainingmanagementplatform.mapper.TeacherMapper;
import com.mycharge.trainingmanagementplatform.model.Result;
import com.mycharge.trainingmanagementplatform.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanMapper mapper;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private SchoolMapper schoolMapper;
    @Autowired
    private CompanyMapper companyMapper;


    @Override
    public Result insert(JSONObject object) {
        try{
            Result res = Result.getResult(1);
            mapper.insert(object);
            List<JSONObject> jsonObjectList = mapper.find(object);
            int planID = jsonObjectList.get(0).getInteger("plan_id");
            JSONArray jsonArray = object.getJSONArray("tea_id");
            if(jsonArray != null){
//                mapper.deletePlanTeacher(object);
                for(int i = 0;i < jsonArray.size(); i ++){
                    JSONObject jsonObject  = new JSONObject();
                    jsonObject.put("tea_id",jsonArray.get(i));
                    jsonObject.put("plan_id",planID);
                    mapper.insertPlanTeacher(jsonObject);
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
            if (object.getInteger("plan_id") != null) {
                Result res = Result.getResult(1);
                JSONArray jsonArray = object.getJSONArray("tea_id");
                int planID = object.getInteger("plan_id");
                if (jsonArray != null && jsonArray.isEmpty()) {
                    mapper.deletePlanTeacher(object);
                    for (int i = 0; i < jsonArray.size(); i++) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("tea_id", jsonArray.get(i));
                        jsonObject.put("plan_id", planID);
                        mapper.insertPlanTeacher(jsonObject);
                    }
                }
                res.put("data", mapper.update(object));
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
    public Result delete(JSONObject object) {
        try {
            if (object.getInteger("plan_id") != null) {
                Result res = Result.getResult(1);
                res.put("data", mapper.delete(object));
                return res;
            }
            return Result.getResult(0);
        } catch (Exception e){
            e.printStackTrace();
            return Result.getResult(0);
        }
    }

    @Override
    public Result findPlanDetail(JSONObject jsonObject) {
        try{
            if(jsonObject.getInteger("plan_id") != null) {
                Result res = Result.getResult(1);
                res.put("data", mapper.find(jsonObject));
                res.put("teacher",teacherMapper.findByPlan(jsonObject));
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

    @Override
    public Result findTeacherByUnit(JSONObject jsonObject) {
        try{

            if(jsonObject.getString("com_name") != null && jsonObject.getString("sch_name") != null)
            {
                Result res = Result.getResult(1);
                List<JSONObject> list1 = teacherMapper.findByCompany(jsonObject);
                List<JSONObject> list2 = teacherMapper.findBySchool(jsonObject);
                if(list1 != null){
                    list1.addAll(list2);
                    res.put("data",list1);
                }
                else if(list2 != null){
                    res.put("data",list2);
                }
                else
                    res = Result.getResult(0);
                return res;
            }
            else{
                return Result.getResult(0);
            }

        }catch (Exception e){
            e.printStackTrace();
            return Result.getResult(0);
        }
    }
}