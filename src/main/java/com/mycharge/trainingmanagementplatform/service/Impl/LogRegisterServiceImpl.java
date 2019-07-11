package com.mycharge.trainingmanagementplatform.service.Impl;


import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.global.Role;
import com.mycharge.trainingmanagementplatform.mapper.*;
import com.mycharge.trainingmanagementplatform.mapper.StudentMapper;
import com.mycharge.trainingmanagementplatform.service.LogRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mycharge.trainingmanagementplatform.model.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.UUID;

@Service
public class LogRegisterServiceImpl implements LogRegisterService {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private AdministratorMapper administratorMapper;
    @Autowired
    private AccountMapper accountMapper;

    //登陆
    @Override
    public Result login(JSONObject jsonObject, HttpServletResponse response,HttpServletRequest request) {
        try {
            Result res;
            List<JSONObject> userList = accountMapper.find(jsonObject);
            if(!userList.isEmpty()){
                res = Result.getResult(1);
                Cookie idCookie = new Cookie("acc_id",userList.get(0).getInteger("acc_id")+"");
                Cookie accountCookie = null;
//                if(userList.get(0).getString("user_type").equals("student")){}
//                else if (userList.get(0).getString("user_type").equals("admin")){}
//                else if(userList.get(0).getString("user_type").equals("teacher")){}
//                request.getSession().setAttribute("role",userList.get(0).getString("user_type"));
                request.getSession().setAttribute("role", Role.getRole(userList.get(0).getString("user_type")));
                accountCookie = new Cookie("account",userList.get(0).getString("account"));
                accountCookie.setPath("/");
                idCookie.setPath("/");
                response.addCookie(accountCookie);
                response.addCookie(idCookie);
                //生成token 客户端用cookie储存
                UUID token=UUID.randomUUID();
                Cookie token_cookie= new Cookie("token",token.toString());
                token_cookie.setPath("/");
                response.addCookie(token_cookie);
                request.getSession().setAttribute("token",token.toString());
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
    public Result register(JSONObject jsonObject) {
        Result res;
        try {
            int i = insertUser(jsonObject);
            res = Result.getResult(1);
            res.put("msg","注册成功");
            return res;
        } catch (SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
            res = Result.getResult(0);
            res.put("msg","账号已存在，请重新输入");
            return res;
        }catch (Exception e) {
            e.printStackTrace();
            return Result.getResult(0);
        }
    }

    ///插入账号，返回账号id
    int insertUser(JSONObject jsonObject) throws SQLIntegrityConstraintViolationException{
        accountMapper.insert(jsonObject);
        int i = accountMapper.find(jsonObject).get(0).getInteger("acc_id");
        if(jsonObject.getString("user_type").equals("student")){
            jsonObject.put("stu_id",i);
            studentMapper.insert(jsonObject);
        }
        else if(jsonObject.getString("user_type").equals("admin")){
            jsonObject.put("admin_id",i);
            administratorMapper.insert(jsonObject);
        }
        else if(jsonObject.getString("user_type").equals("teacher")){
            jsonObject.put("tea_id",i);
            teacherMapper.insert(jsonObject);
        }
        return i;
    }

}
