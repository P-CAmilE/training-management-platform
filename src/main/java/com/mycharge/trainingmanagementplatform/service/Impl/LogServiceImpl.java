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
    public User login(String username, String password,int usertype) {

        User user = null;
        if(usertype == 0)
            user = logMapper.studentLogIn(username, password);
        else if (usertype == 2)
            user = logMapper.teacherLogIn(username,password);
        else if (usertype == 1)
            user = logMapper.companyLogIn(username, password);
        return user;
    }

    @Override
    public int register(String username, String password, int usertype, String email) {
        if(usertype == 0)
            logMapper.studentRegister(username, password, email);
        else if(usertype == 1)
            logMapper.companyRegister(username,password,email);

        return 0;
    }
}
