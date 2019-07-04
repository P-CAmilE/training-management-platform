package com.mycharge.trainingmanagementplatform.service.Impl;


import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.mapper.*;
import com.mycharge.trainingmanagementplatform.mapper.StudentMapper;
import com.mycharge.trainingmanagementplatform.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mycharge.trainingmanagementplatform.model.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private AccountMapper accountMapper;

    //登陆
    @Override
    public Result login(JSONObject jsonObject, HttpServletResponse response) {
        try {
            Result res;
            List<JSONObject> userList = accountMapper.find(jsonObject);
            if(!userList.isEmpty()){
                res = Result.getResult(1);
                Cookie accountCookie = new Cookie("acc_id",userList.get(0).getInteger("acc_id")+"");
//                accountCookie.setPath("/usercookie");
                response.addCookie(accountCookie);
                res.put("msg","登陆成功");
            }
            else {
                res = Result.getResult(0);
                res.put("msg","账号或者密码错误");
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
            JSONObject account = new JSONObject();
            account.put("account",jsonObject.getString("account"));
            List<JSONObject> userList = accountMapper.find(account);
            if(userList.isEmpty()){
                res = Result.getResult(1);
                insertUser(jsonObject);
                res.put("msg","注册成功");
                res.put("usertype",jsonObject.getString("user_type"));
            } else {
                res = Result.getResult(0);
                res.put("msg","账号已存在，请重新输入");
            }
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.getResult(0);
        }
    }


    void insertUser(JSONObject jsonObject) throws SQLException {
        accountMapper.insert(jsonObject);
        JSONObject user = new JSONObject();
        if(jsonObject.getString("user_type").equals("student")){

            user.put("stu_account",jsonObject.getString("account"));
            user.put("stu_email", jsonObject.getString("user_email"));
            studentMapper.insert(user);
        }

        else if(jsonObject.getString("user_type").equals("company")){
            user.put("com_account",jsonObject.getString("account"));
            user.put("com_email", jsonObject.getString("user_email"));
            companyMapper.insert(user);
        }

        else if(jsonObject.getString("user_type").equals("teacher")){
            user.put("tea_account",jsonObject.getString("account"));
            user.put("tea_email", jsonObject.getString("user_email"));
            teacherMapper.insert(user);
        }

    }

}
