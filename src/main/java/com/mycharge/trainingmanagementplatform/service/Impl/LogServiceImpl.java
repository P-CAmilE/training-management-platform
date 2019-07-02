package com.mycharge.trainingmanagementplatform.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.mapper.*;
import com.mycharge.trainingmanagementplatform.mapper.StudentMapper;
import com.mycharge.trainingmanagementplatform.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mycharge.trainingmanagementplatform.model.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private CompanyMapper companyMapper;

    //登陆
    @Override
    public Result login(JSONObject jsonObject, HttpServletResponse response) {
        try {
            Result res;
            List<JSONObject> userList = findUser(jsonObject);
            if(!userList.isEmpty()){
                res = Result.getResult(1);
                Cookie accountCookie = new Cookie("useraccount",String.valueOf(jsonObject.get("useraccount")));
                accountCookie.setPath("/usercookie");
                response.addCookie(accountCookie);
                res.put("msg","登陆成功");
                res.put("state","1");
            }
            else {
                res = Result.getResult(0);
                res.put("msg","账号或者密码错误");
                res.put("state","0");
            }
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.getResult(0);
        }
    }

    //注册
    @Override
    public Result register(JSONObject jsonObject, HttpServletResponse response) {
        try {
            Result res;
            List<JSONObject> userList = findUser(jsonObject);
            if(userList.isEmpty()){
                res = Result.getResult(1);
                int i = insertUser(jsonObject);
                res.put("msg","注册成功");
                res.put("state","1");
            } else {
                res = Result.getResult(0);
                res.put("msg","账号已存在，请重新输入");
                res.put("state","0");
            }
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.getResult(0);
        }
    }

    List<JSONObject> findUser(JSONObject jsonObject){
        List<JSONObject> userList;
        if(jsonObject.getInteger("user_type") == 0)
            userList = studentMapper.find(jsonObject);
        else if(jsonObject.getInteger("user_type") == 1)
            userList = companyMapper.find(jsonObject);
        else if(jsonObject.getInteger("user_type") == 2)
            userList = teacherMapper.find(jsonObject);
        else
            userList = teacherMapper.find(jsonObject);
        return userList;

    }

    int insertUser(JSONObject jsonObject){
        int i;
        if(jsonObject.getInteger("user_type") == 0)
            i = studentMapper.insert(jsonObject);
        else if(jsonObject.getInteger("user_type") == 1)
            i = companyMapper.insert(jsonObject);
        else if(jsonObject.getInteger("user_type") == 2)
            i = teacherMapper.insert(jsonObject);
        else
            i = teacherMapper.insert(jsonObject);
        return i;
    }

}
