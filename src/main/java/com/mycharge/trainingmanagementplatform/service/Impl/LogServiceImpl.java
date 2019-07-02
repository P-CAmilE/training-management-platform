package com.mycharge.trainingmanagementplatform.service.Impl;

import com.mycharge.trainingmanagementplatform.mapper.LogMapper;
import com.mycharge.trainingmanagementplatform.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mycharge.trainingmanagementplatform.model.*;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public MSG login(String username, String password,int usertype) {

        User user = null;
        MSG msg = MSG.newMessage();

        if(usertype == 0)
            user = (Student)logMapper.studentLogIn(username, password);
        else if (usertype == 1)
            user = logMapper.companyLogIn(username,password);
        else if (usertype == 2)
            user = logMapper.companyLogIn(username, password);
        if(user != null){
            msg.put("msg","登陆成功");
            msg.put("logstate","1");
            return msg;
        } else {
            msg.put("msg","账号或者密码错误");
            msg.put("logstate","0");
            return msg;
        }
    }

    @Override
    public MSG register(String username, String password, int usertype, String email) {

        if(usertype == 0)
            logMapper.studentRegister(username, password, email);
        else if(usertype == 1)
            logMapper.companyRegister(username,password,email);
        MSG msg = MSG.newMessage("msg",(Object)"注册成功");
        return msg;
    }
}
