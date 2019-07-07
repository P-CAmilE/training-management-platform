package com.mycharge.trainingmanagementplatform.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.mapper.ProjectMapper;
import com.mycharge.trainingmanagementplatform.mapper.StudentMapper;
import com.mycharge.trainingmanagementplatform.mapper.TeamMapper;
import com.mycharge.trainingmanagementplatform.model.Result;
import com.mycharge.trainingmanagementplatform.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServicelmpl implements TeamService {

    @Autowired
    private TeamMapper mapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public Result insert(JSONObject object) {
        try{
            Result res = Result.getResult(1);
            mapper.insert(object);
            List<JSONObject> jsonObjectList = mapper.find(object);
            int teamID = jsonObjectList.get(0).getInteger("team_id");
            JSONArray jsonArray = object.getJSONArray("stu_id");
            if(jsonArray != null){
//              mapper.deletePlanTeacher(object);
                for(int i = 0;i < jsonArray.size(); i ++){
                    JSONObject jsonObject  = new JSONObject();
                    jsonObject.put("stu_id",jsonArray.get(i));
                    jsonObject.put("team_id",teamID);
                    mapper.insertTeamStudent(jsonObject);
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
            Result res = Result.getResult(1);
            JSONArray jsonArray = object.getJSONArray("stu_id");
            int teamID = object.getInteger("team_id");
            if(jsonArray != null){
                mapper.deleteTeamStudent(object);
                for(int i = 0;i < jsonArray.size(); i ++){
                    JSONObject jsonObject  = new JSONObject();
                    jsonObject.put("stu_id",jsonArray.get(i));
                    jsonObject.put("team_id",teamID);
                    mapper.insertTeamStudent(jsonObject);
                }
            }
            res.put("data",mapper.update(object));

            return res;
        }catch (Exception e){
            e.printStackTrace();
            return Result.getResult(0);
        }
    }

    @Override
    public Result delete(JSONObject object) {
        try{
            if(object.getInteger("team_id")!= null) {
                Result res = Result.getResult(1);
                res.put("data", mapper.delete(object));
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

    @Override
    public Result findPlanProject() {
        try{
            Result res = Result.getResult(1);
            res.put("data",projectMapper.findName());
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return Result.getResult(0);
        }
    }

    @Override
    public Result findStudent(JSONObject jsonObject) {
        try{
            Result res = Result.getResult(1);
            res.put("data",studentMapper.findBySchool(jsonObject));
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return Result.getResult(0);
        }
    }
}

