package com.mycharge.trainingmanagementplatform.service.Impl;


import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.mapper.*;
import com.mycharge.trainingmanagementplatform.mapper.StudentMapper;
import com.mycharge.trainingmanagementplatform.service.LogRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mycharge.trainingmanagementplatform.model.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

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
    public Result login(JSONObject jsonObject, HttpServletResponse response) {
        try {
            Result res;
            List<JSONObject> userList = accountMapper.find(jsonObject);
            if(!userList.isEmpty()){
                res = Result.getResult(1);
                Cookie idCookie = new Cookie("acc_id",userList.get(0).getInteger("acc_id")+"");
                Cookie accountCookie = null;
                if(userList.get(0).getString("user_type").equals("student")){
                   accountCookie = new Cookie("account",userList.get(0).getString("account"));
                   idCookie.setPath("/");
                    accountCookie.setPath("/");
                }
                else if (userList.get(0).getString("user_type").equals("admin")){
                    accountCookie = new Cookie("account",userList.get(0).getString("account"));
                    idCookie.setPath("/");
                    accountCookie.setPath("/");
                }
                else if(userList.get(0).getString("user_type").equals("teacher")){
                    accountCookie = new Cookie("account",userList.get(0).getString("account"));
                    accountCookie.setPath("/");
                    idCookie.setPath("/");
                }
                else if(userList.get(0).getString("user_type").equals("company")){
                    accountCookie = new Cookie("account",userList.get(0).getString("account"));
                    accountCookie.setPath("/");
                    idCookie.setPath("/");
                }
                response.addCookie(accountCookie);
                response.addCookie(idCookie);
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
        Result res;
        try {
            int i = insertUser(jsonObject);
            Cookie idCookie = new Cookie("acc_id",i + "");
            Cookie accountCookie = null;
            if(jsonObject.getString("user_type").equals("student")){
                accountCookie = new Cookie("account",jsonObject.getString("account"));
                idCookie.setPath("/");
                accountCookie.setPath("/");
            }
            else if (jsonObject.getString("user_type").equals("admin")){
                accountCookie = new Cookie("account",jsonObject.getString("account"));
                idCookie.setPath("/");
                accountCookie.setPath("/js/");
            }
            else if(jsonObject.getString("user_type").equals("teacher")){
                accountCookie = new Cookie("account",jsonObject.getString("account"));
                accountCookie.setPath("/js/");
                idCookie.setPath("/");
            }
            response.addCookie(accountCookie);
            response.addCookie(idCookie);
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
        JSONObject user = new JSONObject();
        if(jsonObject.getString("user_type").equals("student")){
            user.put("stu_id",i);
            studentMapper.insert(user);
        }

        else if(jsonObject.getString("user_type").equals("admin")){
            user.put("admin_id",i);
            administratorMapper.insert(user);
        }

        else if(jsonObject.getString("user_type").equals("teacher")){
            user.put("tea_id",i);
            teacherMapper.insert(user);
        }
        return i;
    }

}
