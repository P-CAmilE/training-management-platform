package com.mycharge.trainingmanagementplatform.service.Impl;

import com.mycharge.trainingmanagementplatform.mapper.LogMapper;
import com.mycharge.trainingmanagementplatform.model.MyObject;
import com.mycharge.trainingmanagementplatform.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public int login(MyObject myObject) {
        int usertype=(int)myObject.get("usertype");

        switch (usertype){
            case 0: logMapper.studentLogIn(myObject);break;
            case 1: logMapper.companyLogIn(myObject);break;
            case 2: logMapper.teacherLogIn(myObject);break;
        }


        return 0;
    }

    @Override
    public int register(MyObject myObject) {
        int usertype=(int)myObject.get("usertype");
        if(usertype == 0)
            logMapper.studentRegister(myObject);
        else if(usertype == 1)
            logMapper.companyRegister(myObject);

        return 0;
    }
}
