package com.mycharge.trainingmanagementplatform.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.mapper.ProjectMapper;
import com.mycharge.trainingmanagementplatform.mapper.StudentMapper;
import com.mycharge.trainingmanagementplatform.mapper.TeacherMapper;
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
    @Autowired
    private TeacherMapper teacherMapper;

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
    public Result findTeamDetail(JSONObject jsonObject) {
        try{
            if(jsonObject.getInteger("team_id") != null) {
                Result res = Result.getResult(1);
                res.put("data", mapper.find(jsonObject));
                res.put("student",studentMapper.findByTeam(jsonObject));
                res.put("teacher",teacherMapper.findByTeam(jsonObject));
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
    public Result findByTeacher(JSONObject jsonObject) {
        try{
            if(jsonObject.getInteger("tea_id") != null) {
                Result res = Result.getResult(1);
                res.put("data", mapper.findByTeacher(jsonObject));
                return res;
            }
            else if(jsonObject.getInteger("team_id") != null){
                Result res = Result.getResult(1);
                List<JSONObject> list = mapper.find(jsonObject);
                List<JSONObject> list1 = mapper.findTeamScore(jsonObject);
                if(list1.size() > 0) {
                    float allTeamScore = 0;
                    for (int i = 0; i < list1.size(); i++) {
                        allTeamScore += list1.get(i).getFloat("team_score");
                    }
                    list.get(0).put("team_score", allTeamScore / list1.size());
                }else{
                    list.get(0).put("team_score","目前没有老师打分");
                }
                res.put("data",list);
                res.put("student",studentMapper.findByTeam(jsonObject));
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
    public Result findByStudent(JSONObject jsonObject) {
        try{
            if(jsonObject.getInteger("stu_id") != null) {
                Result res = Result.getResult(1);
                res.put("data", mapper.findByStudent(jsonObject));
                return res;
            }
            else if(jsonObject.getInteger("team_id") != null && jsonObject.getInteger("stu_id") != null){
                Result res = Result.getResult(1);
                List<JSONObject> list = mapper.find(jsonObject);
                List<JSONObject> list1 = mapper.findTeamScore(jsonObject);
                if(list1.size() > 0) {
                    float allTeamScore = 0;
                    for (int i = 0; i < list1.size(); i++) {
                        allTeamScore += list1.get(i).getFloat("team_score");
                    }
                    list.get(0).put("team_score", allTeamScore / list1.size());
                }else{
                    list.get(0).put("team_score","目前没有老师打分");
                }
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
                res.put("student",studentMapper.findByTeam(jsonObject));
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
    public Result scoreForTeam(JSONObject jsonObject) {
        try{
            if(jsonObject.getInteger("tea_id") != null && jsonObject.getInteger("team_id") != null
                    && jsonObject.getFloat("team_score") != null) {
                Result res = Result.getResult(1);
                if(mapper.findTeamScore(jsonObject).isEmpty()) {
                    res.put("data", mapper.scoreForTeam(jsonObject));
                }else{
                    mapper.updateScore(jsonObject);
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


    @Override
    public Result update(JSONObject object) {
        try{
            if(object.getInteger("team_id") != null) {
                Result res = Result.getResult(1);
                JSONArray jsonArray = object.getJSONArray("stu_id");
                int teamID = object.getInteger("team_id");
                if (jsonArray != null) {
                    mapper.deleteTeamStudent(object);
                    for (int i = 0; i < jsonArray.size(); i++) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("stu_id", jsonArray.get(i));
                        jsonObject.put("team_id", teamID);
                        mapper.insertTeamStudent(jsonObject);
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
            if(jsonObject.getInteger("pro_id") != null || jsonObject.getInteger("plan_id") != null) {
                Result res = Result.getResult(1);
                res.put("data", studentMapper.findBySchool(jsonObject));
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

