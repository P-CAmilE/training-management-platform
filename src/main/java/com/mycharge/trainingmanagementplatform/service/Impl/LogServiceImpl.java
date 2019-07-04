package com.mycharge.trainingmanagementplatform.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.mycharge.trainingmanagementplatform.mapper.LogMapper;
import com.mycharge.trainingmanagementplatform.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public int login(JSONObject object) {
        int usertype=object.getInteger("usertype");

        switch (usertype){
            case 0: logMapper.studentLogIn(object);break;
            case 1: logMapper.companyLogIn(object);break;
            case 2: logMapper.teacherLogIn(object);break;
        }


        return 0;
    }

    @Override
    public int register(JSONObject object) {
        int usertype=object.getInteger("usertype");
        if(usertype == 0)
            logMapper.studentRegister(object);
        else if(usertype == 1)
            logMapper.companyRegister(object);

        return 0;
    }
}
